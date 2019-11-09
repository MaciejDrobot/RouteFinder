package pl.debememe.demo.strony;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class WeatherProvider {

    double lat = 51.50;
    double lon = -0.11;
    private final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?&units=metric&APPID=51079114e50559a6a1afc21bd1c24ea0&";
    private String API_KEY = "51079114e50559a6a1afc21bd1c24ea0";

    public WeatherDTO getLatAndLon(double lat, double lon) {
        RestTemplate restTemplate = new RestTemplate();
        String fullURL = WEATHER_URL + "lat=" + lat + "&lon=" + lon;


        HttpEntity<String> entity = createHttpEntity();

        JsonNode forObject = restTemplate.getForObject(fullURL, JsonNode.class).get("main");
        System.out.println(forObject.get("temp"));
        HttpEntity<WeatherDTO> response = restTemplate.exchange(fullURL, HttpMethod.GET, entity, WeatherDTO.class);
        WeatherDTO body = response.getBody();
        body.setTemp(forObject.get("temp").asDouble());
        return body;
    }

    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<>("parameters", headers);
    }

//    public void getTemp(double lat, double lon) {
//        RestTemplate restTemplate = new RestTemplate();
//        String fullURL = WEATHER_URL + "lat=" + lat + "&lon=" + lon;
//
//        HttpEntity<String> entity = createHttpEntity();
//
//        JsonNode forObject = restTemplate.getForObject(fullURL, JsonNode.class).get("main");
//        for (JsonNode jsonNode : forObject) {
//            System.out.println(jsonNode.get("temp") + "°C");
//        }
//
//    }

}
