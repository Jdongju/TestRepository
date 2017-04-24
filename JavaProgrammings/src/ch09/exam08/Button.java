package ch09.exam08;

public class Button {
	// Field
	private OnClickListner onClickListener;
	// Constructor

	// Method

//setOnClickListenr�޼ҵ��� argument�� onClickListner��ü�� onClick�� ����ȴ�.
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
