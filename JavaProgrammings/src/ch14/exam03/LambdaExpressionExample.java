package ch14.exam03;

public class LambdaExpressionExample {
	
	public static int a = 5;
	
	public static void main(String[] args) {
		method1(() -> {
			a = 8;
			System.out.println(a);
		});
		
		int b = 5; // ���ú��� final int b=5; �� �����Ǿ� �ִ�/
		method1(() -> {
			// b=8; //finalƯ���� �ֱ� ������ ������ �� ����. �͸�ü���� ���ú����� ���̳��̱� �����̴�. 
			// ������ �����ٰ� �ؼ� �����尡 ������ �ʱ� ����.
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
