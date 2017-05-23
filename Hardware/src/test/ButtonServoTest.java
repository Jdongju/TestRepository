package test;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import hardware.button.Button;
import hardware.motor.SG90Servo;
import java.io.IOException;

public class ButtonServoTest {

	public static void main(String[] args) throws IOException {
		Button button = new Button(RaspiPin.GPIO_00);
		SG90Servo servo = new SG90Servo(RaspiPin.GPIO_01);

		button.setGpioPinListenerDigital((event) -> {
			if (event.getState() == PinState.LOW) {
				servo.setAngle(90);
			} else {
				servo.setAngle(0);
			}
		});
		System.out.println("Ready....");
		System.in.read(); //키보드 입력까지 대기하도록하여 바로 꺼지는 것을 방지.
	}

}
