package ch18.exam24;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddresssExample {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();
        String address = ia.getHostAddress();
        System.out.println(address);

        InetAddress iaNaver = InetAddress.getByName("www.naver.com");
        String addressNaver = iaNaver.getHostAddress();
        System.out.println(addressNaver);

        System.out.println("------------------------------------------------------");
        InetAddress[] iaNavers=InetAddress.getAllByName("KOSA-L3-01");
        for(InetAddress i : iaNavers){
            System.out.println(i.getHostAddress());
        }
    }
}
