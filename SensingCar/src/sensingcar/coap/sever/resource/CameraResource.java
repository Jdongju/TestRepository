package sensingcar.coap.sever.resource;

import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.io.gpio.RaspiPin;
import hardware.buzzer.ActiveBuzzer;
import hardware.led.Laser;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CameraResource extends CoapResource {

	//Field
		private static final Logger LOGGER= LoggerFactory.getLogger(CameraResource.class);
	private SG90ServoPCA9685Duration leftRightMotor;
	private SG90ServoPCA9685Duration upDownMotor;
	private PCA9685 pca9685;
	private final int minLeftRight = 10;
	private final int maxLeftRight = 170;
	private final int minUpDown = 10;
	private final int maxUpDown = 100;
	private int currLeftRight;
	private int currUpDown;

	//Constructor
	public CameraResource() throws Exception {
		super("camera");
		pca9685 = PCA9685.getInstance();
		leftRightMotor = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_14);
		upDownMotor = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_15);
		turnLeftRight(90);
		turnUpDown(10);
	}

	//Method
	private void turnLeftRight(int angle) {
		if (angle < minLeftRight) {
			angle = minLeftRight;
		}
		if (angle > maxLeftRight) {
			angle = maxLeftRight;
		}
		leftRightMotor.setAngle(angle);
		currLeftRight = angle;

	}

	private void turnUpDown(int angle) {
		if (angle < minUpDown) {
			angle = minUpDown;
		}
		if (angle > maxUpDown) {
			angle = maxUpDown;
		}
		upDownMotor.setAngle(angle);
		currUpDown = angle;
	}

	@Override
	public void handleGET(CoapExchange exchange) {

	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		//{ "command":"change", "leftRight":"90", "upDown":"10" }
		//{ "command":"status" }
		try {
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);
			String command = requestJsonObject.getString("command");
			if (command.equals("change")) {
				int leftRight=Integer.parseInt(requestJsonObject.getString("leftRight"));
				int upDown=Integer.parseInt(requestJsonObject.getString("upDown"));
				turnLeftRight(leftRight);
				turnUpDown(upDown);
			} else if (command.equals("status")) {
			}

			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			responseJsonObject.put("leftRight", String.valueOf(currLeftRight));
			responseJsonObject.put("upDown", String.valueOf(currUpDown));
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
