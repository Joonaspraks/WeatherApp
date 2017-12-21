
import Beans.Forecast.ForecastResponse;
import Beans.Weather.WeatherResponse;
import Models.Coordinates;
import Models.WeatherExtremes;
import Models.WeatherItem;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class WeatherController {
    //Use to export all data to one file
    /*String getEachCombinedWeatherData(List<String> cities) throws IOException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (String city:cities){
            arrayBuilder.add(getCombinedWeatherData(city));
        }
        return Json.createObjectBuilder()
                .add("WeatherCombinedDataPackage", arrayBuilder)
                .build().toString();
    }*/
    WeatherRequester weatherRequester = new WeatherRequester();
    Utility utility = new Utility();
    String getCombinedWeatherData(String city) throws IOException {

        WeatherItem wItem = getCurrentReport(city);
        int amountOfExtremes = 3;
        List<WeatherExtremes> wExtremes = wItem!=null? getWeatherExtremes(city, amountOfExtremes):null;
        Coordinates coordinates = wExtremes!=null?getCityCoordinates(city):null;

        if(wItem!=null&&wExtremes!=null&&coordinates!=null) {

            JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("WeatherItem", Json.createObjectBuilder()
                    .add("temperature", wItem.getTemperature())
                    .add("city", wItem.getCity()));

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (WeatherExtremes we:wExtremes){
                arrayBuilder
                    .add(Json.createObjectBuilder()
                        .add("city", we.getCity())
                        .add("date", we.getDate().getTime()/1000)
                        .add("min", we.getMinTemp())
                        .add("max", we.getMaxTemp()));
            }

            return objectBuilder
                .add("WeatherExtremes", arrayBuilder)
                .add("Coordinates", Json.createObjectBuilder()
                    .add("lat", coordinates.getLatitude())
                    .add("lon", coordinates.getLongitude()))
                    .build()
                    .toString();
        }
        return null;
    }

    WeatherItem getCurrentReport(String city) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String call = "weather";
        //Map<String, Object> bean = mapper.readValue(stream, Map.class);
        InputStream input = weatherRequester.requestWeatherResponse(call, city);
        if(input!=null) {
            WeatherResponse weather = mapper.readValue(input, WeatherResponse.class);

            double temp = Math.round(utility.toCelsius(weather.getMain().getTemp()));
            String responseCity = weather.getName();
            //System.out.println(item.toString());
            return new WeatherItem(temp, responseCity);
        }
        return null;
    }

    Coordinates getCityCoordinates(String city) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String call = "weather";
        //Map<String, Object> bean = mapper.readValue(stream, Map.class);
        InputStream input = weatherRequester.requestWeatherResponse(call, city);
        if (input!=null) {
            WeatherResponse weather = mapper.readValue(input, WeatherResponse.class);

            double longitude = weather.getCoord().getLon();
            double latitude = weather.getCoord().getLat();

            //System.out.println(item.toString());
            return new Coordinates(longitude, latitude);
        }
        return null;
    }

    List<WeatherExtremes> getWeatherExtremes(String city, int amount) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String call = "forecast";
        //Map<String, Object> bean = mapper.readValue(stream, Map.class);
        InputStream inputStr = weatherRequester.requestWeatherResponse(call, city);
        if (inputStr != null) {
            ForecastResponse forecast = mapper.readValue(inputStr, ForecastResponse.class);
            List<WeatherExtremes> extremes = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                Beans.Forecast.List list = forecast.getList().get(i);
                extremes.add(new WeatherExtremes(
                        new Date(list.getDt() * 1000),
                        Math.round(utility.toCelsius(list.getMain().getTempMax())),
                        Math.round(utility.toCelsius(list.getMain().getTempMin())),
                        forecast.getCity().getName()
                ));
            }

            //System.out.println(extremes.toString());
            return extremes;
        }
        else return null;
    }


}
