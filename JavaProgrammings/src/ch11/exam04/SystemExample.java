package ch11.exam04;

public class SystemExample {
	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager(){
			@Override
			public void checkExit(int status) {  //checkExit는 정상종료시 실행된다.
				if (status!=54321) {
					throw new SecurityException();
				}
			}
		});
		
		for (int i = 0; i < 10; i++) {
			System.out.println(i);
			if(i==5){
				try{
					System.exit(54321); //내가 원하는 번호를 넣었을때 종료되도록한다.
				}catch(SecurityException e){
					
				}
				
			}
		}
	}
}
