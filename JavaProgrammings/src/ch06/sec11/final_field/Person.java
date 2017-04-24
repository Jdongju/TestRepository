package ch06.sec11.final_field;

public class Person {
	final String nation="Korea";
	final String ssn;
	String name;
	
	public Person(String ssn, String name){
		this.ssn=ssn;
		this.name=name;
	}
}
