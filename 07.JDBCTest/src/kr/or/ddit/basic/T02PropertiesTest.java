package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/*
 *  key와 value가 모두 String인 것 => Properties
 */
public class T02PropertiesTest {
	public static void main(String[] args) {
		
		Properties prop = new Properties();
		
		try {
			
			// 파일 내용 읽기
			prop.load(new FileInputStream("res/db.properties"));
			
			// 읽어온 내용 출력하기
			Enumeration<String> keys = (Enumeration<String>)prop.propertyNames();
			
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				String value = prop.getProperty(key);
				System.out.println(key+" => "+value);
			}
			
			System.out.println("출력 끝...");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("카피");
	}
}
