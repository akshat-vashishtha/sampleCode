package com.qualtech.cibil.api.entity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.cibil.api.response.CibilResponsePayload;
@Entity
@Table(name="IB_CBL_ADDRESSDETAILS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AddressDetails implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3455625942334963999L;
	private Long address_id;
	private String addressline1="";
	private String addressline2="";
	private String addressline3="";
	private String addressline4="";
	private String addressline5="";
	private String stateCode="";
	private String pinCode="";
	private String addressCategory="";
	private String resedenceCode="";
	private String dateReported="";
	private String memberName="";
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
    @Column(name="ADDRESSLINE1")
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	@Column(name="ADDRESSLINE2")
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	@Column(name="ADDRESSLINE3")
	public String getAddressline3() {
		return addressline3;
	}
	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}
	@Column(name="ADDRESSLINE4")
	public String getAddressline4() {
		return addressline4;
	}
	public void setAddressline4(String addressline4) {
		this.addressline4 = addressline4;
	}
	@Column(name="ADDRESSLINE5")
	public String getAddressline5() {
		return addressline5;
	}
	public void setAddressline5(String addressline5) {
		this.addressline5 = addressline5;
	}
	@Column(name="STATECODE")
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	@Column(name="PINCODE")
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	@Column(name="ADDRESSCATEGORY")
	public String getAddressCategory() {
		return addressCategory;
	}
	public void setAddressCategory(String addressCategory) {
		this.addressCategory = addressCategory;
	}
	@Column(name="RESEDENCECODE")
	public String getResedenceCode() {
		return resedenceCode;
	}
	public void setResedenceCode(String resedenceCode) {
		this.resedenceCode = resedenceCode;
	}
	@Column(name="DATEREPORTED")
	public String getDateReported() {
		return dateReported;
	}
	public void setDateReported(String dateReported) {
		this.dateReported = dateReported;
	}
	@Column(name="MEMBERNAME")
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_ADDR_SQC")
	@SequenceGenerator(name = "IB_CBL_ADDR_SQC", sequenceName = "IB_CBL_ADDR_SQC")
	public Long getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}
	@Override
	public String toString() {
		return "AddressDetails [address_id=" + address_id + ", addressline1=" + addressline1 + ", addressline2="
				+ addressline2 + ", addressline3=" + addressline3 + ", addressline4=" + addressline4 + ", addressline5="
				+ addressline5 + ", stateCode=" + stateCode + ", pinCode=" + pinCode + ", addressCategory="
				+ addressCategory + ", resedenceCode=" + resedenceCode + ", dateReported=" + dateReported
				+ ", memberName=" + memberName + "]";
	}
	@ManyToOne
	@JoinColumn(name="CIBIL_UNIQUE_ID")
	public CibilResponsePayload getCibilresponsepayload() {
		return cibilresponsepayload;
	}
	public void setCibilresponsepayload(CibilResponsePayload cibilresponsepayload) {
		this.cibilresponsepayload = cibilresponsepayload;
	}
}
