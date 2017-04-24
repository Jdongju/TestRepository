package ch05.exam01;

public class TypeExample01 {

	public static void main(String[] args) {
		System.out.println("main() method run..");
		// 기본 타입
		// 정수
		byte v1 = 127; // -128~127
		char v2 = 'A'; // 2byte. 유니코드 값 숫자 저장되기 때문에 정수타입.
		short v3 = 10;// 2byte
		int v4 = 10;// 4byte 좌측에 있는 범위가 우측에 있는 범위보다 항상 커야 오류 발생하지 않는다.
		long v5 = 10L;// 8byte

		// 실수
		float v6 = 10.0f; // 4byte
		double v7 = 10.0;// 8byte
		// 참,거짓
		boolean v8 = true;

		// 참조타입
		String name = "Java";
		int[] scores = { 90, 80, 85 };

	}

}
