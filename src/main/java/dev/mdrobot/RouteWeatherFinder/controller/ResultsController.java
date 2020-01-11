package dev.mdrobot.RouteWeatherFinder.controller;

import dev.mdrobot.RouteWeatherFinder.model.RouteStats;
import dev.mdrobot.RouteWeatherFinder.model.RouteStatsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("session")
public class ResultsController {


    private final RouteStats routeStats;
    private final RouteStatsRepository routeStatsRepository;

    public ResultsController(RouteStats routeStats, RouteStatsRepository routeStatsRepository) {
        this.routeStats = routeStats;
        this.routeStatsRepository = routeStatsRepository;
    }

    @GetMapping
    @RequestMapping("/results")
    public String showHomePage(@ModelAttribute RouteStats routeStats, Model model) {
        model.addAttribute("stats", new RouteStats());
        List<RouteStats> list = routeStatsRepository.findAll();
        model.addAttribute("results", list);
        return "results";
    }


}
