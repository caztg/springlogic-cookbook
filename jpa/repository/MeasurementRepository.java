package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.Measurement;
import cn.springlogic.cookbook.jpa.entity.projection.MeasurementFullProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kinginblue on 2017/4/21.
 */
@RepositoryRestResource(path = "dishes:measurement", excerptProjection = MeasurementFullProjection.class)
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}
