package com.qualtech.equifax.api.response;

public class NsdlRequest {

	private String source;
	private String pannumber;
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getPannumber() {
		return pannumber;
	}
	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}
	@Override
	public String toString() {
		return "NsdlRequest [source=" + source + ", pannumber=" + pannumber + "]";
	}
	
	
}
