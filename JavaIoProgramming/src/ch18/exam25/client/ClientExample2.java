package ch18.exam25.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientExample2 {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            //how1
            //socket = new Socket("192.168.3.40", 50001);

            //how2
            //소켓생성
            socket = new Socket();
            //연결요청
            socket.connect(new InetSocketAddress("192.168.3.40", 50001));
            
                String str=null;
                str.length();
                //통신하기
            
//            OutputStream os = socket.getOutputStream();
//            String strData = "ㄷㄷㄷhello";
//            byte[] data = strData.getBytes();
//            os.write(data);
//            os.flush();
//            System.out.println("데이터 보내기 성공");
//
//            InputStream is = socket.getInputStream();
//            data = new byte[100];
//            int readBytes = is.read(data);
//            strData = new String(data, 0, readBytes);
//            System.out.println("받은 데이터: " + strData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //열려있다면 닫아라.
        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}
