package ch11.exam09;

import java.io.UnsupportedEncodingException;

public class HomeWork {
	public static void main(String[] args) {
		String str = "123";
	
		try {
			byte[] bytes3 = str.getBytes("UTF-16");
			System.out.println("byte3.length: " + bytes3.length);
			String str3 = new String(bytes3, "UTF-16");
			for (int i = 0; i < bytes3.length; i++) {
//				System.out.print(bytes3[i]+",");
				System.out.format("%02X ",bytes3[i]);
			}
			
//			System.out.println("bytes3->String: " + str3);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
