import Interfaces.IInputReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OptionSelector {
    public String offerOptions() throws IOException {

        System.out.println("Choose a method of your liking:");
        System.out.println("'1' to read the target city from console");
        System.out.println("'2' to read the target city from the text file");

        Scanner sc = new Scanner(System.in);
        int i = 0;
        IInputReader reader;
        String result = "";

        WeatherController controller = new WeatherController();
        String city = "";
        List<WeatherExtremes> results = null;
        while (i != 1 && i != 2) {
            if (sc.hasNextInt()) {
                i = sc.nextInt();
                if (i == 1) {
                    while (results == null) {
                        System.out.println("Enter a city of your choosing");
                        city = sc.next();
                        results = controller.getReports("forecast", city);
                    }
                } else if (i == 2) {
                    BufferedReader br = new BufferedReader(new FileReader("src/main/file.txt"));
                    try {
                        //StringBuilder sb = new StringBuilder();
                        city = br.readLine();

                        /*while (line != null) {
                            sb.append(line);
                            sb.append(System.lineSeparator());
                            line = br.readLine();
                        }
                        city = sb.toString();*/
                    } finally {
                        br.close();
                    }
                    results = controller.getReports("forecast", city);
                    List<String> lines = Arrays.asList(results.toString());
                    Path file = Paths.get("forecast.txt");
                    Files.write(file, lines, Charset.forName("UTF-8"));
                } else {
                    System.out.println("Not a suggested number, please select again!");
                }
            } else {
                System.out.println("Not a number, please select again!");
                sc.next();
            }

        }
        return result;
    }

}