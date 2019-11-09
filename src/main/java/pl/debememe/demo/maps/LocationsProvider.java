package pl.debememe.demo.maps;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LocationsProvider {

    private String start;
    private String destination;
    @Value("${API_KEY}")
    private String API_KEY;

    private String queryURL(String start, String destination){
        return "https://maps.googleapis.com/maps/api/directions/json?"
                + "origin=" + start
                + "&destination=" + destination
                + "&key=" + API_KEY;
    }

    public List<Location> getLocations(String start, String destination){
        RestTemplate restTemplate = new RestTemplate();
        LocationsResponse forObject = restTemplate
                .getForObject(queryURL(start, destination), LocationsResponse.class);
        List<Location> search = forObject.getSearch();
        return search;
    }



}
