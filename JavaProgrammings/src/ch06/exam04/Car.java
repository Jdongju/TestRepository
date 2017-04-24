package ch06.exam04;

public class Car {

	// Field

	// ��ǰ
	Body body = new Body(); // Car ���赵 �� ��ü�� �ʵ�� ����.
	Engine engine = new Engine();
	Dashboard dashboard = new Dashboard();
	Seat[] seats = { new Seat(), new Seat(), new Seat(), new Seat() };
	Tire[] tires = { new Tire("�տ�"), new Tire("����"), new Tire("�ڿ�"), new Tire("����") };
	/// ����������
	String makeDay;
	String color;
	/// ���µ�����
	int speed;

	// Constructor -> �����Ҷ� �ٲ��� �ʴ� �� ����.
	Car(String makeDay, String color) {
		this.makeDay = makeDay; // �����Ҷ� ���� �޾Ƽ�(������ �ƱԸ�Ʈ String makeDay) �ʵ尪����
								// �����ϰڴٴ� �ǹ�(Car �ʵ��� makeDay).
		this.color = color;
	}

	// Method ->�Ź� �ٲ�� �� ����.
	void start() {
		engine.start();
	}

	void run() {

		tires[0].roll();
		tires[1].roll();
		tires[2].roll();
		tires[3].roll();

		setSpeed(60); // �ڱ��ڽžȿ��� �����ִ� �޼ҵ带 �θ����� ��ü���� �ʿ����.
						// this.setSpeed(60);�� ���� �ǹ�
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
