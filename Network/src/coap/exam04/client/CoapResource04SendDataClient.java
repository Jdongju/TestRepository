package coap.exam04.client;

import java.util.Random;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class CoapResource04SendDataClient {

	
	//Field
	private CoapClient coapClient;
	//Constructor
	public CoapResource04SendDataClient() {
		coapClient= new CoapClient();
	}
	//Method
	public void post() throws InterruptedException{
		coapClient.setURI("coap://192.168.3.48/resource04");
		Random random= new Random();
		while(true){
			JSONObject jSONObject= new JSONObject();
			jSONObject.put("value", random.nextInt(50));
			String json= jSONObject.toString();
			coapClient.post(json, MediaTypeRegistry.APPLICATION_JSON);
			Thread.sleep(1000);
		}
	}
	
	public void shutdown(){
		coapClient.shutdown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		CoapResource04SendDataClient client= new CoapResource04SendDataClient();
		client.post();
		client.shutdown();
	}
}
