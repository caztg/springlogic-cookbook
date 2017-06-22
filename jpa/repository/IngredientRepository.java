package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.Dishes;
import cn.springlogic.cookbook.jpa.entity.Ingredient;
import cn.springlogic.cookbook.jpa.entity.projection.IngredientFullProjection;
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
@RepositoryRestResource(path = "dishes:ingredient", excerptProjection = IngredientFullProjection.class)
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    @RestResource(path = "dishes")
    List<Ingredient> findByDishes(@Param("dishes") Dishes dishes);

    @RestResource(path = "dishes2",rel = "dishes2")
    Page<Ingredient> findByDishesId(@Param("dishesId")Integer dishesId, Pageable pageable);

}
