package dev.mdrobot.RouteWeatherFinder.maps;

import dev.mdrobot.RouteWeatherFinder.model.LocationWeather;
import dev.mdrobot.RouteWeatherFinder.model.RouteStats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Service
public class Session {

    private String start;
    private String end;
    private List<LocationWeather> locationsWeatherList;
    private RouteStats stats;
}
