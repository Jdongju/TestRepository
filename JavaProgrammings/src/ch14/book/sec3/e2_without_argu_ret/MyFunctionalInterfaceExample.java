package ch14.book.sec3.e2_without_argu_ret;

public class MyFunctionalInterfaceExample {
	
	public static void main(String[] args) {
		MyFunctionalInterface fi;
		
		fi = () -> {
			String str = "method call1";
			System.out.println(str);
		};
		fi.method();
		
		fi = () -> {
			System.out.println("method call2");
		};
		fi.method();
		
		fi=()->System.out.println("method call3");
		fi.method();
	}
	
}
