package ch18.exam27.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerExample {

    public static void main(String[] args) {

        ExecutorService executorServiece = Executors.newFixedThreadPool(100);

        ServerSocket serverSocket = null;

        try {
            //ServerSocket 생성
            serverSocket = new ServerSocket();

            //포트와 바인딩
            serverSocket.bind(new InetSocketAddress(50001));
            //연결 기다리기
            System.out.println("클라이언트의 연결 기다림");
            while (true) {

                Socket socket = serverSocket.accept();
                Runnable task = () -> {
                    try {
                        //통신하기
                        InputStream is = socket.getInputStream();
                        byte[] data = new byte[100];
                        int readBytes = is.read(data);
                        if (readBytes == -1) {
                            throw new IOException("클라이언트가 정상 종료됨");
                        }

                        String strData = new String(data, 0, readBytes);
                        System.out.println("받은 데이터: " + strData);

                        OutputStream os = socket.getOutputStream();
                        data = strData.getBytes();
                        os.write(data);
                        os.flush();
                        System.out.println("데이터 보내기 성공");

                        //연결끊기
                        socket.close();
                    } catch (IOException ex) {
                    }
                };
                executorServiece.submit(task);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //ServerSocket이 50001을 현재 사용하고 있는가?
        if (serverSocket != null && !serverSocket.isClosed()) {
            //ServerSocket 닫기
            try {
                serverSocket.close();
            } catch (IOException ex) {
            }
        }
    }
}
