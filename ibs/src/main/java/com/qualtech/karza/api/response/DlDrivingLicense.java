package com.qualtech.karza.api.response;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_DL_KYC_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DlDrivingLicense implements Serializable{
	
			
		
	private static final long serialVersionUID = 2068727884127514908L;
	@Id
	@JsonIgnore
	private String uniqueid;
	@Column
	@JsonIgnore
	private String corelationid;
	@Column
	private String name;
	@Column
	private String dob;
	@Column
	private String issue_date;
	@Column
	private String blood_group;
	@Column(name="RELATION")
	private String father_husband;
	@Column
	private String address;
	
	@OrderColumn(name="uniqueid")
	@OneToMany(fetch=FetchType.LAZY, mappedBy="dlDrivingLicensecov", cascade=CascadeType.ALL)
	private CoverDetails[] cov_details;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="dlDrivingLicense", cascade=CascadeType.ALL)
	private DlValidity validity;
	@Column
	@Lob
	private String img;

	@Lob
	@Column
	@JsonIgnore
	private String    byte_array;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "DlDrivingLicense [name=" + name + ", dob=" + dob + ", issue_date=" + issue_date + ", blood_group="
				+ blood_group + ", father_husband=" + father_husband + ", address=" + address + ", cov_details="
				+ Arrays.toString(cov_details) + ", validity=" + validity + ", img=" + img + "]";
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}
	public String getBlood_group() {
		return blood_group;
	}
	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}
	public String getFather_husband() {
		return father_husband;
	}
	public void setFather_husband(String father_husband) {
		this.father_husband = father_husband;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CoverDetails[] getCov_details() {
		for (CoverDetails coverDetails : cov_details) {
			coverDetails.setDlDrivingLicensecov(this);
		}
		return cov_details;
	}
	public void setCov_details(CoverDetails[] cov_details) {
		this.cov_details = cov_details;
	}
	public DlValidity getValidity() {
		validity.setDlDrivingLicense(this);
		return validity;
	}
	public void setValidity(DlValidity validity) {
		this.validity = validity;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public String getByte_array() {
		return byte_array;
	}
	public void setByte_array(String byte_array) {
		this.byte_array = byte_array;
	}
	
	
}
