package Models;

import lombok.Data;

@Data
public class WeatherItem {
    private double temperature;
    private String city;

    public WeatherItem(double temperature, String city) {
        this.temperature = temperature;
        this.city = city;
    }

    @Override
    public String toString() {
        return "{" +
                "temperature=" + temperature +
                ", city='" + city + '\'' +
                '}';
    }
}
