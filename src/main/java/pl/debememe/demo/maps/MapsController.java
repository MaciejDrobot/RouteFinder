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

    public MapsController(@Autowired LocationsProvider locationsProvider, WeatherProvider weatherProvider) {
        this.locationsProvider = locationsProvider;
        this.weatherProvider = weatherProvider;
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


//        model.addAttribute("temperature", weatherProvider.getWeather(lat, lon).getTemp() + " °C");
//        model.addAttribute("location", weatherProvider.getWeather(lat, lon).getName());
//        model.addAttribute("description", weatherProvider.getWeather(lat, lon).getDescription());
//        model.addAttribute("icon", weatherProvider.getWeather(lat, lon).getIcon());
        return "index";
    }

    @PostMapping
    @RequestMapping("/showRoute")
    public String showRoute(@ModelAttribute Route route, Model model){
//        List<LocationWeather> list = locationsWeatherProvider.getLocationsWeatherList(route.getStart(), route.getEnd());
//        model.addAttribute("list", list);
        List<LatLong> list = locationsProvider.getLocations(route.getStart(), route.getEnd());
        model.addAttribute("start", route.getStart());
        model.addAttribute("end", route.getEnd());
        return "directions";
    }


}
