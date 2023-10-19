package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class T03LambdaTest {
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();

		list.add("홍길동1");
		list.add("홍길동2");
		list.add("홍길동3");

		for (String name : list) {
			System.out.println(name);
		}
		System.out.println("--------------------------------");

		list.forEach((name) -> System.out.println(name));

		System.out.println("--------------------------------");

		list.forEach(new Consumer<String>() {

			@Override
			public void accept(String name) {
				System.out.println(" " + name);
			}
		});

		System.out.println("--------------------------------");
		
		/*
		 	메서드 참조에 대하여...
		 	
		 	참조변수::인스턴스메서드
		 	클래스명::정적메서드
		 	클래스명::인스턴스메서드
		 	생성자명::new
		 	
		 */
		list.forEach(System.out::println);
		
		MyPrint mp = new MyPrint(); //객체생성
		
		System.out.println("--------------------------------");
		
		list.forEach(mp::printName); // 참조변수::인스턴스메서드
		list.forEach(new MyPrint()::printName); // 클래스명::인스턴스메서드
//		list.forEach(MyPrint()::printName); // printName이 static일 경우 가능, 클래스명::정적메서드
		list.forEach(MyPrint::new); // 생성자명::new
	}
}

class MyPrint {
	
	MyPrint() { } 
	
	public MyPrint(String name) {
		System.out.println("생성자 안에서 name : "+name);
	}

	public void printName(String name) {
		System.out.println("name : "+name);
	}
}