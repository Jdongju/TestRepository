package ch12.exam01;

import java.awt.Toolkit;

import ch12.exam02.BeepThread;


public class BeepPrintExample {
	
	public static void main(String[] args) throws InterruptedException {
		
		/*
		 * BeepTask beepTask = new BeepTask();				BeepTask가 여러군데서 쓰이는 경우
		 * Thread thread=new Thread(beepTask);				클래스 만들어서 쓰는게 편하다.
		 * thread.start();
		 */
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				
				for (int i = 0; i < 5; i++) {
					toolkit.beep();
					System.out.println(Thread.currentThread().getName());
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						
					}
					
				}
			}
		});
		System.out.println(Thread.currentThread().getName());
		thread.start();
		
	
		
		for (int i = 0; i < 5; i++) {
			System.out.println("띵");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				
			}
		}
	}
	
}
