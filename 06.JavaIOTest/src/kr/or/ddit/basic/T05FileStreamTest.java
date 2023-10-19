package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

/**
 	파일 읽기 예제
 * @author PC-11
 *
 */
public class T05FileStreamTest {
	
	public static void main(String[] args) {
		
		FileInputStream fis = null; // 읽을 때
		
		try {
			fis = new FileInputStream("d:/D_Other/test2.txt");
			
			int data = 0;
			
			while ((data= fis.read()) != -1) {
				// 읽어온 데이터 출력
				System.out.print((char) data);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
