package ch10.exam05;

public class AccountExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Account account=new Account();
		System.out.println("�ܰ� : "+account.getBalance());
		account.deposit(100000);
		System.out.println("�ܰ� : "+account.getBalance());
		try {
			account.withdraw(1000000);
		} catch (BalanceInsufficientException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("�ܰ� : "+account.getBalance());
		
	}

}
