package pl.debememe.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "pl.debememe.demo.strony"})
public class RouteFinderApplication {


    public static void main(String[] args) {
        SpringApplication.run(RouteFinderApplication.class, args);
    }

}
