package ch07.exam03;

public class ChildExample {

	public static void main(String[] args) {
		Child child=new Child("��","�浿");
		System.out.print(child.lastName);
		System.out.println(child.firstName);
		child.sound();
		child.play();
	}

}