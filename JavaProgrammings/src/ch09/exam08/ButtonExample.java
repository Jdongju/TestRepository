package ch09.exam08;

public class ButtonExample {

	public static void main(String[] args) {
	
		
		Button button = new Button();
	
		/*Button.OnClickListner onClickListner=new Button.OnClickListner() {};*/
		//�������̽��� ���� Ŭ������ {}�ȿ� �����ϰ� �װ��� ��ü�� ���� �Ű������� �ִ´�.
		button.setOnClickListener(new Button.OnClickListner() {
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				System.out.println("������ ���� �մϴ�.");
			}
		});
		
		button.touch();
		
		
	}

}
