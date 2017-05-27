package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.Dishes;
import cn.springlogic.cookbook.jpa.entity.DishesCollection;
import cn.springlogic.cookbook.jpa.entity.projection.DishesCollectionFullProjection;
import cn.springlogic.user.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kinginblue on 2017/4/26.
 */
@RepositoryRestResource(path = "dishes:dishes-collection", excerptProjection = DishesCollectionFullProjection.class)
public interface DishesCollectionRepository extends JpaRepository<DishesCollection, Integer> {

    @RestResource(path = "user")
    Page<DishesCollection> findByUser(@Param("user") User user, Pageable pageable);

    @RestResource(path = "dishes")
    Page<DishesCollection> findByDishes(@Param("dishes") Dishes dishes, Pageable pageable);

    @Transactional
    @RestResource(path = "delete")
    void deleteByUserAndDishes(@Param("user") User user, @Param("dishes") Dishes dishes);

    /**
     * 查询用户对菜品的收藏状态
     * @param userId 用户ID
     * @param dishesId 菜品ID
     * @return
     */
    @Query("select f from DishesCollection f inner join f.user u inner join f.dishes d where u.id = :userId and d.id = :dishesId")
    @RestResource(exported = false)
    DishesCollection findFirst1UserCollectionTheDishes(@Param("userId") Integer userId, @Param("dishesId") Integer dishesId);

}
