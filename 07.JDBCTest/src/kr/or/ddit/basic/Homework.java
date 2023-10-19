package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Homework {

	public static void main(String[] args) {
		Homework h = new Homework();
		h.start();
	}

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in);

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 글 삭제");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작 메서드
	 */
	public void start() {
		int choice = 0;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 새글 작성
				insert();
				break;
			case 2: // 글 삭제
				delete();
				break;
			case 3: // 글 수정
				update();
				break;
			case 4: // 전체 목록 출력
				displayAll();
				break;
			case 5: // 자료 검색
				search();
				break;
			case 6: // 작업 끝
				System.out.println("기능을 정지합니다.");
				System.exit(0);
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 5);
	}

	private void insert() {

		while (true) {
			System.out.println("새로운 글 작성");

			System.out.print("제목 => ");
			String title = scan.next().trim();

			System.out.print("작성자 => ");
			String name = scan.next().trim();

			System.out.print("내용 입력\n");
			String text = scan.next();

			scan.nextLine();

			try {
				conn = HomeworkUtil.getConnection();

				String sql = " insert into jdbc_board ( board_no, board_title, board_writer, board_date, board_content ) "
						+ " values ( board_seq.nextVal, ?, ?, sysdate, ?) ";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, name);
				pstmt.setString(3, text);

				int cnt = pstmt.executeUpdate();

				if (cnt > 0) {
					System.out.println(title + " 작성 성공");
					break;
				} else {
					System.out.println(title + "작성 실패");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				HomeworkUtil.close(conn, stmt, pstmt, rs);
			}
		}
	}

	private void delete() {
		System.out.println("글 삭제");
		System.out.println("삭제할 글 제목을 입력해주세요.");
		System.out.println("글 제목 => ");
		String title = scan.next();

		try {
			conn = HomeworkUtil.getConnection();

			String sql = " delete from jdbc_board where board_title = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setNString(1, title);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(title + "삭제 성공");
			} else {
				System.out.println(title + "삭제 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			HomeworkUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void update() {

		boolean isExist = false;
		int no = 0;

		do {
			System.out.println();

			System.out.println("수정할 글의 번호를 입력하세요.");
			System.out.print("글 번호  => ");
			no = scan.nextInt();

			isExist = check(no);

			if (!isExist) {
				System.out.println(no + "번 글은 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}

		} while (!isExist);

		System.out.print("제목 => ");
		String title = scan.next();

		System.out.print("작성자 => ");
		String name = scan.next();

		System.out.print("내용 입력\n");
		String text = scan.next();

		scan.nextLine();

		try {
			conn = HomeworkUtil.getConnection();

			String sql = " update jdbc_board set "
					+ "board_title = ? , board_writer = ? , board_date = sysdate , board_content = ?"
					+ " where board_no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, title);
			pstmt.setString(2, name);
			pstmt.setString(3, text);
			pstmt.setInt(4, no);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println(no + "수정 성공");
			} else {
				System.out.println(no + "수정 실패");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			HomeworkUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private boolean check(int no) {
		boolean isExist = false;

		try {
			conn = HomeworkUtil.getConnection();

			String sql = " select count(*) as cnt from jdbc_board where board_no = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			int cnt = 0;

			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				isExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			HomeworkUtil.close(conn, stmt, pstmt, rs);
		}
		return isExist;
	}

	private void displayAll() {
		System.out.println();
		System.out.println("===========================================================");
		System.out.println("번호\t제목\t작성자\t작성날짜\t\t\t내용");
		System.out.println("===========================================================");

		try {
			conn = HomeworkUtil.getConnection();

			stmt = conn.createStatement();

			rs = stmt.executeQuery(" select * from jdbc_board");

			conn.commit();

			while (rs.next()) {
				String no = rs.getString("board_no");
				String title = rs.getString("board_title");
				String name = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String text = rs.getString("board_content");

				System.out.println(" " + no + "\t" + title + "\t" + name + "\t" + date + "\t" + text);
			}
			System.out.println("===========================================================");
			System.out.println("전체 글 목록 출력 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			HomeworkUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private void search() {
		System.out.println("제목으로 검색!");
		System.out.println("글제목 입력 => ");
		String title = scan.next().trim();
		System.out.println("===========================================================");
		System.out.println("번호\t제목\t작성자\t작성날짜\t\t\t내용");
		System.out.println("===========================================================");

		try {
			conn = HomeworkUtil.getConnection();

			String sql = " select * from jdbc_board where board_title like ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, "%" + title + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String no = rs.getString("board_no");
				title = rs.getString("board_title");
				String name = rs.getString("board_writer");
				String date = rs.getString("board_date");
				String text = rs.getString("board_content");

				System.out.println(" " + no + "\t" + title + "\t" + name + "\t" + date + "\t" + text);
			}
			System.out.println("===========================================================");
			System.out.println(title + "검색 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			HomeworkUtil.close(conn, stmt, pstmt, rs);
		}
	}
}