package com.osm.servebite.api.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class RestaurantMeal {

    private Long id;
    private String description;
    private Double price;
    private Long preparationTime;
    private String serviceProvider;
    private String location;
    private byte[] picture;


}
