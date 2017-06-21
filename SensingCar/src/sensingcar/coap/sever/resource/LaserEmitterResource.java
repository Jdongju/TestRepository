package sensingcar.coap.sever.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.led.Laser;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaserEmitterResource extends CoapResource {

	//Field
	private static final Logger LOGGER= LoggerFactory.getLogger(LaserEmitterResource.class);
	private Laser laser;
	private String currStatus;
	
	//Constructor
	public LaserEmitterResource() throws Exception {
		super("laseremitter");
		laser= new Laser(RaspiPin.GPIO_25);
		off();
	}

	//Method
	
	private void on(){
		laser.shoot();
		currStatus="on";
	}
	
	private void off(){
		laser.off();
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
