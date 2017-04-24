package ch14.book.sec6.e1_static_method_instance_method_reference;

import java.util.function.IntBinaryOperator;

public class MethodReferencesExample {
	
	public static void main(String[] args) {
		IntBinaryOperator operator;
		
		operator=(x,y)->Calculator.staticMethod(x, y);
		System.out.println("결과1: "+operator.applyAsInt(1, 2));
		
		operator=Calculator::staticMethod;
		System.out.println("결과2: "+operator.applyAsInt(3, 4));
		
		Calculator obj=new Calculator();
		operator=(x,y)->obj.instanceMethod(x, y);
		System.out.println("결과3: "+operator.applyAsInt(5, 6));
		
		operator=obj::instanceMethod;
		System.out.println("결과4: "+operator.applyAsInt(7, 8));
	}
	
}
