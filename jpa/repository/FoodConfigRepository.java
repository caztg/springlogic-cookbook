package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.FoodConfig;
import cn.springlogic.cookbook.jpa.entity.projection.FoodConfigFullProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kinginblue on 2017/5/11.
 */
@RepositoryRestResource(path = "dishes:food-config", excerptProjection = FoodConfigFullProjection.class)
public interface FoodConfigRepository extends JpaRepository<FoodConfig, Integer> {

}
