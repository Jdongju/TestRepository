package ch06.exam01;

public class Car {
	
	//Field
	String company ="�����ڵ���" ;
	int speed;
	String color;
	boolean airback;
	
	//Constructor
	Car(){
		//...�ڵ�(��ü�� ��� ������ �ȴٶ�� ���� ����)
		
		System.out.println("����");
	}
	
	//Method
	void run(){
		System.out.println("����"+speed+"km/h�� �޸��ϴ�.");
	}

}
