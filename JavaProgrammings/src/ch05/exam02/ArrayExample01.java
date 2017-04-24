package ch05.exam02;

public class ArrayExample01 {

	public static void main(String[] args) {
		int[] v1 = { 80, 90, 87 };
		int[] v2 = { 80, 90, 87, 70 };
		String[] v3 = { "Java", "Program" };

		System.out.println(v1 == v2);// 서로다른 객체이기 때문에 false.
		System.out.println(v1[0] == v2[0]);// 객체의 값이기 때문이 true

		System.out.println(v1.length); // 객체 v1의 길이.
		System.out.println(v2.length);
		System.out.println(v3.length);
		System.out.println(args.length);

		int v4 = v1[0] + v1[1] + v1[2];
		int v5 = 0;
		
		for (int i = 0; i < v1.length; i++) {
			v5+=v1[i];
		}
	}

}
