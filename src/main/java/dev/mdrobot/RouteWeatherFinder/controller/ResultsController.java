package dev.mdrobot.RouteWeatherFinder.controller;

import dev.mdrobot.RouteWeatherFinder.model.RouteStats;
import dev.mdrobot.RouteWeatherFinder.model.RouteStatsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("session")
public class ResultsController {


    private final RouteStats routeStats;
    private final RouteStatsRepository repository;

    public ResultsController(RouteStats routeStats, RouteStatsRepository routeStatsRepository) {
        this.routeStats = routeStats;
        this.repository = routeStatsRepository;
    }

    @GetMapping
    @RequestMapping("/results")
    public String showHomePage(@ModelAttribute RouteStats routeStats, Model model) {
        model.addAttribute("stats", new RouteStats());
        List<RouteStats> list = repository.findAll();
        model.addAttribute("results", list);
        return "results";
    }

    @GetMapping
    @RequestMapping("/delete/{id}")
    public String deleteRoute(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/results";
    }


}
