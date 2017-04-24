package ch12.exam02;

import java.awt.Toolkit;

import ch12.exam02.BeepThread;

public class BeepPrintExample {
	
	public static void main(String[] args) throws InterruptedException {
		
	
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				Toolkit toolkit = Toolkit.getDefaultToolkit();
				
				for (int i = 0; i < 5; i++) {
					toolkit.beep();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						
					}
					
				}
			}
		};
		thread.start();
		for (int i = 0; i < 5; i++) {
			System.out.println("¶ò");
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				
			}
		}
	}
	
}
