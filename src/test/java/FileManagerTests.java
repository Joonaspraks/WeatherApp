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
    public void checkFileReturnsTrueWhenFileExists(){
        File fileMock = Mockito.mock(File.class);
        when(fileMock.exists()).thenReturn(true);
    }
    @Test
    public void checkFileReturnsFalseWhenFileNotExist() throws IOException {
        new File("foo.txt").delete();
        boolean result = fileManager.checkFile("foo", ".txt");

        Assert.assertEquals(result, false);
    }
    @Test
    public void checkFileReturnsFalseWhenExtensionNotMatch() throws IOException {
        File file = new File("foo.txt");
        file.createNewFile();
        boolean result = fileManager.checkFile("foo", ".json");
        file.delete();
        Assert.assertEquals(result, false);
    }
}
