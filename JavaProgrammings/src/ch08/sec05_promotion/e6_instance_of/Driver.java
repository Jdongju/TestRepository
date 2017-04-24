package ch08.sec05_promotion.e6_instance_of;

import ch08.sec05_promotion.e5_casting.Bus;
import ch08.sec05_promotion.e5_casting.Vehicle;

public class Driver {
	public void drive(Vehicle vehicle){
		if (vehicle instanceof Bus) {
			Bus bus=(Bus) vehicle;
			bus.checkFare();
		}
		vehicle.run();
	}
}
