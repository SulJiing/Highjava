package kr.or.ddit.basic;

public class Homework4 {
	/* 문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
	(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 

	예) 행성의 반지름(KM):
	수성(2439), 
	금성(6052), 
	지구(6371), 
	성(3390), 
	목성(69911), 
	토성(58232), 
	천왕성(25362), 
	해왕성(24622)
	*/
	
	public static void main(String[] args) {
		final double PI = 3.14;
		
		Planet[] pArr1 = Planet.values();
		for(Planet p1 : pArr1 ) {
//			System.out.println(p1.name()+"의 부피는 "+(p1.getData()*p1.getData()*p1.getData()*PI*4/3)+"KM입니다.");
			long a = p1.getData()*p1.getData()*p1.getData()*3;
			a += p1.getData()*p1.getData()*p1.getData()/10;
			a += p1.getData()*p1.getData()*p1.getData()/100;
//			System.out.println(a);
			System.out.println(p1.name()+"의 부피는 "+a+" KM입니다.");
		}
		System.out.println();
		Planet[] pArr2 = Planet.values();
		for(Planet p2 : pArr2) {
//			System.out.println(p2.name()+"의 넓이는 "+(p2.getData()*p2.getData()*PI)+"KM입니다.");
			long a = p2.getData()*p2.getData()*p2.getData()*3;
			a += p2.getData()*p2.getData()*p2.getData()/10;
			a += p2.getData()*p2.getData()*p2.getData()/100;
			System.out.println(p2.name()+"의 넓이는 "+a+" KM입니다.");
		}
		System.out.println();
		Planet[] pArr3 = Planet.values();
		for(Planet p3 : pArr2) {
			System.out.println(p3.name()+"의 둘레는 "+(p3.getData()*2*PI)+"KM입니다.");
			System.out.println(p3.name()+"의 둘레는 "+Math.round(p3.getData()*2*PI)+"KM입니다.");
			System.out.println(p3.name()+"의 둘레는 "+(long)(p3.getData()*2*PI)+"KM입니다.");
		}
	}
}
enum Planet{
	수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
	
	private long data;
	
	private Planet(long data) {
		this.data=data;
	}
	public long getData() {
		return data;
	}
}
