package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 파일 출력 예제
 * 
 * @author PC-11
 *
 */
public class T06FileStreamTest {
	public static void main(String[] args) {

		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream("d:/D_Other/out.txt", true); // true 넣으면 기존 데이터에 += 해줌, 기본값은 false
			
			for (char ch = 'a';  ch <= 'z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일 쓰기 작업 완료...");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//////////////////////////////////////// 읽기
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/out.txt");
			
			int data = 0;
			
			while((data = fis.read()) != -1) {
				System.out.print((char)data);
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
