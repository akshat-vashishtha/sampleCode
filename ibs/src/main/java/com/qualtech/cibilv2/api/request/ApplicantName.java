package com.qualtech.cibilv2.api.request;

import java.io.Serializable;

public class ApplicantName implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8077462716616128522L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ApplicantName [name=" + name + "]";
	}
	
	

}
