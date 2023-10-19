package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class T12BufferedIOTest {
	public static void main(String[] args) throws IOException {
		
		FileReader fr = new FileReader("src/kr/or/ddit/basic/T11BufferedIOTest.java");
		
		BufferedReader br = new BufferedReader(fr);
		
		String tempStr = "";
		
		while((tempStr = br.readLine()) != null) {
			System.out.println(tempStr);
		}
		
		br.close();
		/*
		int data = 0;
		
		while((data = fr.read()) != -1) {
			System.out.print((char) data);
		}
		fr.close();*/
	}
}
