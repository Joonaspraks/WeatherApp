import Interfaces.IInputReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class OptionSelector {
    String offerOptions() throws IOException {

        System.out.println("Choose a method of your liking:");
        System.out.println("'1' to read the target city from console");
        System.out.println("'2' to read the target city from the text file");
        System.out.println("'3' to exit");

        Scanner sc = new Scanner(System.in);
        String option;

        String results = "";

        WeatherController controller = new WeatherController();
        String city;

        while (Objects.equals(results, "")) {
            option = sc.next();
            if (Objects.equals(option, "1")) {
                while (Objects.equals(results, "")) {
                    System.out.println("Enter a city of your choosing");
                    city = sc.next();
                    results = controller.getCombinedWeatherData(city);
                }
            } else if (Objects.equals(option, "2")) {
                try (BufferedReader br = new BufferedReader(new FileReader("src/main/file.txt"))) {
                    //StringBuilder sb = new StringBuilder();
                    city = br.readLine();

                    /*while (line != null) {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    }
                    city = sb.toString();*/
                }
                results = controller.getCombinedWeatherData(city);
                List<String> lines = Collections.singletonList(results);
                Path file = Paths.get("forecast.txt");
                Files.write(file, lines, Charset.forName("UTF-8"));
            }
            else if(Objects.equals(option,"3")){
                return "exit";
            }
            else {
                System.out.println("Not a suggested option, please select again!");
            }
        }
        return results;
    }

}