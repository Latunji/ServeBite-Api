package com.osm.servebite.api.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "tbl_service_provider")
@SequenceGenerator(name = "serviceProviderSeq", sequenceName = "tbl_service_provider_seq" , allocationSize = 1)
@NoArgsConstructor
@Getter
@Setter
public class ServiceProvider {

    private static final long serialVersionUID = 1840603817779318751L;

    @Id
    @GeneratedValue(generator = "serviceProviderSeq", strategy = GenerationType.SEQUENCE)
    @Column(name = "service_prov_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "restaurant_name", nullable = false, length = 30 )
    private String restaurantName;

    @Column(name = "phone_number", nullable = false, length = 18 )
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 35 )
    private String email;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="city", nullable = false)
    private City city;

}
