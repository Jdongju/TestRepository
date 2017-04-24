package ch07.book.sec08.abstract_class_declare;

public class Phone {
	public String owner;
	
	public Phone(String owner){
		this.owner=owner;
	}
	
	public void turnOn(){
		System.out.println("폰 전원을 켭니다.");
	}
	
	public void turnOff(){
		System.out.println("폰 전원을 끕니다.");
	}
}
