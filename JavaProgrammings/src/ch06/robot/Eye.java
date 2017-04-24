package ch06.robot;

public class Eye {
	// Field
	String name;
	int battery;
	// Constructor
	Eye(String name ){
		this.name=name;
	}
	// Method
	void projecting(){
		System.out.println(name+"이 켜졌습니다.");
	
	}
	
}
