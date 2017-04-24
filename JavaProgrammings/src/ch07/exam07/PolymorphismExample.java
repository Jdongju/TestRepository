package ch07.exam07;

public class PolymorphismExample {

	public static void main(String[] args) {
		Tire tire1 = new Tire();
		NormalTire tire2 = new NormalTire();
		SnowTire tire3 = new SnowTire();
		
		run(tire1);
		run(tire2);// tire에 tire2'를 대입한것과 같다.
		run(tire3);//Tire tire=tire3;과 같다.
	}

	static void run(Tire tire) {
		tire.roll();
	}

}
