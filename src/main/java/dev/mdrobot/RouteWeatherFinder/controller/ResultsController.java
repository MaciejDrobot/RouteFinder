package dev.mdrobot.RouteWeatherFinder.controller;

import dev.mdrobot.RouteWeatherFinder.model.SearchedRoute;
import dev.mdrobot.RouteWeatherFinder.model.SearchedRouteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@SessionAttributes("session")
public class ResultsController {


    private final SearchedRoute searchedRoute;
    private final SearchedRouteRepository repository;

    public ResultsController(SearchedRoute searchedRoute, SearchedRouteRepository repository) {
        this.searchedRoute = searchedRoute;
        this.repository = repository;
    }

    @GetMapping
    @RequestMapping("/results")
    public String showHomePage(@ModelAttribute SearchedRoute searchedRoute, Model model) {
        model.addAttribute("stats", new SearchedRoute());
        List<SearchedRoute> list = repository.findAll();
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
