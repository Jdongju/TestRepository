package ch07.exam07;

public class TypeChangeExample {

	public static void main(String[] args) {
		Tire tire1 = new Tire();
		NormalTire tire2 = new NormalTire();
		SnowTire tire3 = new SnowTire();

		// 자동타입변환
		tire1 = tire2;

		// 강제타입변환
		tire2 = (NormalTire) tire1;

		// 자동타입변환
		tire1 = tire3;

		// 강제타입변환
		tire3 = (SnowTire) tire1;

	}


}
