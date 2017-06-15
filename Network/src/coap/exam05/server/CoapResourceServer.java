package coap.exam05.server;

import coap.exam04.server.*;
import coap.exam02.server.*;
import coap.exam01.server.*;
import java.net.InetSocketAddress;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.proxy.DirectProxyCoapResolver;
import org.eclipse.californium.proxy.ProxyHttpServer;
import org.eclipse.californium.proxy.resources.ForwardingResource;
import org.eclipse.californium.proxy.resources.ProxyCoapClientResource;

public class CoapResourceServer {

	//Field
	private CoapServer coapServer;
	
	//Constructor
	public CoapResourceServer() throws Exception {
		coapServer= new CoapServer();
		//Isa에서 지정한 IP에서만 서버 가동(라즈베리파이에서 chip이 두개이므로(안테나, 내장) p186
		
		InetSocketAddress isa1= new InetSocketAddress("192.168.3.48", 5683);
		InetSocketAddress isa2= new InetSocketAddress("localhost", 5683);
		coapServer.addEndpoint(new CoapEndpoint(isa1));
		coapServer.addEndpoint(new CoapEndpoint(isa2));
		
		//온도를 감지하는 서버 
		coapServer.add(new CoapResource01());
		//,서보모터+감지
		coapServer.add(new CoapResource02());
		//Observe
		coapServer.add(new CoapResource03());
		//외부값을 Observe해서 관찰값 전달.
		coapServer.add(new CoapResource04());
//		coapServer.add(new CoapResource05());
//		coapServer.add(new CoapResource06());
//		
		//Coap->Coap 프록시 설정
		ForwardingResource coap2coap= new ProxyCoapClientResource("coap2coap");
		coapServer.add(coap2coap);
		
		//http->coap 포워드 프록시 설정
		ProxyHttpServer httpServer= new ProxyHttpServer(9090);
		//http서버를 프록시로 변경. 이때 프록시의 이름을 알려면 ForwardingResource를 통해 어떤 리소스로 가야할지 알려줌.
		httpServer.setProxyCoapResolver(new DirectProxyCoapResolver(coap2coap));
		
		
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
