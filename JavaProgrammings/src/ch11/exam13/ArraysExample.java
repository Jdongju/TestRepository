package ch11.exam13;

import java.util.Arrays;

public class ArraysExample {
	
	public static void main(String[] args) {
		int[] nums={1,2,3,4,5};
		System.out.println(Arrays.binarySearch(nums, 3));  //3�� ��ġ�� �ε���
		System.out.println(Arrays.binarySearch(nums, 8));  
		
		String[] names={"ȫ�浿", "���ڹ�","�繰���ͳ�"};
		System.out.println(Arrays.binarySearch(names, "���ڹ�2"));
		
		
	}
	
}
