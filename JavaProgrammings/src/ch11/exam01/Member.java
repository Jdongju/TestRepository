package ch11.exam01;

public class Member {
	// Field
	private String id;

	// Constructor
	public Member(String id) {
		this.id = id;
	}

	// Method
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Member) {
			Member target = (Member) obj; // object가 아닌 member가 가지고 있는 것을 접근하기 위해 생성
			if (id.equals(target.id)) {
				return true;
			}
		}
		return false;
	}
}
