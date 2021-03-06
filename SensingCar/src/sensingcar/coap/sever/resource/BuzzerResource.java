package sensingcar.coap.sever.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.motor.DCMotor;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuzzerResource extends CoapResource {

	//Field
	private static final Logger LOGGER= LoggerFactory.getLogger(BuzzerResource.class);
	private ActiveBuzzer buzzer;
	private String currStatus;
	private static BuzzerResource instance;
	//Constructor
	public BuzzerResource() throws Exception {
		super("buzzer");
		//현재 CoapResourceServer에서 만들어지는 BuzzerResource객체를 instnace에 저장한다.
		instance=this;
		buzzer= new ActiveBuzzer(RaspiPin.GPIO_24);
		off();
	}

	//Method

	public static BuzzerResource getInstance() {
	
		return instance;
	}
	
	
	public void on(){
		buzzer.on();
		currStatus="on";
	}
	
	public void off(){
		buzzer.off();
		currStatus="off";
	}
		

	@Override
	public void handleGET(CoapExchange exchange) {

	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		//{ "command":"change", ""status":"on" }
		//{ "command":"status" }
		try {
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);
			String command = requestJsonObject.getString("command");
			if (command.equals("change")) {
				String status = requestJsonObject.getString("status");
				if(status.equals("on")) on();
				if(status.equals("off")) off();
			} else if (command.equals("status")) {
			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("status", currStatus);
			String responseJson = responseJsonObject.toString();
			exchange.respond(responseJson);
		} catch (Exception e) {
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			String responseJson = responseJsonObject.toString();
			exchange.respond(responseJson);
		}

	}
}
