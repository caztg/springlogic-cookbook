package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.ProgressConfig;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/5/11.
 */
@Projection(name = "full", types = {ProgressConfig.class})
public interface ProgressConfigFullProjection {

    Integer getId();

    Integer getCode();

    String getName();

    String getTemperature();

    Integer getDuration();

    Date getCreateTime();

    Date getUpdateTime();

}
