package ch06.exam01;

public class CarExample {

	public static void main(String[] args) {
		
		//객체생성코드
		Car mycar=new Car();
		//객체메소드호출
		mycar.run();
		//객체 필드읽기
		System.out.println(mycar.company);
		System.out.println(mycar.speed);
		System.out.println(mycar.color);
		System.out.println(mycar.airback);
		
		//객체의 필드값을 변경
		mycar.speed = 30;
		System.out.println(mycar.speed);
		
		mycar.run();
		
		mycar.color="흰색";
		System.out.println(mycar.color);
		//================================
		
		Car yourcar=new Car(); //두번째 객체생성
		System.out.println(yourcar.company);
		System.out.println(yourcar.speed);
		System.out.println(yourcar.color);
		System.out.println(yourcar.airback);

	}

}
