package ch06.sec13.exam03_field_method_access.package2;

import ch06.sec13.exam03_field_method_access.package1.A;

public class C {

		public C(){
			A a=new A();
			a.field1=1;
//			a.field2=1;  default 접근 불가. 다른 패키지
//			a.field3=1; private 접근 불가. 다른패키지, 다른 클래스
			
			a.method1();   
			//a.method2();   
			//a.method3();  
		}
}
