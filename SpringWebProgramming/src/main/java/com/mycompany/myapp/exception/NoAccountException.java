package com.mycompany.myapp.exception;


//스프링에서 트랜잭션을 처리하기 위해서는 발생되는 예외가 반드시 RuntimeException만 가능

public class NoAccountException extends RuntimeException {
	//메시지 출력 필요없는 경우
	public NoAccountException(){}
	//메시지 출력 필요한 경우
	public NoAccountException(String message) {
		super(message);
	}
}
