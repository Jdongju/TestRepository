package ch06.sec10.watchout_static_method;

public class Car {
	int speed;
	void run(){
		System.out.println(speed+ "���� �޸��ϴ�. ");
	}
	
//	���� �޼ҵ�� ���� ��Ͽ��� �ν��Ͻ� ����� ����ϰ� �ʹٸ� ��ü�� ���������ϰ�
//	���������� �����ؾ��Ѵ�.
	public static void main(String[] args){
		Car myCar=new Car();
		myCar.speed=60;
		myCar.run();
	}
}
