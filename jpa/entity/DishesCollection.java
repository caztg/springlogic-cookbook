package cn.springlogic.cookbook.jpa.entity;

import cn.springlogic.collection.jpa.entity.Collection;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by kinginblue on 2017/4/26.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class DishesCollection extends Collection{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dishes_id")
    private Dishes dishes;

}
