package ch12.exam01;

import java.awt.Toolkit;

import ch12.exam02.BeepThread;


public class BeepPrintExample {
	
	public static void main(String[] args) throws InterruptedException {
		
		/*
		 * BeepTask beepTask = new BeepTask();				BeepTask�� ���������� ���̴� ���
		 * Thread thread=new Thread(beepTask);				Ŭ���� ���� ���°� ���ϴ�.
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
			System.out.println("��");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				
			}
		}
	}
	
}
