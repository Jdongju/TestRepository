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
		System.out.println("�뷡�� �����մϴ�.");
		System.out.println(" � �뷡�� �θ� ���?");
		System.out.println("1. �߶��|2.����|3.Ÿ��");
		this.battery = battery - 10;
		input = scan.nextInt();
		switch (input) {
		case 1:
			System.out.println("������ �İ��� ��~��~���Ǿ����");

			break;
		case 2:
			System.out.println("�G�G�G �ϴ� �ڸ��� žŬ���� ���ո�� �����");
			break;
		case 3:
			System.out.println("��~~~��~�Ӿ�������~~~");
			break;

		}

		return this.battery;
	}
}
