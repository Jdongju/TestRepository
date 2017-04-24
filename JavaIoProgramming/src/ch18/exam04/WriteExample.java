package ch18.exam04;

import ch18.exam03.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteExample {

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        OutputStream os = new FileOutputStream("src/ch18/exam04/test.txt");

//       byte[] data={97,98,99};
       byte[] data="abc".getBytes();
        
       os.write(data);
       
        os.flush();
        os.close();
    }
}
