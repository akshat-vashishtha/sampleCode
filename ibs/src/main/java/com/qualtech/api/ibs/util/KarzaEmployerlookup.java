package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaEmployerlookup implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2210417408944130066L;
	private String uan;

	public String getUan() {
		return uan;
	}

	public void setUan(String uan) {
		this.uan = uan;
	}

	@Override
	public String toString() {
		return "KarzaEmployerlookup [uan=" + uan + "]";
	}
	
	
}
