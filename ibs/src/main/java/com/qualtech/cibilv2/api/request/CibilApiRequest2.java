package com.qualtech.cibilv2.api.request;

import java.io.Serializable;


public class CibilApiRequest2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4803492424111882203L;
	private CibilRequest2 request ;
	
	public CibilRequest2 getRequest() {
		return request;
	}
	public void setRequest(CibilRequest2 request) {
		this.request = request;
	}
	

}
