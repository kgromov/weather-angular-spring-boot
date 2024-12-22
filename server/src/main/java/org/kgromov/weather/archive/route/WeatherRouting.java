package org.kgromov.weather.archive.route;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.queryParam;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class WeatherRouting {
    private final WeatherRouteHandler weatherRouteHandler;

    @Bean
    public RouterFunction<ServerResponse> weatherRoutes() {
        return route()
                .GET("/api/weather/range", accept(APPLICATION_JSON), weatherRouteHandler::getYearsRange)
                .GET("/api/weather/average", accept(APPLICATION_JSON), weatherRouteHandler::getYearAverageTemperature)
                .GET("/api/weather/min", accept(APPLICATION_JSON), weatherRouteHandler::getMinTemperature)
                .GET("/api/weather/max", accept(APPLICATION_JSON), weatherRouteHandler::getMaxTemperature)
                .GET("/api/weather/seasonsInYear", accept(APPLICATION_JSON), weatherRouteHandler::getYearsBySeasonsTemperature)
                .GET("/api/weather/seasons", accept(APPLICATION_JSON), weatherRouteHandler::getSeasonsTemperature)

                .GET("/api/weather/current", accept(APPLICATION_JSON), weatherRouteHandler::getCurrentTemperature)
                .GET("/api/weather/single/{date}", accept(APPLICATION_JSON), weatherRouteHandler::getTemperatureForDate)
                .GET("/api/weather/{date}", accept(APPLICATION_JSON), weatherRouteHandler::getTemperatureForDateInRange)
                .GET("/api/weather/{date}", RequestPredicates.all()
                                .and(queryParam("years", v -> true))
                                .and(accept(APPLICATION_JSON)),
                        weatherRouteHandler::getTemperatureForDateInRange
                )
                .build();
    }
}
