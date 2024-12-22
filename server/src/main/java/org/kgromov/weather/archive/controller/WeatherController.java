package org.kgromov.weather.archive.controller;

import org.kgromov.weather.archive.model.*;
import org.kgromov.weather.archive.repository.DailyTemperatureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    private static final Logger logger =LoggerFactory.getLogger(WeatherController.class);
    private final DailyTemperatureRepository temperatureRepository;

    public WeatherController(DailyTemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @GetMapping("/range")
    public YearsRange getYearsRange() {
        return temperatureRepository.getYearsRange();
    }

    @GetMapping("/average")
    public List<YearAverageTemperature> getYearAverageTemperature() {
        return temperatureRepository.getYearAverageTemperature(Sort.by("year").ascending());
    }

    @GetMapping("/min")
    public Optional<DayTemperature> getMinTemperature() {
        return temperatureRepository.getMinTemperature();
    }

    @GetMapping("/max")
    public Optional<DayTemperature> getMaxTemperature() {
        return temperatureRepository.getMaxTemperature();
    }

    @GetMapping("/seasonsInYear")
    public List<YearBySeasonTemperature> getYearsBySeasonsTemperature() {
        PageRequest pageable = PageRequest.ofSize(100).withSort(Sort.by("year").ascending());
        return temperatureRepository.getYearsBySeasonsTemperature(pageable);
    }

    @GetMapping("/seasons")
    public List<SeasonTemperature> getSeasonsTemperature() {
        PageRequest pageable = PageRequest.ofSize(100).withSort(Sort.by("year").ascending());
        return temperatureRepository.getSeasonsTemperature(pageable);
    }

    @GetMapping("/current")
    public Optional<DailyTemperature> getCurrentTemperature() {
        return temperatureRepository.findByDate(LocalDate.now());
    }

    @GetMapping("/single/{date}")
    public Optional<DailyTemperature> getTemperatureForDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        return temperatureRepository.findByDate(localDate);
    }

    @GetMapping("/{date}")
    public List<DailyTemperature> getTemperatureForDateInRange(@PathVariable String date,
                                                               @RequestParam(required = false) Integer years) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        String monthDay = localDate.format(DateTimeFormatter.ofPattern("-MM-dd"));
        int yearsRange = ofNullable(years).orElse(Integer.MAX_VALUE);
        logger.info("date = {}, monthDay = {}, years = {}", date, monthDay, years);
        PageRequest pageable = PageRequest.ofSize(yearsRange).withSort(Sort.by("date").ascending());
        return temperatureRepository.findByDateInRangeAggregation(monthDay, pageable);
    }
}
