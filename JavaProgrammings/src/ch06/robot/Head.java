package ch06.robot;

import java.util.Scanner;

public class Head {
	// Field
	int batteryAmount = 0;
	Scanner scan = new Scanner(System.in);
	// Constructor
	// Method

	int charging(int batteryAmount) {
		System.out.println();
		this.batteryAmount = batteryAmount;
		System.out.println("��ŭ �����Ͻðڽ��ϱ�?(1~100%)");
		System.out.print(">:");
		this.batteryAmount += scan.nextInt();
		System.out.println("�������Դϴ�.");
		System.out.println("+++");
		System.out.println("+++");
		System.out.println("������ �ٵǾ����ϴ�.");
		System.out.println("���� �ܷ�: " + this.batteryAmount + "%");
		return this.batteryAmount;
	}
}
