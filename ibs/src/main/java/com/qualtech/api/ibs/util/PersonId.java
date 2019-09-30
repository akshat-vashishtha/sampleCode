package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class PersonId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4418645463798790134L;
	private String idName;
	private String idNo;
	private String dtOfExpire;
	private String dtOfIssue;
	
	
	public String getDtOfExpire() {
		return dtOfExpire;
	}
	public void setDtOfExpire(String dtOfExpire) {
		this.dtOfExpire = dtOfExpire;
	}
	public String getDtOfIssue() {
		return dtOfIssue;
	}
	public void setDtOfIssue(String dtOfIssue) {
		this.dtOfIssue = dtOfIssue;
	}
	public String getIdName() {
		return idName;
	}
	public void setIdName(String idName) {
		this.idName = idName;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	@Override
	public String toString() {
		return "PersonId [idName=" + idName + ", idNo=" + idNo + ", dtOfExpire=" + dtOfExpire + ", dtOfIssue="
				+ dtOfIssue + "]";
	}
	
	

}
