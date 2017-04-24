package ch11.exam14;

public class WrapperExample {
	
	public static void main(String[] args) {
		int v1 = 10;
		
		Integer v2 = 10; // Auto boxing :10을 가지고있는 Integer객체를 v2에 대입한다.
		int v3 = v2; // Auto unboxing v2가 가지고 있는 10을 꺼내어 int형 변수 v3에 대입한다.
		
		method(3);	//auto boxing
		int v4=method2(); //auto unboxing
	}
	
	public static void method(Object obj) {
		
	}
	public static Integer method2() {
		return 1;
	}
	
}
