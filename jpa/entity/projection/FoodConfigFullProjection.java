package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.FoodConfig;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/5/11.
 */
@Projection(name = "full", types = {FoodConfig.class})
public interface FoodConfigFullProjection {

    Integer getId();

    Integer getCode();

    String getName();

    Date getCreateTime();

    Date getUpdateTime();

}
