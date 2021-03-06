package dev.mdrobot.RouteWeatherFinder.services;

import com.fasterxml.jackson.databind.JsonNode;
import dev.mdrobot.RouteWeatherFinder.model.Stats;
import dev.mdrobot.RouteWeatherFinder.model.SearchedRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchedRouteProvider{

    private final LocationsProvider locationsProvider;
    private final LocationsWeatherProvider locationsWeatherProvider;
    private final Stats stats;

    public SearchedRouteProvider(@Autowired LocationsProvider locationsProvider, LocationsWeatherProvider locationsWeatherProvider, Stats minMax){
        this.locationsProvider = locationsProvider;
        this.locationsWeatherProvider = locationsWeatherProvider;
        this.stats = minMax;
    }

    public SearchedRoute createSearchedRoute(String start, String end){
        JsonNode response = locationsProvider.getDirectionsResponse(start, end);
        SearchedRoute searchedRoute = new SearchedRoute();
        searchedRoute.setStart(locationsProvider.getStartName(response));
        searchedRoute.setDestination(locationsProvider.getDestinationName(response));
        searchedRoute.setDistance(locationsProvider.getDistance(response));
        //todo set search date
        searchedRoute.setListOfLocations(locationsProvider.getListOfLocations(response));
        searchedRoute.setLocationsWeather(locationsWeatherProvider.getLocationsWeather(searchedRoute.getListOfLocations()));
        searchedRoute.setMaxTemp(stats.getMaxTemp(searchedRoute.getLocationsWeather()));
        searchedRoute.setMinTemp(stats.getMinTemp(searchedRoute.getLocationsWeather()));
        return searchedRoute;

    }
}
