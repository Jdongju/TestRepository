package ch15.book.sec4.e1_HashMap;

import java.util.HashMap;
import java.util.Map;

public class HashMapExample2 {

	public static void main(String[] args) {
		Map<Student,Integer> map=new HashMap<Student,Integer>();
		
		map.put(new Student(1,"È«±æµ¿"),95);
		map.put(new Student(1,"È«±æµ¿"),95);
		System.out.println("ÃÑ Entry ¼ö: "+ map.size());
		
		
	}

}
