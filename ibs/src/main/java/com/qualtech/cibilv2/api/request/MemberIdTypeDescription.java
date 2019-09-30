package com.qualtech.cibilv2.api.request;

import java.io.Serializable;

public class MemberIdTypeDescription implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7566736078959771816L;
	private String memberId;
	private String typeDescription;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getTypeDescription() {
		return typeDescription;
	}
	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
	@Override
	public String toString() {
		return "MemberIdTypeDescription [memberId=" + memberId + ", typeDescription=" + typeDescription + "]";
	}
	
	
	

}
