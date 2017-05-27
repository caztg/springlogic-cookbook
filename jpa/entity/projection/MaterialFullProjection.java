package cn.springlogic.cookbook.jpa.entity.projection;

import cn.springlogic.cookbook.jpa.entity.Material;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

/**
 * Created by kinginblue on 2017/4/27.
 */
@Projection(name = "full", types = {Material.class})
public interface MaterialFullProjection {

    Integer getId();

    String getName();

    String getLanguage();

    Date getCreateTime();

    Date getUpdateTime();

    Material getLocal();

    List<Material> getI18ns();

}
