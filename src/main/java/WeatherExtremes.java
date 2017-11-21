import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
class WeatherExtremes {
    private Date date;
    private double maxTemp;
    private double minTemp;
    private String city;

    WeatherExtremes(Date date, double maxTemp, double minTemp, String city){
        this.date=date;
        this.maxTemp=maxTemp;
        this.minTemp=minTemp;
        this.city=city;
    }

    @Override
    public String toString() {
        return "{" +
                "date=" + date +
                ", maxTemp=" + maxTemp +
                ", minTemp=" + minTemp +
                ", city='" + city + '\'' +
                "}\n";
    }
}
