package ch09.exam02;

public class A {
	// Field
	 int aField;
	// Constructor
	A() {
		//로컬클래스
		class D {
			//Field
			//Constructor
			//Method
			void dMethod(){
				aField=10;
			}
		}
		
	}

	// Method
	void aMethod() {
		//로컬클래스
		class E {
			//Field
			//Constructor
			//Method
			void eMethod(){
				aField=10;
			}
		}
	}

	// 중첩멤버 클래스
	class B {
		//Field
		//Constructor
		//Method
		void bMethod(){
			aField=10;
		}
	}

	static class C {
		//Field
		//Constructor
		//Method
		void cMethod(){
//			aField=10;                    aField는 A객체가 있어야만 사용가능한데 C는 Static이므로 A가 없어도 실행가능하니까 에러 발생
		}
	}

}
