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

	public void rgb(boolean red, boolean green, boolean blue) { //3가지 섹상 동시에 제어해야할 경우
		
		if(red) {
			redPin.low();
		}else{
			redPin.high();
		}
		
		if(green) {
			greenPin.low();
		}else{
			greenPin.high();
		}
		
		if(blue) {
			bluePin.low();
		}else{
			bluePin.high();
		}
	}

	public void red(boolean state){  //그 색만 바꾸는것
		if(state==true){
			redPin.low();
		}else{
			redPin.high();
		}
	}
	public void green(boolean state){
		if(state==true){
			greenPin.low();
		}else{
			greenPin.high();
		}
	}
	public void blue(boolean state){
		if(state==true){
			bluePin.low();
		}else{
			bluePin.high();
		}
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
			test.rgb(true,false,false);
			Thread.sleep(1000);
			test.rgb(false,true,false);
			Thread.sleep(1000);
			test.rgb(false,false,true);
			Thread.sleep(1000);
		}

	}

}
