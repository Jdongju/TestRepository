package ch08.Verify.e04;

public class MySqlDao implements DataAccessObject {
	private String name = "MySQl DB";

	@Override
	public void select() {
		System.out.println(name + "���� �˻�");
	}

	@Override
	public void insert() {
		System.out.println(name + "�� ����");
	}

	@Override
	public void update() {
		System.out.println(name + "�� ����");
	}

	@Override
	public void delete() {
		System.out.println(name + "���� ����");
	}
}
