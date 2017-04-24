package ch08.sec05_promotion.e2_field_poly;

public class Car {
	Tire frontLeftTire=new HankookTIre();
	Tire frontRightTire=new HankookTIre();
	Tire backLeftTire=new HankookTIre();
	Tire backRightTire=new HankookTIre();
	
	void run(){
		frontLeftTire.roll();
		frontRightTire.roll();
		backLeftTire.roll();
		backRightTire.roll();
	}
}
