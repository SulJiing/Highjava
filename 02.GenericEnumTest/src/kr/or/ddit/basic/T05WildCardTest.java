package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class T05WildCardTest {

	/*
	  와일드 카드에 대하여...
	  
	  와일드 카드(?)는 제너릭 타입을 이용한 타입 안전한 코드를 위해 사용되는 특별한 종류의 인수(Argument)로서
	  변수선언, 객체생성 및 메서드를 정의할 때 사용된다.(제너릭 타입 선언시에는 사용할 수 없다.)
	  
	  <? extends T> => 와일드카드의 상한 제한. T와 그 자손들만 가능
	  <? super T>   => 와일드카드의 하한 제한. T와 그 조상들만 가능
	  <?>			=> 모든 허용가능한 타입 가능. 
	 */
		public static void main(String[] args) {
			
//			List<?> list1 = new ArrayList<String>();
//			list1 = new ArrayList<Integer>();
			
			FruitBox<Fruit> fruitBox = new FruitBox<>();
			FruitBox<Apple> appleBox = new FruitBox<>();
			
			fruitBox.add(new Apple());
			fruitBox.add(new Grape());
			
			appleBox.add(new Apple());
			appleBox.add(new Apple());
			
			Juicer.makeJuice(fruitBox);
			Juicer.makeJuice(appleBox);
		}
}
class Juicer{
	
//	static <T extends Fruit> void makeJuice(FruitBox<T>box) {
//		static void makeJuice(FruitBox<? extends Fruit>box) {
	static void makeJuice(FruitBox<?>box) {
		String fruitListstr = ""; // 과일목록
		
		int cnt = 0;
		for(Object f : box.getFruitList()) {
			if(cnt == 0) {
				fruitListstr+= f;
			}else {
				fruitListstr+=", "+f;
			}
			cnt++;
		}
		System.out.println(fruitListstr + " => 쥬스 완성!!!");
	}
	
}

class Fruit{
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Fruit(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "과일 (" + name + ")";
	}
}

class Apple extends Fruit{
	public Apple() {
		super("사과");
	}
}
class Grape extends Fruit{
	public Grape() {
		super("포도");
	}
}
// 과일상자
class FruitBox<T extends Fruit>{
	private List<T> fruitList;

	public FruitBox() {
		fruitList = new ArrayList<T>();
	}

	public List<T> getFruitList() {
		return fruitList;
	}
	public void add(T fruit) {
		fruitList.add(fruit);
	}
}