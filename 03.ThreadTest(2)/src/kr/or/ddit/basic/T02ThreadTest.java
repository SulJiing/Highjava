package kr.or.ddit.basic;

public class T02ThreadTest {

	public static void main(String[] args) {

		// 멀티스레드 프로그램 생성하기
		// start()말고 run()을 쓰면 쓰레드가 한개로 됨

		// 방법 1 : Tread클래스를 상속한 class의 인스턴스를 생성한 후
		// 이 인스턴스의 start()메서드를 호출한다.
		MyThread1 th1 = new MyThread1();
		th1.start();

		// 방법 2 : Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후
		// 이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로
		// 넘겨준다. 이 때 생성된 스레드 객체의 인스턴스 메서드 start()를 호출한다.
		Runnable r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.start();

		// 방법 3 : 익명클래스를 이용하는 방법
		// Runnable 인터페이스를 구현한 익명클래스를 Thread인스턴스를
		// 생성할 때 매개변수로 넣어준다.
		// 이 때 생성된 스레드 객체의 인스턴스 메서드 start()를 호출한다.
		Thread th3 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.print("@");

					try {
						// Thread.sleep(시간_ => 주어진 시간동안 작업을 잠시 멈춘다.
						// 시간은 밀리세컨드 단위를 사용한다.
						// 즉, 1000은 1초를 의미한다.
						Thread.sleep(100);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		th3.start();
		System.out.println("main()메서드 끝...");
	}
}

class MyThread1 extends Thread {
	@Override
	public void run() {

		for (int i = 1; i <= 200; i++) {
			System.out.print("*");

			try {
				// Thread.sleep(시간_ => 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");

			try {
				// Thread.sleep(시간_ => 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}