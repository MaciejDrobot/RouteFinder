package dev.mdrobot.RouteWeatherFinder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Service
@Entity
public class RouteStats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String start;
    private String destination;
    private String distance;
    private String minTemp;
    private String maxTemp;
    private String searchDate;


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


}
