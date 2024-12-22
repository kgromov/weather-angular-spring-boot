package org.kgromov.weather.archive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayTemperature {
    private String date;
    private Double temperature;
}
