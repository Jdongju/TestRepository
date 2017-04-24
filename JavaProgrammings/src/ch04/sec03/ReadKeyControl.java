package ch04.sec03;

import java.io.IOException;

public class ReadKeyControl {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int keycode = 0;

		while (true) {
			if (keycode != 13 && keycode != 10) {
				System.out.println("1.입력|2.출력|3.종료");
				System.out.print("선택: ");
			}
			keycode = System.in.read();

			switch (keycode) {
			case 1:
				System.out.println("입력");
				break;
			case 2:
				System.out.println("출력");
				break;

			case 3:
				System.out.println("종료");
				System.exit(0);
				break;
			}
		}
	}

}
