package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T06ThreadTest {
	
	// 입력여부를 확인하기 위한 변수선언
	public static boolean INPUT_CHECK = false;
	
	public static void main(String[] args) {
		
		Thread th1 = new DataInput();
		th1.start();
		
		Thread th2 = new CountDown();
		th2.start();
	}
}

/**
 * 데이터 입력을 처리하는 스레드
 * @author PC-11
 */
class DataInput extends Thread {

	@Override
	public void run() {

		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		T06ThreadTest.INPUT_CHECK = true; //입력이 완료되었음을 알려줌

		System.out.println("입력한 값은 " + str + "입니다.");
	}
}
/**
 *  카운트 다운 처리를 위한 스레드
 * @author PC-11
 */
class CountDown extends Thread {

	@Override
	public void run() {
		for (int i = 10; i >= 1; i--) {
			if(T06ThreadTest.INPUT_CHECK) {
				return; // 스레드 종료됨
			}
			System.out.println(i);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 10초가 경과되었는데도 입력이 없으면 프로그램을 종료
		System.out.println("10초가 지났습니다. 프로그램 뿌슝빠슝");
		System.exit(0); //프로그램을 종료시키는 명령
	}

}
