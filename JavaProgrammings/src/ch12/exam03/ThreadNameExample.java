package ch12.exam03;

public class ThreadNameExample {
	
	public static void main(String[] args) {
		String threadName=Thread.currentThread().getName(); //�̸���¹��1
		
		Thread mainThread=Thread.currentThread();	//�̸���¹�� 2
		String threadname=mainThread.getName();
		
		System.out.println(threadName+"�� ����� ����");
		System.out.println(threadname+"�� ����� ����");
		
		WorkThread wt1= new WorkThread("wt1");
		wt1.start();
		
		WorkThread wt2= new WorkThread("wt2");
		wt2.start();
		
		
		
	}
	
}
