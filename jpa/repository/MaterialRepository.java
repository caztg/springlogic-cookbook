package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.Material;
import cn.springlogic.cookbook.jpa.entity.projection.MaterialFullProjection;
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
@RepositoryRestResource(path = "dishes:material", excerptProjection = MaterialFullProjection.class)
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    @RestResource(path = "all")
    @Query("SELECT m from Material m where (:name IS NULL OR m.name LIKE CONCAT('%',:name,'%') )")
    Page<Material> findByNameContaining(@Param("name") String name, Pageable pageable);

}
