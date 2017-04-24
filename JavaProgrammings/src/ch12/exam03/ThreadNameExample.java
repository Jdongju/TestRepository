package ch12.exam03;

public class ThreadNameExample {
	
	public static void main(String[] args) {
		String threadName=Thread.currentThread().getName(); //이름출력방법1
		
		Thread mainThread=Thread.currentThread();	//이름출력방법 2
		String threadname=mainThread.getName();
		
		System.out.println(threadName+"이 출력한 내용");
		System.out.println(threadname+"이 출력한 내용");
		
		WorkThread wt1= new WorkThread("wt1");
		wt1.start();
		
		WorkThread wt2= new WorkThread("wt2");
		wt2.start();
		
		
		
	}
	
}
