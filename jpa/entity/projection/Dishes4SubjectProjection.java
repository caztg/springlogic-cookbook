package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.Dishes;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by admin on 2017/5/15.
 */
@Projection(name = "dishes4subject", types = {Dishes.class})
public interface Dishes4SubjectProjection {

    Integer getId();

    String getName();

    int getStatus();
}
