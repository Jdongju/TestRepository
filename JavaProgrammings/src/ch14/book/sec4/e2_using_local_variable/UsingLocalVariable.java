package ch14.book.sec4.e2_using_local_variable;

public class UsingLocalVariable {
	void method(int arg) {
		int localVar = 40;
		
		// arg=31;
		// localVar=41;
		
		MyFunctionalInterface fi = () -> {
			System.out.println("arg: " + arg);
			System.out.println("loaclVar: " + localVar + "\n");
		};
		fi.method();
	}
}
