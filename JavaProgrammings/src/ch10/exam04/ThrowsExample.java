package ch10.exam04;

import java.io.IOError;

public class ThrowsExample {

	public static void main(String[] args) {
		try {
			Class.forName("");
			System.in.read();
		} catch (Exception e) {
			// TODO: handle exception

		}
		try {
			divide(10, 0);
		} catch (ArithmeticException e) {
			System.out.println("�ٽ��Է��ϼ���.");
		}
		

	}

	static void divide(int x, int y) throws ArithmeticException{
	/*	try {
			int result = x / y;
		} catch (ArithmeticException e) {
			// TODO: handle exception
			System.out.println("0���� ������ �ȵ˴ϴ�.");
		}*/
		int result = x / y;

	}
}
