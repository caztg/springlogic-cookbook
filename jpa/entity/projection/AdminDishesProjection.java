package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.Dishes;
import cn.springlogic.vip.jpa.entity.rest.UserPrizeLogProjection;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by admin on 2017/5/12.
 */
@Projection(name = "AdminDishes", types = {Dishes.class})
public interface AdminDishesProjection {
    Integer getId();

    String getName();

    String getDescription();

    Date getCreateTime();

    Date getUpdateTime();

    int getFavorCount();

    int getCollectionCount();

    UserPrizeLogProjection getCreator();

    UserPrizeLogProjection getAuditor();

    int getStatus();
}
