package dev.mdrobot.RouteWeatherFinder.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationWeather {

    private String location;
    private String temp;
    private String description;
    private String icon;

}