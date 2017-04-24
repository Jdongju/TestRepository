package ch06.sec13.exam02_constructor_access.package2;

import ch06.sec13.exam02_constructor_access.package1.A;

public class C {

	A a1= new A(true);
//	A a2= new A(1);//default이므로 다른 패키지 이니까 접근 불가
//	A a3= new A("문자열");//private이므로 다른 패키지, 다른 클래스 접근 불가.
}
