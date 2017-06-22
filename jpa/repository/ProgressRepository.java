package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.Dishes;
import cn.springlogic.cookbook.jpa.entity.Progress;
import cn.springlogic.cookbook.jpa.entity.projection.ProgressFullProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by kinginblue on 2017/4/21.
 */
@RepositoryRestResource(path = "dishes:progress", excerptProjection = ProgressFullProjection.class)
public interface ProgressRepository extends JpaRepository<Progress, Integer> {

    @RestResource(path = "dishes")
    List<Progress> findByDishes(@Param("dishes") Dishes dishes);

    @RestResource(path = "dishes2",rel = "dishes2")
    Page<Progress> findByDishesId(@Param("dishesId")Integer dishesId, Pageable pageable);

}
