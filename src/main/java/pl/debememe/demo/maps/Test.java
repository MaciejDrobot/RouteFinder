package pl.debememe.demo.maps;

import pl.debememe.demo.strony.WeatherProvider;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String start = "Gdansk";
        String destination = "Elblag";

        LocationsProvider locationsProvider = new LocationsProvider();
        WeatherProvider weatherProvider = new WeatherProvider();
        List<LocationWeather> locationsWeatherList = new ArrayList<>();

        List<LatLong> list = locationsProvider.getLocations(start, destination);

        System.out.println(list.get(0).getLatitude());

        for (int i = 0; i < list.size(); i++) {
            LocationWeather locationWeather = new LocationWeather();
            locationWeather.setLocation(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getName());
            locationWeather.setTemp(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getTemp() + " °C");
            locationWeather.setDescription(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getDescription());
            locationWeather.setIcon(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getIcon());
            locationsWeatherList.add(locationWeather);
        }
    }
}
