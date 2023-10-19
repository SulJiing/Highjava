package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HomeworkUtil {
	
		static {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("");

			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패!!!");
				e.printStackTrace();
			}
		}

		public static Connection getConnection() {
			try {
				return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PC17", "java");
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}

		public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
			
			if(rs != null) try {rs.close();} catch (SQLException ex) {}
			if(stmt != null) try {stmt.close();} catch (SQLException ex) {}
			if(pstmt != null) try {pstmt.close();} catch (SQLException ex) {}
			if(conn != null) try {conn.close();} catch (SQLException ex) {}
		}
	}
