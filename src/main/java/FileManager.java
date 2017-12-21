import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileManager {
    boolean writeToFile(String input, String extension, String fileName) throws IOException {
        List<String> lines = Collections.singletonList(input);
        Path file = Paths.get(fileName+extension);
        Files.write(file, lines, Charset.forName("UTF-8"));
        return checkFile(fileName, extension) ;
    }

    boolean checkFile(String path, String extension){
        File file = new File(path+extension);
        return file.exists();
    }
}
