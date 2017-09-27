import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherTests {

    @Test
    public void getReportNowIsRequestCitySameAsInResponse(){
        WeatherController weather = new WeatherController();
        String city = "Paris";

        WeatherItem item = weather.getReportNow(city);

        Assert.assertSame(item.getCity(), city);
    }

    @Test
    public void getReportNowIsRequestCityEqualToResponseCity(){
        WeatherController weather = new WeatherController();
        String city = "Paris";

        WeatherItem item = weather.getReportNow(city);

        Assert.assertEquals(item.getCity(), city);
    }

    @Test
    public void getReportsIsRequestCityEqualToResponseCities(){
        WeatherController weather = new WeatherController();
        String city = "Paris";
        List<String> actualCities = Arrays.asList("Paris", "Paris", "Paris");
        int days = 3;

        List<WeatherExtremes> items = weather.getReports(city, days);
        List<List<WeatherItem>> weatherItems = items.stream().map(WeatherExtremes::getWeatherItems).collect(Collectors.toList());

        List<String> cities = Collections.emptyList();
        for(List<WeatherItem> list : weatherItems ){
            for(WeatherItem item : list){
                cities.add(item.getCity());
            }
        }

        Assert.assertEquals(cities, actualCities);
    }

    @Test
    public void getReportsIsAmountOfResponseObjectsN(){
        WeatherController weather = new WeatherController();
        String city = "Paris";
        int days = 3;

        List<WeatherExtremes> items = weather.getReports(city, days);

        Assert.assertEquals(items.size(), days);
    }

    @Test
    public void getReportsIsAmountOfWeatherItemsInResponseObjects2(){
        WeatherController weather = new WeatherController();
        String city = "Paris";
        int days = 3;

        List<WeatherExtremes> items = weather.getReports(city, days);

        Assert.assertEquals(items.get(0).getWeatherItems().size(), 2);
    }

    @Test
    public void getReportNowIsResponseTemperatureSameAsDouble(){
        WeatherController weather = new WeatherController();
        String city = "Paris";
        double dType = 0;

        WeatherItem item = weather.getReportNow(city);

        Assert.assertSame(item.getTemperature(), dType);
    }
}
