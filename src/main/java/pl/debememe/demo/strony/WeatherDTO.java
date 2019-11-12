package pl.debememe.demo.strony;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherDTO {

    @JsonProperty("name")
    private String name;
    private double lon;
    private double lat;
    public double temp;
    @JsonProperty("description")
    private String description;
    @JsonProperty("EndLocation")
    private List[] end_location;
    private String icon;


    public WeatherDTO() {
    }

    public WeatherDTO(String name, double lon, double lat, double temp, String description, List[] end_location, String icon) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.temp = temp;
        this.description = description;
        this.end_location= end_location;
        this.icon = icon;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List[] getEnd_location() {
        return end_location;
    }

    public void setEnd_location(List[] end_location) {
        this.end_location = end_location;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
