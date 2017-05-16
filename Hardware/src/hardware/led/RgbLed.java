package hardware.led;
//p.142

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class RgbLed {

	private GpioPinDigitalOutput redPin;	//초기화상태는 모두 LOW상태
	private GpioPinDigitalOutput greenPin;
	private GpioPinDigitalOutput bluePin;

	public RgbLed(Pin redPinNO, Pin greenPinNO, Pin bluePinNo) {
		GpioController gpioController = GpioFactory.getInstance();
		redPin = gpioController.provisionDigitalOutputPin(redPinNO,PinState.HIGH);
		redPin.setShutdownOptions(true,PinState.LOW);
		greenPin = gpioController.provisionDigitalOutputPin(greenPinNO,PinState.HIGH);
		greenPin.setShutdownOptions(true,PinState.LOW);
		bluePin = gpioController.provisionDigitalOutputPin(bluePinNo,PinState.HIGH);
		bluePin.setShutdownOptions(true,PinState.LOW);
	}

	public void red() {
		redPin.low();
		greenPin.high();
		bluePin.high();

	}

	public void green() {
		redPin.high();
		greenPin.low();
		bluePin.high();

	}

	public void blue() {
		redPin.high();
		greenPin.high();
		bluePin.low();

	}
	
	public static void main(String[] args) throws InterruptedException {
		RgbLed test= new RgbLed(RaspiPin.GPIO_27, RaspiPin.GPIO_28, RaspiPin.GPIO_29);
//		test.green();
//		for (int i = 0; i < 10; i++) {
//			test.red();
//			Thread.sleep(500);
//			test.green();
//			Thread.sleep(500);
//		}
		while (true) {			
			test.green();
			Thread.sleep(1000);
			test.red();
			Thread.sleep(1000);
			test.blue();
			Thread.sleep(1000);
		}

	}

}
