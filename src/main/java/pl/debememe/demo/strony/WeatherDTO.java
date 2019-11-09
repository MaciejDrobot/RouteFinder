package pl.debememe.demo.strony;

public class WeatherDTO {

    private String name;
    private double lon;
    private double lat;
    private double temp;
    private String description;

    public WeatherDTO() {
    }

    public WeatherDTO(String name, double lon, double lat, double temp, String description) {
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.temp = temp;
        this.description = description;
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
}