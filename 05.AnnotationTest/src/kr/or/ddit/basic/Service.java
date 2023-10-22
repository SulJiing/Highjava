package kr.or.ddit.basic;


@PrintAnnotation //type
public class Service {
	
	public static void main(String[] args) {
		Service m1 = new Service();
		m1.method2();
	}

	@PrintAnnotation //method
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value = "%") // value만 있으면 생력이 가능함
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	@PrintAnnotation(value = "#", count =30)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
}
