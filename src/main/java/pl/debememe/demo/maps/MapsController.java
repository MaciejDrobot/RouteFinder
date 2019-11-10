package pl.debememe.demo.maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.debememe.demo.strony.WeatherProvider;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MapsController {

    private final LocationsProvider locationsProvider;
    private final WeatherProvider weatherProvider;
    private final LocationsWeatherProvider locationsWeatherProvider;

    public MapsController(@Autowired LocationsProvider locationsProvider, WeatherProvider weatherProvider,
                          LocationsWeatherProvider locationsWeatherProvider) {
        this.locationsProvider = locationsProvider;
        this.weatherProvider = weatherProvider;
        this.locationsWeatherProvider = locationsWeatherProvider;
    }


    @GetMapping
    @RequestMapping("/home")
    public String showHomePage(@ModelAttribute WeatherProvider weatherProvider, Model model){
        model.addAttribute("latitude", "51.509865");
        model.addAttribute("longitude", "-0.118092");
        model.addAttribute("route", new Route());
        String lat = "51.50";
        String lon = "-0.11";
        List<LocationWeather> list = new ArrayList<>();
        LocationWeather locationWeather = new LocationWeather();
        locationWeather.setLocation(weatherProvider.getWeather(lat, lon).getName());
        locationWeather.setTemp(weatherProvider.getWeather(lat, lon).getTemp() + " °C");
        locationWeather.setDescription(weatherProvider.getWeather(lat, lon).getDescription());
        locationWeather.setIcon(weatherProvider.getWeather(lat, lon).getIcon());
        list.add(locationWeather);
        model.addAttribute("list", list);

//        model.addAttribute("temperature", weatherProvider.getLatAndLon(lat, lon).getTemp() + " °C");
//        model.addAttribute("location", weatherProvider.getLatAndLon(lat, lon).getName());
//        model.addAttribute("description", weatherProvider.getLatAndLon(lat, lon).getDescription());
//        System.out.println(weatherProvider.getLatAndLon(lat, lon).getTemp());
//        model.addAttribute("icon", weatherProvider.getLatAndLon(lat, lon).getIcon());

        return "index";
    }

    @PostMapping
    @RequestMapping("/showRoute")
    public String showRoute(@ModelAttribute Route route, Model model){

//        List<LocationWeather> list = locationsWeatherProvider.getLocationsWeatherList(route.getStart(), route.getEnd());
//        model.addAttribute("temperature", weatherProvider.getWeather(list.get(0).getLatitude(), list.get(0).getLongitude()).getTemp() + " °C");
//        model.addAttribute("location", weatherProvider.getLatAndLon(list.getLatitude(), list.getLongitude()).getName());
//        model.addAttribute("description", weatherProvider.getLatAndLon(list.getLatitude(), list.getLongitude()).getDescription());

        List<LatLong> list = locationsProvider.getLocations(route.getStart(), route.getEnd());
        List<LocationWeather> locationsWeatherList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            LocationWeather locationWeather = new LocationWeather();
            locationWeather.setLocation(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getName());
            locationWeather.setTemp(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getTemp() + " °C");
            locationWeather.setDescription(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getDescription());
            locationWeather.setIcon(weatherProvider.getWeather(list.get(i).getLatitude(), list.get(i).getLongitude()).getIcon());
            locationsWeatherList.add(locationWeather);
        }

        model.addAttribute("list", locationsWeatherList);
        model.addAttribute("start", route.getStart());
        model.addAttribute("end", route.getEnd());

        return "directions";
    }


}
