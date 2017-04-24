package ch07.book.sec08.overriding;

public abstract class Animal {
	public String kind;
	
	public void breathe(){
		System.out.println("숨을 쉽니다.");
	}
	
	public abstract void sound();
}
