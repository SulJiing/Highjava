package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class MyBatisTest {
	public static void main(String[] args) {

		// myBatis를 이용하여 DB데이터 를 처리하는 작업 순서
		// 1. myBatis의 환경설정 파일을 읽어와 필요한 객체를 생성한다.
		SqlSessionFactory sessionFactory = null;

		try {
			// 1-1. 설정파일 읽기(xml문서)
			// 설정파일의 인코딩정보 설정(한글처리를 위해서...)
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);

			Reader rd = Resources.getResourceAsReader("config/mybatis-config.xml");

			// 1-2. 위에서 생성한 Reader객체를 이용하여 필요한 객체 생성
			sessionFactory = new SqlSessionFactoryBuilder().build(rd);

			rd.close(); // 스트림 닫기

		} catch (IOException e) {
			e.printStackTrace();
		}

		// 2. 실행할 SQL문에 맞는 쿼리문을 호출하여 원하는 작업을 수행한다.

		// 2-1. insert 작업 연습
		System.out.println("insert 작업 시작....");

		MemberVO mv = new MemberVO();
		mv.setMemId("d002");
		mv.setMemName("강감찬");
		mv.setMemTel("010-1111-1111");
		mv.setMemAddr("경남 진해시");

		System.out.println("--------------------------------------------------");

		// SqlSession객체를 이용하여 해당 쿼리문을 실행한다.
		// ex) sqlSession.insert("namespace값.id값", 파라미터 객체)
		// 반환값 : 성공한 레코드 수

		// 세션 열기(오토커밍 여부설정)
		SqlSession sqlSession = sessionFactory.openSession(false);

		try {
			int cnt = sqlSession.insert("memberTest.insertMember", mv);

			if (cnt > 0) {
				System.out.println("insert 작업 성공 : " + cnt);
				sqlSession.commit();
			} else {
				System.out.println("insert 작업 실패 !!!");
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close(); // 세션 닫기 - 연결을 끊는 것이 아니라 커넥션풀에 반납하는 것
		}
		
		System.out.println("--------------------------------------------------");
		
		// 2-2. update 연습
		System.out.println("update 작업 시작...");

		MemberVO mv2 = new MemberVO();
		mv2.setMemId("d001");
		mv2.setMemName("강감찬");
		mv2.setMemTel("010-222-222");
		mv2.setMemAddr("대전시 중구 오류동");

		SqlSession session = sessionFactory.openSession(); // 파라미터값이 없으면 false와 같음

		try {
			// update() 메서드의 반환값도 성공한 레코드 수이다.
			int cnt = session.update("memberTest.updateMember", mv2);

			if (cnt > 0) {
				System.out.println("update 작업 성공");
				session.commit();
			} else {
				System.out.println("update 작업 실패");
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		System.out.println("--------------------------------------------------");
		
		/*// 2-3. delete 연습
 		System.out.println("delete 작업 시작...");
		
 		SqlSession session2 = sessionFactory.openSession();
 		
 		try {
			int cnt = session2.delete("memberTest.deleteMember","d001");
			
			if (cnt > 0) {
				System.out.println("delete 작업 성공");
				session2.commit(); // 오토커밋을 꺼놨기 때문에 명시적으로 해줘야 함.
			} else {
				System.out.println("delete 작업 실패");
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session2.close();
		}*/
		
		System.out.println("--------------------------------------------------");
		
		// 2-4. select 연습
		// 1) 응답의 결과가 여러 개 일 경우
		
		System.out.println("select 연습 시작(결과가 여러 개 일 경우)...");
		
		// 응답결과가 여러 개 일 경우에는 selectList()를 사용한다.
		// 이 메서드는 여러개의 레코드를 VO에 담은 후 VO 데이터를 List에 추가해주는 작업을 자동으로 수행한다.
		
		SqlSession session3 = sessionFactory.openSession(true); // 오토 커밋 켜둔 것
		
		try {
			List<MemberVO> memList = session3.selectList("memberTest.selectAllMember");
			
			for(MemberVO mv3 : memList) {
				System.out.println("ID : "+mv3.getMemId());
				System.out.println("이름 : "+mv3.getMemName());
				System.out.println("전화번호 : "+mv3.getMemTel());
				System.out.println("주소 : "+mv3.getMemAddr());
				System.out.println("========================");
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session3.close();
		}
		
		// 2) 응답결과가 1개인 경우...
		System.out.println("select 연습 시작(응답결과가 1개인 경울)...");
		
		SqlSession session4 = sessionFactory.openSession(true);
		
		try {
			MemberVO mv4 = session4.selectOne("memberTest.getMember", "b001");
			
			System.out.println("ID : "+mv4.getMemId());
			System.out.println("이름 : "+mv4.getMemName());
			System.out.println("전화번호 : "+mv4.getMemTel());
			System.out.println("주소 : "+mv4.getMemAddr());
			System.out.println("========================");
			
		} catch (PersistenceException e) {
			e.printStackTrace();
		} finally {
			session4.close();
		}
	}
}
