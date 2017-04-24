package ch18.book.sec02.exam01_inputstream_read;

import java.io.FileInputStream;
import java.io.InputStream;

public class ReadExample2 {

	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("C:/Temp/test.txt");
		int readByteNo;
		byte[] readBytes = new byte[3];
		String data = "";
		while( true ) {
			readByteNo = is.read(readBytes);
			if(readByteNo == -1) break;
			data += new String(readBytes, 0, readByteNo); 
                        //readBytes배열의 몇바이트까지 읽을 것인지를 readByteNo를 통해 알려줌
		}
		System.out.println(data);
		is.close();
	}

}
