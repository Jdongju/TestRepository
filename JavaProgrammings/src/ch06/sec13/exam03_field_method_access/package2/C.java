package ch06.sec13.exam03_field_method_access.package2;

import ch06.sec13.exam03_field_method_access.package1.A;

public class C {

		public C(){
			A a=new A();
			a.field1=1;
//			a.field2=1;  default ���� �Ұ�. �ٸ� ��Ű��
//			a.field3=1; private ���� �Ұ�. �ٸ���Ű��, �ٸ� Ŭ����
			
			a.method1();   
			//a.method2();   
			//a.method3();  
		}
}
