package cn.springlogic.cookbook.jpa.repository;

import cn.springlogic.cookbook.jpa.entity.Dishes;
import cn.springlogic.cookbook.jpa.entity.Subject;
import cn.springlogic.cookbook.jpa.entity.projection.DishesFullProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by kinginblue on 2017/4/21.
 */
@RepositoryRestResource(path = "dishes:dishes", excerptProjection = DishesFullProjection.class)
public interface DishesRepository extends JpaRepository<Dishes, Integer> {

    @RestResource(path = "name")
    Page<Dishes> findByNameContaining(@Param("name") String name, Pageable pageable);

    @RestResource(path = "subject")
    Page<Dishes> findBySubjects(@Param("subject") Subject subject, Pageable pageable);

    // 等价上面的 findBySubjects 效果
    // @Query(value = "select distinct d from Dishes d, Subject s where d in elements(s.dishes) and s.id = ?1") // 另一种写法
    @Query("select distinct d from Dishes d inner join d.subjects s where s.id = :subjectId")
    @RestResource(exported = false)
    Page<Dishes> findBySubject(@Param("subjectId") Integer subjectId, Pageable pageable);

    @Query("select d from Dishes d where d.local is null")
    @RestResource(exported = false)
    Page<Dishes> findAllLocal(Pageable pageable);

    @Query("select distinct d from Dishes d ,Collection c where c.user.id=:uid and c.dishes.id=d.id and d.local is null")
    @RestResource(exported = false)
    Page<Dishes> findAllCollection(@Param("uid")Integer uid,Pageable pageable);


    @Query("select distinct d from Dishes d where d.name LIKE CONCAT('%',:name,'%') AND (:status IS NULL OR d.status = :status) AND (:creator IS NULL OR d.creator.nickName LIKE CONCAT('%',:creator,'%')) ORDER BY d.createTime DESC ")
    Page<Dishes> findAllDishes(@Param("name")String name,@Param("creator")String creator,@Param("status")Integer status, Pageable pageable);

    @Query("SELECT DISTINCT d from Dishes d where d.name LIKE CONCAT('%',:name,'%') AND (:status IS NULL OR d.status = :status) AND d.auditor.nickName LIKE CONCAT('%',:auditor,'%')  AND (:creator IS NULL OR d.creator.nickName LIKE CONCAT('%',:creator,'%')) ORDER BY d.createTime DESC")
    Page<Dishes> findByAuditor(@Param("name")String name,@Param("creator")String creator,@Param("auditor")String auditor,@Param("status")Integer status, Pageable pageable);

}
