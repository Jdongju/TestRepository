package ch05.exam01;

public class CompareValueExample {

	public static void main(String[] args) {
		int v1=10;
		int v2=10;
		System.out.println(v1==v2); //true
		
		int[] v3={10};
		//int[] v4={10};
		int[] v4=v3; // 같은 번지수를 입력했기 때문에 true가 된다.
		System.out.println(v3==v4);//false v3와 v4는 같은 배열인지 즉 같은 객체인지 검사. 안에 들어간 값은 같지만 다른 객체이다. 그래서 false.
		
		String v5="Java";
		//String v6="Java";
		String v6=v5;
		System.out.println(v5==v6); //문자열 리터럴이 동일하다면 String 객체를 공유한다.
		
		String v7=new String("Java");
		String v8=new String("Java");
		//String v8=v5;
		
		System.out.println(v7==v8); 
		System.out.println(v7.equals(v8));// .equals는 내용비교
		
	}

}
