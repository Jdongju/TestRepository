package ch07.book.sec08.overriding;

public class Cat extends Animal{

	public Cat(){
		this.kind="������";
	}
	
	@Override
	public void sound() {
		// TODO Auto-generated method stub
		System.out.println("�߿�");
	}
}
