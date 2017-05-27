package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.Subject;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fitcooker.app.AppDataPreFixSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

/**
 * Created by kinginblue on 2017/5/9.
 */
@Projection(name = "full", types = {Subject.class})
public interface SubjectFullProjection {

    Integer getId();

    String getName();
    @JsonSerialize(using = AppDataPreFixSerializer.class)
    String getImage();

    String getLanguage();

    Date getCreateTime();

    Date getUpdateTime();

    Subject getLocal();

    List<Subject> getI18ns();

    @Value("#{target.dishes.size()}")
    Integer getDishesCount();

}
