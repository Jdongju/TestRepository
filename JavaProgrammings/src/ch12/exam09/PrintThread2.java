package ch12.exam09;

public class PrintThread2 extends Thread {
	// Field
	// Constructor
	
	// Method
	@Override
	public void run() {
		try{
		while (true) {
			System.out.println("���� ��...");
			System.out.println("���� ��...");
			//Thread.sleep(1);	//�Ͻ����� ���°� �Ǿ�� ���ͷ�Ʈ�� ȿ���� ���´�.
			if(isInterrupted()){  //���ͷ�Ʈ �޼ҵ尡 ȣ��Ǿ�����
									//���ͷ�Ʈ �Ǿ����� true, ���ͷ�Ʈ �ȵǾ����� false
				break;
			}
		}
		}catch(Exception e){
			
		}
			System.out.println("�ڿ� ����");
			System.out.println("���� ����");
	}
	
}
