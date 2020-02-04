package dev.mdrobot.RouteWeatherFinder.services;

import com.fasterxml.jackson.databind.JsonNode;
import dev.mdrobot.RouteWeatherFinder.dto.LocationWeather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class WeatherProvider {

    @Value("${HOME_LAT}")
    double lat;
    @Value("${HOME_LON}")
    double lon;
    @Value("${WEATHER_API_KEY}")
    String WEATHER_API_KEY;

    private String weatherURL() {
        return "http://api.openweathermap.org/data/2.5/weather?&units=metric&APPID="
                + WEATHER_API_KEY + "&";
    }

    public String testURL(String lat, String lon){
        String fullURL = weatherURL();
        return fullURL;
    }

    public LocationWeather getWeather(String lat, String lon) {

        String fullURL = weatherURL() + "lat=" + lat + "&lon=" + lon;
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = createHttpEntity();

        HttpEntity<LocationWeather> response = restTemplate.exchange(fullURL, HttpMethod.GET, entity, LocationWeather.class);
        LocationWeather body = response.getBody();

        JsonNode weatherInfo = restTemplate.getForObject(fullURL, JsonNode.class);
        body.setTemp(weatherInfo.get("main").get("temp").asDouble());
        body.setName(weatherInfo.get("name").asText());
        body.setDescription(weatherInfo.get("weather").get(0).get("description").asText());
        body.setIcon(weatherInfo.get("weather").get(0).get("icon").asText() + ".png");
        return body;
    }

    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<>("parameters", headers);
    }

}
