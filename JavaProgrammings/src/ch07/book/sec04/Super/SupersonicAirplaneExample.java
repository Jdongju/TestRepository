package ch07.book.sec04.Super;

public class SupersonicAirplaneExample {

	public static void main(String[] args) {
		SupersonicAirPlane sa= new SupersonicAirPlane();
		sa.takeOff();
		sa.fly();
		sa.flyMode=SupersonicAirPlane.SUPERSONIC;
		sa.fly();
		sa.flyMode=SupersonicAirPlane.NORMAL;
		sa.fly();
		sa.land();
	}

}
