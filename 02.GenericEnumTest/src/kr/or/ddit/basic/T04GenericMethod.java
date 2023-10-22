package kr.or.ddit.basic;

class Util2 {
	public static <T extends Number> int compare(T t1, T t2) {
		// doubleValue는 숫자만 가능함 - Number를 상속해야만 오류가 사라짐
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}

/*
  제한된 타입 파라미터 문법
  
 */
public class T04GenericMethod {
	public static void main(String[] args) {
		int result = Util2.compare(10, 20);
		System.out.println(result);
		
		result = Util2.compare(3.14, 3);
		System.out.println(result);
		
//		result = Util2.compare(3.14, "홍길동");
	}
}
