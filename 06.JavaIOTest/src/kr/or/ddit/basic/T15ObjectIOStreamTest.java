package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *  객체 입출력 보조스트림 예제(직렬화 및 역직렬화)
 *   객체를 파일로 저장할 때 직렬화가 일어남
 *   파일을 객체로 읽어올 때 역직렬화가 일어남
 * @author PC-11
 *
 */
public class T15ObjectIOStreamTest {
	public static void main(String[] args) {
		
		// Member 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("일지매", 30, "부산");
		Member mem3 = new Member("성춘향", 40, "광주");
		Member mem4 = new Member("이몽룡", 50, "대구");
		
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/memObj.bin"));
			
			// 쓰기 작업...
			oos.writeObject(mem1); // 직렬화...
			oos.writeObject(mem2); // 직렬화...
			oos.writeObject(mem3); // 직렬화...
			oos.writeObject(mem4); // 직렬화...
			
			System.out.println("객체 쓰기 작업 완료...");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//////////////////////////////////////////
		// 저장한 객체를 읽어와 사용하기
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new FileInputStream("d:/D_Other/memObj.bin"));
			
			Object obj = null;
			
			while ((obj = ois.readObject()) != null) {
				
				// 읽어온 데이터를 원래의 객체형으로 변화 후 사용
				Member mem = (Member) obj;
				System.out.println("\n이름 : "+ mem.getName());
				System.out.println("나이 : "+ mem.getAge());
				System.out.println("주소 : "+ mem.getAddr());
				System.out.println("----------------------------------");
			}
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class Member implements Serializable {
						// 자바는 serialiable 인터페이스를 구현한 클래스만 직렬화 할 수 있도록 제한하고 있다.
	
	/*
	 	transient => 직렬화가 되지 않을 멤버변수에 지정한다.
	 				 (static 필드도 직렬화 대상이 아니다.) - 인스턴스 변수가 대상이기 때문에
	 		직렬화가 도지 않는 멤버변수는 기본값으로 저장된다.
	 		(참조변수 : null, 숫자형 변수 : 0)
	 */
	
	private transient String name;
	private int age;
	private String addr;
	
	public Member(String name, int age, String addr) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}