package ch05.exam01;

public class NullExample {

	public static void main(String[] args) {
		String v1 = null; // ����� ���ÿ� �ʱ�ȭ

		String v2;// ����
		//v2 = null;// �ʱ�ȭ. �̶� ��μ� ��������

		int[] v3 = null;// ���� ������ �Ǿ����� �����ϰ����� ����

		//������ ����Ϸ��� �ʱ�ȭ�ؾ��Ѵ�. �׷��� null�� �ʱ�ȭ�Ҽ� �ִ�.
		if (v3 == null) {
			System.out.println("null");
		} else if (v3 != null) {
			System.out.println("not null");
		}
	}
}
