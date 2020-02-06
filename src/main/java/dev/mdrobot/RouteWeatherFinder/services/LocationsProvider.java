package dev.mdrobot.RouteWeatherFinder.services;

import com.fasterxml.jackson.databind.JsonNode;
import dev.mdrobot.RouteWeatherFinder.dto.LatLong;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class LocationsProvider {

    @Value("${MAP_API_KEY}")
    private String MAP_API_KEY;

    private String queryURL(String start, String destination){
        return "https://maps.googleapis.com/maps/api/directions/json?"
                + "origin=" + start
                + "&destination=" + destination
                + "&key=" + MAP_API_KEY;
    }

    public JsonNode getDirectionsResponse(String start, String end){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = createHttpEntity();
        JsonNode response = restTemplate.getForObject(queryURL(start, end), JsonNode.class);
        return response;
    }

    public List<LatLong> getListOfLocations(JsonNode response){
        List<LatLong> locationsList = new ArrayList<>();

        int steps = response.get("routes").get(0).get("legs").get(0).get("steps").size();

        for (int i = 0; i < steps; i++) {
            LatLong location = new LatLong();
            location.setLatitude(response.get("routes").get(0).get("legs").get(0).get("steps").get(i)
                    .get("start_location").get("lat").asText());
            location.setLongitude(response.get("routes").get(0).get("legs").get(0).get("steps").get(i)
                    .get("start_location").get("lng").asText());
            locationsList.add(location);
        }
        return locationsList;
    }

    public String getStartName(JsonNode response){
        String startQuery = response.get("routes").get(0).get("legs").get(0).get("start_address").asText();
        List<String> startName = Arrays.asList(startQuery.split(","));
        return startName.get(0);
    }

    public String getDestinationName(JsonNode response){
        String destinationQuery = response.get("routes").get(0).get("legs").get(0).get("end_address").asText();
        List<String> destinationName = Arrays.asList(destinationQuery.split(","));
        return destinationName.get(0);
    }

    public String getDistance(JsonNode response){
        String distance = response.get("routes").get(0).get("legs").get(0).get("distance").get("text").asText();
        return distance;
    }

    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<>("parameters", headers);
    }

}
