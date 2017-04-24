package ch07.book.sec04.Super;

public class SupersonicAirPlane extends AirPlane{

	public static final int NORMAL=1;
	public static final int SUPERSONIC=2;

	public int flyMode=NORMAL;
	
	@Override
	public void fly() {
		if(flyMode==SUPERSONIC){
			System.out.println("초음속비행합니다.");
		}else{
			super.fly();
		}
	}
}
