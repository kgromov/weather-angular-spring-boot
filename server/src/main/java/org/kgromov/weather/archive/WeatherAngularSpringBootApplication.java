package org.kgromov.weather.archive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class WeatherAngularSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherAngularSpringBootApplication.class, args);
    }

}
