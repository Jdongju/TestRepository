package hardware.sensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class UltrasonicSensor {

	//Field
	private GpioPinDigitalOutput trigPin;
	private GpioPinDigitalInput echoPin;

	private int previousDistance;
	private boolean again;
	private int count;

	//Constructor
	//Method
	public UltrasonicSensor(Pin trigPinNo, Pin echoPinNo) { //센서가 핀을 외부에서 받을 수 있도록 한다.
		GpioController gpio = GpioFactory.getInstance();
		trigPin = gpio.provisionDigitalOutputPin(trigPinNo, PinState.LOW); //처음 상태를 Low로 한다. 준비되었을때 high가 되어 발생시켜야 하므로
		trigPin.setShutdownOptions(true, PinState.LOW); //App이 종료될때 모드를 해제하고(true), Low상태로 만든다.
		echoPin = gpio.provisionDigitalInputPin(echoPinNo);//트리거가 high되면 echo는 high가 되니까 생성시 PinState가 필요 없다.
		echoPin.setShutdownOptions(true, PinState.LOW);
	}

	//한번에 하나의 스레드만 접근 가능하다.
	public int getDistance() {
		//초음파 발신 시간 변수와 수신 시간 변수 선언
		double start = 0;
		double end = 0;

		//초음파를 10 마이크로초 동안 발생
		trigPin.high();
		try {
			Thread.sleep(0, 10000);//0.001micro초 의미. 처음이 밀리, 두번째가 나노
		} catch (Exception e) {
		}
		trigPin.low();

		//발신이 완료되면 echoPin이 High상태가 됨. 발신이 완료될때까지 기다림
		count = 0;
		while (echoPin.isLow()) {
			count++;
			if (count > 50000) {
				return getDistance(); //count가 50000 넘으면 처음부터 다시 측정
			}
		}
//		발신시간을 start에 저장
		start = System.nanoTime();

		//echoPin이 초음음파를 수신하면 Low 상태가 됨(끈 시간 측정)
		count = 0;
		while (echoPin.isHigh()) {
			count++;
			if (count > 50000) {
				return getDistance(); //count가 50000 넘으면 처음부터 다시 측정
			}
		}
		end = System.nanoTime();

		//편도시간(sec)
		double seconds = (end - start) / 1000000000 / 2;
		//거리계산(cm) 2~400
		int distance = (int) (seconds * 34000);

		//100이상 튀는 값이 있을 경우 다시 측정
		if (again == false && Math.abs(previousDistance - distance) > 100) {
			again = true;
			getDistance(); //dummy(가짜로 한번 읽어보는 용도)
			distance = getDistance();//실제 읽는 용도
		} else {//튀는값이 100이하일 경우
			again = false;
		}
		//초과 값 검사(2cm~400cm)
		if (distance < 2) {
			distance = 2;
		} else if (distance > 400) {
			distance = 400;
		}
		previousDistance = distance;//다음 측정을 위해서 오류측정해야하는지 판단하기 위해 저장.
		return distance;
	}

	public static void main(String[] args) throws InterruptedException {
		UltrasonicSensor test = new UltrasonicSensor(RaspiPin.GPIO_04, RaspiPin.GPIO_05);
		while (true) {
			int distance = test.getDistance();
			System.out.println("거리(cm): " + distance);
			Thread.sleep(1000);
		}
	}

}
