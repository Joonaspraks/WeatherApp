import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherRequester {
    public InputStream requestWeatherResponse(String call, String city) throws IOException {

        URL url = new URL("https://api.openweathermap.org/data/2.5/"+call+"?q="+city+"&APPID="+new Scanner(new File("src/main/resources/appId.txt")).useDelimiter("\\Z").next());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        try {
            connection.connect();
            try {
                return connection.getInputStream();
            } catch (FileNotFoundException error) {
                System.out.println("The inserted 'city' " + city + " does not exist");
                return null;
            }
        }catch(java.net.UnknownHostException error){
            System.out.println("The site does not exist or your connection is down. Check your internet connection.");
            return null;
        }
    }
}
