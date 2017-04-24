package ch14.exam03;

public class LambdaExpressionExample {
	
	public static int a = 5;
	
	public static void main(String[] args) {
		method1(() -> {
			a = 8;
			System.out.println(a);
		});
		
		int b = 5; // 로컬변수 final int b=5; 가 생략되어 있다/
		method1(() -> {
			// b=8; //final특성이 있기 때문에 변경할 수 없다. 익명객체에서 로컬변수는 파이널이기 때문이다. 
			// 메인이 끝났다고 해서 스레드가 멈추지 않기 때문.
			/*while (true) {
				System.out.println(b);
			}*/
			
			 System.out.println(b);
		});
		
	}
	
	public static void method1(FunctionalInterface1 i) {
		i.method();
	}
	
}
