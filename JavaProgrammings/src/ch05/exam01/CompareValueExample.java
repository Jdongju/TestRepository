package ch05.exam01;

public class CompareValueExample {

	public static void main(String[] args) {
		int v1=10;
		int v2=10;
		System.out.println(v1==v2); //true
		
		int[] v3={10};
		//int[] v4={10};
		int[] v4=v3; // ���� �������� �Է��߱� ������ true�� �ȴ�.
		System.out.println(v3==v4);//false v3�� v4�� ���� �迭���� �� ���� ��ü���� �˻�. �ȿ� �� ���� ������ �ٸ� ��ü�̴�. �׷��� false.
		
		String v5="Java";
		//String v6="Java";
		String v6=v5;
		System.out.println(v5==v6); //���ڿ� ���ͷ��� �����ϴٸ� String ��ü�� �����Ѵ�.
		
		String v7=new String("Java");
		String v8=new String("Java");
		//String v8=v5;
		
		System.out.println(v7==v8); 
		System.out.println(v7.equals(v8));// .equals�� �����
		
	}

}
