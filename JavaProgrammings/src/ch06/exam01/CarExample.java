package ch06.exam01;

public class CarExample {

	public static void main(String[] args) {
		
		//��ü�����ڵ�
		Car mycar=new Car();
		//��ü�޼ҵ�ȣ��
		mycar.run();
		//��ü �ʵ��б�
		System.out.println(mycar.company);
		System.out.println(mycar.speed);
		System.out.println(mycar.color);
		System.out.println(mycar.airback);
		
		//��ü�� �ʵ尪�� ����
		mycar.speed = 30;
		System.out.println(mycar.speed);
		
		mycar.run();
		
		mycar.color="���";
		System.out.println(mycar.color);
		//================================
		
		Car yourcar=new Car(); //�ι�° ��ü����
		System.out.println(yourcar.company);
		System.out.println(yourcar.speed);
		System.out.println(yourcar.color);
		System.out.println(yourcar.airback);

	}

}
