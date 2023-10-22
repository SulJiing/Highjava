package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) {

		System.out.println("static 상수값 : " + PrintAnnotation.id);

		// reflection 기능을 이용한 메서드의 메터정보 가져오기
		Class<?> clazz = Service.class;

		Method[] methodArr = clazz.getDeclaredMethods();

		for (Method m : methodArr) {

			System.out.println("메서드명 : " + m.getName());

			Annotation[] annos = m.getDeclaredAnnotations();

			for (Annotation anno : annos) {
				if (anno.annotationType().getSimpleName().equals("PrintAnnotation")) { // PrintAnnotation인지 확인
					PrintAnnotation printAnno = (PrintAnnotation) anno;
					for (int i = 0; i < printAnno.count(); i++) {
						System.out.print(printAnno.value());
					}
				}
			}
			System.out.println(); // 줄바꿈 처리
		}
	}
}