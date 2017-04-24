package ch08.exam01;

public class MainManualExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Bike bike=new Bike();
		bike.turnOn();
		bike.setSpeed(10);    //그냥 클래스 사용법
		bike.run();
		bike.turnOff();*/
		/////////////////////////////////////////////////////////////////
		 Manual manual=new Car();  // 불편한 인터페이스 사용법
		 
		 manual.turnOn();
		 manual.setSpeed(10);
		 manual.run();
		 manual.turnOff();
		 System.out.println();
		 ////////////////////////////////////////////////
		 
		 move(new Bike());         //효율적.
		 move(new Car());         //효율적.
	}
	/*public static void move(Bike bike){} //비효율적
	public static void move(Car car){}*/
	public static void move(Manual manual){    //인터페이스 구현한 모든 객체 올수 있다.
		 manual.turnOn();
		 manual.setSpeed(10);
		 manual.run();
		 manual.turnOff();
	}  
	

}
