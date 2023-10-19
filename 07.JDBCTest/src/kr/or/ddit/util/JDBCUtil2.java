package kr.or.ddit.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 	db.properties 파일의 내용으로 DB정보를 성정하는 방법
 * 	방법1) Properties 객체 이용하기
 * @author PC-11
 *
 */
public class JDBCUtil2 {
	
	static Properties prop; // 객체 변수 선언
	
	// static 블록 안에서는 클래스 전체에서 딱 한번만 실행됨(클래스 내부에서 한번만 실행하고자 하는 것이 있을 때 사용)
	static {
		
		prop = new Properties(); // 객체 초기화
		
		try {
			prop.load(new FileInputStream("res/db.properties"));
			
			Class.forName(prop.getProperty("driver"));
			System.out.println("드라이버 로딩 완료!");
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Connection 객체 생성 메서드
	 * @return Connection 객체, 예외 발생시 null
	 */
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 	자원반납을 위한 메서드
	 * @param conn
	 * @param stmt
	 * @param pstmt
	 * @param rs
	 */
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		
		if(rs != null) try {rs.close();} catch (SQLException ex) {}
		if(stmt != null) try {stmt.close();} catch (SQLException ex) {}
		if(pstmt != null) try {pstmt.close();} catch (SQLException ex) {}
		if(conn != null) try {conn.close();} catch (SQLException ex) {}
	}
}
