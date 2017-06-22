package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.Subject;
import cn.springlogic.vip.jpa.entity.rest.UserPrizeLogProjection;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fitcooker.app.serializer.AppDataPreFixSerializer;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by admin on 2017/5/15.
 */
@Projection(name = "AdminSubjects", types = {Subject.class})
public interface SubjectAdminFullProjection {

    Integer getId();

    String getName();
    @JsonSerialize(using = AppDataPreFixSerializer.class)
    String getImage();

    Date getCreateTime();

    UserPrizeLogProjection getCreator();

    String getLanguage();

}
