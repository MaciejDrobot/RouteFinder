package dev.mdrobot.RouteWeatherFinder.model;

import dev.mdrobot.RouteWeatherFinder.model.LatLong;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Service
public class MapsDTO {

    private String start;
    private String destination;
    private String distance;
    private List<LatLong> locations;

}
