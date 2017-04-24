package ch10.exam01;

import java.io.IOException;

public class RuntimeExceptionExample {
	public static void main(String[] args) {
		/*
		 * String str = null; System.out.println(str.length());
		 */

		// String data1=args[0];

	/*	String[]arr={"a","b"};
		String data1=arr[2];*/
		
//		String data1=args[0];
		
//		int num=Integer.parseInt("a100");
		
		/*Animal animal=new Dog();
		Cat cat=(Cat) animal;*/
		
		try {
			int keyCode=System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
