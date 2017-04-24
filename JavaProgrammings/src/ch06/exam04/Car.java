package ch06.exam04;

public class Car {

	// Field

	// 부품
	Body body = new Body(); // Car 설계도 상 차체가 필드로 선언.
	Engine engine = new Engine();
	Dashboard dashboard = new Dashboard();
	Seat[] seats = { new Seat(), new Seat(), new Seat(), new Seat() };
	Tire[] tires = { new Tire("앞우"), new Tire("앞좌"), new Tire("뒤우"), new Tire("뒤좌") };
	/// 고유데이터
	String makeDay;
	String color;
	/// 상태데이터
	int speed;

	// Constructor -> 생성할때 바뀌지 않는 값 설정.
	Car(String makeDay, String color) {
		this.makeDay = makeDay; // 생성할때 값을 받아서(생성자 아규먼트 String makeDay) 필드값으로
								// 저장하겠다는 의미(Car 필드의 makeDay).
		this.color = color;
	}

	// Method ->매번 바뀌는 값 설정.
	void start() {
		engine.start();
	}

	void run() {

		tires[0].roll();
		tires[1].roll();
		tires[2].roll();
		tires[3].roll();

		setSpeed(60); // 자기자신안에서 갖고있는 메소드를 부를때는 객체변수 필요없다.
						// this.setSpeed(60);와 같은 의미
		dashboard.display(60);

	}

	void setSpeed(int speed) {
		this.speed = speed;
	};

	void stop() {
		engine.stop();
		speed = 0;
	}
}
