package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Homework1 {

	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student("981218", "백영웅", 100, 70, 60));
		list.add(new Student("950621", "박주호", 90, 100, 70));
		list.add(new Student("910112", "김현우", 100, 80, 40));
		list.add(new Student("910720", "이상철", 75, 80, 100));
		list.add(new Student("230921", "변상원", 80, 70, 60));
		list.add(new Student("230922", "아저씨", 80, 70, 60));

		Collections.sort(list);
		System.out.println("학번 오름차순 => ");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("================================");
		Collections.sort(list, new SortSumDesc());
		System.out.println("총점 내림차순 + 같으면 학번 내림차순 => ");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
//		Collections.sort(list, new Descc());

	}
}

/*
 * xx class Descc implements Comparator<Student>{
 * 
 * @Override public int compare(Student o1, Student o2) { return
 * o1.compareTo(o2)*-1; } }
 */

// 총점을 역순으로 정렬 + 같으면 학번을 기준으로 내림차순 -1
class SortSumDesc implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		if (o1.getSum() > o2.getSum()) {
			return -1;
		} else if (o1.getSum() == o2.getSum()) {
//			return -1;
			return o1.getNum().compareTo(o2.getNum())*-1;
		} else {
			return 1;
		}
	}
}

// 변수선언, 겟터, 
class Student implements Comparable<Student> {
	private String num;
	private String name;
	private int korean;
	private int english;
	private int math;
	private int sum;
	private int rank;

	public Student(String num, String name, int korean, int english, int math) {
		super();
		this.num = num;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;
		sum = korean + english + math;
	}

	public String getNum() {
		return num;
	}

//	public void setNum(String num) {
//		this.num = num;
//	}
	public String getName() {
		return name;
	}

//	public void setName(String name) {
//		this.name = name;
//	}
	public int getKorean() {
		return korean;
	}

//	public void setKorean(int korean) {
//		this.korean = korean;
//	}
	public int getEnglish() {
		return english;
	}

//	public void setEnglish(int english) {
//		this.english = english;
//	}
	public int getMath() {
		return math;
	}

//	public void setMath(int math) {
//		this.math = math;
//	}
	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	// 학번 오름차순 정렬
	public int compareTo(Student st) {
		return this.getNum().compareTo(st.getNum());
	}

	// 출력형식
	@Override
	public String toString() {
		return "Student num=" + num + ", name=" + name + ", korean=" + korean + ", english=" + english + ", math="
				+ math + " sum = " + this.sum + "]";
	}
}
