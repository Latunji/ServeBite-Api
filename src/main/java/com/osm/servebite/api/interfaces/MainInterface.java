package com.osm.servebite.api.interfaces;

import com.osm.servebite.api.domain.City;
import com.osm.servebite.api.domain.Meal;
import com.osm.servebite.api.domain.RestaurantMeal;
import com.osm.servebite.api.domain.ServiceProvider;

import java.util.List;

public interface MainInterface {

    public List<ServiceProvider> getAllServiceProviders();

    public List<Meal> getAllMeals() throws InstantiationException, IllegalAccessException;

    public List<City> getAllCities();

    public List<Meal> getMealItemsByServiceProvider(Long ServiceProviderId);

    public List<Meal> getMealItemsByLocation(String Location) throws InstantiationException, IllegalAccessException;

    public List<ServiceProvider> getServiceProvidersByCity(String city) throws IllegalAccessException, InstantiationException;
}
