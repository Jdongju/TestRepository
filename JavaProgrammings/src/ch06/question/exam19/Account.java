package ch06.question.exam19;

public class Account {
	public static final int MIN_BALANCE = 0;
	public static final int MAX_BALANCE = 1000000;
	private int balance;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		if (balance < Account.MIN_BALANCE || balance > Account.MAX_BALANCE) {
			System.out.println("범위를 초과한 값입니다.(0~100만원");
			return;
		}
		this.balance = balance;
	}
}
