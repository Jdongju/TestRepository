package ch06.exam05;

import java.util.Scanner;

public class MemberExample {

	public static void main(String[] args) throws Exception {
		Member[] members = new Member[100];
		Scanner scanner = new Scanner(System.in);
		while (true) {

			/*Scanner scanner = new Scanner(System.in);
			int num = scanner.nextInt();
			System.out.println(num);
			*/String str = scanner.nextLine();
			System.out.println(str);

			System.out.println("-------------------------");
			System.out.println("1. 회원가입 | 5.종료");
			System.out.println("-------------------------");
			System.out.println("선택:");
			String strNum = scanner.nextLine();

			if (strNum.equals("1")) {
				String name = scanner.nextLine();
				/*
				 * String strAge = scanner.nextLine(); int
				 * age=Integer.parseInt(strAge);
				 */
				int age = Integer.parseInt(scanner.nextLine());
				Member member = new Member(name, age);
				for (int i = 0; i < members.length; i++) {
					if (members[i] == null) {
						members[i] = member;
						break;
					}
				}
			} else if (strNum.equals("5")) {
				System.exit(0);
			}

			/*
			 * switch (strNum) { case "1": break; case "5": System.exit(0); }
			 */
		}
	}

}
