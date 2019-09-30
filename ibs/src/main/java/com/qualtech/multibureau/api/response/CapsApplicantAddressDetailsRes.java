package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_CAPSAPPLT_ADD_RES")
public class CapsApplicantAddressDetailsRes implements Serializable {


	private static final long serialVersionUID = 612526037397077639L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAPSAPPT_ADD_SQC", allocationSize = 1)
	@JsonIgnore
	private int capsApplicantAddId;

	private String pincode;
	private String landmark;
	private String roadnonamearealocality;
	private String countrycode;
	private String state;
	private String bldgnosocietyname;
	private String city;
	private String flatnoplotnohouseno;

	@OneToOne
	@JoinColumn(name = "capsDetailId", nullable = false)
	@JsonIgnore
	private CapsApplicationDetailListRes capsApplicationDetailListRes;

	public int getCapsApplicantAddId() {
		return capsApplicantAddId;
	}

	public void setCapsApplicantAddId(int capsApplicantAddId) {
		this.capsApplicantAddId = capsApplicantAddId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getRoadnonamearealocality() {
		return roadnonamearealocality;
	}

	public void setRoadnonamearealocality(String roadnonamearealocality) {
		this.roadnonamearealocality = roadnonamearealocality;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBldgnosocietyname() {
		return bldgnosocietyname;
	}

	public void setBldgnosocietyname(String bldgnosocietyname) {
		this.bldgnosocietyname = bldgnosocietyname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFlatnoplotnohouseno() {
		return flatnoplotnohouseno;
	}

	public void setFlatnoplotnohouseno(String flatnoplotnohouseno) {
		this.flatnoplotnohouseno = flatnoplotnohouseno;
	}

	public CapsApplicationDetailListRes getCapsApplicationDetailListRes() {
		return capsApplicationDetailListRes;
	}

	public void setCapsApplicationDetailListRes(CapsApplicationDetailListRes capsApplicationDetailListRes) {
		this.capsApplicationDetailListRes = capsApplicationDetailListRes;
	}

	@Override
	public String toString() {
		return "CapsApplicantAddressDetailsRes [capsApplicantAddId=" + capsApplicantAddId + ", pincode=" + pincode
				+ ", landmark=" + landmark + ", roadnonamearealocality=" + roadnonamearealocality + ", countrycode="
				+ countrycode + ", state=" + state + ", bldgnosocietyname=" + bldgnosocietyname + ", city=" + city
				+ ", flatnoplotnohouseno=" + flatnoplotnohouseno + "]";
	}

	
}
