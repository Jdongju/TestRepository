package ch06.exam03;

public class Calculator {

	// Field

	// Constructor

	// Method

	void powerOn() {
		System.out.println("전원을 켭니다.");

	}

	void powerOff() {
		System.out.println("전원을 끕니다.");

	}

	String info() {
		return "삼성전자-2017-03-28";
	}

	int plus(int x, int y) {
		int result = x + y;
		return result;
	}
	
	//메소드 오버로딩(매개변수의 수, 매개변수의 타입만 다른 경우를 말한다.)
	double plus(double x, double y) {
		double result = x + y;
		return result;
	}

	double divide(double x, double y) {
		double result = x / y;
		return result;
	}

	int[] changeArray(int x, int y, int z) {
		int[] array = { x, y, z };
		return array;
	}

	Car makeCar(String color, boolean airback) {
		Car car = new Car(color, airback);
		return car;
	}

}
