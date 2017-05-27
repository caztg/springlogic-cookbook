package cn.springlogic.cookbook.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by kinginblue on 2017/5/11.
 */
@Entity
@Data
public class ProgressConfig {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String temperature;

    @Column(nullable = false)
    private Integer duration;

    @Column(name = "create_time", nullable = false)
    @CreationTimestamp
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    @UpdateTimestamp
    private Date updateTime;

}
