package coap.exam02.server;

import coap.exam01.server.*;
import java.net.InetSocketAddress;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;

public class CoapResourceServer {

	//Field
	private CoapServer coapServer;
	
	//Constructor
	public CoapResourceServer() throws Exception {
		coapServer= new CoapServer();
		//Isa에서 지정한 IP에서만 서버 가동(라즈베리파이에서 chip이 두개이므로(안테나, 내장) p186
		
		InetSocketAddress isa= new InetSocketAddress("192.168.3.48", 5683);
		coapServer.addEndpoint(new CoapEndpoint(isa));
		
		coapServer.add(new CoapResource01());
		coapServer.add(new CoapResource02());
		coapServer.start();
	}
		
	//Method
	public void shutdown(){
		coapServer.stop();
		coapServer.destroy();
	}
	
	public static void main(String[] args) throws Exception {
		CoapResourceServer server = new CoapResourceServer();
		System.out.println("CoAP server is listening on port" +5683);
		
	}
}
