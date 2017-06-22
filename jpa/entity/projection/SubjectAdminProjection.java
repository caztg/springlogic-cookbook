package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.Subject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fitcooker.app.serializer.AppDataPreFixSerializer;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/5/15.
 */
@Projection(name = "subjectinfo", types = {Subject.class})
public interface SubjectAdminProjection {

    Integer getId();

    String getName();
    @JsonSerialize(using = AppDataPreFixSerializer.class)
    String getImage();

    Date getCreateTime();

    List<Dishes4SubjectProjection>  getDishes();
}
