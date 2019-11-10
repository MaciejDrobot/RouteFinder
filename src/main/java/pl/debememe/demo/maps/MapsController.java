package pl.debememe.demo.maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.debememe.demo.strony.WeatherProvider;


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
        model.addAttribute("temperature", weatherProvider.getLatAndLon(lat, lon).getTemp() + " °C");
        model.addAttribute("location", weatherProvider.getLatAndLon(lat, lon).getName());
        model.addAttribute("description", weatherProvider.getLatAndLon(lat, lon).getDescription());
        System.out.println(weatherProvider.getLatAndLon(lat, lon).getTemp());
        return "index";
    }

    @PostMapping
    @RequestMapping("/showRoute")
    public String showRoute(@ModelAttribute Route route, Model model){
        LatLong list = locationsProvider.getLocations(route.getStart(), route.getEnd());
        model.addAttribute("temperature", weatherProvider.getLatAndLon(list.getLatitude(), list.getLongitude()).getTemp() + " °C");
        model.addAttribute("location", weatherProvider.getLatAndLon(list.getLatitude(), list.getLongitude()).getName());
        model.addAttribute("description", weatherProvider.getLatAndLon(list.getLatitude(), list.getLongitude()).getDescription());
        model.addAttribute("start", route.getStart());
        model.addAttribute("end", route.getEnd());

        return "directions";
    }


}
