package ch09.exam05;

public class Example {
	public static void main(String[] args) {
//		B b=new B();		B�� A���� ��� ����
//		A.B b= new A.B();
		A a=new A();
		A.B b= a.new B();
		
//		C c= new C();
		A.C c= new A.C();
	}
}
