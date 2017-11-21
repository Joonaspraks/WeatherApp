
import Beans.Forecast.ForecastResponse;
import Beans.Weather.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class WeatherController {

    String combinedWeatherData(String city){
     return null;
    }

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
        InputStream inputStr = requester(call, city);
        if (inputStr != null) {
            ForecastResponse forecast = mapper.readValue(inputStr, ForecastResponse.class);
            List<WeatherExtremes> extremes = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Beans.Forecast.List list = forecast.getList().get(i);
                extremes.add(new WeatherExtremes(
                        new Date(list.getDt() * 1000),
                        Math.round(toCelsius(list.getMain().getTempMax())),
                        Math.round(toCelsius(list.getMain().getTempMin())),
                        forecast.getCity().getName()
                ));
            }

            System.out.println(extremes.toString());
            return extremes;
        }
        else return null;
    }

    InputStream requester(String call, String city) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/"+call+"?q="+city+"&APPID=460facc052c77294321c494c3d1fd4ad");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        try{
            return connection.getInputStream();
        }
        catch(FileNotFoundException error){
            System.out.println("The inserted 'city' does not exist");
            return null;
        }

    }

    double toCelsius(double kelvin){
        return kelvin-273.15;
    }

}
