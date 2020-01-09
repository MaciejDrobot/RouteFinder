package dev.mdrobot.RouteWeatherFinder.controller;

import dev.mdrobot.RouteWeatherFinder.maps.Session;
import dev.mdrobot.RouteWeatherFinder.services.LocationsWeatherProvider;
import dev.mdrobot.RouteWeatherFinder.model.RouteStats;
import dev.mdrobot.RouteWeatherFinder.model.RouteStatsRepository;
import dev.mdrobot.RouteWeatherFinder.model.LocationWeather;
import dev.mdrobot.RouteWeatherFinder.model.MapsDTO;
import dev.mdrobot.RouteWeatherFinder.services.LocationsProvider;
import dev.mdrobot.RouteWeatherFinder.services.WeatherProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@SessionAttributes("session")
public class MapsController {

    List<LocationWeather> locationsWeatherList;

    private final LocationsProvider locationsProvider;
    private final WeatherProvider weatherProvider;
    private final LocationsWeatherProvider locationsWeatherProvider;
    private final MapsDTO mapsDTO;
    private final RouteStatsRepository repository;
    private final RouteStats routeStats;
    private final Session session;



    public MapsController(@Autowired LocationsProvider locationsProvider, WeatherProvider weatherProvider,
                          LocationsWeatherProvider locationsWeatherProvider, MapsDTO mapsDTO, RouteStatsRepository repository, RouteStats routeStats, Session session) {
        this.locationsProvider = locationsProvider;
        this.weatherProvider = weatherProvider;
        this.locationsWeatherProvider = locationsWeatherProvider;
        this.mapsDTO = mapsDTO;
        this.repository = repository;
        this.routeStats = routeStats;
        this.session = session;
    }

    @GetMapping
    @RequestMapping("/home")
    public String showHomePage(@ModelAttribute WeatherProvider weatherProvider, Model model) {
        model.addAttribute("latitude", "51.5071934");
        model.addAttribute("longitude", "-0.12777652");
        model.addAttribute("stats", new RouteStats());
        List<LocationWeather> list = locationsWeatherProvider.createInitialLocation("51.50", "-0.12");
        model.addAttribute("list", list);
        model.addAttribute("temp", list.get(0).getTemp());
        //model.addAttribute("icon", list.get(0).getIcon());
        //model.addAttribute("icon", "'/css/weatherIcons/01d.png'");
        return "index";
    }

    @PostMapping
    @RequestMapping("/showRoute")
    public String showRoute(@ModelAttribute RouteStats route, Model model){

        Session session = new Session();

        MapsDTO mapsDTO = locationsProvider.getDirections(route.getStart(), route.getDestination());
        List<LocationWeather> locationsWeatherList =
                locationsWeatherProvider.getLocationsWeatherList(mapsDTO.getLocations());
        List<LocationWeather> sorted = new ArrayList<>(locationsWeatherList);
        RouteStats stats = routeStats.getRouteStats(mapsDTO, sorted);

        session.setStart(route.getStart());
        session.setEnd(route.getDestination());
        session.setLocationsWeatherList(locationsWeatherList);
        session.setStats(stats);

        model.addAttribute("session", session);
        model.addAttribute("list", session.getLocationsWeatherList());
        model.addAttribute("stats", session.getStats());
        model.addAttribute("start", session.getStart());
        model.addAttribute("end", session.getEnd());

        return "directions";
    }

    @PostMapping
    @RequestMapping("/saveRoute")
    public String saveRoute(@ModelAttribute ("session") Session session, Model model){
        model.addAttribute("list", session.getLocationsWeatherList());
        model.addAttribute("stats", session.getStats());
        model.addAttribute("start", session.getStart());
        model.addAttribute("end", session.getEnd());
        repository.save(session.getStats());
        return "directions";
    }
}
