package coap.exam04.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

public class CoapResource04 extends CoapResource{

	
	//field
	private int value;
	//constructor
	public CoapResource04() {
		super("resource04");
		//관찰 기능 활성화
		setObservable(true);
		//관찰 기능을 제공한다라는 것을 클라이언트가 알 수있도록 한다.
		 getAttributes().setObservable();
		 
		 //테스트
		 
	}
	//method
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond("value="+value);
	}
	@Override
	public void handlePOST(CoapExchange exchange) {
		JSONObject jSONObject= new JSONObject(exchange.getRequestText());
		value=jSONObject.getInt("value");
		if(value>30){
			changed();
		}
		//changed()가 실행되면 GET이 실행되어서 관찰요청한 클라이언트에 데이터가 제공된다.
		exchange.respond("ok");
		
	}

	
	
	
}
