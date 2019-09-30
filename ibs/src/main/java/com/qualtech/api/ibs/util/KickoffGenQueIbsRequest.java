package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KickoffGenQueIbsRequest implements Serializable{
	
	private String  captchCode;

	public String getCaptchCode() {
		return captchCode;
	}

	public void setCaptchCode(String captchCode) {
		this.captchCode = captchCode;
	}

	@Override
	public String toString() {
		return "KickoffGenQueIbsRequest [captchCode=" + captchCode + "]";
	}
	
	

}
