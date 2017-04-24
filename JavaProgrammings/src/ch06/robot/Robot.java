package ch06.robot;

import java.util.Scanner;

public class Robot {

	Scanner scan = new Scanner(System.in);
	int input = 0;
	// Field
	// 부품
	Arm[] arms = { new Arm("왼팔"), new Arm("오른팔") };

	Ear[] ears = { new Ear("왼쪽귀"), new Ear("오른쪽귀") };
	Eye[] eyes = { new Eye("왼쪽눈"), new Eye("오른쪽눈") };
	Head head = new Head();
	Leg[] legs = { new Leg(), new Leg() };
	Mouse mouse = new Mouse();

	// 고유데이터
	String productionDate;
	String owner;

	// 상태데이터
	int batteryAmount = 50;

	// Constructor
	Robot(String productionDate, String owner) {
		this.productionDate = productionDate;
		this.owner = owner;
	}

	// Method
	void start() {
		System.out.println("로봇이 작동 시작");
		displayInfo();
		displayBattery(batteryAmount);
		while (true) {
			input = receivingInput();
			switcher(input);
			if (batteryAmount <= 0) {
				System.out.println("전원이 부족합니다.");
				System.out.println("종료합니다.");
				return;
			}
		}

	}

	void switcher(int input) {
		switch (input) {

		case 1:
			batteryAmount = mouse.singing(batteryAmount);
			displayBattery(batteryAmount);
			break;
		case 2:
			projecting(eyes[0], eyes[1]);
			break;

		case 3:
			massage();
			break;

		case 4:
			displayBattery(batteryAmount);
			batteryAmount = head.charging(batteryAmount);
			break;

		case 5:
			System.out.println("종료합니다.");
			System.exit(0);
			break;
		}
	}

	void displayBattery(int batteryAmount) {
		System.out.println();
		System.out.println("현재 배터리 잔량은 " + batteryAmount + "%입니다.");
	}

	void displayInfo() {
		System.out.println("사용자: " + this.owner + " \n" + "생산일:" + this.productionDate);
	}

	void projecting(Eye eye1, Eye eye2) {
		System.out.println(" 영사를 시작합니다.");
		eye1.projecting();
		eye2.projecting();
		System.out.println(" Once upon a time...");
		this.batteryAmount -= 10;
		displayBattery(batteryAmount);
	}

	void massage() {
		System.out.println();
		System.out.println("안마받을 곳을 선택하십시오");
		System.out.println("1.다리 | 2.어깨 3.등");
		System.out.print(">");
		input = scan.nextInt();
		if (input == 1) {
			System.out.println("엎드려 주세요");
		} else if (input == 2) {
			System.out.println("등받이에 기대 주세요");
		} else if (input == 3) {
			System.out.println("누워주세요");
		}
		arms[0].massage(input);
		arms[1].massage(input);
		this.batteryAmount -= 10;
		displayBattery(batteryAmount);
	}

	int receivingInput() {
		System.out.println();
		System.out.println("원하는 동작을 입력하세요");
		System.out.println("1. 노래부르기 | 2. 빔프로젝터 | 3. 안마");
		System.out.println("4. 태양광 충전 | 5. 종료");
		System.out.print(">");
		input = scan.nextInt();
		return input;
	}
}
