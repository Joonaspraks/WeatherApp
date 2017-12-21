import Models.Coordinates;
import Models.WeatherExtremes;
import Models.WeatherItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

public class WeatherControllerTests {
    private WeatherRequester weatherRequester;
    @Before
    public void setup(){

    }

    @Test
    public void getCurrentReportReturnsNullWhenInputIsNull() throws IOException {
        weatherRequester = Mockito.mock(WeatherRequester.class);
        when(weatherRequester.requestWeatherResponse(anyString(), anyString())).thenReturn(null);

        WeatherController weatherController = new WeatherController(weatherRequester);
        WeatherItem weatherItem = weatherController.getCurrentReport("Foo");

        Assert.assertEquals(null, weatherItem);
    }
    @Test
    public void getCurrentReportMapsCorrectlyToBean() throws IOException {
        weatherRequester = Mockito.mock(WeatherRequester.class);
        when(weatherRequester.requestWeatherResponse(anyString(), anyString())).thenReturn(
                new ByteArrayInputStream(new Scanner(new File("src/test/WeatherResponse.json")).useDelimiter("\\Z")
                        .next().getBytes(StandardCharsets.UTF_8.name())));

        WeatherController weatherController = new WeatherController(weatherRequester);
        WeatherItem weatherItem = weatherController.getCurrentReport("Foo");

        Assert.assertThat(weatherItem.getCity(), instanceOf(String.class));
    }
    @Test
    public void getWeatherExtremesReturnsNullWhenInputIsNull() throws IOException {
        weatherRequester = Mockito.mock(WeatherRequester.class);
        when(weatherRequester.requestWeatherResponse(anyString(), anyString())).thenReturn(null);

        WeatherController weatherController = new WeatherController(weatherRequester);
        List<WeatherExtremes> weatherExtremesList = weatherController.getWeatherExtremes("Foo", 3);

        Assert.assertEquals(null, weatherExtremesList);
    }
    @Test
    public void getWeatherExtremesMapsCorrectlyToBean() throws IOException {
        weatherRequester = Mockito.mock(WeatherRequester.class);
        when(weatherRequester.requestWeatherResponse(anyString(), anyString())).thenReturn(
                new ByteArrayInputStream(new Scanner(new File("src/test/ForecastResponse.json")).useDelimiter("\\Z")
                        .next().getBytes(StandardCharsets.UTF_8.name())));

        WeatherController weatherController = new WeatherController(weatherRequester);
        List<WeatherExtremes> weatherExtremesList = weatherController.getWeatherExtremes("Foo", 3);

        Assert.assertThat(weatherExtremesList.get(0).getCity(), instanceOf(String.class));
    }
    @Test
    public void getCityCoordinatesReturnsNullWhenInputIsNull() throws IOException {
        weatherRequester = Mockito.mock(WeatherRequester.class);
        when(weatherRequester.requestWeatherResponse(anyString(), anyString())).thenReturn(null);

        WeatherController weatherController = new WeatherController(weatherRequester);
        Coordinates coordinates = weatherController.getCityCoordinates("Foo");

        Assert.assertEquals(null, coordinates);
    }
    @Test
    public void getCityCoordinatesMapsCorrectlyToBean() throws IOException {
        weatherRequester = Mockito.mock(WeatherRequester.class);
        when(weatherRequester.requestWeatherResponse(anyString(), anyString())).thenReturn(
                new ByteArrayInputStream(new Scanner(new File("src/test/WeatherResponse.json")).useDelimiter("\\Z")
                        .next().getBytes(StandardCharsets.UTF_8.name())));

        WeatherController weatherController = new WeatherController(weatherRequester);
        Coordinates coordinates = weatherController.getCityCoordinates("Foo");

        double lat = coordinates.getLatitude();
        boolean result = lat >= -90 && lat <= 90;
        Assert.assertEquals(true, result);
    }
}
