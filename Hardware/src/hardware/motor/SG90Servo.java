package hardware.motor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;

public class SG90Servo {

	//Field
	private GpioPinPwmOutput pin;
	private int angle;
	private double minStep;
	private double maxStep;

	//Constructor
	public SG90Servo(Pin pin) {
		this(pin, 8, 27);
	}
	
	public SG90Servo(Pin pinNo, double minStep, double maxStep) {
		GpioController gpioController = GpioFactory.getInstance();
		pin = gpioController.provisionPwmOutputPin(pinNo);
		Gpio.pwmSetMode(Gpio.PWM_MODE_MS);//Mark-Space 모드로 바꾼다.
		Gpio.pwmSetClock(1920);//라즈베리파이의 원래 주파수 19.2Mhz 고려. PWM클럭. 이를 이용해 50Hz만드러고함.
		Gpio.pwmSetRange(200);//PWM범위
		this.minStep = minStep;
		this.maxStep = maxStep;
	}

	//Method
	public int getAngle() {
		return angle;
	}
	
	public void setAngle(int angle) {
		if (angle < 0) {
			angle = 0;
		} else if (angle > 180) {
			angle = 180;
		}
		this.angle = angle;
		int step = (int) (minStep + (angle * (maxStep - minStep) / 180.0));
		pin.setPwm(step);
	}
	
	public static void main(String[] args) throws InterruptedException {
		SG90Servo servo = new SG90Servo(RaspiPin.GPIO_01, 8, 27);
//		while(true){
//			servo.setAngle(0);
//			Thread.sleep(2000);
//			servo.setAngle(90);
//			Thread.sleep(2000);
//			servo.setAngle(180);
//			Thread.sleep(2000);
//			servo.setAngle(90);
//			Thread.sleep(2000);
//		}
		for (int i = 0; i <180; i+=10) {
			servo.setAngle(i);
			Thread.sleep(500);
		}
		servo.setAngle(0);
		Thread.sleep(0);
	}
}
