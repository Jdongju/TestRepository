package test;

import com.pi4j.io.gpio.RaspiPin;
import hardware.sensor.UltrasonicSensor;
import hardware.buzzer.ActiveBuzzer;
import java.io.IOException;

public class ActiveBuzzerUltrasonicTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		ActiveBuzzer buzzer = new ActiveBuzzer(RaspiPin.GPIO_00);

		UltrasonicSensor ultraSonic = new UltrasonicSensor(RaspiPin.GPIO_04, RaspiPin.GPIO_05);
		while (true) {
			int distance = ultraSonic.getDistance();
			System.out.println("거리(cm): " + distance);
			Thread.sleep(1);
			if (distance < 20) {
				buzzer.on();
			} else {
				buzzer.off();
			}
		}
//		System.out.println("Ready...");
//		System.in.read();

	}
}
