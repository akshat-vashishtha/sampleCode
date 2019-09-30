package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
@Table(name="IB_K_ICWAIFIRM_KYC_RES")
public class ICWAIFirmAuthMemberDetails implements Serializable {

	private static final long serialVersionUID = -7769440200320710200L;
	private Long sequenceid;
	private String city;
	private String memberNo;
	private String pin;
	private String memberName;
	private String state;
	private String address;
	private ICWAIFirmAuthResult icwaiFirmAuthResult;
	

	
	@ManyToOne
	@JoinColumn(name="UNIQUE_ID",nullable=false)
	@JsonIgnore
	public ICWAIFirmAuthResult getIcwaiFirmAuthResult() {
		return icwaiFirmAuthResult;
	}
	public void setIcwaiFirmAuthResult(ICWAIFirmAuthResult icwaiFirmAuthResult) {
		this.icwaiFirmAuthResult = icwaiFirmAuthResult;
	}
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="MEMBER_NO")
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	@Column(name="PIN")
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Column(name="MEMBER_NAME")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ICWAIFirmAuthMemberDetails [city=" + city + ", memberNo=" + memberNo + ", pin=" + pin + ", memberName="
				+ memberName + ", state=" + state + ", address=" + address + "]";
	}


	@JsonIgnore
	@Id
	@Column(name="SEQUENCE_ID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_ICWAIFirm_KYC_SQC",allocationSize=1)
	public Long getSequenceid() {
		return sequenceid;
	}
	public void setSequenceid(Long sequenceid) {
		this.sequenceid = sequenceid;
	}
	
	
	
	
	
	
}
