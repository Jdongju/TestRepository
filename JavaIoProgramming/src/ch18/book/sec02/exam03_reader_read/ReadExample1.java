package sec02.exam03_reader_read;

import java.io.FileReader;
import java.io.Reader;

public class ReadExample1 {

	public static void main(String[] args) throws Exception {
		Reader reader = new FileReader("C:/Temp/test.txt");
                //path에 있는 파일을 FIleReader로 읽는다.(문자열만 읽음)
		int readData;
                //마지막까지 읽은 경우 -1리턴하고 이때 종료.
		while( true ) {
			readData = reader.read();
			if(readData == -1) break;
			System.out.print((char)readData);
		}
		
		reader.close();
	
	}

}
