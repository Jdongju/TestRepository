package ch11.exam03;

public class Member {
	// Field
	private String id;

	// Constructor
	public Member(String id) {
		this.id = id;
	}

	// Method
	@Override
	public String toString() {
		return id;
	}
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Member) {
			Member target=(Member) obj;
			if(id.equals(target.id)){
				return true;
			}
		}
		return false;
		
	}
}
