package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_RC_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RCSearchResult implements Serializable{

	private static final long serialVersionUID = -8251784410816419882L;
	private String uniqueid;
	private String corelationid;
	private String rcRegnNo;
	private String rcOwnerName;
	private String rcEngNo;
	private String rcChasiNo;
	private String rcMakerDesc;
	private String rcMakerModel;
	private String rcRegnDt;
	private String rcVhClassDesc;
	private String rcColor;
	private String rcManuMonthYr;
	@Column(name="RCREGNNO")
	public String getRcRegnNo() {
		return rcRegnNo;
	}
	public void setRcRegnNo(String rcRegnNo) {
		this.rcRegnNo = rcRegnNo;
	}
	@Column(name="RCOWNERNAME")
	public String getRcOwnerName() {
		return rcOwnerName;
	}
	public void setRcOwnerName(String rcOwnerName) {
		this.rcOwnerName = rcOwnerName;
	}
	@Column(name="RCENGNO")
	public String getRcEngNo() {
		return rcEngNo;
	}
	public void setRcEngNo(String rcEngNo) {
		this.rcEngNo = rcEngNo;
	}
	@Column(name="RCCHASINO")
	public String getRcChasiNo() {
		return rcChasiNo;
	}
	public void setRcChasiNo(String rcChasiNo) {
		this.rcChasiNo = rcChasiNo;
	}
	@Column(name="RCMAKERDESC")
	public String getRcMakerDesc() {
		return rcMakerDesc;
	}
	public void setRcMakerDesc(String rcMakerDesc) {
		this.rcMakerDesc = rcMakerDesc;
	}
	@Column(name="RCMAKERMODEL")
	public String getRcMakerModel() {
		return rcMakerModel;
	}
	public void setRcMakerModel(String rcMakerModel) {
		this.rcMakerModel = rcMakerModel;
	}
	@Column(name="RCREGNDT")
	public String getRcRegnDt() {
		return rcRegnDt;
	}
	public void setRcRegnDt(String rcRegnDt) {
		this.rcRegnDt = rcRegnDt;
	}
	@Column(name="RCVHCLASSDESC")
	public String getRcVhClassDesc() {
		return rcVhClassDesc;
	}
	public void setRcVhClassDesc(String rcVhClassDesc) {
		this.rcVhClassDesc = rcVhClassDesc;
	}
	@Column(name="RCCOLOR")
	public String getRcColor() {
		return rcColor;
	}
	public void setRcColor(String rcColor) {
		this.rcColor = rcColor;
	}
	@Column(name="RCMANUMONTHYR")
	public String getRcManuMonthYr() {
		return rcManuMonthYr;
	}
	public void setRcManuMonthYr(String rcManuMonthYr) {
		this.rcManuMonthYr = rcManuMonthYr;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "RCSearchResult [rcRegnNo=" + rcRegnNo + ", rcOwnerName=" + rcOwnerName + ", rcEngNo=" + rcEngNo
				+ ", rcChasiNo=" + rcChasiNo + ", rcMakerDesc=" + rcMakerDesc + ", rcMakerModel=" + rcMakerModel
				+ ", rcRegnDt=" + rcRegnDt + ", rcVhClassDesc=" + rcVhClassDesc + ", rcColor=" + rcColor
				+ ", rcManuMonthYr=" + rcManuMonthYr + "]";
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
	@Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
}
