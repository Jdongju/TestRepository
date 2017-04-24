package ch05.exam01;

public class NullExample {

	public static void main(String[] args) {
		String v1 = null; // 선언과 동시에 초기화

		String v2;// 선언
		//v2 = null;// 초기화. 이때 비로소 변수생성

		int[] v3 = null;// 변수 생성은 되었으나 참조하고있지 않음

		//변수를 사용하려면 초기화해야한다. 그래서 null로 초기화할수 있다.
		if (v3 == null) {
			System.out.println("null");
		} else if (v3 != null) {
			System.out.println("not null");
		}
	}
}
