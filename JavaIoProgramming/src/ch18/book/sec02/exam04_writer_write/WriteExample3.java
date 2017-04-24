package sec02.exam04_writer_write;

import java.io.FileWriter;
import java.io.Writer;

public class WriteExample3 {

	public static void main(String[] args) throws Exception {
		Writer writer = new FileWriter("C:/Temp/test.txt");
		char[] data = "홍길동".toCharArray();
		writer.write(data, 1, 2);
                //홍길동을 인덱스 1부터 2만큼 쓴다.
		writer.flush();
		writer.close();
	}

}
