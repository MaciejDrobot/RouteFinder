package dev.mdrobot.RouteWeatherFinder.controller;

import dev.mdrobot.RouteWeatherFinder.model.RouteStats;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("session")
public class ResultsController {


    private final RouteStats routeStats;

    public ResultsController(RouteStats routeStats) {
        this.routeStats = routeStats;
    }

    @GetMapping
    @RequestMapping("/results")
    public String showHomePage(@ModelAttribute RouteStats routeStats, Model model) {
        model.addAttribute("stats", new RouteStats());

        return "results";
    }


}
