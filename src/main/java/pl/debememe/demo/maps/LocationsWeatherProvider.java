package pl.debememe.demo.maps;

import pl.debememe.demo.strony.WeatherProvider;

import java.util.ArrayList;
import java.util.List;

public class LocationsWeatherProvider {

    private LocationsProvider locationsProvider = new LocationsProvider();
    private WeatherProvider weatherProvider;
    private String start;
    private String destination;

    //List<LatLong> list = locationsProvider.getLocations(start, destination);
    //List<LocationWeather> locationsWeatherList = new ArrayList<>();


    List<LocationWeather> getLocationsWeatherList(String start, String destination){
        List<LatLong> list = locationsProvider.getLocations(start, destination);
        List<LocationWeather> locationsWeatherList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++){
            LocationWeather locationWeather = new LocationWeather();
            locationWeather.setLocation(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getName());
            locationWeather.setTemp(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getTemp() + " °C");
            locationWeather.setDescription(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getDescription());
            locationWeather.setIcon(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getIcon());
            locationsWeatherList.add(locationWeather);
        }
        return locationsWeatherList;
    }


//    for (LatLong o : list){
//        LocationWeather locationWeather = new LocationWeather();
//        locationWeather.setTemp(weatherProvider.getLatAndLon(list.get(0).getLatitude(), list.get(0).getLongitude()).getTemp() + " °C");
//
//    }
//
//        model.addAttribute("list", list);
//
//
//        model.addAttribute("temperature", weatherProvider.getLatAndLon(list.get(0).getLatitude(), list.get(0).getLongitude()).getTemp() + " °C");
//        model.addAttribute("location", weatherProvider.getLatAndLon(list.get(0).getLatitude(), list.get(0).getLongitude()).getName());
//        model.addAttribute("description", weatherProvider.getLatAndLon(list.get(0).getLatitude(), list.get(0).getLongitude()).getDescription());


}
