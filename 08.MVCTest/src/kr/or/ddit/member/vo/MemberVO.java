package kr.or.ddit.member.vo;
/**
 *  DB테이블에 있는 컬럼을 기준으로 데이터를 객체화하기 위한 클래스
 * @author PC-11
 *	<p>
 *	DB 테이블의 '컬럼명'을 참고하여 멤버변수를 생성한다.<br>
 *	DB 테이블의 컬럼값이 멤버변수에 맵핑되게 한다.<br>
 *	</p>
 */

import java.time.LocalDate;

public class MemberVO {
	private String memId;
	private String memName;
	private String memTel;
	private String memAddr;
	
	public MemberVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public MemberVO(String memId, String memName, String memTel, String memAddr) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.memTel = memTel;
		this.memAddr = memAddr;
	}

	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", memTel=" + memTel + ", memAddr=" + memAddr
				+ "]";
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public String getMemAddr() {
		return memAddr;
	}

	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}

	public LocalDate getRegDt() {
		return regDt;
	}

	public void setRegDt(LocalDate regDt) {
		this.regDt = regDt;
	}

	private LocalDate regDt;
}
