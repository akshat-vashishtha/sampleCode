package com.qualtech.karza.api.response;

import java.io.Serializable;

public class LpgResult implements Serializable{

	
	/**
	 * 
	 */
	private  LpgData lpg;
	private  KarzaName name;
	@Override
	public String toString() {
		return "LpgResult [lpg=" + lpg + ", name=" + name + "]";
	}
	
	public LpgData getLpg() {
		return lpg;
	}
	public void setLpg(LpgData lpg) {
		this.lpg = lpg;
	}
	public KarzaName getName() {
		return name;
	}
	public void setName(KarzaName name) {
		this.name = name;
	}
	
	
	
	
}
