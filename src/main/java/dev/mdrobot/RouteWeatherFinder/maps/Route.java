package dev.mdrobot.RouteWeatherFinder.maps;

public class Route {

    public String start;
    public String end;

    public Route() {
    }

    public Route(String start, String end) {
        this.start = start;
        this.end = end;
    }

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
}
