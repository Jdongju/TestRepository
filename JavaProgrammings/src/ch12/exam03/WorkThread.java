package ch12.exam03;

public class WorkThread extends Thread {
	// Field
	
	// Constructor
	WorkThread(String ThraedName) {
//		super(ThraedName);
		setName(ThraedName);
	}
	// Method
	@Override
	public void run() {
		String threadName=Thread.currentThread().getName();
		for (int i = 0; i < 2; i++) {
			System.out.println(threadName+"�� ����� ����");
		}
	}
	
}