package ch07.exam08;

public class SuperTire extends Tire{

	public SuperTire() {
		super();
		System.out.println("SuperTire °´Ã¼ »ý¼º");
	}
	@Override
	void roll() {
		System.out.println("SuperTire°¡ ±¼·¯°©´Ï´Ù.");
	}
	
}
