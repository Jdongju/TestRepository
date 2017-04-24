package ch08.sec04_use.e1_method;

import ch08.sec02.e5_static.RemoteControl;
import ch08.sec03_implement.e1_class.Audio;
import ch08.sec03_implement.e1_class.Television;

public class RemoteControlExample {

	public static void main(String[] args) {
		RemoteControl rc=null;
		
		rc=new Television();
		rc.turnOn();
		rc.turnOff();
		
		rc=new Audio();
		rc.turnOn();
		rc.turnOff();
	}

}
