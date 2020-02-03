package dev.mdrobot.RouteWeatherFinder.controller;

import dev.mdrobot.RouteWeatherFinder.dto.LocationWeather;
import dev.mdrobot.RouteWeatherFinder.dto.RouteQuery;
import dev.mdrobot.RouteWeatherFinder.model.SearchedRoute;
import dev.mdrobot.RouteWeatherFinder.services.LocationsProvider;
import dev.mdrobot.RouteWeatherFinder.services.LocationsWeatherProvider;
import dev.mdrobot.RouteWeatherFinder.services.WeatherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@SessionAttributes("session")
public class MapsController {

    private final LocationsProvider locationsProvider;
    private final WeatherProvider weatherProvider;
    private final LocationsWeatherProvider locationsWeatherProvider;
    private final SearchedRoute searchedRoute;
    private final LocationWeather locationWeather;

    @Value("${HOME_LAT}")
    private String lat;
    @Value("${HOME_LON}")
    private String lon;
    @Value("${WEATHER_API_KEY}")
    private String WEATHER_API_KEY;



    public MapsController(@Autowired LocationsProvider locationsProvider, WeatherProvider weatherProvider,
                          LocationsWeatherProvider locationsWeatherProvider, SearchedRoute searchedRoute, LocationWeather locationWeather) {
        this.locationsProvider = locationsProvider;
        this.weatherProvider = weatherProvider;
        this.locationsWeatherProvider = locationsWeatherProvider;
        this.searchedRoute = searchedRoute;
        this.locationWeather = locationWeather;
    }

    @GetMapping
    @RequestMapping("/home")
    public String showHomePage(@ModelAttribute WeatherProvider weatherProvider, Model model) {
        System.out.println(WEATHER_API_KEY);

        LocationWeather homepageWeather = weatherProvider.getWeather(lat, lon);
        List<LocationWeather> initialWeather = Collections.singletonList(homepageWeather);
        model.addAttribute("latitude", lat);
        model.addAttribute("longitude", lon);
        model.addAttribute("list", initialWeather);
        model.addAttribute("query", new RouteQuery());
        return "index";
    }

//    @PostMapping
//    @RequestMapping("/showRoute")
//    public String showRoute(@ModelAttribute RouteStats route, Model model){
//
//        Session session = new Session();
//
//        //MapsDTO mapsDTO = locationsProvider.getDirections(route.getStart(), route.getDestination());
////        List<LocationWeather> locationsWeatherList =
////                locationsWeatherProvider.getLocationsWeather(mapsDTO.getLocations());
////        List<LocationWeather> sorted = new ArrayList<>(locationsWeatherList);
////        RouteStats stats = routeStats.getRouteStats(mapsDTO, sorted);
//
//        session.setStart(route.getStart());
//        session.setEnd(route.getDestination());
//        session.setLocationsWeatherList(locationsWeatherList);
////        session.setStats(stats);
//
//        model.addAttribute("session", session);
//        model.addAttribute("list", session.getLocationsWeatherList());
//        model.addAttribute("stats", session.getStats());
//        model.addAttribute("start", session.getStart());
//        model.addAttribute("end", session.getEnd());
//
//        return "directions";
//    }
//
//    @PostMapping
//    @RequestMapping("/saveRoute")
//    public String saveRoute(@ModelAttribute("session") Session session, Model model){
//        model.addAttribute("list", session.getLocationsWeatherList());
//        model.addAttribute("stats", session.getStats());
//        model.addAttribute("start", session.getStart());
//        model.addAttribute("end", session.getEnd());
//        //repository.save(session.getStats());
//        return "directions";
//    }


}
