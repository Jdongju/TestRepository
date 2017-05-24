package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.led.DualColorLed;
import hardware.sensor.FlameSensor;

public class FlameSensorBuzzerDualLedTest {
	public static void main(String[] args) throws Exception {
		ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_02);
		PCF8591 pcf8591= new PCF8591(0x48, PCF8591.AIN0);
		FlameSensor flameSensor = new FlameSensor(pcf8591, RaspiPin.GPIO_00);
		DualColorLed dualColorLed= new DualColorLed(RaspiPin.GPIO_01, RaspiPin.GPIO_04);
		
		flameSensor.setGpioPinListenerDigital(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(event.getState()==PinState.LOW){
						System.out.println("********** 화재발생");
					activeBuzzer.on();
					dualColorLed.red();
				}else{
					System.out.println("********** 정상상태");
					activeBuzzer.off();
					dualColorLed.green();
				}
			}
		});
			System.out.println("Ready....");
		System.in.read(); //키보드 입력까지 대기하도록하여 바로 꺼지는 것을 방지.
	}
}
