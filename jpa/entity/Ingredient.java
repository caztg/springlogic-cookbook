package cn.springlogic.cookbook.jpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by kinginblue on 2017/4/20.
 */
@Entity
@Data
public class Ingredient {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private Integer total;

    @Column(nullable = false)
    private Integer rank;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "dishes_id", nullable = false)
    private Dishes dishes;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "measurement_id", nullable = false)
    private Measurement measurement;

}
