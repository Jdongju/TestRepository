package ch14.exam05;

public class MethodReferenceExample {
	
	public static void main(String[] args) {
		
		method1(new FunctionalInterface1() {
			
			@Override
			public boolean method(String a, String b) {
				return a.equals(b);
			}
		});
		
		method1((a, b) -> {
			return a.equals(b);
		});
		method1((a, b) -> a.equals(b));
		method1(String::equals); //equals가 인스턴스라면 a.equals(b)와 같은 의미이다.
										//equals가 정적이라면 eqauls(a,b)이다.
	}
	
	public static void method1(FunctionalInterface1 i) {
		boolean result = i.method("java", "Java");
		System.out.println(result);
	}
	
}
