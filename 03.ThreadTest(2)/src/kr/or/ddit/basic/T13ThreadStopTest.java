package kr.or.ddit.basic;

public class T13ThreadStopTest {
	
	public static void main(String[] args) {
		
		/*ThreadStopEX1 th1 = new ThreadStopEX1();
		th1.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		th1.setStopped(true);
		
		/**
		  stop() 메서드를 호출하면 스레드가 바로 중지된다.
		  이때 자원을 정리하지 못하고 종료되어서 프로그램에 나쁜영향을 줄 수 있다.
		  그래서 현재는 비추천(Deprecated)으로 되어있다.
		 */
//		th1.stop();*/
		
		ThreadStopEX2 th2 = new ThreadStopEX2();
		th2.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		th2.interrupt(); // 인터럽트 걸기
	}
}

class ThreadStopEX1 extends Thread {
	
	private boolean isStopped;

	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
	}
	@Override
	public void run() {
		while(!isStopped) {
			System.out.println("스레드 작업 중...");
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
}
/**
 * interrupt() 메서드를 이용하여 스레드 멈추기
 * @author PC-11
 *
 */
class ThreadStopEX2 extends Thread {
	
	@Override
	public void run() {
	/*	// 방법 1 => sleep() 메서드나 join() 메서드 등을을 사용했을 때
		//			interrupt()를 호출하면 InterruptedExcepton이 발생한다.
		//			이 예외를 이용하는 방법.
		try {
			while(true) {
				System.out.println("스레드 처리 중...");
				Thread.sleep(1);
			}
		} catch (InterruptedException ex) {}*/
		
		// 방법 2 => interrupt()가 호출되었는지 검사하기
		while(true) {
			System.out.println("스레드 처리 중...");
			
			/*// 검사방법 1 => 스레드의 인스턴스 메서드를 이용하는 방법
			if(this.isInterrupted()) { // interrupt()가 호출되면 true...
				System.out.println("인스턴스 메서드 isInterrupted() 호출됨");
				break;
			}*/
			// 검사방법 2 => 스레드의 static 메서드를 이용하는 방법 단,한번 호출되고 false로 돌려놓기 때문에 2번 호출하면 안됨
			if (Thread.interrupted()) {// interrupt()가 호출되면 true...
				System.out.println("정적 메서드 interrupted() 호출됨\t"+"두번째 호출시 => "+Thread.interrupted());
				break;
			}
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}
}