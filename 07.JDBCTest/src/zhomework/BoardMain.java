package zhomework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.BoardService;
import service.IBoardService;
import vo.VO;

public class BoardMain {

	private IBoardService boService;
	
	private static final Logger PARAM_LOGGER = LogManager.getLogger("log4jexam"); 
	
	public static void main(String[] args) {
		BoardMain h = new BoardMain();
		h.start();
	}

	public BoardMain() {
		boService = new BoardService();
	}

	private Scanner scan = new Scanner(System.in);

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

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
			case 1: // 새로운 게시글 작성
				insert();
				break;
			case 2: // 게시글 삭제
				delete();
				break;
			case 3: // 게시글 수정
				update();
				break;
			case 4: // 전체 게시글 목록 출력
				displayAll();
				break;
			case 5: // 게시글 검색
				search();
				break;
			case 6: // 작업 끝
				System.out.println("기능을 정지합니다.");
				System.exit(0);
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	private void insert() {

		boolean isExist = false;

		do {
			System.out.println("새로운 게시글 작성");

		} while (isExist);

		System.out.print("게시글 제목 => ");
		String title = scan.next().trim();

		System.out.print("작성자 => ");
		String name = scan.next().trim();

		scan.nextLine();

		System.out.print("내용 입력\n");
		String text = scan.nextLine();

		VO vo = new VO();

		vo = new VO(title, name, text, vo.getNo(), vo.getDate());

		int cnt = boService.registerBoard(vo);

		if (cnt > 0) {
			System.out.println(title + " 게시글 작성 성공");
		} else {
			System.out.println(title + " 게시글 작성 실패");
		}
	}

	private void delete() {
		System.out.println("게시글 삭제");
		System.out.println("삭제할 게시글의 번호를 입력해주세요.");
		System.out.print("게시글 번호 => ");
		String title = scan.next();

		int cnt = boService.remove(title);

		if (cnt > 0) {
			System.out.println(title + " 게시글 삭제 실패");
		} else {
			System.out.println(title + " 게시글 삭제 성공");
		}
	}

	private void update() {

		boolean isExist = false;

		String no = "";

		VO vo = new VO();

		do {
			System.out.println();

			System.out.println("수정할 게시글의 번호를 입력하세요.");
			System.out.print("게시글 번호  => ");
			no = scan.next();

			isExist = boService.checkBoard(no);

			if (!isExist) {
				System.out.println(no + "번 게시글은 존재하지 않습니다.");
				System.out.println("게시글 번호를 다시 입력해 주세요.");
			}

		} while (!isExist);

		System.out.print("게시글 제목 => ");
		String title = scan.next();

		System.out.print("작성자 => ");
		String name = scan.next();

		scan.nextLine();

		System.out.print("내용 입력\n");
		String text = scan.next();

		scan.nextLine();

		vo = new VO(title, name, text, vo.getDate(), no);

		System.out.println("Title: " + vo.getTitle());
		System.out.println("Name: " + vo.getName());
		System.out.println("Text: " + vo.getText());
		System.out.println("No: " + vo.getNo());

		int cnt = boService.modifyBoard(vo);

		if (cnt > 0) {
			System.out.println(no + "번 게시글 수정 성공");
		} else {
			System.out.println(no + "번 게시글 수정 실패");
		}
	}

	private void displayAll() {
		System.out.println();
		System.out.println("===========================================================");
		System.out.println("번호\t제목\t작성자\t작성날짜\t\t\t내용");
		System.out.println("===========================================================");

		List<VO> voList = boService.displayAll();
		
		for (VO vo : voList) {
			System.out.println(" " + vo.getNo() + "\t" + vo.getTitle() + "\t" + vo.getName() + "\t" + vo.getDate()
					+ "\t" + vo.getText());
		}
				
		System.out.println("===========================================================");
		System.out.println("전체 게시글 목록 출력 완료");
	}

	private void search() {

		scan.nextLine();
		System.out.println("검색!");
		System.out.print("게시글 제목 입력 => ");
		String title = scan.nextLine().trim();
		System.out.print("작성자 입력 => ");
		String name = scan.nextLine().trim();
		System.out.print("내용 입력 => ");
		String text = scan.nextLine().trim();

		VO vo = new VO();

		VO vo2 = new VO(title, name, text, vo.getDate(), vo.getNo());
		
		
		List<VO> voList = boService.searchBoard(vo2);

		System.out.println();
		System.out.println("===========================================================");
		System.out.println("번호\t제목\t작성자\t작성날짜\t\t\t내용");
		System.out.println("===========================================================");

		for (VO vo1 : voList)
			System.out.println(" " + vo1.getNo() + "\t" + vo1.getTitle() + "\t" + vo1.getName() + "\t" + vo1.getDate()
					+ "\t" + vo1.getText());
		System.out.println("===========================================================");
		System.out.println(title+" 게시글 검색 완료");
		
//		PARAM_LOGGER.debug(title, name, text, vo.getDate(), vo.getNo());
	}
}