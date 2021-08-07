package com.osm.servebite.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_city")
@SequenceGenerator(name = "citySeq", sequenceName = "tbl_city_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class City {

    private static final long serialVersionUID = 1840603817779318751L;

    @Id
    @GeneratedValue(generator = "citySeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "city_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false, length = 30 )
    private String name;
}
