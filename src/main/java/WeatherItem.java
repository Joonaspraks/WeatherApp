import lombok.Data;

@Data class WeatherItem {
    private float temperature;
    private String city;

    WeatherItem(float temperature, String city){
        this.temperature = temperature;
        this.city = city;
    }
}
