package ch05.exam02;

public class ArrayExample03 {

	public static void main(String[] args) {
		/*String[] v1 = new String[5];

		for (int i = 0; i < v1.length; i++) {
			System.out.println("v1[" + i + "]:" + v1[i]);
		}*/
		int[]v1=new int[5];
		v1[0]=10;
		v1[2]=10;
		
		
		String[]v2=new String[5];
		//v2[0]=new String("Java");
		v2[0]="Java";
		v2[2]="Java";

		System.out.println(v1[0]==v1[2]); //값비교이니까 true
		System.out.println(v2[0]==v2[2]);//번지 비교이니까 false
		System.out.println(v2[0].equals(v2[2]));//값비교이니까 true.
	}

}
