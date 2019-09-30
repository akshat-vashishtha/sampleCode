package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_DL_KYC_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DlResult2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private DlDrivingLicense2 dl;
//	private KarzaName name;
	
	@Id
	@JsonIgnore
	private String    uniqueid;
	@Column
	@JsonIgnore
	private String    corelationid;
	@Column
	@JsonIgnore
	private String    byte_array;
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
	@OneToMany(fetch=FetchType.LAZY, mappedBy="dlResult2", cascade=CascadeType.ALL)
	private DlCoverDetails2[] cov_details;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="dlResult2val", cascade=CascadeType.ALL)
	private DlValidity2 validity;
	@Column
	private String img;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public DlCoverDetails2[] getCov_details() {
		for (DlCoverDetails2 dlCoverDetails2 : cov_details) {
			dlCoverDetails2.setDlResult2(this);
		}
		/*for (DlCoverDetails2 coverDetails : cov_details) {
			coverDetails.setDlDrivingLicensecov(this);
		}*/
		return cov_details;
	}
	public void setCov_details(DlCoverDetails2[] cov_details) {
		this.cov_details = cov_details;
	}
	public DlValidity2 getValidity() {
		validity.setDlResult2val(this);
		return validity;
	}
	public void setValidity(DlValidity2 validity) {
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
	
	
	
/*	
	
	public DlDrivingLicense2 getDl() {
		return dl;
	}
	public void setDl(DlDrivingLicense2 dl) {
		this.dl = dl;
	}
	public KarzaName getName() {
		return name;
	}
	@Override
	public String toString() {
		return "DlResult2 [dl=" + dl + ", name=" + name + "]";
	}
	public void setName(KarzaName name) {
		this.name = name;
	}
	*/
}
