package ch08.exam01;

public interface Manual {

	//Field
//	public static final String COMPANY="�Ｚ����";
	 String COMPANY="�Ｚ����";  //��������
	int MAX_SPEED=150;  //�ʵ�� �ݵ�� �������� �ʾƵ� �ȴ�. �ִ� ������ �����Ѵ�.
	int MIN_SPEED=10;                             
	//Method
//	public abstract void turnOn();
	void turnOn(); //��������
	void turnOff();
	void setSpeed(int speed);
	int getSpeed();
	void run();
}
