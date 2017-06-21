package coap.exam04.server;

import com.pi4j.io.i2c.I2CFactory;
import hardware.converter.PCF8591;
import hardware.sensor.ThermistorSensor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

public class CoapResource05 extends CoapResource {

	//온도 1초마다 관찰클라이언트(coapresource05Client)에 통보
	//coapresource05Client는 1초마다 온도 출력
	//handlePost필요없고  handleGet만 만들면 된다.
	//스레드 사용해야한다.
	//field
	private int value;
	private ThermistorSensor thermistorSensor;
	private PCF8591 pcf8591;
	private int temperature;

//constructor
	public CoapResource05() throws Exception {
		super("resource05");

		pcf8591 = new PCF8591(0x48, PCF8591.AIN1);
		thermistorSensor = new ThermistorSensor(pcf8591);



		//테스트
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						temperature = (int) thermistorSensor.getValue();
							changed();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
						}
					} catch (IOException ex) {
					} catch (I2CFactory.UnsupportedBusNumberException ex) {
					} catch (Exception ex) {
						Logger.getLogger(CoapResource05.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
		};
		thread.start();
		//관찰 기능 활성화
		setObservable(true);
		//관찰 기능을 제공한다라는 것을 클라이언트가 알 수있도록 한다.
		getAttributes().setObservable();
	}

	//method
	@Override
	public void handleGET(CoapExchange exchange) {

//		String temperature = exchange.getRequestOptions().getUriQuery().get(0).split("=")[1];
		exchange.respond(String.valueOf(temperature));
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		JSONObject jSONObject = new JSONObject(exchange.getRequestText());
		value = jSONObject.getInt("value");
		if (value > 10) {
			changed();
		}
		//changed()가 실행되면 GET이 실행되어서 관찰요청한 클라이언트에 데이터가 제공된다.
		exchange.respond("ok");

	}

}
