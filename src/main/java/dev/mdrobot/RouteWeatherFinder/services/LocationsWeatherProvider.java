package dev.mdrobot.RouteWeatherFinder.services;

import dev.mdrobot.RouteWeatherFinder.dto.LatLong;
import dev.mdrobot.RouteWeatherFinder.dto.LocationWeather;
import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.utility.ListIterate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class LocationsWeatherProvider {

    private final LocationsProvider locationsProvider;
    private final WeatherProvider weatherProvider;

    public LocationsWeatherProvider(@Autowired LocationsProvider locationsProvider, WeatherProvider weatherProvider) {
        this.locationsProvider = locationsProvider;
        this.weatherProvider = weatherProvider;
    }

    public List<LocationWeather> getLocationsWeather(List<LatLong> list) {

        List<LocationWeather> locationsWeatherList = new ArrayList<>();

        for (int i = 0; i < list.size(); i+=2) {
            LocationWeather locationWeather = new LocationWeather();
            locationWeather.setName(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getName());
            locationWeather.setTemp(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getTemp());
            locationWeather.setDescription(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getDescription());
            locationWeather.setIcon(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getIcon());
            locationsWeatherList.add(locationWeather);
        }
        //return filterLocations(locationsWeatherList);
        return locationsWeatherList;
    }

    static List<LocationWeather> filterLocations(List<LocationWeather> list){
        List<LocationWeather> uniqueLocations = ListIterate
                .distinct(list, HashingStrategies.fromFunction(LocationWeather::getName));
        return uniqueLocations;
    }

//    public List<LocationWeather> createInitialLocation(String lat, String lon){
//        List<LocationWeather> list = new ArrayList<>();
//        LocationWeather locationWeather = new LocationWeather();
//        locationWeather.setLocation(weatherProvider.getWeather(lat, lon).getName());
//        locationWeather.setTemp(String.valueOf(weatherProvider.getWeather(lat, lon).getTemp()));
//        locationWeather.setDescription(weatherProvider.getWeather(lat, lon).getDescription());
//        locationWeather.setIcon(weatherProvider.getWeather(lat, lon).getIcon());
//        list.add(locationWeather);
//        return list;
//    }



}