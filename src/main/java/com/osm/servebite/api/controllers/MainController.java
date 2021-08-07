package com.osm.servebite.api.controllers;

import com.osm.servebite.api.domain.*;
import com.osm.servebite.api.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping(value = "/getAllServiceProviders", method = RequestMethod.GET)
    public ResponseEntity<List<ServiceProvider>> getAllServiceProviders() {
        return new ResponseEntity<>(mainService.getAllServiceProviders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllMeals", method = RequestMethod.GET)
        public ResponseEntity<List<Meal>> getAllMeals() throws IllegalAccessException, InstantiationException {
        return new ResponseEntity<>(mainService.getAllMeals(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllCities", method = RequestMethod.GET)
    public ResponseEntity<List<City>> getAllCities() {
        return new ResponseEntity<>(mainService.getAllCities(), HttpStatus.OK);
    }

    @RequestMapping(value = "/getMealItemsByServiceProvider", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Meal>> getMealItemsByServiceProvider(
            @RequestBody ServiceProvider serv) {
        return new ResponseEntity<>(mainService.getMealItemsByServiceProvider(serv.getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/getMealItemsByLocation", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Meal>> getMealItemsByLocation(
            @RequestBody City city) throws IllegalAccessException, InstantiationException {
        return new ResponseEntity<>(mainService.getMealItemsByLocation(city.getName()), HttpStatus.OK);
    }

    @RequestMapping(value = "/getServiceProvidersByCity", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ServiceProvider>> getServiceProvidersByCity(
            @RequestBody City city) throws InstantiationException, IllegalAccessException {
        return new ResponseEntity<>(mainService.getServiceProvidersByCity(city.getName()), HttpStatus.OK);
    }
}
