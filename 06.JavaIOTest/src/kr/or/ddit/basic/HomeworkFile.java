package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HomeworkFile {
	public static void main(String[] args) {

		String one = "d:/D_Other/Tulips.jpg";
		String two = "d:/D_Other/복사본_Tulips.jpg";

		try {
			FileInputStream inSrc = new FileInputStream(one);
			FileOutputStream outSrc = new FileOutputStream(two);

			int data = 0;

			while ((data = inSrc.read()) != -1) {
				outSrc.write(data);
			}

			inSrc.close();
			outSrc.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
