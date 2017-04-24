package ch11.exam13;

public class Member implements Comparable<Member> { // ����� ������� ���ؾ��ϴϱ�
													// <Member>���׸�Ÿ�� �־���.
	
	private String name; // Arrays�� ComparableŸ���� �������� ���Ѵ�. ComparableŸ����
							// MemberŸ���� ���ǵ��� �����Ƿ� Comparable�� �� �Ұ����ϴ�.
							// ���� Member�� interface Comparable�� �����Ͽ� compareTo��
							// MemberŸ�� ��ȯ �����ϵ��� �������̵� ���ش�.
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
		 // String�� �����ִ� compareTo�� �Ű����� o�� �̸���
										// �������� ����, ���ٸ� 0, �ڿ��´ٸ� ����� �����Ѵ�.
	}
	
	@Override
	public String toString() {
		
		return name+"("+age+")";
	}
	
}
