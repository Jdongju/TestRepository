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
		System.out.println("얼만큼 충전하시겠습니까?(1~100%)");
		System.out.print(">:");
		this.batteryAmount += scan.nextInt();
		System.out.println("충전중입니다.");
		System.out.println("+++");
		System.out.println("+++");
		System.out.println("충전이 다되었습니다.");
		System.out.println("현재 잔량: " + this.batteryAmount + "%");
		return this.batteryAmount;
	}
}
