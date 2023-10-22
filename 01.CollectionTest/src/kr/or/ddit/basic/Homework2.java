package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Homework2 {
	
/*	로또를 구매하는 프로그램 작성하기
	 
	 사용자는 로또를 구매할 때 구매할 금액을 입력하고
	 입력한 금액에 맞게 로또번호를 출력한다.
	 (단, 로또 한장의 금액은 1000원이고 거스름돈도 계산하여
	      출력한다.)

		==========================
	         Lotto 프로그램
		--------------------------
		 1. Lotto 구입
		  2. 프로그램 종료
		==========================		 
		메뉴선택 : 1  <-- 입력
				
		 Lotto 구입 시작
			 
		(1000원에 로또번호 하나입니다.)
		금액 입력 : 2500  <-- 입력
				
		행운의 로또번호는 아래와 같습니다.
		로또번호1 : 2,3,4,5,6,7
		로또번호2 : 20,21,22,23,24,25
				
		받은 금액은 2500원이고 거스름돈은 500원입니다.
				
	   	 ==========================
	         Lotto 프로그램
		--------------------------
		  1. Lotto 구입
		  2. 프로그램 종료
		==========================		 
		메뉴선택 : 2  <-- 입력
			
		감사합니다*/
	
	
	public static void main(String[] args) {
		program();
	}

	public static Object program(){
		int money = 0;
		System.out.println("==========================");
		System.out.println("        Lotto 프로그램               ");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴선택 : ");
		Scanner sc = new Scanner(System.in);
		try{ int choice = sc.nextInt();
		switch (choice) {
		case 1:
			System.out.print("금액 : ");
			money = sc.nextInt();
			for(int i=1; i<=money/1000; i++) {
				System.out.println("로또번호"+i+" : "+ shuffle());
			}
			break;
		case 2: 
			System.out.println("프로그램 종료 감사합니다!");
			System.exit(0);
		default: System.out.println("잘못입력! 다시 입력해주세요!");
		return program();
		}
		System.out.println("받은 금액 : "+money+"\t남은 금액 : "+money%1000);
		}
		catch (Exception e) {
			System.out.println("잘못입력! 정수를 입력해주세요!");
			return program();
		}
		return program();
}

	public static Set<Integer> shuffle() {
//		Set hs1 = new HashSet();
//		Iterator it = hs1.iterator();
		
		Set<Integer> intRnd = new HashSet<Integer>();
		
		while(intRnd.size()<6)
		{
			int num = (int) (Math.random() * 45 + 1);
			intRnd.add(num);
		}
//		hs1.clear();
		return intRnd;
	}
}


