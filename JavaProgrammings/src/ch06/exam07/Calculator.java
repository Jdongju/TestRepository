package ch06.exam07;

public class Calculator {
	// Field
	static String model = "MI-84";
	static String makeDay = "2017.03.29";
	static String info; //MI-84(2017-03-29)

	
	//static block : 메소드 영역에 코드가 다 올라가면 자동 생성된다.
	//static field의 복잡한 계산은 주로static block에서 한다.
	
	static{
		info+=model;
		info+="(";
		info+=makeDay;
		info+=")";
	}
	
	// method

}
