package com.qualtech.api.ibs.util;

public class KarzaIcsiMembership {
	
	
	private String membershipNo;
	private String cpNo;
	public String getMembershipNo() {
		return membershipNo;
	}
	public void setMembershipNo(String membershipNo) {
		this.membershipNo = membershipNo;
	}
	public String getCpNo() {
		return cpNo;
	}
	public void setCpNo(String cpNo) {
		this.cpNo = cpNo;
	}
	@Override
	public String toString() {
		return "KarzaIcsiMembership [membershipNo=" + membershipNo + ", cpNo=" + cpNo + "]";
	}
	
}
