package com.qualtech.pan.api.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PanValidationResPayload {

	private String pan;
	private String panStatus;
	private String lName;
	private String fName;
	private String mName;
	private String title;
	private String dobIncorporationITD;
	private String fatherLName;
	private String fatherFName;
	private String fatherMName;
	private String lastUpdatedDate;
	private String uniqueId;
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPanStatus() {
		return panStatus;
	}
	public void setPanStatus(String panStatus) {
		this.panStatus = panStatus;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDobIncorporationITD() {
		return dobIncorporationITD;
	}
	public void setDobIncorporationITD(String dobIncorporationITD) {
		this.dobIncorporationITD = dobIncorporationITD;
	}
	public String getFatherLName() {
		return fatherLName;
	}
	public void setFatherLName(String fatherLName) {
		this.fatherLName = fatherLName;
	}
	public String getFatherFName() {
		return fatherFName;
	}
	public void setFatherFName(String fatherFName) {
		this.fatherFName = fatherFName;
	}
	public String getFatherMName() {
		return fatherMName;
	}
	public void setFatherMName(String fatherMName) {
		this.fatherMName = fatherMName;
	}
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	@Override
	public String toString() {
		return "PanValidationResPayload [pan=" + pan + ", panStatus=" + panStatus + ", lName=" + lName + ", fName="
				+ fName + ", mName=" + mName + ", title=" + title + ", dobIncorporationITD=" + dobIncorporationITD
				+ ", fatherLName=" + fatherLName + ", fatherFName=" + fatherFName + ", fatherMName=" + fatherMName
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", uniqueId=" + uniqueId + "]";
	}
	
	
}
