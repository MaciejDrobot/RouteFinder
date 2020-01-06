package dev.mdrobot.RouteWeatherFinder.services;

import com.fasterxml.jackson.databind.JsonNode;
import dev.mdrobot.RouteWeatherFinder.maps.WeatherDTO;
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

    @Value("${HOME_LAT")
    double lat;
    @Value("${HOME_LON}")
    double lon;
    @Value("${WEATHER_API_KEY}")
    private String WEATHER_API_KEY;
    private final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?&units=metric&APPID="
            + WEATHER_API_KEY + "&";

    public WeatherDTO getWeather(String lat, String lon) {
        RestTemplate restTemplate = new RestTemplate();
        String fullURL = WEATHER_URL + "lat=" + lat + "&lon=" + lon;

        HttpEntity<String> entity = createHttpEntity();

        HttpEntity<WeatherDTO> response = restTemplate.exchange(fullURL, HttpMethod.GET, entity, WeatherDTO.class);
        WeatherDTO body = response.getBody();

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
