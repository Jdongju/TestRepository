package ch07.book.sec04.Override;

public class Computer extends Calculator{

	@Override
	double areaCircle(double r) {
		System.out.println("Computer ��ü�� areaCircle ����");
		return Math.PI*r*r;
	}
}
