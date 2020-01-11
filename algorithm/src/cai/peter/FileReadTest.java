package cai.peter;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReadTest {
    @Test
    public void read() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream("solution.txt");
        BufferedReader reader = new BufferedReader((new InputStreamReader(stream)));
        String s = reader.readLine();
        while(s!=null){
            s = reader.readLine();
        }
        reader.close();
    }
}
