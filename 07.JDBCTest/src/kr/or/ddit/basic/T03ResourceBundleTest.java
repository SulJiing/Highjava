package kr.or.ddit.basic;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

// source 폴더와 일반 폴더의 차이점
// source 폴더 안에는 자바 파일을 구성하기 위함(자바의 코드) => 마지막에는 컴파일을 해주어야 함
public class T03ResourceBundleTest {
	public static void main(String[] args) {
		/*
		 	ResourceBundle 객체 => 확장자가 properties인 파일 정보를 읽어와
		 						 key값과 value값을 분리한 정보를 갖는 객체
		 						 
		 				=> 읽어올 파일은 'key값=value값' 형태로 되어있어야 한다.
		 */
		
		// ResourceBundle 객체 생성하기
		// 클래스패스(여기서는bin)를 기준으로 찾음
		// => 파일을 지정할 때는 '파일명'만 지정하고 확장자는 생략한다.
		ResourceBundle bundle = ResourceBundle.getBundle("db",Locale.JAPANESE);
		
		// key값들만 읽어오기
		Enumeration<String> keys = bundle.getKeys();
		
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			
			String value = bundle.getString(key);
			
			System.out.println(key +" => "+value);
		}
		System.out.println("출력 끝...");
	}
}
