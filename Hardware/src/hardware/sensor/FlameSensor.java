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

public class FlameSensor {

	//Field
	private PCF8591 pcf8591;
	private GpioPinDigitalInput gpioPinDigitalInput;
	//Constructor
	public FlameSensor(PCF8591 pcf8591, Pin digitalPinNo) throws Exception {
		this.pcf8591 = pcf8591;
		GpioController gpioController= GpioFactory.getInstance();
		gpioPinDigitalInput=gpioController.provisionDigitalInputPin(digitalPinNo);
		gpioPinDigitalInput.setShutdownOptions(true,PinState.LOW);
	}


	public void setGpioPinListenerDigital(GpioPinListenerDigital gpioPinListenerDigital){
		gpioPinDigitalInput.addListener(gpioPinListenerDigital);
	}
	
	
	

	public double getValue() throws Exception {
		int analogVal = pcf8591.analogRead();//0~255
		
		return analogVal; //섭씨 리턴
	}

	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591 = new PCF8591(0x48, PCF8591.AIN0); //i2cAddress는 0x48로 고정. AIN0으로 온도센서와 연결
		FlameSensor test = new FlameSensor(pcf8591, RaspiPin.GPIO_00);
	//방법1: DigitalPin의 상태 이용
//		test.setGpioPinListenerDigital(event->{
//			if(event.getState()==PinState.LOW){
//			System.out.println("********** 화재발생");
//			}else {
//				System.out.println("]********** 정상상태");
//			}
//		});	
		test.setGpioPinListenerDigital(new GpioPinListenerDigital() {
			@Override
			public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				if(event.getState()==PinState.LOW){
			System.out.println("********** 화재발생");
			}else {
				System.out.println("********** 정상상태");
			}
			}
		});


	//방법2: Analog값 이용
//		while(true)
//		{
//			double value = test.getValue();
//			System.out.println(value + "도");
//			Thread.sleep(1000);
//		}
		System.out.println("Ready..");
		System.in.read();
	}

}
