package ch07.book.sec08.abstract_class_declare;

public class Phone {
	public String owner;
	
	public Phone(String owner){
		this.owner=owner;
	}
	
	public void turnOn(){
		System.out.println("�� ������ �մϴ�.");
	}
	
	public void turnOff(){
		System.out.println("�� ������ ���ϴ�.");
	}
}
