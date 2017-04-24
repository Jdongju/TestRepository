package ch05.exam02;

public class ArrayExample02 {

	public static void main(String[] args) {
		int[] v1 = { 10, 20, 30 };

		/*
		 * int[] v2; v2={10,20,30};
		 */
		// 오류나는 이유: 컴파일러는 배열변수 선언할때 배열길이 얼마짜리 참조한다는 것을 확인한다.
		int[] v2;
		v2 = new int[] { 10, 20, 30 };

		int result1=sum(v1);
		int result2=sum(v2);
		// sum({10,20,30}); 안되는 이유: 배열을 생성하고 배열 번지수를 리턴해서 대입해야한다. 이경우는
		// 이미 args라는 변수가 선언되어있는데 거기에 {10,20,30}을 넣으니 오류. 그래서 new
		// int[]{10,20,30}으로 해야함

		int result3=sum(new int[] { 10, 20, 30 });
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);

	}

	public static int sum(int[] v2) {
		int sum = 0;
		for (int i = 0; i < v2.length; i++) {
			sum += v2[i];
		}
		return sum;
	}

}
