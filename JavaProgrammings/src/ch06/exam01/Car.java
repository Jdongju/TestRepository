package ch06.exam01;

public class Car {
	
	//Field
	String company ="현대자동차" ;
	int speed;
	String color;
	boolean airback;
	
	//Constructor
	Car(){
		//...코드(객체를 어떻게 만들어야 된다라는 내용 삽입)
		
		System.out.println("생성");
	}
	
	//Method
	void run(){
		System.out.println("현재"+speed+"km/h로 달립니다.");
	}

}
