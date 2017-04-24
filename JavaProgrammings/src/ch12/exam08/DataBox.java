package ch12.exam08;

public class DataBox {
	// Field
	private String data;
	// Constructor
	
	// Method
	
	public synchronized String getData() {
		if (data == null) {
			try {
				wait(); // wait는 Object클래스의 wait. wait와 notify는 synchronized에서만
						// 가능 읽을 값이 없다면 wait()한다.
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		String returnValue = data; //읽은 데이터를 returnValue에 저장
		System.out.println("읽은 데이터: " + returnValue);
		data = null;	//data는 새로운 값의 저장을 위해 null로 다시 초기화;
		notify();
		return returnValue;
	}
	
	public synchronized void setData(String data) {
		
		if (this.data != null) {
			try {
				wait(); //데이터가 있다면 데이터를 넣지 않고 wait 상태로 전환
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		this.data = data; //데이터가 null이면 매개변수의 값을 데이터에 저장.
		System.out.println("생성 데이터: "+this.data);
		notify();
	}
	
}
