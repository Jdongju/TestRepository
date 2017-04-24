package ch18.exam19;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class LineDataExample {

    public static void main(String[] args) throws IOException {

//        //보조데이터 없이 불편한 방법 1
//        FileWriter fw = new FileWriter("src/ch18/exam19/test.txt");
//        fw.write("abcde\r\n");
//        fw.write("12345\r\n");
//        fw.write("가나다라마");
//
//        fw.flush();
//        fw.close();
//
//        //보조데이터 없이 불편한 방법 2
//        FileOutputStream fos = new FileOutputStream("src/ch18/exam19/test.txt");
//        OutputStreamWriter osw = new OutputStreamWriter(fos);
//        osw.write("abcde\r\n");
//        osw.write("12345\r\n");
//        osw.write("가나다라마");
//
//        osw.flush();
//        fos.flush();
//        osw.close();
//        fos.close();
        //
        FileOutputStream fos = new FileOutputStream("src/ch18/exam19/test.txt");
        PrintStream out = new PrintStream(fos);
//        PrintWriter out= new PrintWriter(out);
        out.println("abcde");
        out.println("12345");
        out.print("가나다라마");
        out.flush();
        fos.flush();
        out.close();
        fos.close();

        FileInputStream fis = new FileInputStream("src/ch18/exam19/test.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        while (true) {
            String line = br.readLine(); //더이상 읽을게 없으면 null 리턴
            if (line == null) {
                break;
            }
            System.err.println(line);

        }

        br.close();
        isr.close();
        fis.close();

    }
}
