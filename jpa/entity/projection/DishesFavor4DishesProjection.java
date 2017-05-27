package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.DishesFavor;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/5/3.
 */
@Projection(name = "4Dishes", types = {DishesFavor.class})
public interface DishesFavor4DishesProjection {

    Integer getId();

    Date getCreateTime();
}
