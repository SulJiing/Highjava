package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class T08MapTest {
public static void main(String[] args) {
	
	/*
	  Map => key값과 value값을 한 쌍으로 관리하는 객체
	  	  => key값은 중복을 허용하지 않고, 인덱스가 존재하지 않는다(Set의 특징)
	  	  => value값은 중복을 허용한다.(List의 특징)
	 */
	
	Map<String, String> map = new HashMap<String, String>();

	// 데이터 추가
	map.put("name", "홍길동");
	map.put("addr", "대전");
	map.put("tel", "010-1111-1111");

	System.out.println("map => " + map);
	
	// 데이터 수정 => 데이터를 저장할 때 key값이 같으면 나중에 입력한 값이 저장된다.
	//					put(수정할 key값, 새로운 value값)
	map.put("addr", "서울");
	System.out.println("map => " + map);
	
	// 자료 삭제 => remove(삭제할 데이터의 key값)
	map.remove("name");
	System.out.println("map => " + map);
	
	// 데이터 읽기 => get(key값)
	System.out.println("addr = " + map.get("addr"));
	System.out.println("==============================================");
	
	// key값들을 읽어와 데이터를 출력하는 방법
	
	// 방법1 => keySet() 이용하기
	Set<String> keySet = map.keySet();
	System.out.println("Iterator를 이용하는 방법");

	Iterator<String> it = keySet.iterator();
	while (it.hasNext()) {
		String key = it.next();
		System.out.println(key + " : " + map.get(key));
	}
	System.out.println("-------------------------------------------------");
	
	// 방법2 => 향상된 for문(forEach문)을 이용하는 방법
	System.out.println("향상된 for문(forEach문)을 이용하는 방법");
	for (String key : keySet) {
		System.out.println(key + " : " + map.get(key));
	}
	System.out.println("--------------------------------------------------");
	
	// 방법3 => value값들만 읽어와 출력하기
	System.out.println("values() 메서드를 이용한 방법");
	for (String value : map.values()) {
		System.out.println(value);

	}
	System.out.println("--------------------------------------------------");
	
	// 방법4 => Map관련 클래스에 는 Map.Entry타입의 내부 클래스가 만들어져 있다.
	// 		   Map에서 이 Map.Entry타입의 객체들을 Set타입으로 제공하는 메서드를 사용한다.
	
	// Map.entry타입의 객체를 담은 Set객체 가져오기
	System.out.println("entrySet()을 이용한 방법");

	Set<Map.Entry<String, String>> entrySet = map.entrySet();

	Iterator<Map.Entry<String, String>> entryIt = entrySet.iterator();
	while (entryIt.hasNext()) {
		Map.Entry<String, String> entry = entryIt.next();

		System.out.println("key값 : " + entry.getKey());
		System.out.println("value값 : " + entry.getValue());
		System.out.println();
	}
}
}
