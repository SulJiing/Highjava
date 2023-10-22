package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class RockGame {
	public static boolean DATA_INPUT = false;
 public static void main(String[] args) {
	Thread th1 = new DataInput1();
	th1.start();
	
	Thread th2 = new CountDown1();
	th2.start();
}
}

class DataInput1 extends Thread {
	@Override
	public void run() {
		int computer = (int)(Math.random()*3+1);
		
		
		int str = Integer.valueOf(JOptionPane.showInputDialog("가위바위보 입력 가위1, 바위2, 보자기3"));
		RockGame.DATA_INPUT=true;
		
		if(computer-str==2 || computer-str==-1) {
			System.out.println("사용자 승리");
		} else if(computer-str==1 || computer-str== -2){
			System.out.println("컴퓨터 승리");
		} else {
			System.out.println("무승부");
		}
		System.out.println("컴퓨터 : "+computer+"  사용자 : "+str);
	}
}

class CountDown1 extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			if (RockGame.DATA_INPUT) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("5초 지남 게임 끝 깝 ㄴㄴ");
		System.exit(0);
	}
}