package kr.or.ddit.ref;

/**
  	Class 오브젝트(클래스 정보를 담고있는)를 생성하기
 */
public class T01ClassObjectCreationTest {
/*
 	Java Reflection에 대하여...
 	
 	1. 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를 가져오거나 수정할 수 있고,
 	       새로운 객체를 생성하거나, 메서드를 실행할 수 있다.
 	   (컴파일 시점에 해당 정보를 알 수 없는 경우(소스코드의 부재)에 유용하게 사용될 수 있음)
 	2. 리플레션 API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공된다.
 	3. java.lang.Class의 주요 메서드
 	 	- getName(), getSuperClass(), getInterface(), getModifiers() 등.
 	4. java.lang.reflect 패키지의 주요 클래스
 	 	- Field, Method, Constructor, Modifier 등.
 */
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		// 첫번째 방법 : Class.forName() 이용하기
		Class<?> klass = Class.forName("kr.or.ddit.ref.T01ClassObjectCreationTest");
		
		// 두번째 방법 : getClass() 메서드 이용하기
		T01ClassObjectCreationTest obj = new T01ClassObjectCreationTest();
		klass = obj.getClass();
		
		// 세번째 방법 : .class 이용
		klass = T01ClassObjectCreationTest.class;
	}
}