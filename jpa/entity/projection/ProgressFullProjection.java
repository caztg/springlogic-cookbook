package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.FoodConfig;
import cn.springlogic.cookbook.jpa.entity.Progress;
import cn.springlogic.cookbook.jpa.entity.ProgressConfig;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/4/24.
 */
@Projection(name = "full", types = {Progress.class})
public interface ProgressFullProjection {

    Integer getId();

    String getTemperature();

    Integer getDuration();

    Integer getRank();

    Date getCreateTime();

    Date getUpdateTime();

    ProgressConfig getProgressConfig();

    FoodConfig getFoodConfig();

}
