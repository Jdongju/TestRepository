package ch05.exam01;

public class TypeExample01 {

	public static void main(String[] args) {
		System.out.println("main() method run..");
		// �⺻ Ÿ��
		// ����
		byte v1 = 127; // -128~127
		char v2 = 'A'; // 2byte. �����ڵ� �� ���� ����Ǳ� ������ ����Ÿ��.
		short v3 = 10;// 2byte
		int v4 = 10;// 4byte ������ �ִ� ������ ������ �ִ� �������� �׻� Ŀ�� ���� �߻����� �ʴ´�.
		long v5 = 10L;// 8byte

		// �Ǽ�
		float v6 = 10.0f; // 4byte
		double v7 = 10.0;// 8byte
		// ��,����
		boolean v8 = true;

		// ����Ÿ��
		String name = "Java";
		int[] scores = { 90, 80, 85 };

	}

}
