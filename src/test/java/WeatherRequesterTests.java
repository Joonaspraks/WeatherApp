import Models.WeatherItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class WeatherRequesterTests {
    private WeatherRequester weatherRequester;
    @Before
    public void setup(){
        weatherRequester = new WeatherRequester();
    }
    @Test
    public void requestWeatherResponseReturnsNullIfCityDoesNotExist() throws IOException {
        String city = "FooBar";
        String call = "weather";

        InputStream in = weatherRequester.requestWeatherResponse(call, city);

        Assert.assertSame(null, in);
    }
    @Test
    public void requestWeatherResponseReturnsNullIfCallNotExist() throws IOException {
        String city = "Paris";
        String call = "FooBar";

        InputStream in = weatherRequester.requestWeatherResponse(call, city);

        Assert.assertSame(null, in);
    }
    @Test
    public void requestWeatherResponseReturnsInputStreamIfCityAndCallCorrect() throws IOException {
        String city = "Paris";
        String call = "weather";

        InputStream in = weatherRequester.requestWeatherResponse(call, city);

        Assert.assertThat(in, instanceOf(InputStream.class));
    }
}
