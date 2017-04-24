package ch06.exam09;

public class Member {
	
	
	
	String name;
	final String ssn;   //final : 생성자를통해서 한번 변경하되 그 이후에는 바꿀 수없다.
	static final String NATION="Korean";
	
	Member(String name, String ssn){
		this.name=name;
		this.ssn=ssn;
	}
}
