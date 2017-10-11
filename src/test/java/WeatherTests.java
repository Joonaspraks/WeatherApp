import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherTests {

    @Test
    public void getReportNowIsRequestCitySameAsInResponse() throws IOException {
        WeatherController weather = new WeatherController();
        String city = "Paris";
        String call = "weather";

        WeatherItem item = weather.getReportNow(call, city);

        Assert.assertSame(item.getCity(), city);
    }

    @Test
    public void getReportNowIsRequestCityEqualToResponseCity() throws IOException {
        WeatherController weather = new WeatherController();
        String city = "Paris";
        String call = "weather";

        WeatherItem item = weather.getReportNow(call, city);

        Assert.assertEquals(item.getCity(), city);
    }

    @Test
    public void getReportsIsRequestCityEqualToResponseCities() throws IOException {
        WeatherController weather = new WeatherController();
        String city = "Paris";
        String call = "forecast";
        List<String> actualCities = Arrays.asList("Paris", "Paris", "Paris");

        List<WeatherExtremes> items = weather.getReports(call, city);
        //List<List<WeatherItem>> weatherItems = items.stream().map(WeatherExtremes::getWeatherItems).collect(Collectors.toList());

        List<String> cities = Collections.emptyList();
            for(WeatherExtremes item : items){
                cities.add(item.getCity());
            }


        Assert.assertEquals(cities, actualCities);
    }

    @Test
    public void getReportsIsAmountOfResponseObjectsN() throws IOException {
        WeatherController weather = new WeatherController();
        String city = "Paris";
        String call = "forecast";
        int days = 3;

        List<WeatherExtremes> items = weather.getReports(call, city);

        Assert.assertEquals(items.size(), days);
    }

    @Test
    public void getReportsIsAmountOfWeatherItemsInResponseObjects2() throws IOException {
        WeatherController weather = new WeatherController();
        String city = "Paris";
        String call = "forecast";

        List<WeatherExtremes> items = weather.getReports(call, city);

        Assert.assertEquals(items.size(), 4);
    }

    @Test
    public void getReportNowIsResponseTemperatureSameAsDouble() throws IOException {
        WeatherController weather = new WeatherController();
        String city = "Paris";
        String call = "weather";
        double dType = 0;

        WeatherItem item = weather.getReportNow(call, city);

        Assert.assertSame(item.getTemperature(), dType);
    }
}
