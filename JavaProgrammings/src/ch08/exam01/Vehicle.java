package ch08.exam01;

public class Vehicle {
	//Field
	private Manual manual;
	//Constructor
	Vehicle(Manual manual){
		this.manual=manual;
	}
	//Method
	public void move(){    //인터페이스 구현한 모든 객체 올수 있다.
		 manual.turnOn();
		 manual.setSpeed(10);                      //다형성 구현
		 manual.run();
		 manual.turnOff();
	}  
}
