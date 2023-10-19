package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *  프린터 기능을 제공하는 보조스트림
 * @author PC-11
 *
 */
public class T14PrintStreamTest {
	public static void main(String[] args) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("d:/D_Other/print.txt");	
		
		// PrintStream은 모든 타입의 데이터를 출력할 수 있는 기능을 제공하는 OutputStream의 서브 클래스이다.
		PrintStream out = new PrintStream(fos);
		out.print("안녕하세요. PrintStream입니다.\n");
		out.println("안녕하세요. PrintStream입니다.2");
		out.println("안녕하세요. PrintStream입니다.3");
		out.println(fos); // 객체출력
		out.println(3.14);
		
		System.out.println("1.출력완료...");
		
		out.close();
		
		////////////////////////////////
		
		// PrintWriter가 PrintStream보다 다양한 언어의 문자를 처리하는데 적합하다.
		
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/print2.txt");
//		PrintWriter pw = new PrintWriter(fos2);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos2,"CP949"));
		pw.print("안녕하세요. PrintWriter입니다.\n");
		pw.println("안녕하세요. PrintWriter입니다.2");
		pw.println("안녕하세요. PrintWriter입니다.3");
		pw.println(fos2);
		
		System.out.println("2.출력완료...");
		
		pw.close();
	}
}
