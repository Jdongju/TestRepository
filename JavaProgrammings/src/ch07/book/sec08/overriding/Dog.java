package ch07.book.sec08.overriding;

public class Dog extends Animal{

	public Dog(){
		this.kind="Æ÷À¯·ù";
	}
	
	@Override
	public void sound() {
		// TODO Auto-generated method stub
		System.out.println("¸Û¸Û");
	}
}
