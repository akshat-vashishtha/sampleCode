package com.qualtech.karza.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_ICWAIFIRMAUTH_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICWAIFirmAuthResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;

	private String uniqueid;
	private String correlationid;
	private String approvalDate;
	private String firmType;
	private String firmName;
	private String pin;
	private String city;
	private String reConDate;
	private String region;
	private String address;
	private List<ICWAIFirmAuthMemberDetails> memberDetails;
	private String deedDt;
	private String state;
	private String contact;
	private String mobile;
	private String ldt;
	private String dist;
	private String email;
	@Column(name="APPROVAL_DATE")
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	@Column(name="FIRM_TYPE")
	public String getFirmType() {
		return firmType;
	}
	public void setFirmType(String firmType) {
		this.firmType = firmType;
	}
	@Column(name="FIRM_NAME")
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	@Column(name="PIN")
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="RECON_DATE")
	public String getReConDate() {
		return reConDate;
	}
	public void setReConDate(String reConDate) {
		this.reConDate = reConDate;
	}
	@Column(name="REGION")
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
    //@Transient
	@OneToMany(fetch=FetchType.LAZY,mappedBy="icwaiFirmAuthResult", cascade=CascadeType.ALL)
	public List<ICWAIFirmAuthMemberDetails> getMemberDetails() {
		for(ICWAIFirmAuthMemberDetails icwaiFirmAuthMemberDetails : this.memberDetails) {
			icwaiFirmAuthMemberDetails.setIcwaiFirmAuthResult(this);
		}
		return memberDetails;
	}
	public void setMemberDetails(List<ICWAIFirmAuthMemberDetails> memberDetails) {
		this.memberDetails = memberDetails;
	}
	@Column(name="DEED_DT")
	public String getDeedDt() {
		return deedDt;
	}
	public void setDeedDt(String deedDt) {
		this.deedDt = deedDt;
	}
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="CONTACT")
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(name="MOBILE")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="LDT")
	public String getLdt() {
		return ldt;
	}
	public void setLdt(String ldt) {
		this.ldt = ldt;
	}
	@Column(name="DIST")
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ICWAIFirmAuthResult [approvalDate=" + approvalDate + ", firmType=" + firmType + ", firmName=" + firmName
				+ ", pin=" + pin + ", city=" + city + ", reConDate=" + reConDate + ", region=" + region + ", address="
				+ address + ", memberDetails=" + memberDetails + ", deedDt=" + deedDt + ", state=" + state
				+ ", contact=" + contact + ", mobile=" + mobile + ", ldt=" + ldt + ", dist=" + dist + ", email=" + email
				+ "]";
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	
}
