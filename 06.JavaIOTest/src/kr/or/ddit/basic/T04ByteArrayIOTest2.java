package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04ByteArrayIOTest2 {
	public static void main(String[] args) throws IOException {
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		byte[] temp = new byte[4];
		
		// 스트림 클래스를 이용하는 방법
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc); // 객체 생성 가져오기 (입력용)
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); // 내보내기(출력용)
		
		int readBytes = 0;
		
		// read()메서드 => 1 byte 단위로 데이터를 읽어와 int형으로 반환한다.
		//				더 이상 읽을 데이터가 없으면 -1을 반환한다. -1은 byte타입에 안 담기므로 read의 리턴타입은 int이다.
		
		while((readBytes = bais.read(temp)) != -1) {
			baos.write(temp,0,readBytes);
			
			System.out.println("temp => "+Arrays.toString(temp));
		}
		System.out.println();
		
		// 풀력된 스트림 데이터를 배열로 변환해서 반환하는 메서드
		outSrc = baos.toByteArray();
		
		System.out.println("inSrc => "+Arrays.toString(inSrc));
		System.out.println("outSrc => "+Arrays.toString(outSrc));
	}
}
