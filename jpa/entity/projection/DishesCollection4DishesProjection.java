package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.DishesCollection;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/4/26.
 */
@Projection(name = "4Dishes", types = {DishesCollection.class})
public interface DishesCollection4DishesProjection {

    Integer getId();

    Date getCreateTime();

}
