package dev.mdrobot.RouteWeatherFinder.maps;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapsDTO {

    private String start;
    private String destination;
    private String distance;
    private List<LatLong> locations;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<LatLong> getLocations() {
        return locations;
    }

    public void setLocations(List<LatLong> locations) {
        this.locations = locations;
    }
}
