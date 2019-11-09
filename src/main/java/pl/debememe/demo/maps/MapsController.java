package pl.debememe.demo.maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.debememe.demo.strony.WeatherProvider;

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
        //model.addAttribute("temperature", "10");

        double lat = 51.50;
        double lon = -0.11;
        model.addAttribute("temperature", weatherProvider.getLatAndLon(lat, lon).getTemp());
        System.out.println(weatherProvider.getLatAndLon(lat, lon).getTemp());

        return "index";
    }

    @PostMapping
    @RequestMapping("/showRoute")
    public String showRoute(@ModelAttribute Route route, Model model){
        List<Location> list = locationsProvider.getLocations(route.getStart(), route.getEnd());
        model.addAttribute("start", route.getStart());
        model.addAttribute("end", route.getEnd());

        return "directions";
    }


    @GetMapping
    @RequestMapping("/getWeatherForDefaultCoordinates")
    public String provideWeather(Model model) {
        //model.addAttribute("temperature", "5");
        return "index";
    }


}
