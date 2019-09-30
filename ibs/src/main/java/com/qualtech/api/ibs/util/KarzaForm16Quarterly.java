package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaForm16Quarterly implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9195139971336472472L;
	private String tan;
	private String pan;
    private String fiscalYear;
    
    
	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getTan() {
		return tan;
	}
	public void setTan(String tan) {
		this.tan = tan;
	}
	public String getFiscalYear() {
		return fiscalYear;
	}
	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}
    
    
    

}
