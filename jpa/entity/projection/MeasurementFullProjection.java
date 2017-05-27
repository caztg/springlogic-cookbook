package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.Measurement;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

/**
 * Created by kinginblue on 2017/4/27.
 */
@Projection(name = "full", types = {Measurement.class})
public interface MeasurementFullProjection {

    Integer getId();

    String getName();

    String getLanguage();

    Date getCreateTime();

    Date getUpdateTime();

    Measurement getLocal();

    List<Measurement> getI18ns();

}
