package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문자기반 스트림 예제
 * 
 * @author PC-11
 *
 */
public class T07FileWriterTest {
	public static void main(String[] args) {

		// 사용자가 입력한 내용을 그대로 파일로 저장하기

		// 콘솔(표준입출력장치)과 연결된 입력용 문자스트림 생성

		// InputStreamReader => 바이트기반 스트림을 문자기반 스트림으로 변환해 주는 보조 스트림 (보조스트림은 반드시 기본스트림이 필요하다(System.in))
		// 문자기반 스트림인지 확인하는 방법은 Reader, Writer
		InputStreamReader isr = new InputStreamReader(System.in);

		FileWriter fw = null; // 파일 출력용 문자기반 스트림

		try {
			fw = new FileWriter("d:/D_Other/testChar.txt");

			int data = 0;

			System.out.println("아무거나 입력하세요");

			// 콘솔에서 입력할 때 입력의 끝 표시는 Ctrl+z 키를 누르면 된다.
			
			while ((data = isr.read()) != -1) {
				fw.write(data); // 콘솔에 입력한 값을 파일에 출력하기
			}

			System.out.println("작업 끝...");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
