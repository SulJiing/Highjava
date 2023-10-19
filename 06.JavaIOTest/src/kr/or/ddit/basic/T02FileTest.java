package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class T02FileTest {
	public static void main(String[] args) throws IOException {

		File f1 = new File("d:/D_Other/sample.txt");
		File f2 = new File("d:/D_Other/test.txt");

		if (f1.exists()) { // true/false
			System.out.println(f1.getAbsolutePath() + "은 존재합니다");
		} else {
			System.out.println(f1.getAbsolutePath() + "은 없는 파일입니다.");
			if (f1.createNewFile()) {
				System.out.println(f1.getAbsolutePath() + "파일을 새로 만들었습니다.");
			}
		}
		if (f2.exists()) {
			System.out.println(f1.getAbsolutePath() + "은 존재합니다.");
		} else {
			System.out.println(f2.getAbsolutePath() + "은 없는 파일입니다.");
		}
		System.out.println("-------------------------------------------");

		File f3 = new File("d:/D_Other");
		File[] files = f3.listFiles();

		for (File f : files) {
			System.out.print(f.getName() + " => ");
			if (f.isFile()) {
				System.out.println("파일");
			} else if (f.isDirectory()) {
				System.out.println("디렉토리(폴더)");
			}
		}
		System.out.println("===========================================");

		String[] strFiles = f3.list();
		for (String name : strFiles) {
			System.out.println(name);
		}
		System.out.println("-------------------------------------------");
		System.out.println();
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		displayFileList(f3);
	}
	/**
	 	지정된 디렉토리(폴더)에 포함된 파일과 디렉토리 목록을 보여주기 위함 메서드
	 * @param dir 파일과 디렉토리 목록을 보여주고자 하는 파일 객체
	 */
	private static void displayFileList(File dir) {
		
		System.out.println("["+dir.getAbsolutePath()+"] 디렉토리의 내용");
		
		// 디렉토리 안의 모든 파일 목록을 가져온다.
		File[] files = dir.listFiles();
		
		// 하위 디렉토리 정보를 저장할 List객체 생성(인덱스 정보 저장용)
		List<Integer> subDirList = new ArrayList<Integer>();
		
		// 날짜를 출력하기 위한 형식 설정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		for(int i = 0; i<files.length; i++) {
			
			String attr = ""; // 파일의 속성정보(읽기, 쓰기, 히든, 디렉토리 구분)
			String size = ""; // 파일크기(용량)
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
				subDirList.add(i);
			} else {
				attr = files[i].canRead() ? "R" : " ";
				attr += files[i].canWrite() ? "W" : " ";
				attr += files[i].isHidden() ? "H" : " ";
				size = files[i].length()+"";
			}
			System.out.printf("%s %-5s %-12s %s\n",
			sdf.format(new Date(files[i].lastModified())), attr, size, files[i].getName());
		} // for문 끝
		
		int dirCount = subDirList.size(); // 하위폴더 갯수
		int fileCount = files.length - dirCount; // 파일 갯수
		
		System.out.println(fileCount+"개의 파일, "+dirCount+"개의 디렉토리(폴더)");
		System.out.println();
		
		for(Integer i : subDirList) {
			displayFileList(files[i]);
		}
 	}
}