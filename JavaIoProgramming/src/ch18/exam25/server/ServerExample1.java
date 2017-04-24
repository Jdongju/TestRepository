package ch18.exam25.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample1 {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            //ServerSocket 생성
            serverSocket = new ServerSocket();
            //포트와 바인딩
            serverSocket.bind(new InetSocketAddress(50001));
            //연결 기다리기
            Socket socket = serverSocket.accept();

            //클라이언트의 정보 얻기
            InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
            System.out.println(isa.getHostName());

            //통신하기
            //연결끊기
            socket.close();

        } catch (IOException ex) {
           ex.printStackTrace();
        }
        //ServerSocket이 50001을 현재 사용하고 있는가?
        if(serverSocket!=null&&!serverSocket.isClosed()){
            //ServerSocket 닫기
            try {serverSocket.close();} catch (IOException ex) {}
        }
    }
}