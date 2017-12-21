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
    private String extension = ".txt";
    WeatherController controller = new WeatherController();

    String getDataByConsole(Scanner sc) throws IOException {
        System.out.println("Enter a city of your choosing");
        cities.add(sc.next());
        result = controller.getCombinedWeatherData(cities.get(0));

        response=(result!=null?
                fileManager.writeToFile(result, extension, cities.get(0))?
                        "File "+cities.get(0)+".txt created":
                        "File creation failed":
                "No results found");

        return response;
    }
    String getDataByFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/requestedCities.txt"))) {
            String line = br.readLine();
            while (line != null) {
                cities.add(line);
                line = br.readLine();
            }
        }
        for(String city:cities) {
            result = controller.getCombinedWeatherData(city);
            if (result != null) {
                response+=(fileManager.writeToFile(result, extension, city)?
                        "File "+city+".txt created"
                        :"File " +city+ "creation failed")+"\n";
            }
            else response+="File " +city+ " creation failed"+"\n";
        }
        return response;
    }
}
