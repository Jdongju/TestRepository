package hardware.buzzer;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class ActiveBuzzer {
	//Field
	private GpioPinDigitalOutput buzzerPin;
	private String status="off";
	//Constructor
	public ActiveBuzzer(Pin buzzerPinNo) {
		GpioController gpioController= GpioFactory.getInstance();
		buzzerPin=gpioController.provisionDigitalOutputPin(buzzerPinNo, PinState.HIGH);//Digital Output모드로 핀을 얻는다.
		buzzerPin.setShutdownOptions(false,PinState.HIGH);//false: 핀의 상태를 어플리케이션이 종료될때 그 (디지털 아웃풋, 인풋) 모드를 해지하지 않고 그대로 내버려두겠다는 의미
															//high: 종료될 때 디지털 아웃풋모드 핀을 high로 유지하겠다는 의미
	}
	//Method
	public void on(){
		buzzerPin.low();
		status="on";
	}
	
	public void off(){
		buzzerPin.high();
		status="off";
	}

	public String getStatus() {
		return status;
	}
	
	public static void main(String[] args) throws InterruptedException {
		 ActiveBuzzer test= new ActiveBuzzer(RaspiPin.GPIO_00);
		 
		 for (int i = 0; i < 100; i++) {
			test.on();
			Thread.sleep(200);
			test.off();
			Thread.sleep(200);
		}
	}
	

	

}
