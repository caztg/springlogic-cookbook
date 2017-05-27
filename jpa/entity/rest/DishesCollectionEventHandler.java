package cn.springlogic.cookbook.jpa.entity.rest;

import cn.springlogic.cookbook.jpa.entity.DishesCollection;
import cn.springlogic.cookbook.jpa.entity.DishesFavor;
import cn.springlogic.cookbook.jpa.repository.DishesCollectionRepository;
import com.fitcooker.app.BussinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/5/24.
 */
@Component
@RepositoryEventHandler(DishesCollection.class)
public class DishesCollectionEventHandler {


    @Autowired
    private DishesCollectionRepository dishesCollectionRepository;

    @HandleBeforeCreate
    public void beforeCreate(DishesFavor dishesFavor) throws BussinessException {


        //验证收藏是否已经存在
        DishesCollection first1UserCollectionTheDishes = dishesCollectionRepository.findFirst1UserCollectionTheDishes(dishesFavor.getUser().getId(), dishesFavor.getDishes().getId());
        if(first1UserCollectionTheDishes!=null){
            throw new BussinessException("您已经对该菜品进行过收藏操作了!");
        }

    }

}
