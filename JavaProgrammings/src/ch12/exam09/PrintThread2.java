package ch12.exam09;

public class PrintThread2 extends Thread {
	// Field
	// Constructor
	
	// Method
	@Override
	public void run() {
		try{
		while (true) {
			System.out.println("실행 중...");
			System.out.println("실행 중...");
			//Thread.sleep(1);	//일시정지 상태가 되어야 인터럽트가 효력을 갖는다.
			if(isInterrupted()){  //인터럽트 메소드가 호출되었을때
									//인터럽트 되었으면 true, 인터럽트 안되었으면 false
				break;
			}
		}
		}catch(Exception e){
			
		}
			System.out.println("자원 정리");
			System.out.println("실행 종료");
	}
	
}
