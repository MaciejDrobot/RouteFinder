package pl.debememe.demo.maps;

import pl.debememe.demo.strony.WeatherProvider;

public class Test {

    public static void main(String[] args) {

        WeatherProvider weatherProvider = new WeatherProvider();
        double lat = 51.50;
        double lon = -0.11;

        System.out.println(weatherProvider.getLatAndLon(lat, lon));
    }






}
