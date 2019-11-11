package pl.debememe.demo.maps;

import org.eclipse.collections.impl.block.factory.HashingStrategies;
import org.eclipse.collections.impl.utility.ListIterate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.debememe.demo.strony.WeatherProvider;
import java.util.*;


@Service
public class LocationsWeatherProvider {

    private final LocationsProvider locationsProvider;
    private final WeatherProvider weatherProvider;

    public LocationsWeatherProvider(@Autowired LocationsProvider locationsProvider, WeatherProvider weatherProvider) {
        this.locationsProvider = locationsProvider;
        this.weatherProvider = weatherProvider;
    }

    List<LocationWeather> getLocationsWeatherList(String start, String destination) {

        List<LatLong> list = locationsProvider.getLocations(start, destination);
        List<LocationWeather> locationsWeatherList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            LocationWeather locationWeather = new LocationWeather();
            locationWeather.setLocation(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getName());
            locationWeather.setTemp(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getTemp() + " °C");
            locationWeather.setDescription(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getDescription());
            locationWeather.setIcon(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getIcon());
            locationsWeatherList.add(locationWeather);
        }
        return filterLocations(locationsWeatherList);
    }

    List<LocationWeather> createInitialLocation(String lat, String lon){
        List<LocationWeather> list = new ArrayList<>();
        LocationWeather locationWeather = new LocationWeather();
        locationWeather.setLocation(weatherProvider.getWeather(lat, lon).getName());
        locationWeather.setTemp(weatherProvider.getWeather(lat, lon).getTemp() + " °C");
        locationWeather.setDescription(weatherProvider.getWeather(lat, lon).getDescription());
        locationWeather.setIcon(weatherProvider.getWeather(lat, lon).getIcon());
        list.add(locationWeather);
        return list;
    }

    static List<LocationWeather> filterLocations(List<LocationWeather> list){
        List<LocationWeather> uniqueLocations = ListIterate
                .distinct(list, HashingStrategies.fromFunction(LocationWeather::getLocation));
        return uniqueLocations;
    }

}