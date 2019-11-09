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
        String longitude = "23.010";
        model.addAttribute("latitude", latitude);
        model.addAttribute("longitude", longitude);
        model.addAttribute("location", new Location());
        return "home";
    }

    @PostMapping
    @RequestMapping("/showLocation")
    public String showLocation(@ModelAttribute Location location, Model model){
        model.addAttribute("latitude", location.getLatitude());
        model.addAttribute("longitude", location.getLongitude());
        return "home";
    }


}
