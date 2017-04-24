package ch18.book.sec05.e2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamExample {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        
        int data=-1;
        long start=0;
        long end=0;
        
        fis=new FileInputStream("D:/IoTCourse/NetBeansWorkspace/JavaIoProgramming/src/ch18/book/sec05/e2/Koala.jpg");
        bis=new BufferedInputStream(fis);
        fos=new FileOutputStream("C:/Temp/forest.jpg");
        start=System.currentTimeMillis();
        while((data=bis.read())!=-1){
            fos.write(data);
        }
        fos.flush();
        end=System.currentTimeMillis();
        fos.close();bis.close();fis.close();
        System.out.println("사용하지 않았을 때"+(end-start)+"ms");
        
        fis=new FileInputStream("D:/IoTCourse/NetBeansWorkspace/JavaIoProgramming/src/ch18/book/sec05/e2/Koala.jpg");
        bis= new BufferedInputStream(fis);
        fos= new FileOutputStream("C:/Temp/forest.jpg");
        bos= new BufferedOutputStream(fos);
        start=System.currentTimeMillis();
        while((data=bis.read())!=-1){
            bos.write(data);
        }
        bos.flush();
        end=System.currentTimeMillis();
        bos.close(); fos.close(); bis.close(); fis.close();
        System.out.println("사용 했을 때 : "+(end-start)+"ms");
        
        
    }
}
