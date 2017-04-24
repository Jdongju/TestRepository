package ch18.book.sec05.e2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class BufferedReaderExample {

    public static void main(String[] args) throws  IOException {
        InputStream is= System.in;
        Reader reader=new InputStreamReader(is);
        BufferedReader br= new BufferedReader(reader);
        
        System.out.println("입력 : ");
        String lineString=br.readLine();
        
        System.out.println("출력: "+lineString);
        
    }
}
