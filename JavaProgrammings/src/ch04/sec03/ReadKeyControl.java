package ch04.sec03;

import java.io.IOException;

public class ReadKeyControl {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int keycode = 0;

		while (true) {
			if (keycode != 13 && keycode != 10) {
				System.out.println("1.�Է�|2.���|3.����");
				System.out.print("����: ");
			}
			keycode = System.in.read();

			switch (keycode) {
			case 1:
				System.out.println("�Է�");
				break;
			case 2:
				System.out.println("���");
				break;

			case 3:
				System.out.println("����");
				System.exit(0);
				break;
			}
		}
	}

}
