import Models.WeatherExtremes;
import Models.WeatherItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.when;

public class FileManagerTests {
    private FileManager fileManager;
    @Before
    public void setup(){
        fileManager = new FileManager();
    }
    @Test
    public void checkFileReturnsTrueWhenFileExists() throws IOException {
        File file = new File("foo.json");
        file.createNewFile();
        boolean result = fileManager.checkFile("foo", ".json");
        Assert.assertEquals(true, result);
        file.delete();
    }
    @Test
    public void checkFileReturnsFalseWhenFileNotExist() throws IOException {
        new File("foo.json").delete();
        boolean result = fileManager.checkFile("foo", ".json");

        Assert.assertEquals(false, result);
    }
    @Test
    public void checkFileReturnsFalseWhenExtensionNotMatch() throws IOException {
        File file = new File("foo.json");
        file.createNewFile();
        boolean result = fileManager.checkFile("foo", ".txt");
        file.delete();
        Assert.assertEquals(false, result);
    }
}
