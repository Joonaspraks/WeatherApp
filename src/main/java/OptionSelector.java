import java.io.BufferedReader;
import java.io.Console;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class OptionSelector {
    boolean offerOptions() throws IOException {

        System.out.println("Choose a method of your liking:");
        System.out.println("'1' to read the target city from console");
        System.out.println("'2' to read the target city from the text file");
        System.out.println("'3' to exit");

        Scanner sc = new Scanner(System.in);
        String option;

        String result = "";
        OptionCaller optionCaller = new OptionCaller(new FileManager());

        while (Objects.equals(result, "")) {
            option = sc.next();
            if (Objects.equals(option, "1")) {
                result = optionCaller.getDataByConsole(sc);

            } else if (Objects.equals(option, "2")) {
                result = optionCaller.getDataByFile();
            }
            else if(Objects.equals(option,"3")){
                return true;
            }
            else {
                System.out.println("Not a suggested option, please select again");
            }
        }
        System.out.println(result);
        return false;

    }
}