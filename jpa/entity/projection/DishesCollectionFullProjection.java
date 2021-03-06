package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.blog.jpa.entity.rest.UserProjection;
import cn.springlogic.cookbook.jpa.entity.Dishes;
import cn.springlogic.cookbook.jpa.entity.DishesCollection;
import cn.springlogic.user.jpa.entity.User;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/4/26.
 */
@Projection(name = "full", types = {DishesCollection.class})
public interface DishesCollectionFullProjection {

    Integer getId();

    Date getCreateTime();

    UserProjection getUser();

    Dishes getDishes();

}
