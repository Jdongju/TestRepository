package ch18.book.sec02.exam01_inputstream_read;

import java.io.FileInputStream;
import java.io.InputStream;

public class ReadExample1 {

	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("C:/Temp/test.txt"); 
                //InputStream을 FileInputStream으로 하고 파일path를 넣는다.
		int readByte;
                //몇바이트 읽었는지 받는 값을 선언하고 이 값이 -1이면 마지막 까지 읽은 것이다.
		while(true) {
			readByte = is.read(); //계속 읽다가
			if(readByte == -1) break; //다읽었으면 while문 break해서 종료
			System.out.println((char)readByte);
		}
		is.close();
	}

}
