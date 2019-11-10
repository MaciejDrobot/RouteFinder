package pl.debememe.demo.maps;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LatLong {

    @JsonProperty("lng")
    public String longitude;

    @JsonProperty("lat")
    public String latitude;


    public LatLong() {
    }

    public LatLong(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
