package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public class Hotel2 {
public static void main(String[] args) {
	Map<String, String> map1;
	ObjectInputStream ois = null;
	
	try {
		ois = new ObjectInputStream(new FileInputStream("d:/D_Other/호텔.txt"));
		
		Object obj = null;
		
		obj = ois.readObject();
		if(obj != null)	{
			// 읽어온 데이터를 원래의 객체형으로 변화 후 사용
			map1 = (Map<String, String>)obj;
			for(String key : map1.keySet()) {
				System.out.println("객실번호 : "+key+"호 \n예약자명 : "+ map1.get(key));
				System.out.println();
			}
		}
					
		
	} catch (IOException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	} finally {
		try {
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
}

	
