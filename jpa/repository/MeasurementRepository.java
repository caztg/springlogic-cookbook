package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.Measurement;
import cn.springlogic.cookbook.jpa.entity.projection.MeasurementFullProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by kinginblue on 2017/4/21.
 */
@RepositoryRestResource(path = "dishes:measurement", excerptProjection = MeasurementFullProjection.class)
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    @RestResource(path = "all")
    @Query("SELECT m from Measurement m where (:name IS NULL OR m.name LIKE CONCAT('%',:name,'%') )")
    Page<Measurement> findByNameContaining(@Param("name") String name, Pageable pageable);

}
