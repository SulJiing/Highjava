package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

import util.PrintUtil;

public class T01ArrayListTest {
	public static void main(String[] args) {
		
		// 기본용량 : 10
		List list1 = new ArrayList();
		
		// add()메서드를 사용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(111);
		list1.add('k');
		list1.add(true);
		list1.add(12.24);
		
		//size() => 데이터 갯수
		System.out.println("size() => "+list1.size());
		System.out.println("list1 => "+list1);
		PrintUtil.bar();
		//get()을 이용하여 데이터 꺼내오기
		System.out.println("1번째 자료 : "+list1.get(0));
		PrintUtil.bar();
		//데이터 끼워넣기
		list1.add(0,"zzz");
		System.out.println("list1(끼워넣기 후) => "+list1);
		PrintUtil.bar();
		//데이터 변경하기
		String temp = (String) list1.set(0, "yyy");
		list1.set(1, "우왕");
		System.out.println("temp => "+temp);
		System.out.println("list1(데이터 변경 후) => "+list1);
		PrintUtil.bar();		
		//데이터 삭제하기
		list1.remove(0);
		System.out.println("데이터 삭제 후 => "+ list1);
		PrintUtil.bar();
		list1.remove("bbb");
		System.out.println("bbb 삭제 후 => "+list1);
		list1.remove(new Integer(111));
		list1.remove("111");
		System.out.println(list1);
		System.out.println("111 삭제 후 => "+list1);
		PrintUtil.bar();
		// 제너릭을 지정하여 선언할 수 있다.
		List <String> list2 = new ArrayList<String>();
		list2.add("AAA");
		list2.add("BBB");
		list2.add("CCC");
		list2.add("DDD");
		list2.add("EEE");
		PrintUtil.bar();
		for(String s :list2) {
			System.out.println(s);
		}
		//contains(비교객체) => 리스트에 '비교객게'가 있으면 true, 없으면 false
		System.out.println(list2.contains("DDD"));
		System.out.println(list2.contains("ZZZ"));
		PrintUtil.bar();
		//indexOf(비교객체) => 리스트에서 '비교객체'를 찾아 '비교객체'가 존재하는 index값을 반환한다. 
		//없으면 -1을 반환한다.
		System.out.println("DDD의 index값 : "+list2.indexOf("DDD"));
		System.out.println("ZZZ의 index값 : "+list2.indexOf("ZZZ"));
		PrintUtil.bar();
		
		for (int i = 0; i < list2.size(); i++) { //거꾸로 해야 함
			list2.remove(i);
		}
		System.out.println("list2 size() => "+list2.size());
		System.out.println(list2);
	}
}
