package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public interface T10FileEncodingTest {
	public static void main(String[] args) throws IOException {
		/*
		 	키보드로 입력한 내용을 파일로 저장하는데 
		 	out_utf8.txt 파일은 'UTF-8' 인코딩 방식으로
		 	out_ansi.txt 파일은 'CP949' 인코딩 방식으로 저장한다.
		 */
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		// 파일 출력용
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_anai.txt");
		
		// OutputStreamWriter는 바이트기반 출력스트림에 연결되어
		// 문자기반 출력스트림인 Writer로 변환시켜주는 보조스트림
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "UTF-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos2, "CP949");
		
		int data = 0;
		
		System.out.println("아무거나 입력하세요.");
		
		while ((data = isr.read()) != -1) {
			osw1.write(data);
			osw2.write(data);
		}
		
		System.out.println("작업완료...");
		
		isr.close();
		osw1.close();
		osw2.close();
	}
}
