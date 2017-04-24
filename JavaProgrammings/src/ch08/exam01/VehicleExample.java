package ch08.exam01;

public class VehicleExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle vehicle1=new Vehicle(new Bike());
		vehicle1.move();
		
		Vehicle vehicle2=new Vehicle(new Car());
		vehicle2.move();
	}

}
