/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
		String fileName = "D:/list.txt";
		File inputFile = new File(fileName);
		FileInputStream fis = new FileInputStream(inputFile);

		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);

		int listNum = 0;
		while (true) {
			String readLine = br.readLine();
			if (readLine == null) {
				break;
			}
			String path = "D:/new/" + readLine;
			File file = new File(path);
			file.mkdirs();
		}

	}

}
