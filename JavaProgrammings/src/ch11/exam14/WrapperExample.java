package ch11.exam14;

public class WrapperExample {
	
	public static void main(String[] args) {
		int v1 = 10;
		
		Integer v2 = 10; // Auto boxing :10�� �������ִ� Integer��ü�� v2�� �����Ѵ�.
		int v3 = v2; // Auto unboxing v2�� ������ �ִ� 10�� ������ int�� ���� v3�� �����Ѵ�.
		
		method(3);	//auto boxing
		int v4=method2(); //auto unboxing
	}
	
	public static void method(Object obj) {
		
	}
	public static Integer method2() {
		return 1;
	}
	
}
