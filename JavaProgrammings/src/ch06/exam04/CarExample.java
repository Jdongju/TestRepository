package ch06.exam04;

public class CarExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Car��ü����
		Car myCar = new Car("2017-03-28", "����");
		//----------------------------------------------------
		// Engine�� �޼ҵ� ȣ��
		myCar.engine.start();
		myCar.engine.stop();
		// Dashboard�� �޼ҵ� ȣ��
		myCar.dashboard.display(60);
		//Tire�� �ʵ� �� �б�
		System.out.println(myCar.tires[0].location); 
		//Tire�� �޼ҵ� ȣ��
		myCar.tires[2].roll();
		//----------------------------------------------------
		//Car�� �޼ҵ� ȣ��
		myCar.start();
		myCar.run();
		//----------------------------------------------------
		//��ǰ��ü
		myCar.tires[1]=new Tire("������");
		myCar.run();
		
	}

}