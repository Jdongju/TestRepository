package ch09.exam08;

public class ButtonExample {

	public static void main(String[] args) {
	
		
		Button button = new Button();
	
		/*Button.OnClickListner onClickListner=new Button.OnClickListner() {};*/
		//인터페이스의 구현 클래스를 {}안에 정의하고 그것을 객체로 만들어서 매개값으로 넣는다.
		button.setOnClickListener(new Button.OnClickListner() {
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				System.out.println("서버에 접속 합니다.");
			}
		});
		
		button.touch();
		
		
	}

}
