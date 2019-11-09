package pl.debememe.demo.strony;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
        @GetMapping
        @RequestMapping("/home")
        public String showMoviesPage(Model model){
            return "Geolocation";
        }
    }

