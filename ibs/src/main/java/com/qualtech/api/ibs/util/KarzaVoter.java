package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaVoter implements Serializable{
	
	private String epicNo;

	public String getEpicNo() {
		return epicNo;
	}

	public void setEpicNo(String epicNo) {
		this.epicNo = epicNo;
	}

	@Override
	public String toString() {
		return "KarzaVoter [epicNo=" + epicNo + "]";
	}
	

}
