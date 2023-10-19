package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * 	db.properties 파일의 내용으로 DB정보를 성정하는 방법
 * 	방법2) ResourceBundle 객체 이용하기
 * @author PC-11
 *
 */
public class JDBCUtil3 {
	
	static ResourceBundle bundle; // 객체 변수 선언
	
	// static 블록 안에서는 클래스 전체에서 딱 한번만 실행됨(클래스 내부에서 한번만 실행하고자 하는 것이 있을 때 사용)
	static {

		bundle = ResourceBundle.getBundle("db");

		try {
			Class.forName(bundle.getString("driver"));
			System.out.println("드라이버 로딩 완료!");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!!!");
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
					bundle.getString("url"),
					bundle.getString("username"),
					bundle.getString("password"));
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
