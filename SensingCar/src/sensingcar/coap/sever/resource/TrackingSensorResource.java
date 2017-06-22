package sensingcar.coap.sever.resource;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.converter.PCF8591;
import hardware.sensor.PhotoresistorSensor;
import hardware.sensor.TrackingSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackingSensorResource extends CoapResource {

	//Field
	private static final Logger LOGGER = LoggerFactory.getLogger(TrackingSensorResource.class);

	private TrackingSensor trackingSensor;
	private String currColor;

	//Constructor
	public TrackingSensorResource() throws Exception {
		super("trackingsensor");
		//관찰을 위한 메소드
		setObservable(true);
		//클라이언트가 관찰하는것을 알게끔한다.
		getAttributes().setObservable();
		//보내기만하면 끝이다 응답을 받지 않아도된다. 라는 의미.
		setObserveType(CoAP.Type.NON);
		
	trackingSensor= new TrackingSensor(RaspiPin.GPIO_26);
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try{
						PinState pinstate= trackingSensor.getStatus();
						if(pinstate==PinState.HIGH){
							currColor="Black";
						} else{
							currColor="White";
						}
						changed();
						Thread.sleep(1000);
					}catch(Exception e){
						LOGGER.info(e.toString());
					}
				}
			}

		};
		thread.start();
		
		trackingSensor.setGpioPinListenerDigital(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				PinState pinState= event.getState();
				if(pinState==PinState.HIGH){
					BuzzerResource.getInstance().off();
				}else{
					BuzzerResource.getInstance().on();
				}
			}
		});
	}

	//Method
	@Override
	public void handleGET(CoapExchange exchange) {
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("trackingsensor", currColor);
		String responseJson = responseJsonObject.toString();
		exchange.respond(responseJson);
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		//{ "command":"status" }
		try {
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);
			String command = requestJsonObject.getString("command");
		 if (command.equals("status")) {
			}

			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("trackingsensor", currColor);
			String responseJson = responseJsonObject.toString();
			exchange.respond(responseJson);
		} catch (Exception e) {
			LOGGER.info(e.toString());
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			String responseJson = responseJsonObject.toString();
			exchange.respond(responseJson);
		}

	}
}
