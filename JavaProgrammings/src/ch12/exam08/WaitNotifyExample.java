package ch12.exam08;

public class WaitNotifyExample {
	
	public static void main(String[] args) {
		//傍蜡按眉积己
		DataBox dataBox=new DataBox();
		
		//胶饭靛 按眉 积己
		ReadThread t1=new ReadThread(dataBox);
		WriteThread t2=new WriteThread(dataBox);
		
		t1.start();
		t2.start();
		
		
	}
	
}
