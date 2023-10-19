package kr.or.ddit.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	private static SqlSessionFactory sessionFactory;
	
	static {
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
	}
	
	/**
	 * 	SqlSession 객체를 제공하기 위한 메서드
	 * @return SqlSession 객체
	 */
	public static SqlSession getInstance() {
		return sessionFactory.openSession();
	}
	
	/**
	 * 	SqlSession 객체를 제공하기 위한 메서드
	 * @param autoCommit 오토커밋 여부 설정
	 * @return SqlSession 객체
	 */
	public static SqlSession getInstance(boolean autoCommit) {
		return sessionFactory.openSession(autoCommit);
	}
}
