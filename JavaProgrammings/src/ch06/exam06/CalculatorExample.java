package ch06.exam06;

public class CalculatorExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Calculator.field2);
		Calculator.method2();
		
		Calculator calc=new Calculator();
		System.out.println(calc.field2); //�ν��Ͻ� ������� ����ƽ �޼ҵ� �ҷ��� ������ ���. 
												//���� ����ƽ �޼ҵ�� �׻� �����ϴϱ� ������ �ȴ�.
												//�ǵ��� ����ƽ�� Ŭ���� ������ �����ϴ°��� ����.
		calc.method1();
	}

}
