package ch06.robot;

import java.util.Scanner;

public class Arm {
	Scanner scan = new Scanner(System.in);

	// Field
	String armName;
	int input = 0;

	// Constructor
	Arm(String name) {
		this.armName = name;
	}
	// Method

	void massage(int input) {

		switch (input) {
		case 1:
			System.out.println(armName + "�� �ٸ� �ֹ���");
			break;
		case 2:
			System.out.println(armName + "�� ��� ������");
			break;
		case 3:
			System.out.println(armName + "�� �� ������");
			break;
		}

	}

}
