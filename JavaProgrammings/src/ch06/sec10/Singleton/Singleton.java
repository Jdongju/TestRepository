package ch06.sec10.Singleton;

public class Singleton {
	private static Singleton singleton = new Singleton();
	
	private Singleton(){
		
	}
	
	static Singleton getInstance(){
		return singleton;
	}
}
