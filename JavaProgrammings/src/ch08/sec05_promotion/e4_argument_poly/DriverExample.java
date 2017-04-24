package ch08.sec05_promotion.e4_argument_poly;

public class DriverExample {

	public static void main(String[] args) {
		Driver driver=new Driver();
		
		Bus bus=new Bus();
		Taxi taxi=new Taxi();
		
		driver.drive(taxi);
		driver.drive(bus);
	}

}
