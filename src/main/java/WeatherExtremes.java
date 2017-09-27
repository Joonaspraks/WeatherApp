import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
class WeatherExtremes {
    private Date date;
    private List<WeatherItem> weatherItems;

    WeatherExtremes(Date date, List<WeatherItem> weatherItems){
        this.date=date;
        this.weatherItems=weatherItems;
    }
}
