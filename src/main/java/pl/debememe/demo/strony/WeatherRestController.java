package pl.debememe.demo.strony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class WeatherRestController {

    private WeatherProvider weatherProvider;

/*    double lat = 53.02;
    double lon = 20.83;*/

//    public WeatherRestController(@Autowired WeatherProvider weatherProvider) {
//        this.weatherProvider = weatherProvider;
//    }

//    @GetMapping
//    @RequestMapping("/getWeatherForDefaultCoordinates")
//    public String provideWeather(Model model) {
//         double lat = 53.02; //tutaj tak na prawde powinny wskakiwac wspolrzedne z tablicy od google
//         double lon = 20.83; //tutaj tez
//         //return weatherProvider.getLatAndLon(lat, lon);
//         //model.addAttribute("temperature", weatherProvider.getLatAndLon(lat, lon).getTemp());
//         //model.addAttribute("temperature", "5");
//         return "index";
//    }
}
