package cn.springlogic.cookbook.jpa.entity.rest;

import cn.springlogic.communicate.util.CommUtil;
import cn.springlogic.cookbook.jpa.entity.DishesFavor;
import cn.springlogic.cookbook.jpa.repository.DishesFavorRepository;
import cn.springlogic.user.jpa.entity.User;
import com.fitcooker.app.BussinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/5/24.
 */
@Component
@RepositoryEventHandler(DishesFavor.class)
public class DishesFavorEventHandler {

    @Autowired
    private DishesFavorRepository dishesFavorRepository;

    @HandleBeforeCreate
    public void beforeCreate(DishesFavor dishesFavor) throws BussinessException {


        //验证点赞是否已经存在
        DishesFavor userFavorTheDishes = dishesFavorRepository.findFirst1UserFavorTheDishes(dishesFavor.getUser().getId(), dishesFavor.getDishes().getId());
        if(userFavorTheDishes!=null){
            throw new BussinessException("你已经对该菜品进行过点赞操作");
        }


    }


}
