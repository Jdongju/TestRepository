package ch08.sec05_promotion.e2_field_poly;

public class CarExample {
	public static void main(String[] args) {
		Car myCar=new Car();
		
		myCar.run();
		
		myCar.frontLeftTire=new KumhoTIre();
		myCar.frontRightTire=new KumhoTIre();
		
		myCar.run();
	}
}
