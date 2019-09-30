package com.qualtech.equifax.api.response;

public class NsdlResponse {

	private String firstname;
	private String percentmatch;
	private String lastupdateddate;
	private String returncode;
	private String nsdlrespid;
	private String title;
	private String pan;
	private String returncodedesc;
	private String lastname;
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getPercentmatch() {
		return percentmatch;
	}
	public void setPercentmatch(String percentmatch) {
		this.percentmatch = percentmatch;
	}
	public String getLastupdateddate() {
		return lastupdateddate;
	}
	public void setLastupdateddate(String lastupdateddate) {
		this.lastupdateddate = lastupdateddate;
	}
	public String getReturncode() {
		return returncode;
	}
	public void setReturncode(String returncode) {
		this.returncode = returncode;
	}
	public String getNsdlrespid() {
		return nsdlrespid;
	}
	public void setNsdlrespid(String nsdlrespid) {
		this.nsdlrespid = nsdlrespid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getReturncodedesc() {
		return returncodedesc;
	}
	public void setReturncodedesc(String returncodedesc) {
		this.returncodedesc = returncodedesc;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	@Override
	public String toString() {
		return "NsdlResponse [firstname=" + firstname + ", percentmatch=" + percentmatch + ", lastupdateddate="
				+ lastupdateddate + ", returncode=" + returncode + ", nsdlrespid=" + nsdlrespid + ", title=" + title
				+ ", pan=" + pan + ", returncodedesc=" + returncodedesc + ", lastname=" + lastname + "]";
	}
	
	
}
