package ch09.exam07;


public class Example2 {

	public static void main(String[] args) {
		//로컬클래스를 정의하고 객체 생성
		
		/*class CImpl implements A.C{
			@Override
			public void cMethod() {
				System.out.println("CImpl-dMethod()");
			}
		}
		//방법 1
		CImpl cimpl = new CImpl();
		cimpl.cMethod();
		
		//방법 2
		A.C c=new CImpl();
		c.cMethod();*/
		
		//방법3
		A.C c= new A.C() {        //C를 가지고 구현클래스를 만드는데 {}안에 구현내용을 넣는다. 그리고 객체생성해서 대입한다.
										//CImpl 이라는 생성자를 없애는 꼴
			@Override
			public void cMethod() {
				// TODO Auto-generated method stub
				System.out.println("CImpl-dMethod()");
			}
		};
		c.cMethod();
	}
}
