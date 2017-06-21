package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.sensor.TrackingSensor;
import hardware.buzzer.ActiveBuzzer;
import hardware.converter.PCF8591;
import hardware.lcd.LCD1602;
import static hardware.lcd.LCD1602.displayIPaddress;
import hardware.led.Laser;
import hardware.led.RgbLedDigital;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.motor.SG90ServoPCA9685Step;
import hardware.sensor.GasSensor;
import hardware.sensor.PhotoresistorSensor;
import hardware.sensor.ThermistorSensor;
import hardware.sensor.UltrasonicSensor;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarPrepare {

	public static void main(String[] args) throws Exception {
		PCF8591 pcf8591Photoresistor = new PCF8591(0x48, PCF8591.AIN0); //photoresisotr
		PCF8591 pcf8591Thermistor = new PCF8591(0x48, PCF8591.AIN1); //Thermo
PCF8591 pcf8591Gas = new PCF8591(0x48, PCF8591.AIN2); //가스
		LCD1602 lcd1602 = new LCD1602(0x27);
		PhotoresistorSensor photoresistorSensor = new PhotoresistorSensor(pcf8591Photoresistor);
		TrackingSensor trackingSensor = new TrackingSensor(RaspiPin.GPIO_26);
		GasSensor gasSensor = new GasSensor(pcf8591Gas, RaspiPin.GPIO_23);
		ThermistorSensor thermistorSensor = new ThermistorSensor(pcf8591Thermistor);
		RgbLedDigital rgbLedDigital = new RgbLedDigital(RaspiPin.GPIO_04, RaspiPin.GPIO_05, RaspiPin.GPIO_06);
		ActiveBuzzer activeBuzzer = new ActiveBuzzer(RaspiPin.GPIO_24);
		Laser laser = new Laser(RaspiPin.GPIO_25);
		UltrasonicSensor ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		
//		Laser
//		while (true) {			
//			laser.shoot();
//			Thread.sleep(1000);
//			laser.shoot1();
//			Thread.sleep(1000);
//		}

	//ActiveBuzzer
//	 for (int i = 0; i < 100; i++) {
//			activeBuzzer.on();
//			Thread.sleep(200);
//			activeBuzzer.off();
//			Thread.sleep(200);
//		}

////RgbLedDigital
//while (true) {			
//			rgbLedDigital.rgb(true,false,false);
//			Thread.sleep(1000);
//			rgbLedDigital.rgb(false,true,false);
//			Thread.sleep(1000);
//			rgbLedDigital.rgb(false,false,true);
//			Thread.sleep(1000);
//		}

//Thermosistor 
//		while(true)
//		{
//			double value = thermistorSensor.getValue();
//			System.out.println(value + "도");
//			Thread.sleep(1000);
//		}

//gas
//while (true) {
//			double value = gasSensor.getValue();
//			System.out.println(value);
//			if (value > 100) {
////				analog값을 이용해서 처리.
//			}
//			Thread.sleep(1000);
//		}
		
//trackingSensor
//trackingSensor.setGpioPinListenerDigital(event->{
//			if(event.getState()==PinState.HIGH){
//				System.out.println("Black");
//			}else{
//				System.out.println("White");
//			}
//		});
//		System.out.println("Ready..");
//		System.in.read();

//Photoresistor
//while(true)
//		{
//			double value = photoresistorSensor.getValue();
//			System.out.println(value + "도");
//			Thread.sleep(1000);
//		}

//LCD
//lcd1602.write(0, 0, "Current Time");
//		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//		for (int i = 0; i < 10; i++) {
//			lcd1602.write(1, 0, formatter.format(new Date()));
//			Thread.sleep(1000);
//		}
//		
//		lcd1602.clear();
//
//		displayIPaddress(lcd1602);
//		Thread.sleep(5000);

//UltraSonic : 안됨
//while (true) {
//			int distance = ultrasonicSensor.getDistance();
//			System.out.println("거리(cm): " + distance);
//			Thread.sleep(1000);
//		}

PCA9685 pca9685= PCA9685.getInstance();
		SG90ServoPCA9685Step ultraServo= new SG90ServoPCA9685Step(pca9685, PCA9685.PWM_11); //ultra
		SG90ServoPCA9685Step steeringServo= new SG90ServoPCA9685Step(pca9685, PCA9685.PWM_00); //steering
SG90ServoPCA9685Step leftRightServo= new SG90ServoPCA9685Step(pca9685, PCA9685.PWM_14);// 좌우운동. 10도일때 중앙을 향해야 함.
SG90ServoPCA9685Step upDownServo= new SG90ServoPCA9685Step(pca9685, PCA9685.PWM_15);// 상하운동. 90도일떄 정면을 바라보아야 함
		
//		for (int i = 10; i <= 170; i+=10) {
//			ultraServo.setAngle(i);
//			Thread.sleep(500);
//		}
//		for (int i = 10; i <= 170; i+=10) {
//			steeringServo.setAngle(i);
//			Thread.sleep(500);
//		}
//		for (int i = 10; i <= 170; i+=10) {
//			upDownServo.setAngle(i);
//			Thread.sleep(500);
//		}
//		for (int i = 10; i <= 170; i+=10) {
//			leftRightServo.setAngle(i);
//			Thread.sleep(500);
//		}
//servo.setAngle(160);
//		servo.setAngle(10);
//		servo.setAngle(0);
//		servo.setAngle(90);
//		servo.setAngle(180);
		//servo.setAngle(270);
		//servo.setAngle(360);
		
//		ultraServo.setAngle(90);
//		steeringServo.setAngle(90);
//		upDownServo.setAngle(0);
//		leftRightServo.setAngle(10);
	}
}
