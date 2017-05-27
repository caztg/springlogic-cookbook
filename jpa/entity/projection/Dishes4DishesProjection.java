package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.Dishes;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fitcooker.app.AppDataPreFixSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by kinginblue on 2017/4/24.
 */
@Projection(name = "1Dishes", types = {Dishes.class})
public interface Dishes4DishesProjection {

    Integer getId();

    String getName();
    @JsonSerialize(using = AppDataPreFixSerializer.class)
    String getImage();

    String getDescription();

    String getLanguage();

    Date getCreateTime();

    Date getUpdateTime();

    // Dishes getLocal();

    // List<Dishes> getI18ns();

    @Value("#{target.dishesFavors.size()}")
    Integer getFavorCount();

    @Value("#{target.dishesCollections.size()}")
    Integer getCollectionCount();

    Integer getCookMinutes();

    DishesFavor4DishesProjection getFavor();

    DishesCollection4DishesProjection getCollection();

}
