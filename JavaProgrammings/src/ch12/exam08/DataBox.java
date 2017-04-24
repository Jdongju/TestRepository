package ch12.exam08;

public class DataBox {
	// Field
	private String data;
	// Constructor
	
	// Method
	
	public synchronized String getData() {
		if (data == null) {
			try {
				wait(); // wait�� ObjectŬ������ wait. wait�� notify�� synchronized������
						// ���� ���� ���� ���ٸ� wait()�Ѵ�.
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		String returnValue = data; //���� �����͸� returnValue�� ����
		System.out.println("���� ������: " + returnValue);
		data = null;	//data�� ���ο� ���� ������ ���� null�� �ٽ� �ʱ�ȭ;
		notify();
		return returnValue;
	}
	
	public synchronized void setData(String data) {
		
		if (this.data != null) {
			try {
				wait(); //�����Ͱ� �ִٸ� �����͸� ���� �ʰ� wait ���·� ��ȯ
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		this.data = data; //�����Ͱ� null�̸� �Ű������� ���� �����Ϳ� ����.
		System.out.println("���� ������: "+this.data);
		notify();
	}
	
}
