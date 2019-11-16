package pl.debememe.demo.maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.debememe.demo.strony.WeatherProvider;

import java.util.*;

@Controller
public class MapsController {

    private RouteStats lastStats;
    List<LocationWeather> locationsWeatherList;

    private final LocationsProvider locationsProvider;
    private final WeatherProvider weatherProvider;
    private final LocationsWeatherProvider locationsWeatherProvider;
    private final MapsDTO mapsDTO;
    private final RouteStatsRepository repository;


    public MapsController(@Autowired LocationsProvider locationsProvider, WeatherProvider weatherProvider,
                          LocationsWeatherProvider locationsWeatherProvider, MapsDTO mapsDTO, RouteStatsRepository repository) {
        this.locationsProvider = locationsProvider;
        this.weatherProvider = weatherProvider;
        this.locationsWeatherProvider = locationsWeatherProvider;
        this.mapsDTO = mapsDTO;
        this.repository = repository;
    }

    @GetMapping
    @RequestMapping("/home")
    public String showHomePage(@ModelAttribute WeatherProvider weatherProvider, Model model) {
        model.addAttribute("latitude", "51.5071934");
        model.addAttribute("longitude", "-0.12777652");
        model.addAttribute("route", new Route());
        List<LocationWeather> list = locationsWeatherProvider.createInitialLocation("51.50", "-0.12");
        model.addAttribute("list", list);
        model.addAttribute("temp", list.get(0).getTemp());
        //model.addAttribute("icon", list.get(0).getIcon());
        //model.addAttribute("icon", "'/css/weatherIcons/01d.png'");
        return "index";
    }

    @PostMapping
    @RequestMapping("/showRoute")
    public String showRoute(@ModelAttribute Route route, Model model) {
        if(lastStats == null) {
            MapsDTO mapsDTO = locationsProvider.getDirections(route.getStart(), route.getEnd());
            locationsWeatherList =
                    locationsWeatherProvider.getLocationsWeatherList(mapsDTO.getLocations());
            List<LocationWeather> sorted = new ArrayList<>(locationsWeatherList);
            RouteStats stats = RouteStats.getRouteStats(mapsDTO, sorted);
            lastStats = stats;
            setModel(route, model);
        }
        else {
            setModel(route, model);
            lastStats = null;
        }
        return "directions";
    }

    private void setModel(@ModelAttribute Route route, Model model) {
        model.addAttribute("list", locationsWeatherList);
        model.addAttribute("stats", lastStats);
        model.addAttribute("start", route.getStart());
        model.addAttribute("end", route.getEnd());
    }

    @PostMapping
    @RequestMapping("/saveRoute")
    public String saveRoute() {
        repository.save(lastStats);
        return "redirect:showRoute";
    }


}
