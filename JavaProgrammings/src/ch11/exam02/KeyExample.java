package ch11.exam02;

import java.util.HashMap;

public class KeyExample {

	public static void main(String[] args) {
		HashMap hashMap=new HashMap();
		
		Key key1=new Key(10);
		Member member1=new Member("blue");
		hashMap.put(key1, member1);

		Key key2=new Key(10);
		Member member2=new Member("white");
		hashMap.put(key2, member2);
		
		System.out.println(hashMap.size());
		
	}

}
