package coap.exam04.server;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.i2c.I2CFactory;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.sensor.GasSensor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

public class CoapResource06 extends CoapResource {

	//가스 감지해서 일정 넘으면 가스검출이라는 메시지를 coapresource06Client에 준다.
	//coapresource06Client는 가스발생, 정상상태 출력 
	//field
	private int gasValue;
	GasSensor gasSensor;

	//constructor
	public CoapResource06() throws Exception {
		super("resource06");
		//관찰 기능 활성화
		setObservable(true);
		//관찰 기능을 제공한다라는 것을 클라이언트가 알 수있도록 한다.
		getAttributes().setObservable();

		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN2);
		gasSensor = new GasSensor(pcf8591, RaspiPin.GPIO_23);
		ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_24);
		//테스트
		Thread thread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						gasValue = (int) gasSensor.getValue();

						if (gasValue > 150) {

							System.out.println("********** 가스 검출 " + gasValue);
							Thread.sleep(1000);
							activeBuzzer.on();
						} else {
							System.out.println("********** 정상상태" + gasValue);
							Thread.sleep(1000);
							activeBuzzer.off();
						}
						changed();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
							Logger.getLogger(CoapResource06.class.getName()).log(Level.SEVERE, null, ex);
						}
					} catch (IOException ex) {
					} catch (I2CFactory.UnsupportedBusNumberException ex) {
					} catch (InterruptedException ex) {
						Logger.getLogger(CoapResource06.class.getName()).log(Level.SEVERE, null, ex);
					}

				}
			}
		};
		thread.start();

	}

	//method
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond("Server handle GET() : gasValue" + gasValue);
	}

}
