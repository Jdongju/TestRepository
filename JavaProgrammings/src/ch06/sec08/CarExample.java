package ch06.sec08;

public class CarExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car myCar=new Car();
		myCar.setGas(5);
		boolean gasState=myCar.isLeftGas();
		if(gasState){
			System.out.println("����մϴ�.");
			myCar.run();
		}
		
		if(myCar.isLeftGas()){
			System.out.println("������ ������ �ʿ䰡 �����ϴ�.");
		}else{
			System.out.println("������ �����ϼ���.");
		}
	}

}
