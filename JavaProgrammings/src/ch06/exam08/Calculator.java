package ch06.exam08;
//�̱���

public  class Calculator {
	// Field
	private static Calculator singleton=new Calculator(); //�ѹ�  �����ǰ� �� ��ü���� �ٽ� �������ϰ���.
	
	//static block : �޼ҵ� ������ �ڵ尡 �� �ö󰡸� �ڵ� �����ȴ�.
	//static field�� ������ ����� �ַ�static block���� �Ѵ�.
	
	
	//Constructor
	private Calculator(){  //���� ���ϰ� ����.
		
	}
	
	static Calculator getInstance(){
		return singleton; //������ ����
	}
	
	
	// method

}
