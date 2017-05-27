package cn.springlogic.cookbook.jpa.entity;

import cn.springlogic.user.jpa.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kinginblue on 2017/4/20.
 */
@Entity
@Data
public class Dishes {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String language;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    @UpdateTimestamp
    private Date updateTime;

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Dishes local;

    @OneToMany(mappedBy = "local")
    private List<Dishes> i18ns = new ArrayList<>();

    @ManyToMany(mappedBy = "dishes")
    private List<Subject> subjects = new ArrayList<>();

    @OneToMany(mappedBy = "dishes")
    private List<DishesFavor> dishesFavors = new ArrayList<>();

    @OneToMany(mappedBy = "dishes")
    private List<DishesCollection> dishesCollections = new ArrayList<>();

    @OneToMany(mappedBy = "dishes")
    private List<Progress> progresses = new ArrayList<>();

    // 点赞数
    @Transient
    private Integer favorCount;

    // 收藏数
    @Transient
    private Integer collectionCount;

    // 烹饪时间
    @Transient
    private Integer cookMinutes;

    // 点赞状态对象
    @Transient
    private DishesFavor favor;

    // 收藏状态对象
    @Transient
    private DishesCollection collection;

    //创建者
    @ManyToOne(fetch = FetchType.EAGER,  // 指定user属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity = User.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name = "create_user_id", // 外键列的列名
            referencedColumnName = "id") // 指定引用user表的主键列
    private User creator;

    //审核者
    @ManyToOne(fetch = FetchType.EAGER,  // 指定user属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity = User.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name = "audit_user_id", // 外键列的列名
            referencedColumnName = "id") // 指定引用user表的主键列
    private User auditor;

    //状态 1为未通过  2为通过
    private Integer status = 2;
}
