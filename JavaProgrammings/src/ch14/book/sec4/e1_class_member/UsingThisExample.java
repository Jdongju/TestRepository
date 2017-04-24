package ch14.book.sec4.e1_class_member;

public class UsingThisExample {
	
	public static void main(String[] args) {
		UsingThis usingThis=new UsingThis();
		UsingThis.Inner inner=usingThis.new Inner();
		inner.method();
	}
	
}
