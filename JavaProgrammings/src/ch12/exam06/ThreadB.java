package ch12.exam06;

public class ThreadB extends Thread {
	// Field
	private boolean stop = false; // 스레드 종료용
	
	private boolean work = true;// 스레드 작업 양보용
	
	// Constructor
	
	// Method
	@Override
	public void run() {
		while (!stop) {
			if (work) {
				System.out.println("ThreadB 작업 중... ");
			}else{
				yield();
			}
		}
		System.out.println("ThreadB 작업 종료");
		
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	public void setWork(boolean work) {
		this.work = work;
	}
}
