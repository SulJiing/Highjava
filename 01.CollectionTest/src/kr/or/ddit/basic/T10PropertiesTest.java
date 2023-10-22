package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class T10PropertiesTest {
public static void main(String[] args) throws IOException {
	/*
	   Properties는 Map보다 축소된 기능의 객체라고 할 수 있다.
	   Map은 모든 타입의 객체 데이터를 key와 value값으로 사용할 수 있지만
	   Properties 객체는 key와 value값으로 String만 사용할 수 있다.
	   
	   Map은 put(), get()을 이용하여 데이터를 입출력 하지만,
	   Properties는 setProerty(), getProperty()를 이용하여 데이터 입출력을 한다
	 */
	Properties prop = new Properties();
	
	prop.setProperty("name", "홍길동");
	prop.setProperty("tel", "010-1111-1111");
	prop.setProperty("addr", "대전");
	
	String name = prop.getProperty("name");
	String tel = prop.getProperty("tel");
	
	System.out.println("이름 : "+name);
	System.out.println("전화 : "+tel);
	System.out.println("주소 : "+prop.getProperty("addr"));
	
	// 데이터를 파일로 저장하기
	// 자바의 파일경로에서 현재경로(./) => 프로젝트 경로를 의미
	prop.store(new FileOutputStream("src/kr/or/ddit/basic/test.properties"),"comments(주석)");
	
	//Properties 파일 읽어오기
	prop.load(new FileReader("src/kr/or/ddit/basic/test2.properties"));
	
	System.out.println("읽어온 정보");
	System.out.println("age : "+prop.getProperty("age"));
}
}
