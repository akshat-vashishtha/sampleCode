package com.qualtech.api.ibs.util;

public class KarzaShopEstablishment {
	
	private String regNo;
    
    private String areaCode;

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Override
	public String toString() {
		return "KarzaShopEstablishment [regNo=" + regNo + ", areaCode=" + areaCode + "]";
	}

    
    

}
