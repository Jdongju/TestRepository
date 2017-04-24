package sec02.exam03_reader_read;

import java.io.FileReader;
import java.io.Reader;

public class ReadExample2 {

	public static void main(String[] args) throws Exception {
		Reader reader = new FileReader("C:/Temp/test.txt");
		int readCharNo;
		char[] cbuf = new char[2];
                //버퍼배열의 크기를 2로 설정하여 2씩 읽어들인다.
		String data = "";
                //읽어들인 값을 2단위로 data라는 String 변수에 집어넣는다.
		while( true ) {
			readCharNo = reader.read(cbuf);
			if(readCharNo == -1) break;
			data += new String(cbuf, 0, readCharNo);
		}
		System.out.println(data);
		
		reader.close();
	
	}

}
