package com.osm.servebite.api.services;

import com.osm.servebite.api.abstractentities.predicate.CustomPredicate;
import com.osm.servebite.api.base.services.GenericService;
import com.osm.servebite.api.domain.City;
import com.osm.servebite.api.domain.Meal;
import com.osm.servebite.api.domain.RestaurantMeal;
import com.osm.servebite.api.domain.ServiceProvider;
import com.osm.servebite.api.interfaces.MainInterface;
import com.osm.servebite.api.payroll.utils.IppmsUtils;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MainService implements MainInterface {

    @Autowired
    GenericService genericService;


    @Override
    public List<ServiceProvider> getAllServiceProviders() {
        return this.genericService.loadAllObjectsWithoutRestrictions(ServiceProvider.class, "");
    }

    @Override
    public List<Meal> getAllMeals() throws InstantiationException, IllegalAccessException {
        List<Meal> mealList = this.genericService.loadAllObjectsWithoutRestrictions(Meal.class, "");
        if(IppmsUtils.isNotNullOrEmpty(mealList)){
            for(Meal m : mealList){
                ServiceProvider s = this.genericService.loadObjectWithSingleCondition(ServiceProvider.class,
                        new CustomPredicate("id", m.getServiceProvider().getId()));
                m.setServiceProviderName(s.getRestaurantName());
                m.setEmail(s.getEmail());
                m.setPhoneNumber(s.getPhoneNumber());
                m.setLocation(s.getCity().getName());
                m.setPictureString("data:image/jpg;base64,"+ Base64.encode(m.getPicture()));
            }
        }
        else{
            return new ArrayList<>();
        }
        return mealList;
    }

    @Override
    public List<City> getAllCities() {
        return this.genericService.loadAllObjectsWithoutRestrictions(City.class, "");
    }

    @Override
    public List<Meal> getMealItemsByServiceProvider(Long ServiceProviderId) {

        List<Meal> mealList = this.genericService.loadAllObjectsWithSingleCondition(Meal.class, new CustomPredicate(
                "serviceProvider", ServiceProviderId),  "");
        if(IppmsUtils.isNotNullOrEmpty(mealList)) {
            return mealList;
        }
        else{
            return new ArrayList<>();
        }
    }

    @Override
    public List<Meal> getMealItemsByLocation(String Location) throws InstantiationException, IllegalAccessException {
        List<ServiceProvider> serviceProviders = this.genericService.loadAllObjectsWithSingleCondition(ServiceProvider.class,
                new CustomPredicate("city.name", Location), "");
        System.out.println("got here......");
        List<Meal> mealList = new ArrayList<>();

        for(ServiceProvider s :serviceProviders){
            Meal meal = this.genericService.loadObjectWithSingleCondition(Meal.class,
                    new CustomPredicate("serviceProvider.id",s.getId()));
            meal.setPictureString("data:image/jpg;base64,"+ Base64.encode(meal.getPicture()));
            meal.setServiceProviderName(s.getRestaurantName());
            meal.setEmail(s.getEmail());
            meal.setPhoneNumber(s.getPhoneNumber());
            meal.setLocation(s.getCity().getName());
            mealList.add(meal);
            System.out.println("meal here is: "+meal);
        }
        System.out.println("meal list: "+mealList);
        return mealList;
    }

    @Override
    public List<ServiceProvider> getServiceProvidersByCity(String cityName) throws IllegalAccessException, InstantiationException {
        City city = this.genericService.loadObjectUsingRestriction(City.class,
                Arrays.asList(new CustomPredicate("name", cityName)));
        List<ServiceProvider> searchList = this.genericService.loadAllObjectsUsingRestrictions(ServiceProvider.class,
                Arrays.asList(new CustomPredicate("city", city.getId())), "");
        if(IppmsUtils.isNotNullOrEmpty(searchList)){
            return  searchList;
        }
        else{
            return new ArrayList<>();
        }
    }
}
