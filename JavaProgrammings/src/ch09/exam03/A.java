package ch09.exam03;

public class A {
	// Field
	// Constructor
	A() {
		 int localVariable=2;
		// 로컬클래스
		class D {
			// Field
			// Constructor
			// Method
			void dMethod() {
				int result=localVariable+8;
//				localVariable=10;
			}
		}

	}

	// Method
	void aMethod() {
		// 로컬클래스
		class E {
			// Field
			// Constructor
			// Method
			void eMethod() {
			}
		}
	}

}
