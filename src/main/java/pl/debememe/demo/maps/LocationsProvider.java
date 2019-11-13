package pl.debememe.demo.maps;


import com.fasterxml.jackson.databind.JsonNode;
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

    public MapsDTO getDirections(String start, String destination){

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = createHttpEntity();
        MapsDTO route = new MapsDTO();
        List<LatLong> locationsList = new ArrayList<>();

        JsonNode locationInfo = restTemplate.getForObject(queryURL(start, destination), JsonNode.class);

        int steps = locationInfo.get("routes").get(0).get("legs").get(0).get("steps").size();

        for (int i = 0; i < steps; i++) {
            LatLong location = new LatLong();
            location.setLatitude(locationInfo.get("routes").get(0).get("legs").get(0).get("steps").get(i).get("start_location").get("lat").asText());
            location.setLongitude(locationInfo.get("routes").get(0).get("legs").get(0).get("steps").get(i).get("start_location").get("lng").asText());
            locationsList.add(location);
        }
        String startQuery = locationInfo.get("routes").get(0).get("legs").get(0).get("start_address").asText();
        List<String> startName = Arrays.asList(startQuery.split(","));
        route.setStart(startName.get(0));
        String destinationQuery = locationInfo.get("routes").get(0).get("legs").get(0).get("end_address").asText();
        List<String> destinationName = Arrays.asList(destinationQuery.split(","));
        route.setDestination(destinationName.get(0));
        route.setDistance(locationInfo.get("routes").get(0).get("legs").get(0).get("distance").get("text").asText());
        route.setLocations(locationsList);

        return route;
    }

    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<>("parameters", headers);
    }


}
