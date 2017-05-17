package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.button.Button;
import hardware.led.DualColorLed;
import hardware.led.RgbLed;
import java.io.IOException;

public class ButtonRGBTest {
	public static void main(String[] args) throws IOException {
		Button button1= new Button(RaspiPin.GPIO_00);
		Button button2= new Button(RaspiPin.GPIO_01);
		Button button3= new Button(RaspiPin.GPIO_02);
		
		RgbLed test= new RgbLed(RaspiPin.GPIO_27, RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		button1.setGpioPinListenerDigital(new GpioPinListenerDigital(){
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(event.getState()==PinState.LOW){
					test.red(true);
				}else{
					test.red(false);
				}
			
			}
		});
		button2.setGpioPinListenerDigital(new GpioPinListenerDigital(){
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(event.getState()==PinState.LOW){
					test.green(true);
				}else{
					test.green(false);
				}
			
			}
		});
		button3.setGpioPinListenerDigital(new GpioPinListenerDigital(){
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(event.getState()==PinState.LOW){
					test.blue(true);
				}else{
					test.blue(false);
				}
			
			}
		});
		System.out.println("Ready....");
		System.in.read(); //키보드 입력까지 대기하도록하여 바로 꺼지는 것을 방지.
		
		
	}
}
