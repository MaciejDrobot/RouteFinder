package pl.debememe.demo.maps;

public class Location {

    public String longitude;
    public String latitude;
    public String start;
    public String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Location() {
    }

    public Location(String start, String end) {
        this.start = start;
        this.end = end;
    }

    //    public Location(String longitude, String latitude) {
//        this.longitude = longitude;
//        this.latitude = latitude;
//    }

//    public String getLongitude() {
//        return longitude;
//    }
//
//    public void setLongitude(String longitude) {
//        this.longitude = longitude;
//    }
//
//    public String getLatitude() {
//        return latitude;
//    }
//
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }
}
