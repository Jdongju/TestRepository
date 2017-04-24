package ch12.exam08;

public class ReadThread extends Thread{
	//Field
	private DataBox dataBox;	//공유객체 저장필드
	//Constructor
	public ReadThread(DataBox dataBox){
		this.dataBox=dataBox;			
	}//생성자 주입
	//Method
	/*public void setDataBox(DataBox dataBox) {
		this.dataBox = dataBox;					//세터 주입
	}*/
	@Override
		public void run() {
		for (int i = 0; i < 10; i++) {
			String data=dataBox.getData();
		}
	}	
}
