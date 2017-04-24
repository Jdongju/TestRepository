package ch11.exam15;

import java.util.Random;

public class RandomEample {
	
	public static void main(String[] args) {
		// Math.random(); 0<=..<1
		// how1
		double random = Math.random();
		
		int maxNum = 6;
		
		int randNum = (int) (random * maxNum) + 1;
		System.out.println(randNum);
		
		// how2
		Random obj = new Random();
		int num2=obj.nextInt(maxNum)+1;
		System.out.println(num2);
	}
	
}
