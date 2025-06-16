package dto;

import java.sql.Date;

public class MemberDTO {

	private int mNo; // 회원번호
	private String mName; // 회원이름
	private String mId; // 회원아이디
	private String mPw; // 회원 비밀번호
	private Date regidate; // 회원생성날짜

	public int getmNo() {
		return mNo;
	}

	public String getmName() {
		return mName;
	}

	public String getmId() {
		return mId;
	}

	public String getmPw() {
		return mPw;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setmNo(int mNo) {
		this.mNo = mNo;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}
}
