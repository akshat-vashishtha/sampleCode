package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_ICWAI_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICWAIMembershipResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	private String uniqueid;
	private String correlationid;
	private String memberDt;
	private String chapter;
	private String retired;
	private String dob;
	private String mName;
	private String protFirmName;
	private String validUpDt;
	private String menCategory;
	private String fName;
	private String gender;
	private String srName;
	private String effectiveDt;
	private String memRegion;
	private String crtEmployer;
	private String firmEftDt;
	private String cancellationDt;
	@Column(name="MEMBER_DT")
	public String getMemberDt() {
		return memberDt;
	}
	public void setMemberDt(String memberDt) {
		this.memberDt = memberDt;
	}
	@Column(name="CHAPTER")
	public String getChapter() {
		return chapter;
	}
	public void setChapter(String chapter) {
		this.chapter = chapter;
	}
	@Column(name="RETIRED")
	public String getRetired() {
		return retired;
	}
	public void setRetired(String retired) {
		this.retired = retired;
	}
	@Column(name="DOB")
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	@Column(name="M_NAME")
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	@Column(name="PROT_FIRM_NAME")
	public String getProtFirmName() {
		return protFirmName;
	}
	public void setProtFirmName(String protFirmName) {
		this.protFirmName = protFirmName;
	}
	@Column(name="VALID_UP_DT")
	public String getValidUpDt() {
		return validUpDt;
	}
	public void setValidUpDt(String validUpDt) {
		this.validUpDt = validUpDt;
	}
	@Column(name="MEN_CATEGORY")
	public String getMenCategory() {
		return menCategory;
	}
	public void setMenCategory(String menCategory) {
		this.menCategory = menCategory;
	}
	@Column(name="F_NAME")
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	@Column(name="GENDER")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="SR_NAME")
	public String getSrName() {
		return srName;
	}
	public void setSrName(String srName) {
		this.srName = srName;
	}
	@Column(name="EFFECTIVE_DT")
	public String getEffectiveDt() {
		return effectiveDt;
	}
	public void setEffectiveDt(String effectiveDt) {
		this.effectiveDt = effectiveDt;
	}
	@Column(name="MEM_REGION")
	public String getMemRegion() {
		return memRegion;
	}
	public void setMemRegion(String memRegion) {
		this.memRegion = memRegion;
	}
	@Column(name="CRT_EMPLOYER")
	public String getCrtEmployer() {
		return crtEmployer;
	}
	public void setCrtEmployer(String crtEmployer) {
		this.crtEmployer = crtEmployer;
	}
	@Column(name="FIRM_EFF_DT")
	public String getFirmEftDt() {
		return firmEftDt;
	}
	public void setFirmEftDt(String firmEftDt) {
		this.firmEftDt = firmEftDt;
	}
	@Column(name="CANCELLATION_DT")
	public String getCancellationDt() {
		return cancellationDt;
	}
	public void setCancellationDt(String cancellationDt) {
		this.cancellationDt = cancellationDt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ICWAIMembershipResult [memberDt=" + memberDt + ", chapter=" + chapter + ", retired=" + retired
				+ ", dob=" + dob + ", mName=" + mName + ", protFirmName=" + protFirmName + ", validUpDt=" + validUpDt
				+ ", menCategory=" + menCategory + ", fName=" + fName + ", gender=" + gender + ", srName=" + srName
				+ ", effectiveDt=" + effectiveDt + ", memRegion=" + memRegion + ", crtEmployer=" + crtEmployer
				+ ", firmEftDt=" + firmEftDt + ", cancellationDt=" + cancellationDt + "]";
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

