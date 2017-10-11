
import Beans.Forecast.ForecastResponse;
import Beans.Weather.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class WeatherController {

    WeatherItem getReportNow(String call, String city) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //Map<String, Object> bean = mapper.readValue(stream, Map.class);
        WeatherResponse weather = mapper.readValue(requester(call, city), WeatherResponse.class);

        double temp = Math.round(toCelsius(weather.getMain().getTemp()));
        String responseCity = weather.getName();
        WeatherItem item = new WeatherItem(temp, responseCity);
        System.out.println(item.toString());
        return item;
    }

    Coordinates getCityCoordinates(String call, String city) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //Map<String, Object> bean = mapper.readValue(stream, Map.class);
        WeatherResponse weather = mapper.readValue(requester(call, city), WeatherResponse.class);

        double longitude = weather.getCoord().getLon();
        double latitude = weather.getCoord().getLat();

        Coordinates item = new Coordinates(longitude, latitude);
        System.out.println(item.toString());
        return item;
    }

    List<WeatherExtremes> getReports(String call, String city) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //Map<String, Object> bean = mapper.readValue(stream, Map.class);
        ForecastResponse forecast = mapper.readValue(requester(call, city), ForecastResponse.class);
        List<WeatherExtremes> extremes = new ArrayList<>();
        for(int i=0; i<3; i++){
            Beans.Forecast.List list = forecast.getList().get(i);
            extremes.add(new WeatherExtremes(
                    new Date(list.getDt()*1000),
                    Math.round(toCelsius(list.getMain().getTempMax())),
                    Math.round(toCelsius(list.getMain().getTempMin())),
                    forecast.getCity().getName()
                    ));
        }

        System.out.println(extremes.toString());
        return extremes;
    }

    InputStream requester(String call, String city) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/"+call+"?q="+city+"&APPID=460facc052c77294321c494c3d1fd4ad");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection.getInputStream();
    }

    double toCelsius(double kelvin){
        return kelvin-273.15;
    }

}
