package ch07.book.sec04.Override;

public class ComputerExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int r=10;
		
		Calculator calculator= new Calculator();
		System.out.println(" ������ :"+calculator.areaCircle(r));
		Computer computer= new Computer();
		System.out.println(" ������ :"+computer.areaCircle(r));

	}

}
