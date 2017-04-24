package sec02.exam03_reader_read;

import java.io.FileReader;
import java.io.Reader;

public class ReadExample3 {

	public static void main(String[] args) throws Exception {
		Reader reader = new FileReader("C:/Temp/test.txt");
		int readCharNo;
		char[] cbuf = new char[4];
		readCharNo = reader.read(cbuf, 1, 2);
                //4크기 배열 cbuf에 파일로부터 2만큼 읽어들이고 인덱스 1부터 2만큼 저장한다.
                //다읽었으면 -1리턴
		for(int i=0; i<cbuf.length; i++) {
			System.out.println(cbuf[i]);
		}
		reader.close();
	
	}

}
