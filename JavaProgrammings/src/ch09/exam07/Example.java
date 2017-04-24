package ch09.exam07;

public class Example {

	public static void main(String[] args) {
		// 로컬클래스를 정의하고 객체생성
/*
		class B2 extends A.B {
			@Override
			void bMethod() {
				System.out.println("B2-bMethod()");
			}
		}
		
		 * B2 b2=new B2(); b2.bMethod();
		  
		A.B b=new B2();
		b.bMethod();*/
		
		A.B b=new A.B(){
			@Override
			void bMethod() {
				System.out.println("B2-bMethod()");
			}
		}; //B를 상속하고 {}사이에 자식의 내용을 정의한 후 자식객체를 만들어서 대입한다.
		b.bMethod();
	}
}
