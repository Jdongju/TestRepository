package hardware.button;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.io.IOException;

public class Button {

	//Field
	private GpioPinDigitalInput gpioPinDigitalInput;

	//Constructor
	public Button(Pin buttonPinNo) {
		GpioController gpioController = GpioFactory.getInstance();//싱글톤을 위해 GpioFactory를 쓴다. 다른곳에서 get하더라도 똑같은 객체를 사용할 수 있도록
		gpioPinDigitalInput = gpioController.provisionDigitalInputPin(buttonPinNo);
		gpioPinDigitalInput.setShutdownOptions(true);

	}

	public void setGpioPinListenerDigital(GpioPinListenerDigital listener) {
		gpioPinDigitalInput.addListener(listener);
	}

	//Method
	public static void main(String[] args) throws IOException {
		Button button = new Button(RaspiPin.GPIO_00);
		button.setGpioPinListenerDigital(new GpioPinListenerDigital() {  //리스너가 인터페이스이기 때문에 익명객체나 람다식으로 구현해야한다.
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if (event.getState() == PinState.HIGH) {
					System.out.println("High"); //버튼 땠을때 High
				} else {
					System.out.println("Low"); //버튼 눌렀을때 LOW
				}
			}
		});
		
		
		
		System.out.println("Ready....");
		System.in.read(); //키보드 입력까지 대기하도록하여 바로 꺼지는 것을 방지.
	}
}
