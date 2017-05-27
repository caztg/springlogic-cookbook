package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.Measurement;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by admin on 2017/5/15.
 */
@Projection(name = "info", types = {Measurement.class})
public interface MeasurementProjection {
    Integer getId();

    String getName();

    Date getCreateTime();
}
