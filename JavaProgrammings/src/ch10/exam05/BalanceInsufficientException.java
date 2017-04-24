package ch10.exam05;

public class BalanceInsufficientException extends Exception {
		public BalanceInsufficientException(){					//  기본생성자
			super();
		}
		public BalanceInsufficientException(String message){  //메시지 설명
			super(message);
		}
}
