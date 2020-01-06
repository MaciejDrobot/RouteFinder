package dev.mdrobot.RouteWeatherFinder.maps;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "route")
public class RouteStats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_location")
    private String start;
    @Column(name = "destination_location")
    private String destination;
    @Column(name = "distance")
    private String distance;
    @Column(name = "min_temp")
    private String minTemp;
    @Column(name = "max_temp")
    private String maxTemp;


    public static RouteStats getRouteStats(MapsDTO route, List<LocationWeather> list) {
        RouteStats routeStats = new RouteStats();
        routeStats.setStart(route.getStart());
        routeStats.setDestination(route.getDestination());
        routeStats.setDistance(route.getDistance());
        routeStats.setMinTemp(getMinTemp(list));
        routeStats.setMaxTemp(getMaxTemp(list));
        return routeStats;
    }

    public static String getMinTemp(List<LocationWeather> list) {
        List<Double> collect = list.stream().map(element -> Double.valueOf(element.getTemp())).collect(Collectors.toList());
        return String.valueOf(collect.stream().min(Double::compare).get()) + " °C";
    }

    public static String getMaxTemp(List<LocationWeather> list) {
        List<Double> collect = list.stream().map(element -> Double.valueOf(element.getTemp())).collect(Collectors.toList());
        return String.valueOf(collect.stream().max(Double::compare).get()) + " °C";
    }


    public static class OrderByTemp implements Comparator<LocationWeather> {
        @Override
        public int compare(LocationWeather l1, LocationWeather l2) {
            return l1.getTemp().compareTo(l2.getTemp());
        }
    }


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

    public String getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(String minTemp) {
        this.minTemp = minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(String maxTemp) {
        this.maxTemp = maxTemp;
    }


}
