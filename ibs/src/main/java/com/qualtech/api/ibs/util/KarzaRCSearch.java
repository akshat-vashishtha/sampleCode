package com.qualtech.api.ibs.util;

public class KarzaRCSearch {
	
	private String engineNo;
	private String chassisNo;
	private String rcRegNumber;
	public String getEngineNo() {
		return engineNo;
	}
	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}
	public String getChassisNo() {
		return chassisNo;
	}
	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}
	@Override
	public String toString() {
		return "KarzaRCSearch [engineNo=" + engineNo + ", chassisNo=" + chassisNo + ", rcRegNumber=" + rcRegNumber
				+ "]";
	}
	public String getRcRegNumber() {
		return rcRegNumber;
	}
	public void setRcRegNumber(String rcRegNumber) {
		this.rcRegNumber = rcRegNumber;
	}
	
	
	

}
