package ch09.exam08;

public class Button {
	// Field
	private OnClickListner onClickListener;
	// Constructor

	// Method

//setOnClickListenr메소드의 argument의 onClickListner객체의 onClick이 실행된다.
	public void setOnClickListener(OnClickListner onClickListener) {
		this.onClickListener = onClickListener;
	}
	
	public void touch(){
		if (onClickListener!=null) {
			onClickListener.onClick();
		}
	}

	// Nested Interface
	interface OnClickListner {
		void onClick();
	}
}
