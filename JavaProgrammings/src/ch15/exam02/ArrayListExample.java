package ch15.exam02;

import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ArrayListExample {
	
	public static void main(String[] args) {
		
		List<String> list = new LinkedList<String>();
//		List<String> list = new Vector<String>();
//		List<String>list2=new ArrayList<>();

		list.add("Java");
		list.add("JDBC");
		list.add("Servlet/JSP");
		list.add(2,"Database");
		list.add("iBATIS");
		
		int size=list.size();
		System.out.println("�� ��ü��: "+size);
		System.out.println();
		
		String skill=list.get(2);
		System.out.println("2: "+skill);
		System.out.println();
		
		for (int i = 0; i < list.size(); i++) {
			String str=list.get(i);
			System.out.println(i+":"+str);
		}
		System.out.println();
		
		list.remove(2);
		list.remove(2);
		list.remove("iBATIS");
		
		for (int i = 0; i < list.size(); i++) {
			String str=list.get(i);
			System.out.println((i+":"+str));
		}
		
		
	}
	
}