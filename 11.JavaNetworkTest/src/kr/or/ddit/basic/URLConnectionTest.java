package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class URLConnectionTest {
	public static void main(String[] args) throws IOException {
		
		// URLConnection 클래스 => 애플리케이션과 URL간의 통신 연결을 위한 추상클래스
		
		// 특정 서버(ex:네이버)의 정보가 파일을 내용을 가져오기
		
		URL url = new URL("https://www.naver.com/index.html"); // s의 유무는 암호화 
		
		// URLConnection 객체 생성하기
		URLConnection urlConn = url.openConnection();

		System.out.println("Content-Type : "+urlConn.getContentType());
		System.out.println("Encoding : "+urlConn.getContentEncoding());
		System.out.println("Content : "+urlConn.getContent());
		System.out.println();
		
		// 전제 Header 정보 가져오기
		Map<String, List<String>> headerMap = urlConn.getHeaderFields();
		
		Iterator<String> it = headerMap.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			System.out.println(key+" : "+headerMap.get(key));
		}
		System.out.println("---------------------------------------------");
		
		// 응답 메세지의 내용을 가져오기
		InputStream is = (InputStream) urlConn.getContent();
		InputStreamReader isr = new InputStreamReader(is);
		
		int data = 0;
		
		while((data = isr.read()) != -1) {
			System.out.print((char) data);
		}
		isr.close();
	}
}
