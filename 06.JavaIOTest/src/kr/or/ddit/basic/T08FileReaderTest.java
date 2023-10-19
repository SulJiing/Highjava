package kr.or.ddit.basic;

import java.io.FileReader;
import java.io.IOException;

public class T08FileReaderTest {
	public static void main(String[] args) {
		
		FileReader fr = null;
		
		try {
			
			fr = new FileReader("d:/D_Other/testChar.txt");
			
			int data = 0;
			
			while ((data = fr.read()) != -1) {
				System.out.print((char)data);
			}
			System.out.println("\n읽기 작업 끝...");
			
		} catch (IOException e) {
			e.printStackTrace();
		} try {
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
