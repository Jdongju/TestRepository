package ch11.exam17;

import java.text.DecimalFormat;

public class DecimalFormatExample {
	public static void main(String[] args) {
		double num = 1234567.89;
				
		DecimalFormat df = new DecimalFormat("0"); //�ݿø��ϰ� ���
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("0.0"); //�Ҽ��� 1�ڸ�
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("0000000000.00000"); //�ٿ��ְ� �������� 0���� ä���.
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("#");//10����
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("#.#");//10���� 1�ڸ�
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("##########.#####"); //���ڸ� 0���� ��ä��
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("#.0");//�Ҽ��� 1�ڸ�
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("+#.0");
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("-#.0");
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("#,###.0");//�ڸ��� ����
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("0.0E0"); //10�� N��
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("+#,### ; -#,###");
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("#.# %");//%
		System.out.println( df.format(num) );
		
		df = new DecimalFormat("\u00A4 #,###");//�����ڵ� ���
		System.out.println( df.format(num) );
	}
}
