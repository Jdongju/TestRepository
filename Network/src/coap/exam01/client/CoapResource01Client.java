package coap.exam01.client;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;

public class CoapResource01Client {
	//Field
	private CoapClient coapClient;
	//Constructor
	
	public CoapResource01Client() {
		coapClient= new CoapClient();
		
	}
	//Method
	public String get(){
//		coapClient.setURI("coap://localhost/resource01");
		coapClient.setURI("coap://192.168.3.48/resource01"); //Car(48)이 서버일때 연결
//		coapClient.setURI("coap://192.168.3.47/resource01"); //RPi(47)가 서버일떄 연결
		CoapResponse response= coapClient.get(); //Get방식으로 요청
		if(response.getCode()==CoAP.ResponseCode.CONTENT){
			return response.getResponseText();
		}else{
			return null;
		}
	}
	
	public void shutdown(){
		coapClient.shutdown();
	}
	
	public static void main(String[] args) {
		CoapResource01Client client= new CoapResource01Client();
		String text=client.get();
		System.out.println(text); //응답내용 출력
		client.shutdown();
	}
	
}
