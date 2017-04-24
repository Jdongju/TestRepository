package ch06.exam05;

public class Member {
	// Field
	String name; // 항상 바뀌는 값이므로 초기화하지 않는다. 객체생성시에 값이 정해지므로 생성자의 매개값으로 넣는다.
	int age;

	// Constructor
	Member(String name, int age) {
		this.name = name;
		this.age = age;
	}
	// Method
}
