package hardware.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class DualColorLed {
	//Field
	private GpioPinDigitalOutput redPin;
	private GpioPinDigitalOutput greenPin;
	//Constructor
	public DualColorLed(Pin redPinNo, Pin greenPinNo){
		//GpioController 객체 얻기
		GpioController gpioController= GpioFactory.getInstance(); //GPIO controller를 얻는다.싱글톤
		//디지털 출력핀 생성
		redPin=gpioController.provisionDigitalOutputPin(redPinNo);
		//애플리케이션 종료될 때 출력 모드를 해제하고, 핀의 출력을 LOW(0, 출력이 안되는 상태)
		redPin.setShutdownOptions(true,PinState.LOW);//프로그램종료시  핀상태를 Low로 하겠다.
		greenPin=gpioController.provisionDigitalOutputPin(greenPinNo);
		greenPin.setShutdownOptions(true,PinState.LOW);//프로그램종료시  핀상태를 Low로 하겠다.
		
	}
	//Method
	public void red(){
		redPin.high();
		greenPin.low();
	}
	
	public void green(){
		redPin.low();
		greenPin.high();
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		DualColorLed test= new DualColorLed(RaspiPin.GPIO_22, RaspiPin.GPIO_21);
//		test.green();
//		for (int i = 0; i < 10; i++) {
//			test.red();
//			Thread.sleep(500);
//			test.green();
//			Thread.sleep(500);
//		}
		while (true) {			
			test.green();
			Thread.sleep(3000);
			test.red();
			Thread.sleep(1000);
		}

	}
}
