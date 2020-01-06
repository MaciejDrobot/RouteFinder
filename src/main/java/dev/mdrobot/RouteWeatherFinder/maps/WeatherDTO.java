package dev.mdrobot.RouteWeatherFinder.maps;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {

    private String name;
    private double lon;
    private double lat;
    public double temp;
    private String description;
    private List[] end_location;
    private String icon;

}
