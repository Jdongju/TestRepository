package ch07.book.sec08.overriding;

public abstract class Animal {
	public String kind;
	
	public void breathe(){
		System.out.println("���� ���ϴ�.");
	}
	
	public abstract void sound();
}
