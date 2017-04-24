package ch11.exam17;

import java.text.DecimalFormat;

public class DecimalFormatExample {
	public static void main(String[] args) {
		double num = 1234567.89;
				
		DecimalFormat df = new DecimalFormat("0"); //반올림하고 출력
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("0.0"); //소수점 1자리
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("0000000000.00000"); //붙여넣고 나머지는 0으로 채운다.
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("#");//10진수
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("#.#");//10진수 1자리
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("##########.#####"); //빈자리 0으로 안채움
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("#.0");//소수점 1자리
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("+#.0");
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("-#.0");
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("#,###.0");//자리수 구분
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("0.0E0"); //10의 N승
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("+#,### ; -#,###");
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("#.# %");//%
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("\u00A4 #,###");//유니코드 출력
		System.out.println( df.format(num) );
	}
}
