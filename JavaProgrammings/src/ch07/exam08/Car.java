package ch07.exam08;

public class Car {
	// Field
	private Tire tire; // Tire, NormalTire, SnowTire ��� ���԰���. ������ �ǹ�

	// Constructor
	
	public Car(Tire tire){
		this.tire=tire;
	}
	// Method
	public void run(){
		tire.roll();
	}
	public void setTire(Tire tire) {
		this.tire = tire;
	}
	
}
