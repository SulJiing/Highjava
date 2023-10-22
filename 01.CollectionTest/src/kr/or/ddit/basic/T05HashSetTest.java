package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class T05HashSetTest {

	public static void main(String[] args) {

		/*
		 	List와 Set의 차이점
		 	
		 	1. List
		 	- 입력한 데이터의 순서(인덱스)가 있다.
		 	- 중복되는 데이터를 저장할 수 있다.
		 	
		 	2. Set
		 	- 입력한 데이터의 순서(인덱스)가 없다.
		 	- 중복되는 데이터를 저장할 수 없다.
		 */
		
		Set hs1 = new HashSet();
		
		// Set에 데이터를 추가할 때도 add()를 이용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set 데이터 : "+hs1);
		System.out.println();
		
		//Set은 데이터 중복을 허용하지 않는다.
		//그래서 이미 있는 데이터를 add하면 false를 반환하고, 데이터는 추가되지 않는다.
		boolean isAdded = hs1.add("FF");
		System.out.println("중복되지 않을 때 : "+isAdded);
		System.out.println("Set 데이터 : "+hs1);
		System.out.println();
		
		isAdded = hs1.add("CC");
		System.out.println("중복될 때 : "+isAdded);
		System.out.println("Set 데이터 : "+hs1);
		System.out.println();
		
//		System.out.println("============================");
//		for(Integer num : hs1) {
//			System.out.println(num+" ");
//		}
		//Set 데이터를 수정하려면 수정하는 메서드가 따로 없기 때문에 해당 자료를 삭제한 후
		//새로운 데이터를 추가해 주어야 한다
		
		// 삭제하는 메서드
		// 1) clear() => 전제 데이터 삭제
		// 2) remove(삭제할 자료) => 해당 자료 삭제
		
		// 예) 'FF'를 'EE'로 수정하기
		hs1.remove("FF");
		System.out.println("FF 삭제 후 Set 데이터 : "+hs1);
		System.out.println();
		
		hs1.add("EE");
		System.out.println("EE 추가 후 Set 데이터 : "+hs1);
		System.out.println();
		
//		hs1.clear();//전제자료 삭제
//		System.out.println("clear() 후 Set 데이터 : "+hs1);
		System.out.println("Set의 데이터 개수 : "+hs1.size());
		System.out.println();
		
		//Set에서는 인덱스를 사용할 수 없기 때문에 Iterator 객체를 이용하여 하나씩 꺼내야 함
		Iterator it = hs1.iterator();
		
		// hasNext => 포인터 다음위치에 데이터가 있으면 true, 없으면 false를 반환한다.
		while (it.hasNext()) { //다음 데이터가 있는지 검사
			// next() => 포인터를 다음위치로 이동하고, 이동한 위치의 데이터를 반환한다.
			System.out.println(it.next());
		}
		
		// 1. 1~100 사이의 중복되지 않는 정수 5개 만들기
		Set<Integer> intRnd = new HashSet<Integer>();
		
		while(intRnd.size() < 5 ) {
			int num = (int) (Math.random()*100+1); //1~100 사이의 난수
			intRnd.add(num);
		}
		System.out.println("만들어진 난수들 : "+intRnd);
		
		//Collection 유형의 객체들을 서로 다른 자료구조로 쉽게 변경해서 사용할 수 있다.
		//다른 타입의 객체를 생성할 때 생성자의 파라미터로 해당 객체를 넣어주면 된다.
		List<Integer> intRndList = new ArrayList<Integer>(intRnd);
		System.out.println("List의 데이터 출력 ...");
		
		for(Integer num : intRndList) {
			System.out.println(num+" ");
		}
		System.out.println("============================");
		for(Integer num : intRnd) {
			System.out.println(num+" ");
		}
	}
}
