package ch05.exam01;

public class NullPointerException {

	public static void main(String[] args) {
		/*
		int[] scores=null;
		System.out.println(scores[0]);//nullpointerException. �迭�� ���� ���� �ȵǾ����ϱ�.
//		�ƹ��� ��ü�� �������� �ʰ� �ִµ� ���� ����Ϸ��� �ϴϱ� NullPointerException
*/
		String name=null;
		System.out.println(name.length()); //name�̶�� ��ü�� length�� Ȯ���ض�. .�� ��ü ���� �����ڶ� �θ���.
//		name�� NUll�̴ϱ� ��ü���ٿ����� �Ұ����ؼ� NullPointerException �߻�.
	}
}
