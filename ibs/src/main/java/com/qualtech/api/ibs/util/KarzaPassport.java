package com.qualtech.api.ibs.util;

public class KarzaPassport {

	
	private String doe;
	private String type;
	public String getDoe() {
		return doe;
	}
	public void setDoe(String doe) {
		this.doe = doe;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "KarzaPassport [doe=" + doe + ", type=" + type + "]";
	}
	
	
	
}
