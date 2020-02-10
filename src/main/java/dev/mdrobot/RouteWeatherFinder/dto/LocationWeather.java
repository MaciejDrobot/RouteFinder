package dev.mdrobot.RouteWeatherFinder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Component
public class LocationWeather {

    public String name;
    public double lon;
    public double lat;
    public String temp;
    public String description;
    public List[] end_location;
    public String icon;

    public LocationWeather(String name, String temp){
        this.name = name;
        this.temp = temp;
    }
}
