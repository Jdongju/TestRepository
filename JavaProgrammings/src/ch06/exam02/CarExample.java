package ch06.exam02;

public class CarExample {

	public static void main(String[] args) {

		// 객체생성코드
		Car mycar = new Car();

		System.out.println(mycar.company);
		System.out.println(mycar.speed);
		System.out.println(mycar.color);
		System.out.println(mycar.airback);

		Car yourcar = new Car("검정", true);
		System.out.println(yourcar.company);
		System.out.println(yourcar.speed);
		System.out.println(yourcar.color);
		System.out.println(yourcar.airback);

		// 생성한 객체변수에 다시 생성 불가능
		
		//yourcar = new Car(true, "빨강");
		System.out.println(yourcar.company);
		System.out.println(yourcar.speed);
		System.out.println(yourcar.color);
		System.out.println(yourcar.airback);

	}

}
