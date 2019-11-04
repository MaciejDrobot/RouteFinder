package pl.debememe.demo.maps;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LocationsProvider {

    private String start;
    private String destination;
    private String API_KEY;

    private String queryURL(String start, String destination, String API_KEY){
        return "https://maps.googleapis.com/maps/api/directions/json?"
                + "origin=" + start
                + "&destination=" + destination
                + "&key=" + API_KEY;
    }

    public List<Location> getLocations(String start, String destination, String API_KEY){
        RestTemplate restTemplate = new RestTemplate();
        LocationsResponse forObject = restTemplate
                .getForObject(queryURL(start, destination, API_KEY), LocationsResponse.class);
        List<Location> search = forObject.getSearch();
        return search;
    }



}
