package ch08.exam01;

public class Vehicle {
	//Field
	private Manual manual;
	//Constructor
	Vehicle(Manual manual){
		this.manual=manual;
	}
	//Method
	public void move(){    //�������̽� ������ ��� ��ü �ü� �ִ�.
		 manual.turnOn();
		 manual.setSpeed(10);                      //������ ����
		 manual.run();
		 manual.turnOff();
	}  
}
