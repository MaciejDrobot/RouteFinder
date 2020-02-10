package dev.mdrobot.RouteWeatherFinder.model;

import dev.mdrobot.RouteWeatherFinder.dto.LocationWeather;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class Stats {

    public String getMinTemp(List<LocationWeather> list) {
        Comparator<LocationWeather> comparator = (l1, l2) -> l1.getTemp().compareTo(l2.getTemp());
        List<LocationWeather> sorted = list.stream().sorted(comparator).collect(Collectors.toList());
        return sorted.get(0).getTemp() + " °C (" + sorted.get(0).getName() + ")";
    }

    public String getMaxTemp(List<LocationWeather> list) {
        Comparator<LocationWeather> comparator = (l1, l2) -> l1.getTemp().compareTo(l2.getTemp());
        List<LocationWeather> sorted = list.stream().sorted(comparator.reversed()).collect(Collectors.toList());
        return sorted.get(0).getTemp() + " °C (" + sorted.get(0).getName() + ")";
    }

}
