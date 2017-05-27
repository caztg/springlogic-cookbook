package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.Ingredient;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by kinginblue on 2017/4/24.
 */
@Projection(name = "full", types = {Ingredient.class})
public interface IngredientFullProjection {

    Integer getId();

    Integer getTotal();

    Integer getRank();

    MaterialFullProjection getMaterial();

    MeasurementFullProjection getMeasurement();

}
