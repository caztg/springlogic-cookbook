package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.Dishes;
import cn.springlogic.cookbook.jpa.entity.DishesFavor;
import cn.springlogic.cookbook.jpa.entity.projection.DishesFavorFullProjection;
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
@RepositoryRestResource(path = "dishes:dishes-favor", excerptProjection = DishesFavorFullProjection.class)
public interface DishesFavorRepository extends JpaRepository<DishesFavor, Integer> {

    @RestResource(path = "user")
    Page<DishesFavor> findByUser(@Param("user") User user, Pageable pageable);

    @RestResource(path = "dishes")
    Page<DishesFavor> findByDishes(@Param("dishes") Dishes dishes, Pageable pageable);

    @Transactional
    @RestResource(path = "delete")
    void deleteByUserAndDishes(@Param("user") User user, @Param("dishes") Dishes dishes);

    /**
     * 查询用户对菜品的点赞状态
     * @param userId 用户ID
     * @param dishesId 菜品ID
     * @return
     */
    @Query("select f from DishesFavor f inner join f.user u inner join f.dishes d where u.id = :userId and d.id = :dishesId")
    @RestResource(exported = false)
    DishesFavor findFirst1UserFavorTheDishes(@Param("userId") Integer userId, @Param("dishesId") Integer dishesId);

}
