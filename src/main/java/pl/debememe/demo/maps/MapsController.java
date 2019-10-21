package pl.debememe.demo.maps;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MapsController {


    @GetMapping
    @RequestMapping("/home")
    public String showHomePage(Model model){

        return "home";
    }


}
