package cn.springlogic.cookbook.jpa.entity;

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
public class Material {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

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
    private Material local;

    @OneToMany(mappedBy = "local")
    private List<Material> i18ns = new ArrayList<>();

}
