package pl.debememe.demo.maps;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class MapsController {

    MapsProvider mapsProvider;


    @GetMapping
    @RequestMapping("/home")
    public String showHomePage(Model model){
        String latitude = "58.400";
        String longitude = "22.010";
        model.addAttribute("latitude", latitude);
        model.addAttribute("longitude", longitude);
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
