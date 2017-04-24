package ch12.exam10;

public class AutoSaveThread extends Thread{
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save();
		
			
		}
	}

	public void save() {
		System.out.println("작업내용을 저장합니다.");
	}
}
