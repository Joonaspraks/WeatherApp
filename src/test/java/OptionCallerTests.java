import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class OptionCallerTests {
    private FileManager fileManagerMock;
    @Before
    public void setup(){
        fileManagerMock = Mockito.mock(FileManager.class);
    }
    @Test
    public void getDataByConsole() throws IOException {
        when(fileManagerMock.writeToFile(anyString(), eq(".txt"), eq("Bratislava"))).thenReturn(true);
        //when(fileManagerMock.checkFile("Bratislava", ".txt")).thenReturn(true);
        OptionCaller optionCaller = new OptionCaller(fileManagerMock);
        ByteArrayInputStream in = new ByteArrayInputStream("Bratislava".getBytes());
        String result = optionCaller.getDataByConsole(new Scanner(in));
        Assert.assertEquals("File Bratislava.txt created", result);
    }

}
