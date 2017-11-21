import lombok.Data;

@Data class WeatherItem {
    private double temperature;
    private String city;

    WeatherItem(double temperature, String city) {
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
