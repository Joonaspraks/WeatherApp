import Models.WeatherExtremes;
import Models.WeatherItem;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherTests {

    @Test
    public void firstMock() throws IOException {
        WeatherController wControllerMock = Mockito.mock(WeatherController.class);
        //when(wControllerMock.getWeatherExtremes("Paris")).thenReturn(null);
        when(wControllerMock.getCurrentReport("Paris")).thenReturn(null);
        when(wControllerMock.getCityCoordinates("Paris")).thenReturn(null);
        //String answer = wControllerMock.getCombinedWeatherData("Paris");
        /*verify(wControllerMock).getCurrentReport("Paris");
        verify(wControllerMock).getWeatherExtremes("Paris");
        verify(wControllerMock).getCityCoordinates("Paris");*/
        //Assert.assertEquals(answer, null);
    }

    @Test
    public void getReportNowIsRequestCitySameAsInResponse() throws IOException {
        WeatherController controller = new WeatherController(new WeatherRequester());
        String city = "Paris";
        String call = "weather";

        WeatherItem item = controller.getCurrentReport(city);

        Assert.assertSame(item.getCity(), city);
    }

    @Test
    public void getReportNowIsRequestCityEqualToResponseCity() throws IOException {
        WeatherController controller = new WeatherController(new WeatherRequester());
        String city = "Paris";
        String call = "weather";

        WeatherItem item = controller.getCurrentReport(city);

        Assert.assertEquals(item.getCity(), city);
    }

    @Test
    public void getReportsIsRequestCityEqualToResponseCities() throws IOException {
        WeatherController controller = new WeatherController(new WeatherRequester());
        String city = "Paris";
        String call = "forecast";
        List<String> actualCities = Arrays.asList("Paris", "Paris", "Paris");

        //List<WeatherExtremes> items = weather.getWeatherExtremes(city);
        //List<List<Models.WeatherItem>> weatherItems = items.stream().map(Models.WeatherExtremes::getWeatherItems).collect(Collectors.toList());

        List<String> cities = Collections.emptyList();
            /*for(WeatherExtremes item : items){
                cities.add(item.getCity());
            }*/


        Assert.assertEquals(cities, actualCities);
    }

    @Test
    public void getReportsIsAmountOfResponseObjectsN() throws IOException {
        WeatherController controller = new WeatherController(new WeatherRequester());
        String city = "Paris";
        String call = "forecast";
        int days = 3;

        //List<WeatherExtremes> items = weather.getWeatherExtremes(city);

        //Assert.assertEquals(items.size(), days);
    }

    @Test
    public void getReportsIsAmountOfWeatherItemsInResponseObjects2() throws IOException {
        WeatherController controller = new WeatherController(new WeatherRequester());
        String city = "Paris";
        String call = "forecast";

        //List<WeatherExtremes> items = weather.getWeatherExtremes(city);

        //Assert.assertEquals(items.size(), 4);
    }

    @Test
    public void getReportNowIsResponseTemperatureSameAsDouble() throws IOException {
        WeatherController controller = new WeatherController(new WeatherRequester());
        String city = "Paris";
        String call = "weather";
        double dType = 0;

        WeatherItem item = controller.getCurrentReport(city);

        Assert.assertSame(item.getTemperature(), dType);
    }
}
