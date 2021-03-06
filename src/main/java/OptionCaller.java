import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class OptionCaller {
    private FileManager fileManager;
    OptionCaller(FileManager fileManager){
        this.fileManager=fileManager;
    }
    private List<String> cities = new ArrayList<>();
    private String result;
    String response ="";
    private String extension = ".json";
    WeatherController controller = new WeatherController(new WeatherRequester());

    String getDataByConsole(Scanner sc) throws IOException {
        System.out.println("Enter a city of your choosing");
        cities.add(sc.next());
        result = controller.getCombinedWeatherData(cities.get(0));

        response=(result!=null?
                fileManager.writeToFile(result, extension, cities.get(0))?
                        "File "+cities.get(0)+".json created":
                        "File creation failed":
                "No results found");

        return response;
    }
    String getDataByFile() throws IOException {

        cities = fileManager.readFromFile("src/main/resources/requestedCities.txt");
        for(String city:cities) {
            result = controller.getCombinedWeatherData(city);
            if (result != null) {
                response+=(fileManager.writeToFile(result, extension, city)?
                        "File "+city+".json created"
                        :"File " +city+ "creation failed")+"\n";
            }
            else response+="No results found for "+city+"\n";
        }
        return response;
    }
}
