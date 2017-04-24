package ch06.exam06;

public class CalculatorExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Calculator.field2);
		Calculator.method2();
		
		Calculator calc=new Calculator();
		System.out.println(calc.field2); //인스턴스 방식으로 스태틱 메소드 불러도 되지만 경고. 
												//물론 스태틱 메소드는 항상 존재하니까 실행은 된다.
												//되도록 스태틱은 클래스 명으로 접근하는것이 좋다.
		calc.method1();
	}

}
