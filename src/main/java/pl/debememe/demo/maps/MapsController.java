package pl.debememe.demo.maps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MapsController {

    private final LocationsProvider locationsProvider;

    public MapsController(@Autowired LocationsProvider locationsProvider) {
        this.locationsProvider = locationsProvider;
    }

    @GetMapping
    @RequestMapping("/home")
    public String showHomePage(Model model){
        model.addAttribute("latitude", "51.509865");
        model.addAttribute("longitude", "-0.118092");
        model.addAttribute("route", new Route());
        return "home";
    }

    @PostMapping
    @RequestMapping("/showRoute")
    public String showRoute(@ModelAttribute Route route, Model model){
        List<Location> list = locationsProvider.getLocations(route.getStart(), route.getEnd(), "AIzaSyBV6hjFNhlm5eCfM3aO-jWz2LapYwza3rM");
        model.addAttribute("start", route.getStart());
        model.addAttribute("end", route.getEnd());

        return "directions";
    }


}
