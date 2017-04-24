package ch10.exam05;

public class Account {
	private long balance;
	
	public long getBalance() {
		return balance;
	}

	public void deposit(int money){
		balance+=money;
	}
	
	public void withdraw(int money) throws BalanceInsufficientException{
		if (money>balance) {
			throw new BalanceInsufficientException("잔고부족: "+(money-balance)+" 모자람");
//			throw new BalanceInsufficientException();
		}
		balance-=money;
	}
}
