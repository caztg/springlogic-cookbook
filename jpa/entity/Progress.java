package cn.springlogic.cookbook.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by kinginblue on 2017/4/20.
 */
@Entity
@Data
public class Progress {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String temperature;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Integer rank;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    @UpdateTimestamp
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "dishes_id", nullable = false)
    private Dishes dishes;

    @ManyToOne
    @JoinColumn(name = "progress_config_id", nullable = false)
    private ProgressConfig progressConfig;

    @ManyToOne
    @JoinColumn(name = "food_config_id", nullable = false)
    private FoodConfig foodConfig;

}
