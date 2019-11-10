package pl.debememe.demo.maps;

import org.springframework.stereotype.Service;
import pl.debememe.demo.strony.WeatherProvider;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsWeatherProvider {

    private LocationsProvider locationsProvider = new LocationsProvider();
    private WeatherProvider weatherProvider;
    private String start;
    private String destination;

    //List<LatLong> list = locationsProvider.getLocations(start, destination);
    //List<LocationWeather> locationsWeatherList = new ArrayList<>();

    List<LocationWeather> getLocationsWeatherList(String start, String destination) {

        LocationsProvider locationsProvider = new LocationsProvider();
        WeatherProvider weatherProvider = new WeatherProvider();
        List<LocationWeather> locationsWeatherList = new ArrayList<>();

        List<LatLong> list = locationsProvider.getLocations(start, destination);

        for (int i = 0; i < list.size(); i++) {
            LocationWeather locationWeather = new LocationWeather();
            locationWeather.setLocation(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getName());
            locationWeather.setTemp(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getTemp() + " °C");
            locationWeather.setDescription(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getDescription());
            locationWeather.setIcon(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getIcon());
            locationsWeatherList.add(locationWeather);
        }
        return locationsWeatherList;
    }

}