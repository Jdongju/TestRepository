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
		System.out.println("³ë·¡¸¦ ½ÃÀÛÇÕ´Ï´Ù.");
		System.out.println(" ¾î¶² ³ë·¡¸¦ ºÎ¸¦ ±î¿ä?");
		System.out.println("1. ¹ß¶óµå|2.ÈüÇÕ|3.Å¸·É");
		this.battery = battery - 10;
		input = scan.nextInt();
		switch (input) {
		case 1:
			System.out.println("³»¾ÈÀ» ÆÄ°íµå´Â °¡~½Ã~°¡µÇ¾î¾î¾î¾î");

			break;
		case 2:
			System.out.println("®G®G®G ¾Ï´õ ÄÚ¸®¾È Å¾Å¬·¡½º ÈüÇÕ¸ğ¹ü ³ëºí·¹½º");
			break;
		case 3:
			System.out.println("¾¦~~~´ë~¸Ó¾î¾î¸®ÀÌÀÌÀÌ~~~");
			break;

		}

		return this.battery;
	}
}
