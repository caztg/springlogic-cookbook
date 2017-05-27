package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.Subject;
import cn.springlogic.cookbook.jpa.entity.projection.SubjectFullProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by kinginblue on 2017/4/19.
 */
@RepositoryRestResource(path = "dishes:subject", excerptProjection = SubjectFullProjection.class)
public interface SubjectRepository extends JpaRepository<Subject, Integer> {


    @RestResource(path = "all")
    @Query("SELECT DISTINCT s from Subject s where (:name IS NULL or s.name Like  CONCAT('%',:name,'%') ) AND (:creator IS NULL OR s.creator.nickName Like  CONCAT('%',:creator,'%') )")
    Page<Subject> findAllBySearch(@Param("name")String name,@Param("creator")String creator, Pageable pageable);

}
