import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class OptionCallerTests {
    private FileManager fileManagerMock;
    @Before
    public void setup(){

    }
    @Test
    public void getDataByConsole() throws IOException {
        fileManagerMock = Mockito.mock(FileManager.class);
        when(fileManagerMock.writeToFile(anyString(), eq(".txt"), eq("Bratislava"))).thenReturn(true);
        //when(fileManagerMock.checkFile("Bratislava", ".txt")).thenReturn(true);
        OptionCaller optionCaller = new OptionCaller(fileManagerMock);
        ByteArrayInputStream in = new ByteArrayInputStream("Bratislava".getBytes());
        String result = optionCaller.getDataByConsole(new Scanner(in));
        Assert.assertEquals("File Bratislava.txt created", result);
    }
    @Test
    public void getDataByFileCreatesCorrectlyLabeledFiles() throws IOException {
        /*new File("London.txt").delete();
        new File("Paris.txt").delete();
        new File("Tokyo.txt").delete();*/
        fileManagerMock = Mockito.mock(FileManager.class);
        List<String> list = new ArrayList<>();
        list.add("London");
        list.add("Paris");
        list.add("Tokyo");
        when(fileManagerMock.readFromFile(anyString())).thenReturn(list);
        when(fileManagerMock.writeToFile(anyString(), anyString(), anyString())).thenReturn(true);

        OptionCaller optionCaller = new OptionCaller(fileManagerMock);
        String result = optionCaller.getDataByFile();

        Assert.assertEquals("File London.txt created\nFile Paris.txt created\nFile Tokyo.txt created\n", result);
        /*new File("London.txt").delete();
        new File("Paris.txt").delete();
        new File("Tokyo.txt").delete();*/
    }

}
