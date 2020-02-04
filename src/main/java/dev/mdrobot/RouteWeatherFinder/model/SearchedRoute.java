package dev.mdrobot.RouteWeatherFinder.model;

import dev.mdrobot.RouteWeatherFinder.dto.LatLong;
import dev.mdrobot.RouteWeatherFinder.dto.LocationWeather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Service
@Entity
public class SearchedRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String start;
    private String destination;
    private String distance;
    private String minTemp;
    private String maxTemp;
    private String searchDate;

    @Transient
    private List<LatLong> listOfLocations;

    @Transient
    private List<LocationWeather> locationsWeather;


}
