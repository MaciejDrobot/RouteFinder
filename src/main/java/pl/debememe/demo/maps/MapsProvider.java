package pl.debememe.demo.maps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MapsProvider {

    @Value("${API_KEY}")
    private String API_KEY;

//    public MapsProvider(@Autowired String API_KEY) {
//        API_KEY = API_KEY;
//    }

    private String Start;
    private String Finish;

//    private final String MAP_URL = "https://maps.googleapis.com/maps/api/js?" +
//            "key=AIzaSyBV6hjFNhlm5eCfM3aO-jWz2LapYwza3rM&callback=initMap";

    private final String MAPS_API_URL = "https://maps.googleapis.com/maps/api/directions/json?"
            + "origin=" + Start
            + "&destination=" + Finish
            + "&key=AIzaSyBV6hjFNhlm5eCfM3aO-jWz2LapYwza3rM";

    public String query (String start, String finish){
        return "https://maps.googleapis.com/maps/api/directions/json?"
                + "origin=" + start
                + "&destination=" + finish
                + "&key=AIzaSyBV6hjFNhlm5eCfM3aO-jWz2LapYwza3rM";
    }

    public List<Location> getLocations(String searchQuery){
        RestTemplate restTemplate = new RestTemplate();
        LocationsResponse forObject = restTemplate
                .getForObject(query(Start, Finish), LocationsResponse.class);

        List<Location> search = forObject.getSearch();

        return search;
    }

}
