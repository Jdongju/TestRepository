package ch15.exam07;

public class Person  {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
//	@Override
//	public int compareTo(Person o) {
//		/*if (age < o.getAge()) {
//			return -1;
//		} else if (age == o.age) {
//			return 0;
//		} else {
//			return 1;
//		}*/
//		return Integer.compare(o.getAge(), age);
//		
//	};
}
