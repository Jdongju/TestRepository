package ch06.sec08.overloading;

public class Calculator {
	//���簢���� ����
	double areaRectangle(double width) {
		return width * width;
	}
	
	//���簢���� ����
	double areaRectangle(double width, double height) {
		return width * height;
	}	
}

