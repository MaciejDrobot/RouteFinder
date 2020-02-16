package dev.mdrobot.RouteWeatherFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RouteWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouteWeatherApplication.class, args);
	}


	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RouteWeatherApplication.class);
	}


}
