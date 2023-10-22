package kr.or.ddit.basic;

class NonGenericClass{
	private Object val;

	public Object getVal() {
		return val;
	}

	public void setVal(Object val) {
		this.val = val;
	}
}

class MyGeneric<T>{
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
}

public class T02GenericClassTest {
/*
  제너릭 클래스를 선언하는 방법
  
  형식)
  class 클래스명 <제너릭타입글자>{
  
  		제너릭타입글자 변수명; // 변수 선언에 제너릭을 사용할 경우...
  		...
  		제너릭타입글자 메서드명(){ // 반환값이 있는 메서드에 사용하는 경우...
  		...
  		return 값;
  		
  		}
  		...
  	}
 -- 제너릭타입글자 --
 T => Type
 K => key
 V => Value
 E => Element
 */
	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		String rtnVal1 = (String) ng1.getVal();
		System.out.println("문자열 반환값 rtnVal1 => "+rtnVal1);
		
		Integer rtnVal2 = (Integer) ng2.getVal();
		System.out.println("정수형 반환값 rtnVal2 => "+rtnVal2);
		
		MyGeneric<String> mg1 = new MyGeneric<String>();
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(400);
		
		rtnVal1 = mg1.getVal();
		rtnVal2 = mg2.getVal();
		
		System.out.println("제너릭 문자열 반환값 : "+rtnVal1);
		System.out.println("제너릭 정수형 반환값 : "+rtnVal2);
		
		/*
		 Object를 사용하면 좋지만 일일이 가져올 값의 타입을 알고 캐스팅 작업을 해줘야함
		 Generic에서 <T>가 없으면 일일이 모든 타입의 제너릭 클래스를 모두 만를어주어야 함
		 */
	}
}
