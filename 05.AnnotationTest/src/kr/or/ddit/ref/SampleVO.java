package kr.or.ddit.ref;

import kr.or.ddit.basic.PrintAnnotation;

public class SampleVO implements Runnable{

	public String id;
	protected String nameString;
	private int age;
	
	public SampleVO(String id, String nameString, int age) {
		super();
		this.id = id;
		this.nameString = nameString;
		this.age = age;
	}


	@Override
	public String toString() {
		return "SampleVO [id=" + id + ", nameString=" + nameString + ", age=" + age + "]";
	}
	
	@PrintAnnotation
	@Deprecated
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public SampleVO() {

	}

	@Override
	public void run() {
		
	}
}
