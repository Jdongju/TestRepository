package ch11.exam14;

public class WrapperEqualsExample {
	
	public static void main(String[] args) {
		Integer v1=new Integer(10);
		Integer v2=new Integer(10);
	/*	Integer v1=10;
		Integer v2=10;	*/		//�������� ������ -128~127;
		
		System.out.println(v1==v2);	//������
		System.out.println(v1.intValue()==v2.intValue());	//�����
		System.out.println(v1.equals(v2));	//�����
	}
	
}
