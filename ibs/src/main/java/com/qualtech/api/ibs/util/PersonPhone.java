package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class PersonPhone implements Serializable{
	
	private String telephoneExtn;
    private String teleNo;
    private String type;
	public String getTelephoneExtn() {
		return telephoneExtn;
	}
	public void setTelephoneExtn(String telephoneExtn) {
		this.telephoneExtn = telephoneExtn;
	}
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PersonPhone [telephoneExtn=" + telephoneExtn + ", teleNo=" + teleNo + ", type=" + type + "]";
	}
    

}
