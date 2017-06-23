package sensingcar.coap.sever.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.UltrasonicSensor;
import java.util.logging.Level;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UltrasonicSensorResource extends CoapResource {

	//Field
	private static final Logger LOGGER = LoggerFactory.getLogger(UltrasonicSensorResource.class);
	private PCA9685 pca9685;
	private SG90ServoPCA9685Duration servoMotor;
	private UltrasonicSensor ultrasonicSensor;
	private int currAngle;
	private final int maxAngle = 170;
	private final int minAngle = 10;
	private int currDistance;

	//Constructor
	public UltrasonicSensorResource() throws Exception {
		super("ultrasonicsensor");
		//관찰을 위한 메소드
		setObservable(true);
		//클라이언트가 관찰하는것을 알게끔한다.
		getAttributes().setObservable();
		//보내기만하면 끝이다 응답을 받지 않아도된다. 라는 의미.
		setObserveType(CoAP.Type.NON);

		pca9685 = PCA9685.getInstance();
		servoMotor = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);
		ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		setAngle(90);
		//멀티스레드환경일때는 synchronized를 붙여서 한번에 하나의 스레드만 해당 메소드(getDistance)를 사용하도록 한다.
		Thread thread = new Thread() {
			@Override
			public void run() {
				int count = 0;
				while (true) {
					try {
						currDistance = ultrasonicSensor.getDistance();
						//Handleget이 자동으로 실행되고 그 내용이 클라이언트에게 전달된다.
						//여기서는 1초마다 값을 전달한다.
						if (count == 2) {
							changed();
							count = 0;
						}
						Thread.sleep(500);
						count++;
					} catch (Exception e) {
						LOGGER.info(e.toString());
					}
				}
			}
		};
		thread.start();
	}

	//Method
	private void setAngle(int angle) {
		if (angle < minAngle) {
			angle = minAngle;
		}
		if (angle > maxAngle) {
			angle = maxAngle;
		}
		currAngle = angle;
		servoMotor.setAngle(angle);

		currDistance = ultrasonicSensor.getDistance();

	}

	@Override
	public void handleGET(CoapExchange exchange) {
		JSONObject responseJsonObject = new JSONObject();
		responseJsonObject.put("angle", String.valueOf(currAngle));
		responseJsonObject.put("distance", String.valueOf(currDistance));
		String responseJson = responseJsonObject.toString();
		exchange.respond(responseJson);
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		//{ "command":"change", "angle":"90" }
		//{ "command":"status" }
		try {
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);
			String command = requestJsonObject.getString("command");
			if (command.equals("change")) {
				int angle = Integer.parseInt(requestJsonObject.getString("angle"));
				setAngle(angle);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					java.util.logging.Logger.getLogger(UltrasonicSensorResource.class.getName()).log(Level.SEVERE, null, ex);
				}
			} else if (command.equals("status")) {
			}

			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("angle", String.valueOf(currAngle));
			responseJsonObject.put("distance", String.valueOf(currDistance));
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
