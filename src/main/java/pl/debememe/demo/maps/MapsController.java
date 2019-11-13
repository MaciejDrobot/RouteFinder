package pl.debememe.demo.maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.debememe.demo.strony.WeatherProvider;

import java.util.*;

@Controller
public class MapsController {

    private final LocationsProvider locationsProvider;
    private final WeatherProvider weatherProvider;
    private final LocationsWeatherProvider locationsWeatherProvider;
    private final RouteStats routeStats;


    public MapsController(@Autowired LocationsProvider locationsProvider, WeatherProvider weatherProvider,
                          LocationsWeatherProvider locationsWeatherProvider, RouteStats routeStats) {
        this.locationsProvider = locationsProvider;
        this.weatherProvider = weatherProvider;
        this.locationsWeatherProvider = locationsWeatherProvider;
        this.routeStats = routeStats;
    }

    @GetMapping
    @RequestMapping("/home")
    public String showHomePage(@ModelAttribute WeatherProvider weatherProvider, Model model){
        model.addAttribute("latitude", "51.509865");
        model.addAttribute("longitude", "-0.118092");
        model.addAttribute("route", new Route());
        List<LocationWeather> list = locationsWeatherProvider.createInitialLocation("51.50", "-0.11");
        model.addAttribute("list", list);
        return "index";
    }

    @PostMapping
    @RequestMapping("/showRoute")
    public String showRoute(@ModelAttribute Route route, Model model){
        List<LocationWeather> locationsWeatherList =
                locationsWeatherProvider.getLocationsWeatherList(route.getStart(), route.getEnd());
        //List<LocationWeather> locationsWeatherList = locationsWeatherProvider.getTestList();
        List<LocationWeather> sorted = new ArrayList<>(locationsWeatherList);
        RouteStats stats = routeStats.getRouteStats(route, sorted);
        model.addAttribute("list", locationsWeatherList);
        model.addAttribute("stats", stats);
        model.addAttribute("start", route.getStart());
        model.addAttribute("end", route.getEnd());
        return "directions";
    }


}
