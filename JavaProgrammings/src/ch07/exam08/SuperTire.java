package ch07.exam08;

public class SuperTire extends Tire{

	public SuperTire() {
		super();
		System.out.println("SuperTire ��ü ����");
	}
	@Override
	void roll() {
		System.out.println("SuperTire�� �������ϴ�.");
	}
	
}
