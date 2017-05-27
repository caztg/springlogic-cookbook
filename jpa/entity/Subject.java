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
 * Created by kinginblue on 2017/4/19.
 */
@Entity
@Data
public class Subject {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String image;

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
    private Subject local;

    @OneToMany(mappedBy = "local")
    private List<Subject> i18ns = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "subject_has_dishes",
            joinColumns = {@JoinColumn(name = "subject_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "dishes_id", referencedColumnName = "id")})
    private List<Dishes> dishes = new ArrayList<>();

    //创建者
    @ManyToOne(fetch = FetchType.EAGER,  // 指定user属性的抓取策略 FetchType.LAZY:延迟加载   FetchType.EAGER:立即加载
            targetEntity = User.class)// 指定关联的持久化类
    /** 生成关联的外键列 */
    @JoinColumn(name = "create_user_id", // 外键列的列名
            referencedColumnName = "id") // 指定引用user表的主键列
    private User creator;

}
