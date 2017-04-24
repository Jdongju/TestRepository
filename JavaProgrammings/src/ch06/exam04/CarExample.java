package ch06.exam04;

public class CarExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Car객체생성
		Car myCar = new Car("2017-03-28", "검정");
		//----------------------------------------------------
		// Engine의 메소드 호출
		myCar.engine.start();
		myCar.engine.stop();
		// Dashboard의 메소드 호출
		myCar.dashboard.display(60);
		//Tire의 필드 값 읽기
		System.out.println(myCar.tires[0].location); 
		//Tire의 메소드 호출
		myCar.tires[2].roll();
		//----------------------------------------------------
		//Car의 메소드 호출
		myCar.start();
		myCar.run();
		//----------------------------------------------------
		//부품교체
		myCar.tires[1]=new Tire("새앞좌");
		myCar.run();
		
	}

}
