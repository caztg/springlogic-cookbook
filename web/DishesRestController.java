package cn.springlogic.cookbook.web;

import cn.springlogic.cookbook.jpa.entity.Dishes;
import cn.springlogic.cookbook.jpa.entity.DishesCollection;
import cn.springlogic.cookbook.jpa.entity.DishesFavor;
import cn.springlogic.cookbook.jpa.entity.Progress;
import cn.springlogic.cookbook.jpa.repository.DishesCollectionRepository;
import cn.springlogic.cookbook.jpa.repository.DishesFavorRepository;
import cn.springlogic.cookbook.jpa.repository.DishesRepository;
import cn.springlogic.social.util.SortListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kinginblue on 2017/4/30.
 */
@RepositoryRestController
@RequestMapping(value = "dishes:dishes")
public class DishesRestController {

    @Autowired
    private DishesRepository dishesRepository;

    @Autowired
    private DishesFavorRepository dishesFavorRepository;

    @Autowired
    private DishesCollectionRepository dishesCollectionRepository;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    /**
     * 自定义实现的按菜品主题ID查找菜品分页
     *
     * @param userId            查询者用户ID，可空
     * @param subjectId         菜谱主题ID
     * @param pageable
     * @param resourceAssembler
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/search/custom-subject")
    public ResponseEntity<PagedResources<PersistentEntityResource>> customSearchBySubject(@RequestParam(value = "userId", required = false) Integer userId,
                                                                                          @RequestParam(value = "subjectId") Integer subjectId,
                                                                                          Pageable pageable,
                                                                                          PersistentEntityResourceAssembler resourceAssembler) {

        Page<Dishes> page = dishesRepository.findBySubject(subjectId, pageable);

        Page<Dishes> customDishesPage = page.map(new CustomDishesConverter(userId, dishesFavorRepository, dishesCollectionRepository));

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(customDishesPage, resourceAssembler));
    }

    /**
     * 自定义实现的按菜品名称查找菜品分页
     *
     * @param userId            查询者用户ID，可空
     * @param name              菜品名称
     * @param pageable
     * @param resourceAssembler
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/search/custom-name")
    public ResponseEntity<PagedResources<PersistentEntityResource>> customSearchByName(@RequestParam(value = "userId", required = false) Integer userId,
                                                                                       @RequestParam(value = "name", required = false) String name,
                                                                                       Pageable pageable,
                                                                                       PersistentEntityResourceAssembler resourceAssembler) {

        Page<Dishes> page;
        // 菜品名称查找值没有的情况下，默认查所有所有菜品
        if (null != name && !"".equals(name) && !"".equals(name.trim())) {
            page = dishesRepository.findByNameContaining(name, pageable);
        } else {
            page = dishesRepository.findAllLocal(pageable);
        }

        Page<Dishes> customDishesPage = page.map(new CustomDishesConverter(userId, dishesFavorRepository, dishesCollectionRepository));

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(customDishesPage, resourceAssembler));
    }

    /**
     * 自定义实现的所有菜品分页
     *
     * @param userId            查询者用户ID，可空
     * @param pageable
     * @param resourceAssembler
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/search/custom-all")
    public ResponseEntity<PagedResources<PersistentEntityResource>> customSearchAll(@RequestParam(value = "userId", required = false) Integer userId,
                                                                                    Pageable pageable,
                                                                                    PersistentEntityResourceAssembler resourceAssembler) {

        Page<Dishes> page = dishesRepository.findAllLocal(pageable);

        Page<Dishes> customDishesPage = page.map(new CustomDishesConverter(userId, dishesFavorRepository, dishesCollectionRepository));

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(customDishesPage, resourceAssembler));
    }

    /**
     * 自定义实现的单个菜品
     *
     * @param id                菜品ID
     * @param userId            查询者用户ID，可空
     * @param resourceAssembler
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersistentEntityResource> customFindOne(@PathVariable("id") Integer id,
                                                                  @RequestParam(value = "userId", required = false) Integer userId,
                                                                  PersistentEntityResourceAssembler resourceAssembler) {
        Dishes dishes = dishesRepository.findOne(id);

        Converter<Dishes, Dishes> converter = new CustomDishesConverter(userId, dishesFavorRepository, dishesCollectionRepository);

        dishes = converter.convert(dishes);

        return ResponseEntity.ok(resourceAssembler.toResource(dishes));
    }

    /**
     * 转换器类
     */
    private static final class CustomDishesConverter implements Converter<Dishes, Dishes> {

        private final Integer currentUserId;
        private DishesFavorRepository dishesFavorRepository;
        private DishesCollectionRepository dishesCollectionRepository;

        public CustomDishesConverter(Integer currentUserId, DishesFavorRepository dishesFavorRepository, DishesCollectionRepository dishesCollectionRepository) {
            this.currentUserId = currentUserId;
            this.dishesFavorRepository = dishesFavorRepository;
            this.dishesCollectionRepository = dishesCollectionRepository;
        }

        @Override
        public Dishes convert(Dishes source) {
            source.setFavorCount(source.getDishesFavors().size());
            source.setCollectionCount(source.getDishesCollections().size());
            source.setCookMinutes(source.getProgresses().stream().collect(Collectors.summingInt(Progress::getDuration)));

            // 计算当前用户喜欢&收藏状态
            if (null != currentUserId) {
                loadFavorAndCollection(source);

                // 计算关联的 Local 的当前用户喜欢&收藏状态
                Dishes local = source.getLocal();
                if (null != local) {
                    loadFavorAndCollection(local);
                }
            }

            return source;
        }

        /**
         * 关联当前用户喜欢&收藏状态
         *
         * @param source
         */
        private void loadFavorAndCollection(Dishes source) {
            DishesFavor dishesFavor = dishesFavorRepository.findFirst1UserFavorTheDishes(currentUserId, source.getId());
            source.setFavor(dishesFavor);

            DishesCollection dishesCollection = dishesCollectionRepository.findFirst1UserCollectionTheDishes(currentUserId, source.getId());
            source.setCollection(dishesCollection);
        }
    }

    /**
     * 显示用户收藏的菜品列表
     *
     * @param userId            查询者用户ID，可空
     * @param pageable
     * @param resourceAssembler
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/search/custom-collection")
    public ResponseEntity<PagedResources<PersistentEntityResource>> customSearchCollection(@RequestParam(value = "userId", required = false) Integer userId,
                                                                                           Pageable pageable,
                                                                                           PersistentEntityResourceAssembler resourceAssembler) {

        Page<Dishes> page = dishesRepository.findAllCollection(userId, pageable);

        Page<Dishes> customDishesPage = page.map(new CustomDishesConverter(userId, dishesFavorRepository, dishesCollectionRepository));

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(customDishesPage, resourceAssembler));
    }

    /*
      后台显示 菜品列表
     */
    @ResponseBody
    @GetMapping(value = "/search/admin/custom-dishes")
    public ResponseEntity<PagedResources<PersistentEntityResource>> adminDishes(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                                                                                @RequestParam(value = "creator", required = false) String creatorName,
                                                                                @RequestParam(value = "auditor", required = false) String auditName,
                                                                                @RequestParam(name = "favor_sort", required = false) String favorSort,
                                                                                @RequestParam(name = "collect_sort", required = false) String collectionSort,
                                                                                @RequestParam(name = "status", required = false) Integer status,
                                                                                Pageable pageable,
                                                                                PersistentEntityResourceAssembler resourceAssembler) {
        Page<Dishes> page = null;
        if (auditName == null) {
            //默认是按时间最新排序
            page = dishesRepository.findAllDishes(name, creatorName, status, pageable);
        } else {
            //根据审核者查询,传过来了auditor
            page = dishesRepository.findByAuditor(name, creatorName,auditName, status, pageable);
        }
        Page<Dishes> customDishesPage = page.map(new adminDishesConverter());

          /*根据收藏数或者点赞数排序处理
           不可以直接对 getContent()拿出来的 list集合进行处理
           UnsupportedOperationException. 其实List结构按是否可修改也是可以在分为两个类型的
           这里拿到的是不可修改类型,所以只能clone一个同样的list集合
         */
        List<Dishes> content = customDishesPage.getContent();
        List<Dishes> d = new ArrayList<Dishes>(content);

        //收藏总数排序
        if (collectionSort != null) {
            //发帖总数排序默认DESC
            Collections.sort(d, new SortListUtils<Dishes>("getCollectionCount", SortListUtils.DESC));

            if ("ASC".equalsIgnoreCase(collectionSort)) {
                Collections.sort(d, new SortListUtils<Dishes>("getCollectionCount", SortListUtils.ASC));
            }

        }

        //点赞总数排序
        if (favorSort != null) {
            if ("DESC".equalsIgnoreCase(favorSort)) {
                Collections.sort(d, new SortListUtils<Dishes>("getFavorCount", SortListUtils.DESC));
            } else {
                Collections.sort(d, new SortListUtils<Dishes>("getFavorCount", SortListUtils.ASC));
            }
        }

        Page<Dishes> p = new PageImpl<Dishes>(d, pageable, content.size());

        return ResponseEntity.ok(pagedResourcesAssembler.toResource(p, resourceAssembler));
    }

    /**
     * 转换器类
     */
    private static final class adminDishesConverter implements Converter<Dishes, Dishes> {


        public adminDishesConverter() {

        }

        @Override
        public Dishes convert(Dishes source) {
            source.setFavorCount(source.getDishesFavors().size());
            source.setCollectionCount(source.getDishesCollections().size());
            source.getCreator().setPassword(null);
            return source;
        }


    }
}
