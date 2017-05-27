package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.ProgressConfig;
import cn.springlogic.cookbook.jpa.entity.projection.ProgressConfigFullProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kinginblue on 2017/5/11.
 */
@RepositoryRestResource(path = "dishes:progress-config", excerptProjection = ProgressConfigFullProjection.class)
public interface ProgressConfigRepository extends JpaRepository<ProgressConfig, Integer> {

}
