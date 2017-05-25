package hardware.motor;

import com.pi4j.gpio.extension.pca.PCA9685GpioProvider;
import com.pi4j.gpio.extension.pca.PCA9685Pin;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import java.math.BigDecimal;

public class PCA9685 {

	//Field
	private static PCA9685 singleton; //PCA9685는 하나만 존재해야한다.
	//그래서 다음과 같이 singleton 형태로 작성한다.

	public static PCA9685 getinstance() throws Exception {
		if (singleton == null) {
			singleton = new PCA9685(); //null일 경우에만 객체 생성해서 singleton에 대입.
		}
		return singleton;
	}

	private PCA9685GpioProvider gpioProvider; //pi4j에서 재공하는 gpioProvider;

	//PCA9685의 드라이버 만드는 개념.
	private static final Pin PWM_00 = PCA9685Pin.PWM_00;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_01 = PCA9685Pin.PWM_01;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_02 = PCA9685Pin.PWM_02;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_03 = PCA9685Pin.PWM_03;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_04 = PCA9685Pin.PWM_04;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_05 = PCA9685Pin.PWM_05;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_06 = PCA9685Pin.PWM_06;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_07 = PCA9685Pin.PWM_07;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_08 = PCA9685Pin.PWM_08;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_09 = PCA9685Pin.PWM_09;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_10 = PCA9685Pin.PWM_10;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_11 = PCA9685Pin.PWM_11;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_12 = PCA9685Pin.PWM_12;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_13 = PCA9685Pin.PWM_13;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_14 = PCA9685Pin.PWM_14;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입
	private static final Pin PWM_15 = PCA9685Pin.PWM_15;//pi4j에서 재공하는 pca9685pin을 gpioPin에 대입

	private int period; //해당 장치의 period 저장할 수 있는 필드.

	//Contructor
	private PCA9685() throws Exception {
		I2CBus i2CBus = I2CFactory.getInstance(I2CBus.BUS_1); //I2C통신을 위한 I2C버스를 생성하며 버스번호는 1번
		//0x40: PCA9685 모듈의 I2C장치 번호(i2cdetect -y 1)로 확인
		//PWM주파수를 50Hz로 설정(SG90 Servo Motor가 50Hz에서 동작하기 때문)
		gpioProvider = new PCA9685GpioProvider(i2CBus, 0x40, new BigDecimal(50));//gpioProvider(Pin의 정보를 갖고있는 객체)에서 버스번호, PCA9685 모듈의 I2C장치번호, 주파수 설정(서보모터는 50Hz);
		//한 사이클당 시간(period): 1초/Frequency= 1/50Hz=0.02s=20ms=20000us
		period = gpioProvider.getPeriodDurationMicros(); //20000.

		//GPIO PWM 출력핀 설정.(외부장치의 provider를 통해 외부장치 제어 가능)
		GpioController gpioController = GpioFactory.getInstance();
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_00); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_01); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_02); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_03); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_04); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_05); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_06); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_07); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_08); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_09); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_10); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_11); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_12); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_13); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_14); //PCA9685에서 제공하는 핀을 provider에 넣는다.
		gpioController.provisionPwmOutputPin(gpioProvider, PWM_15); //PCA9685에서 제공하는 핀을 provider에 넣는다.

		gpioProvider.reset();//초기화
	}

	public void setDuration(Pin pin, int duration) {
		//duration: 0~19999us(서보모터는 한 사이클의 period가 20ms이다. 사이클 내에서 duration(high)가 한번은 low가 되어야하기 때문에 
		//duration 범위가 20ms다. 20ms일 경우 사이클이 형성되지 않음
		//장치마다  duration범위가 다름
		if (duration >= period) {
			duration = (period - 1);
		} else if (duration < 0) {
			duration = 0;
		}
		if (duration != 0) {
			gpioProvider.setPwm(pin, duration); //duration으로 조절.
		} else {
			gpioProvider.setAlwaysOff(pin); //전부 0이면 끈다.
		}

	}

	public void setStep(Pin pin, int step) {
		//step: 0~4095 장치마다 다르지 않고 PCA9685이면 고정
		if (step >= 4096) {
			step = 4095;
		} else if (step < 0) {
			step = 0;
		}
		if (step != 0) { //step이 0이면 전부다 LOW이기 때문에 사이클을 형성하지 않는다.
			gpioProvider.setPwm(pin, 0, step);// 0부터 step까지를 high로 하겠다는 의미.
		} else {
			gpioProvider.setAlwaysOff(pin); //전부 0이면 끈다.
		}
	}

	//Method
	public static void main(String[] args) throws Exception {
		PCA9685 pca9685 = PCA9685.getinstance();//싱글톤 형태로 객체가 하나만 존재하도록 한다.

		while (true) {
			pca9685.setStep(PWM_00, 164);//164단계를 주면 0도
			Thread.sleep(2000);
			pca9685.setStep(PWM_00, 358);//358단계를 주면 90도
			Thread.sleep(2000);
			pca9685.setStep(PWM_00, 552);//552단계를 주면 180도
			Thread.sleep(2000);
		}

//		while(true){
//			pca9685.setDuration(PWM_00, 800);//0도로 회전
//			Thread.sleep(2000);
//			pca9685.setDuration(PWM_00,(800+2700)/2 );//90도로 회전
//			Thread.sleep(2000);
//			pca9685.setDuration(PWM_00, 2700);//180도로 회전
//			Thread.sleep(2000);
//		}
	}
}
