package coap.exam04.client;

import java.io.IOException;
import java.util.Random;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class CoapResource05Client {

	//Field
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;

	//Constructor
	public CoapResource05Client() {
		coapClient = new CoapClient();
	}

	//Method
	
	public void observe()  {
		coapClient.setURI("coap://192.168.3.48/resource05");
		//비동기방식으로 서버의 응답을 받는다. 비동기하면 그동안 다른일 가능
		coapObserveRelation= coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				String text=response.getResponseText();
				System.out.println(text);
			}

			@Override
			public void onError() {
			}
		});
	}

	public void shutdown() {
		coapObserveRelation.proactiveCancel();
		coapClient.shutdown();
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		CoapResource05Client client = new CoapResource05Client();
		client.observe();
		System.in.read();
		client.shutdown();
	}
}
