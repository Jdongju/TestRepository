package ch05.exam01;

public class NullPointerException {

	public static void main(String[] args) {
		/*
		int[] scores=null;
		System.out.println(scores[0]);//nullpointerException. 배열이 아직 생성 안되었으니까.
//		아무런 객체를 참조하지 않고 있는데 값을 출력하려고 하니까 NullPointerException
*/
		String name=null;
		System.out.println(name.length()); //name이라는 객체의 length를 확인해라. .은 객체 접근 연산자라 부른다.
//		name이 NUll이니까 객체접근연산이 불가능해서 NullPointerException 발생.
	}
}
