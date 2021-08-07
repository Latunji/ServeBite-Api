package com.osm.servebite.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_meal")
@SequenceGenerator(name = "mealSeq", sequenceName = "tbl_meal_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class Meal {

    private static final long serialVersionUID = 1840603817779318751L;

    @Id
    @GeneratedValue(generator = "mealSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "meal_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "description", nullable = false, length = 40 )
    private String description;

    @Column(name = "price", columnDefinition = "numeric(15,2) default '0.00'", nullable = false )
    private Double price;

    @Column(name = "preparation_time", nullable = false)
    private Long preparationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="serviceProvider", nullable = false)
    private ServiceProvider serviceProvider;

    @Basic(fetch=FetchType.LAZY)
    @Lob @Column(name = "picture")
    protected byte[] picture;

    @Transient private String serviceProviderName;
    @Transient private String location;
    @Transient String phoneNumber;
    @Transient String email;
    @Transient private  String pictureString;
}
