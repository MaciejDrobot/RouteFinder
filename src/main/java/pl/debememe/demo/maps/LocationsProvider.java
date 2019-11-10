package pl.debememe.demo.maps;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.stream.Location;
import java.util.ArrayList;
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

    public List<LatLong> getLocations(String start, String destination){

        RestTemplate restTemplate = new RestTemplate();
       // HttpEntity<String> entity = createHttpEntity();

//        HttpEntity<Location> response = restTemplate.exchange(queryURL(start, destination), HttpMethod.GET, entity, Location.class);
        List<LatLong> locationsList = new ArrayList<>();


        JsonNode locationInfo = restTemplate.getForObject(queryURL(start, destination), JsonNode.class);

        int steps = locationInfo.get("routes").get(0).get("legs").get(0).get("steps").size();

        for (int i = 0; i < steps; i++) {
            LatLong location = new LatLong();
            location.setLatitude(locationInfo.get("routes").get(0).get("legs").get(0).get("steps").get(i).get("start_location").get("lat").asText());
            location.setLongitude(locationInfo.get("routes").get(0).get("legs").get(0).get("steps").get(i).get("start_location").get("lng").asText());
            locationsList.add(location);
        }


        return locationsList;
    }
}
