/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch18.exam11;

import java.io.File;
import java.io.IOException;

public class FileExample {

    public static void main(String[] args) throws IOException {
        //파일 및 디렉토리 정보 얻기
        File file = new File("src/ch18/exam11/FileExample.java");
        System.out.println(file.exists());
        System.out.println(file.length());
        System.out.println(file.isDirectory());

        System.out.println("-----------------------");
        File dir = new File("C:/Windows");
        System.out.println(dir.exists());
        System.out.println(dir.length());
        System.out.println(dir.isDirectory());

        System.out.println("-----------------------");

        String[] contents1 = dir.list(); //파일이나 디렉토리의 이름 리턴
        File[] contents2 = dir.listFiles(); //해당 디렉토리의 파일크기등을 리턴

        for (File f : contents2) {
            String info = "";
            info += f.getName();
            info += "\t\t";
            info += (f.isDirectory() ? "<dir>" : "");
            info += "\t\t";
            info += f.length();
            System.out.println(info);
        }
        System.out.println("-----------------------");

        //파일 및 디렉토리의 생성과 삭제
        File file2 = new File("C:/Temp/test.txt");
        file2.createNewFile();
        file2.delete();

        File dir2 = new File("C:/Temp/dir2");
        dir2.mkdir();

        File dir3 = new File("C:/Temp/dir3/dir4/dir5");
        dir3.mkdir();
    }
}
