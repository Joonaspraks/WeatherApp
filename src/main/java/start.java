import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class start {
    public static void main(String[] args) throws IOException {

        OptionSelector optionSelector = new OptionSelector();
        boolean exit = false;

        while(!exit) {
            exit = optionSelector.offerOptions();
        }

        System.out.println("System has been exited");
    }
}
