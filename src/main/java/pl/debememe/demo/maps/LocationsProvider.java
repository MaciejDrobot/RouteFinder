package pl.debememe.demo.maps;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public LatLong getLocations(String start, String destination){

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = createHttpEntity();

        HttpEntity<LatLong> response = restTemplate.exchange(queryURL(start, destination), HttpMethod.GET, entity, LatLong.class);

        LatLong body = response.getBody();
        JsonNode locationInfo = restTemplate.getForObject(queryURL(start, destination), JsonNode.class);
        body.setLatitude(locationInfo.get("routes").get(0).get("legs").get(0).get("steps").get(0).get("start_location").get("lat").asText());
        body.setLongitude(locationInfo.get("routes").get(0).get("legs").get(0).get("steps").get(0).get("start_location").get("lng").asText());
        return body;
    }

    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<>("parameters", headers);
    }


}
