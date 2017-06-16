package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.i2c.I2CFactory;
import hardware.converter.PCF8591;
import java.io.IOException;

public class GasSensor {

	//Field
	private PCF8591 pcf8591;
	private GpioPinDigitalInput gpioPinDigitalInput;

	//Constructor
	public GasSensor(PCF8591 pcf8591, Pin digitalPinNo) throws Exception {
		this.pcf8591 = pcf8591;  //digitalPinNO: DO핀의 상태를 감지하기 위해서 연결한 핀의 번호.
		GpioController gpioController = GpioFactory.getInstance();
		gpioPinDigitalInput = gpioController.provisionDigitalInputPin(digitalPinNo); //gpioPindigitalInput을 얻는다.
		gpioPinDigitalInput.setShutdownOptions(true, PinState.LOW);//app이 종료되면 입력모드해제, 디폴트 상태를 LOW로 한다.
	}

	public void setGpioPinListenerDigital(GpioPinListenerDigital gpioPinListenerDigital) { //상태변화를 감지하기 위한 리스너 추가.
		gpioPinDigitalInput.addListener(gpioPinListenerDigital);
	}

	public double getValue() throws Exception {
		int analogVal = pcf8591.analogRead();//0~255 //value는 아날로그 값을 읽는다.

		return analogVal; //섭씨 리턴
	}

	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN2); //i2cAddress는 0x48로 고정. AIN0으로 온도센서와 연결
		GasSensor test = new GasSensor(pcf8591, RaspiPin.GPIO_23);
		test.setGpioPinListenerDigital(new GpioPinListenerDigital() { //DIgital in의 상태를 조사하여 메소드 동작.
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState() == PinState.LOW) {
					System.out.println("********** 가스 검출");
				} else {
					System.out.println("********** 정상상태");
				}
			}
		});

		//방법2: Analog값 이용   //아날로그 값을 이용해서
		while (true) {
			double value = test.getValue();
			System.out.println(value);
			if (value > 100) {
//				analog값을 이용해서 처리.
			}
			Thread.sleep(1000);
		}
	}

}
