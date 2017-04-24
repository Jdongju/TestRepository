package ch11.sec16_java.time;

import java.time.LocalDate;

public class DateTimeCreateExample {
	
	public static void main(String[] args) {
		//날짜얻기
		LocalDate currDate=LocalDate.now();
		System.out.println("현재 날짜: "+currDate);
		
		LocalDate targetDate=LocalDate.of(2024, 5, 10);
//		System.out.println("목표 날짜 : "+targetDate+"\n");
		System.out.println("목표 날짜 : "+targetDate.toString());
	}
	
}
