package ch06.robot;

import java.util.Scanner;

public class Mouse {
	// Field
	Scanner scan = new Scanner(System.in);
	int input = 0;
	int battery;
	// Constructor

	// Method
	int singing(int battery) {
		System.out.println("노래를 시작합니다.");
		System.out.println(" 어떤 노래를 부를 까요?");
		System.out.println("1. 발라드|2.힙합|3.타령");
		this.battery = battery - 10;
		input = scan.nextInt();
		switch (input) {
		case 1:
			System.out.println("내안을 파고드는 가~시~가되어어어어");

			break;
		case 2:
			System.out.println("췍췍췍 암더 코리안 탑클래스 힙합모범 노블레스");
			break;
		case 3:
			System.out.println("쑥~~~대~머어어리이이이~~~");
			break;

		}

		return this.battery;
	}
}
