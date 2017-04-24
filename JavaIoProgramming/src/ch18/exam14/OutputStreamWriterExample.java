package ch18.exam14;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class OutputStreamWriterExample {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream("src/ch18/exam14/test.txt");
        OutputStreamWriter osw=new OutputStreamWriter(os);
//        byte[] data = "가".getBytes(); //write()의 매개변수가 int혹은 byte배열이여야하기 때문에 변환해준다.
        osw.write("가");
        osw.flush();
        osw.close();
    }
}
