package ch11.sec16_java.time;

import java.time.LocalDate;

public class DateTimeCreateExample {
	
	public static void main(String[] args) {
		//��¥���
		LocalDate currDate=LocalDate.now();
		System.out.println("���� ��¥: "+currDate);
		
		LocalDate targetDate=LocalDate.of(2024, 5, 10);
//		System.out.println("��ǥ ��¥ : "+targetDate+"\n");
		System.out.println("��ǥ ��¥ : "+targetDate.toString());
	}
	
}
