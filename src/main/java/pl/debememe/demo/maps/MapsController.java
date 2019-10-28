package pl.debememe.demo.maps;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MapsController {

    LocationsProvider locationsProvider;

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

        model.addAttribute("start", route.getStart());
        model.addAttribute("end", route.getEnd());

        return "directions";
    }


}
