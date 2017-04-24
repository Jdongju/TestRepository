package ch06.sec10.watchout_static_method;

public class Car {
	int speed;
	void run(){
		System.out.println(speed+ "으로 달립니다. ");
	}
	
//	정적 메소드와 정적 블록에서 인스턴스 멤버를 사용하고 싶다면 객체를 먼저생성하고
//	참조변수로 접근해야한다.
	public static void main(String[] args){
		Car myCar=new Car();
		myCar.speed=60;
		myCar.run();
	}
}
