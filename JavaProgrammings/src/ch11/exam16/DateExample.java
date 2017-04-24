package ch11.exam16;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateExample {
	
	public static void main(String[] args) {
		Date now=new Date();
		System.out.println(now);
		/*System.out.println(now.getYear());
		System.out.println(now.getMonth());
		System.out.println(now.getDate());
		System.out.println(now.getDay());
		System.out.println(now.getHours());
		System.out.println(now.getMinutes());
		System.out.println(now.getSeconds());*/
		
//		Calendar cal=new Calendar();
		Calendar cal=Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int date=cal.get(Calendar.DAY_OF_MONTH);
		int date2=cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		int day=cal.get(Calendar.DAY_OF_WEEK);
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		int minute=cal.get(Calendar.MINUTE);
		int second=cal.get(Calendar.SECOND);
		
		System.out.println(year+"-"+month+"-"+date);
		System.out.println(date2);
		System.out.println(day+"-"+hour+":"+minute+":"+second);
		
		/*String[] availableIDs=TimeZone.getAvailableIDs();
		for(String id:availableIDs){
			System.out.println(id);
		}*/
	}
	
}
