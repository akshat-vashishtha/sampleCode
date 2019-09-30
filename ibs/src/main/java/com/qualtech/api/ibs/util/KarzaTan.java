package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaTan implements Serializable{
	
	private String tanNo;

	public String getTanNo() {
		return tanNo;
	}

	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
	}

	@Override
	public String toString() {
		return "KarzaTan [tanNo=" + tanNo + "]";
	}
	

}
