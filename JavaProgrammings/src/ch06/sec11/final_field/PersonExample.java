package ch06.sec11.final_field;

public class PersonExample {

	public static void main(String[] args) {
		Person p1=new Person("12345-456789", "계백");
		
		System.out.println(p1.nation);
		System.out.println(p1.ssn);
		System.out.println(p1.name);
		
		p1.name="을지문덕";
		System.out.println(p1.name);
	}

}
