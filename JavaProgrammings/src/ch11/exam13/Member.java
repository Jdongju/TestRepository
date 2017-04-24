package ch11.exam13;

public class Member implements Comparable<Member> { // 멤버는 멤버끼리 비교해야하니까
													// <Member>제네릭타입 넣었다.
	
	private String name; // Arrays는 Comparable타입을 기준으로 비교한다. Comparable타입은
							// Member타입이 정의되지 않으므로 Comparable이 비교 불가능하다.
							// 따라서 Member가 interface Comparable을 구현하여 compareTo를
							// Member타입 변환 가능하도록 오버라이딩 해준다.
	private int age;
	public Member(String name, int age) {
		this.name = name;
		this.age=age;
	}
	
	@Override
	public int compareTo(Member o) {
		/*if (age<o.age) {
			return -1;
		}else if(o.age==age){
			return 0;
		}else{
			return 1;
		}*/
		
//		return Integer.compare(age, o.age);
		return o.name.compareTo(name);
		 // String이 갖고있는 compareTo는 매개변수 o의 이름이
										// 먼저오면 음수, 같다면 0, 뒤에온다면 양수를 리턴한다.
	}
	
	@Override
	public String toString() {
		
		return name+"("+age+")";
	}
	
}
