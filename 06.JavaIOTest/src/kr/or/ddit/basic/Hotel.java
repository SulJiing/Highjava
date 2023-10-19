package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
	Scanner sc = new Scanner(System.in);
	public static Map<String, String> map = new HashMap<String, String>();
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		ObjectInputStream ois = null;
		ois = new ObjectInputStream(new FileInputStream("d:/D_Other/호텔.txt"));
		Object obj = null;

		obj = ois.readObject();

		map = (Map<String, String>) obj;
			
		ois.close();

		Hotel h = new Hotel();
		h.open();
	}
	
	public void open() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		printBar();
	}
	
	public void menu() {
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.print("번호입력 => ");
	}

	public void printBar() {

		menu();
		while (true) {
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomCheck();
				break;
			case 4:
				close();
				break;
			default:
				System.out.println("잘못입력! 다시 입력해주세요!");
				printBar();
			}
			printBar();
		}
	}

	private void checkIn() {
		System.out.println("체크인할 방번호를 입력하세요");
		System.out.print("방번호 => ");
		String roomNo = sc.next();
		if (map.get(roomNo) != null) {
			System.out.println(roomNo+"호는 이미 예약되었습니다. 다른 호실을 선택해주세요");
			return;
		}
		System.out.print("이름 =>");
		String name = sc.next();
		map.put(roomNo, name);
		System.out.println(map);
		System.out.println(roomNo + " 축하합니당 예약완료 " + name + "님 감사합니다.");
	}

	private void checkOut() {
		System.out.println("체크아웃할 방번호를 입력하세요");
		System.out.print("방번호 => ");
		String roomNo = sc.next();
		if (map.get(roomNo) == null) {
			System.out.println("예약된 방번호가 아닙니다.");
			return;
		}
		map.remove(roomNo);
		System.out.println(roomNo + " 감사합니다 체크아웃되었습니다. ");
	}

	private void roomCheck() {
		Set<Map.Entry<String, String>> key = map.entrySet();
		int cnt = 1;
		if (key.size() == 0) {
			System.out.println("예약된 방이 없습니다");
		} else {
			Iterator<Map.Entry<String, String>> it = key.iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entrykey = it.next();
				System.out.println("" + cnt + "\t" + entrykey.getKey() + "호\t" + entrykey.getValue() + "님");
				cnt++;
			}
		}
	}
	
	private void close() {
		System.out.println("업무종료!");
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");

		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream("d:/D_Other/호텔.txt"));

			oos.writeObject(map);

			System.out.println("저장 완료...");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}
}

