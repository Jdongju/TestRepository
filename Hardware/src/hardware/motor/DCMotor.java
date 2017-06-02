package hardware.motor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import static hardware.motor.PCA9685.PWM_01;

public class DCMotor {
	//Field

	//정방향 역방향 제어
	private GpioPinDigitalOutput out1; // RaspiPin.GPIO_00 라즈베리파이가 모터 드라이버에 아웃풋을 보낸다.
	private GpioPinDigitalOutput out2;// RaspiPin.GPIO_01 아웃1과 아웃2가 달라야 동작. HL는 정방향 LH는 역방향
	//PWM으로 속도 제어
	private PCA9685 pca9685;
	private Pin pwm;//PCA9685PWM_00~PCA9685.PWM_15

	//Constructor
//Method
	public DCMotor(Pin out1, Pin out2, PCA9685 pca9685, Pin pwm) {
		GpioController gpioController = GpioFactory.getInstance();
		this.out1 = gpioController.provisionDigitalOutputPin(out1, PinState.LOW); //초기 상태를 LOW로 한다.
		this.out1.setShutdownOptions(true, PinState.LOW);//셧다운 옵션 활성화, PinState는 LoW로 한다.
		this.out2 = gpioController.provisionDigitalOutputPin(out2, PinState.LOW); //초기 상태를 LOW로 한다.
		this.out2.setShutdownOptions(true, PinState.LOW);//셧다운 옵션 활성화, PinState는 LoW로 한다.
		this.pca9685 = pca9685;
		this.pwm = pwm;
	}

	public void setSpeed(int step) {
		//step(총 4096단계): 0~4096
		pca9685.setStep(pwm, step);
	}

	public void forward() {
		out1.high();
		out2.low();
	}

	public void backward() {
		out1.low();
		out2.high();
	}

	public void stop() {
		out1.low();
		out2.low();
		setSpeed(0);
	}

	public static void main(String[] args) throws Exception {
		PCA9685 pca9685 = PCA9685.getinstance();
		
		DCMotor motorA= new DCMotor(RaspiPin.GPIO_00, RaspiPin.GPIO_01, pca9685, PCA9685.PWM_05);
		DCMotor motorB= new DCMotor(RaspiPin.GPIO_02, RaspiPin.GPIO_03, pca9685, PCA9685.PWM_04);
		
		motorA.forward();
		motorA.setSpeed(4095);
		
		motorB.forward();
		motorB.setSpeed(4095);
		
		pca9685.setStep(PWM_01, 358);
		Thread.sleep(5000);
		motorA.stop();
		motorB.stop();
	}
}
