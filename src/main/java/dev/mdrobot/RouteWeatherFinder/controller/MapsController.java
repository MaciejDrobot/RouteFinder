package dev.mdrobot.RouteWeatherFinder.controller;

import dev.mdrobot.RouteWeatherFinder.dto.LocationWeather;
import dev.mdrobot.RouteWeatherFinder.dto.RouteQuery;
import dev.mdrobot.RouteWeatherFinder.model.SearchedRoute;
import dev.mdrobot.RouteWeatherFinder.model.SearchedRouteRepository;
import dev.mdrobot.RouteWeatherFinder.services.LocationsProvider;
import dev.mdrobot.RouteWeatherFinder.services.LocationsWeatherProvider;
import dev.mdrobot.RouteWeatherFinder.services.SearchedRouteProvider;
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
    private final SearchedRouteProvider searchedRouteProvider;
    private final SearchedRouteRepository repository;

    @Value("${HOME_LAT}")
    private String lat;
    @Value("${HOME_LON}")
    private String lon;

    public MapsController(@Autowired LocationsProvider locationsProvider, WeatherProvider weatherProvider,
                          LocationsWeatherProvider locationsWeatherProvider, SearchedRoute searchedRoute, LocationWeather locationWeather, SearchedRouteProvider searchedRouteProvider, SearchedRouteRepository repository) {
        this.locationsProvider = locationsProvider;
        this.weatherProvider = weatherProvider;
        this.locationsWeatherProvider = locationsWeatherProvider;
        this.searchedRoute = searchedRoute;
        this.locationWeather = locationWeather;
        this.searchedRouteProvider = searchedRouteProvider;
        this.repository = repository;
    }

    @GetMapping
    @RequestMapping("/home")
    public String showHomePage(Model model) {
        LocationWeather homepageWeather = weatherProvider.getWeather(lat, lon);
        List<LocationWeather> initialWeather = Collections.singletonList(homepageWeather);
        model.addAttribute("latitude", lat);
        model.addAttribute("longitude", lon);
        model.addAttribute("list", initialWeather);
        model.addAttribute("query", new RouteQuery());
        return "index";
    }

    @PostMapping
    @RequestMapping("/showRoute")
    public String showRoute(@ModelAttribute RouteQuery rq, Model model){
        SearchedRoute sr =
                searchedRouteProvider.createSearchedRoute(rq.getStart(), rq.getEnd());
        setModel(sr, rq, model);
        return "directions";
    }

    @PostMapping
    @RequestMapping("/saveRoute")
    public String saveRoute(@ModelAttribute("session") SearchedRoute searchedRoute, RouteQuery rq,Model model){
        setModel(searchedRoute, rq, model);
        repository.save(searchedRoute);
        return "directions";
    }

    public Model setModel(@ModelAttribute("session") SearchedRoute sr, RouteQuery rq, Model model) {
        model.addAttribute("session", sr);
        model.addAttribute("searchedRoute", sr);
        model.addAttribute("list", sr.getLocationsWeather());
        model.addAttribute("start", sr.getStart());
        model.addAttribute("end", sr.getDestination());
        model.addAttribute("query", rq);
        return model;
    }
}
