package ch06.robot;

import java.util.Scanner;

public class Robot {

	Scanner scan = new Scanner(System.in);
	int input = 0;
	// Field
	// ��ǰ
	Arm[] arms = { new Arm("����"), new Arm("������") };

	Ear[] ears = { new Ear("���ʱ�"), new Ear("�����ʱ�") };
	Eye[] eyes = { new Eye("���ʴ�"), new Eye("�����ʴ�") };
	Head head = new Head();
	Leg[] legs = { new Leg(), new Leg() };
	Mouse mouse = new Mouse();

	// ����������
	String productionDate;
	String owner;

	// ���µ�����
	int batteryAmount = 50;

	// Constructor
	Robot(String productionDate, String owner) {
		this.productionDate = productionDate;
		this.owner = owner;
	}

	// Method
	void start() {
		System.out.println("�κ��� �۵� ����");
		displayInfo();
		displayBattery(batteryAmount);
		while (true) {
			input = receivingInput();
			switcher(input);
			if (batteryAmount <= 0) {
				System.out.println("������ �����մϴ�.");
				System.out.println("�����մϴ�.");
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
			System.out.println("�����մϴ�.");
			System.exit(0);
			break;
		}
	}

	void displayBattery(int batteryAmount) {
		System.out.println();
		System.out.println("���� ���͸� �ܷ��� " + batteryAmount + "%�Դϴ�.");
	}

	void displayInfo() {
		System.out.println("�����: " + this.owner + " \n" + "������:" + this.productionDate);
	}

	void projecting(Eye eye1, Eye eye2) {
		System.out.println(" ���縦 �����մϴ�.");
		eye1.projecting();
		eye2.projecting();
		System.out.println(" Once upon a time...");
		this.batteryAmount -= 10;
		displayBattery(batteryAmount);
	}

	void massage() {
		System.out.println();
		System.out.println("�ȸ����� ���� �����Ͻʽÿ�");
		System.out.println("1.�ٸ� | 2.��� 3.��");
		System.out.print(">");
		input = scan.nextInt();
		if (input == 1) {
			System.out.println("����� �ּ���");
		} else if (input == 2) {
			System.out.println("����̿� ��� �ּ���");
		} else if (input == 3) {
			System.out.println("�����ּ���");
		}
		arms[0].massage(input);
		arms[1].massage(input);
		this.batteryAmount -= 10;
		displayBattery(batteryAmount);
	}

	int receivingInput() {
		System.out.println();
		System.out.println("���ϴ� ������ �Է��ϼ���");
		System.out.println("1. �뷡�θ��� | 2. ���������� | 3. �ȸ�");
		System.out.println("4. �¾籤 ���� | 5. ����");
		System.out.print(">");
		input = scan.nextInt();
		return input;
	}
}
