package ch07.exam09;

public class Car {
	// Field
	private Tire tire; // Tire, NormalTire, SnowTire 모두 대입가능. 다형성 의미

	// Constructor

	public Car(Tire tire) {
		this.tire = tire;
	}

	// Method
	public void run() {
		tire.roll();
		if (tire instanceof SnowTire) {
			SnowTire snowTire = (SnowTire) tire;
			snowTire.notSlide();
		}
	}

	public void setTire(Tire tire) {
		this.tire = tire;
	}

}
