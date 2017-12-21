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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class OptionCallerTests {
    private FileManager fileManagerMock;
    @Before
    public void setup(){

    }
    @Test
    public void getDataByConsoleReturnsCorrectStatement() throws IOException {
        fileManagerMock = Mockito.mock(FileManager.class);
        when(fileManagerMock.writeToFile(anyString(), eq(".json"), eq("Bratislava"))).thenReturn(true);

        OptionCaller optionCaller = new OptionCaller(fileManagerMock);
        ByteArrayInputStream in = new ByteArrayInputStream("Bratislava".getBytes());
        String result = optionCaller.getDataByConsole(new Scanner(in));
        Assert.assertEquals("File Bratislava.json created", result);
    }
    @Test
    public void getDataByConsoleUsesFileManagerRequiredMethods() throws IOException {
        fileManagerMock = Mockito.mock(FileManager.class);
        when(fileManagerMock.writeToFile(anyString(), eq(".json"), eq("Bratislava"))).thenReturn(true);

        OptionCaller optionCaller = new OptionCaller(fileManagerMock);
        ByteArrayInputStream in = new ByteArrayInputStream("Bratislava".getBytes());
        optionCaller.getDataByConsole(new Scanner(in));

        Mockito.verify(fileManagerMock).writeToFile(anyString(), anyString(), anyString());
    }
    @Test
    public void getDataByFileReturnsCorrectStatements() throws IOException {
        fileManagerMock = Mockito.mock(FileManager.class);
        List<String> list = new ArrayList<>();
        list.add("London");
        list.add("Paris");
        list.add("Tokyo");
        when(fileManagerMock.readFromFile(anyString())).thenReturn(list);
        when(fileManagerMock.writeToFile(anyString(), anyString(), anyString())).thenReturn(true);

        OptionCaller optionCaller = new OptionCaller(fileManagerMock);
        String result = optionCaller.getDataByFile();

        Assert.assertEquals("File London.json created\nFile Paris.json created\nFile Tokyo.json created\n", result);
    }
    @Test
    public void getDataByFileUsesFileManagerRequiredMethods() throws IOException {
        fileManagerMock = Mockito.mock(FileManager.class);
        List<String> list = new ArrayList<>();
        list.add("London");
        list.add("Paris");
        list.add("Tokyo");
        when(fileManagerMock.readFromFile(anyString())).thenReturn(list);
        when(fileManagerMock.writeToFile(anyString(), anyString(), anyString())).thenReturn(true);

        OptionCaller optionCaller = new OptionCaller(fileManagerMock);
        optionCaller.getDataByFile();

        Mockito.verify(fileManagerMock).readFromFile(anyString());
        Mockito.verify(fileManagerMock, times(3)).writeToFile(anyString(), anyString(), anyString());
    }
}
