package ch08.exam01;

public interface Manual {

	//Field
//	public static final String COMPANY="삼성전자";
	 String COMPANY="삼성전자";  //생략가능
	int MAX_SPEED=150;  //필드는 반드시 존재하지 않아도 된다. 최대 최저만 설정한다.
	int MIN_SPEED=10;                             
	//Method
//	public abstract void turnOn();
	void turnOn(); //생략가능
	void turnOff();
	void setSpeed(int speed);
	int getSpeed();
	void run();
}
