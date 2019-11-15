package pl.debememe.demo.maps;

import javax.persistence.Id;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
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


    RouteStats getRouteStats(MapsDTO route, List<LocationWeather> list) {
        RouteStats routeStats = new RouteStats();
        routeStats.setStart(route.getStart());
        routeStats.setDestination(route.getDestination());
        routeStats.setDistance(route.getDistance());
        routeStats.setMinTemp(getMinTemp(list));
        routeStats.setMaxTemp(getMaxTemp(list));
        return routeStats;
    }

    String getMinTemp(List<LocationWeather> list) {
        Comparator<LocationWeather> compareByTemp =
                (LocationWeather l1, LocationWeather l2) -> l1.getTemp().compareTo(l2.getTemp());
        Collections.sort(list, compareByTemp);
        return list.get(0).getTemp() + " °C (" + list.get(0).getLocation() + ")";
    }

    String getMaxTemp(List<LocationWeather> list) {
        Comparator<LocationWeather> compareByTemp =
                (LocationWeather l1, LocationWeather l2) -> l1.getTemp().compareTo(l2.getTemp());
        Collections.sort(list, compareByTemp);
        int i = list.size() - 1;
        return list.get(i).getTemp() + " °C (" + list.get(i).getLocation() + ")";
    }


    public static class OrderByTemp implements Comparator<LocationWeather> {
        @Override
        public int compare(LocationWeather l1, LocationWeather l2) {
            return l1.getTemp().compareTo(l2.getTemp());
        }
    }

        public String getStart () {
            return start;
        }

        public void setStart (String start){
            this.start = start;
        }

        public String getDestination () {
            return destination;
        }

        public void setDestination (String destination){
            this.destination = destination;
        }

        public String getDistance () {
            return distance;
        }

        public void setDistance (String distance){
            this.distance = distance;
        }

        public String getMinTemp () {
            return minTemp;
        }

        public void setMinTemp (String minTemp){
            this.minTemp = minTemp;
        }

        public String getMaxTemp () {
            return maxTemp;
        }

        public void setMaxTemp (String maxTemp){
            this.maxTemp = maxTemp;
        }


    }
