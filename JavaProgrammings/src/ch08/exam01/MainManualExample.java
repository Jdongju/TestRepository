package ch08.exam01;

public class MainManualExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Bike bike=new Bike();
		bike.turnOn();
		bike.setSpeed(10);    //�׳� Ŭ���� ����
		bike.run();
		bike.turnOff();*/
		/////////////////////////////////////////////////////////////////
		 Manual manual=new Car();  // ������ �������̽� ����
		 
		 manual.turnOn();
		 manual.setSpeed(10);
		 manual.run();
		 manual.turnOff();
		 System.out.println();
		 ////////////////////////////////////////////////
		 
		 move(new Bike());         //ȿ����.
		 move(new Car());         //ȿ����.
	}
	/*public static void move(Bike bike){} //��ȿ����
	public static void move(Car car){}*/
	public static void move(Manual manual){    //�������̽� ������ ��� ��ü �ü� �ִ�.
		 manual.turnOn();
		 manual.setSpeed(10);
		 manual.run();
		 manual.turnOff();
	}  
	

}
