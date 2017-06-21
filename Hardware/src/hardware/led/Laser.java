package hardware.led;
//p.142

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Laser {

	private  GpioPinDigitalOutput lasPin;	//초기화상태는 모두 LOW상태

	public Laser(Pin lasPinNo) {
		GpioController gpioController = GpioFactory.getInstance();
		lasPin = gpioController.provisionDigitalOutputPin(lasPinNo);
		lasPin.setShutdownOptions(true,PinState.LOW);
		
	}
	public void shoot(){
		lasPin.low();
	}
		public void off(){
		lasPin.high();
	}

	
	
	public static void main(String[] args) throws InterruptedException {
		Laser test= new Laser(RaspiPin.GPIO_04);
		while (true) {			
			test.shoot();
			Thread.sleep(1000);
			test.off();
			Thread.sleep(1000);
		}

	}

}
