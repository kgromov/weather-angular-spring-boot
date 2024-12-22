package org.kgromov.weather.archive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "weather_archive")
public class DailyTemperature {
    @Id
    private String id;
    private String date;
    private Double morningTemperature;
    private Double afternoonTemperature;
    private Double eveningTemperature;
    private Double nightTemperature;

    public DailyTemperature() {
    }

    public String getId() {
        return this.id;
    }

    public String getDate() {
        return this.date;
    }

    public Double getMorningTemperature() {
        return this.morningTemperature;
    }

    public Double getAfternoonTemperature() {
        return this.afternoonTemperature;
    }

    public Double getEveningTemperature() {
        return this.eveningTemperature;
    }

    public Double getNightTemperature() {
        return this.nightTemperature;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMorningTemperature(Double morningTemperature) {
        this.morningTemperature = morningTemperature;
    }

    public void setAfternoonTemperature(Double afternoonTemperature) {
        this.afternoonTemperature = afternoonTemperature;
    }

    public void setEveningTemperature(Double eveningTemperature) {
        this.eveningTemperature = eveningTemperature;
    }

    public void setNightTemperature(Double nightTemperature) {
        this.nightTemperature = nightTemperature;
    }

    public String toString() {
        return "DailyTemperature(id=" + this.getId() + ", date=" + this.getDate() + ", morningTemperature=" + this.getMorningTemperature() + ", afternoonTemperature=" + this.getAfternoonTemperature() + ", eveningTemperature=" + this.getEveningTemperature() + ", nightTemperature=" + this.getNightTemperature() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof DailyTemperature)) return false;
        final DailyTemperature other = (DailyTemperature) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof DailyTemperature;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
