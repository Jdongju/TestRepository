package ch09.exam07;


public class Example2 {

	public static void main(String[] args) {
		//����Ŭ������ �����ϰ� ��ü ����
		
		/*class CImpl implements A.C{
			@Override
			public void cMethod() {
				System.out.println("CImpl-dMethod()");
			}
		}
		//��� 1
		CImpl cimpl = new CImpl();
		cimpl.cMethod();
		
		//��� 2
		A.C c=new CImpl();
		c.cMethod();*/
		
		//���3
		A.C c= new A.C() {        //C�� ������ ����Ŭ������ ����µ� {}�ȿ� ���������� �ִ´�. �׸��� ��ü�����ؼ� �����Ѵ�.
										//CImpl �̶�� �����ڸ� ���ִ� ��
			@Override
			public void cMethod() {
				// TODO Auto-generated method stub
				System.out.println("CImpl-dMethod()");
			}
		};
		c.cMethod();
	}
}
