package ch06.exam08;
//싱글톤

public  class Calculator {
	// Field
	private static Calculator singleton=new Calculator(); //한번  생성되고 이 객체에서 다시 생성못하게함.
	
	//static block : 메소드 영역에 코드가 다 올라가면 자동 생성된다.
	//static field의 복잡한 계산은 주로static block에서 한다.
	
	
	//Constructor
	private Calculator(){  //생성 못하게 막음.
		
	}
	
	static Calculator getInstance(){
		return singleton; //접근자 제공
	}
	
	
	// method

}
