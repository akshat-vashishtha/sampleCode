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
@Table(name = "IB_BUREAU_CURRENT_ADD_RES")
public class CurrentAppAddDetailsRes implements Serializable {

	private static final long serialVersionUID = -487772733524459318L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CURRENT_ADD_SQC", allocationSize = 1)
	@JsonIgnore
	private int currAddressId;

	private String pincode;
	private String landmark;
	private String state;
	private String countrycode;
	private String roadnumbernamearealocality;
	private String bldgnumbersocietyname;
	private String flatnoplotnohouseno;
	private String city;
	
	
	@OneToOne
	@JoinColumn(name = "currAppDetailsId", nullable = false)
	@JsonIgnore
	private CurrentApplicationDetailsRes currAddressRes;


	public int getCurrAddressId() {
		return currAddressId;
	}


	public void setCurrAddressId(int currAddressId) {
		this.currAddressId = currAddressId;
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


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountrycode() {
		return countrycode;
	}


	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}


	public String getRoadnumbernamearealocality() {
		return roadnumbernamearealocality;
	}


	public void setRoadnumbernamearealocality(String roadnumbernamearealocality) {
		this.roadnumbernamearealocality = roadnumbernamearealocality;
	}


	public String getBldgnumbersocietyname() {
		return bldgnumbersocietyname;
	}


	public void setBldgnumbersocietyname(String bldgnumbersocietyname) {
		this.bldgnumbersocietyname = bldgnumbersocietyname;
	}


	public String getFlatnoplotnohouseno() {
		return flatnoplotnohouseno;
	}


	public void setFlatnoplotnohouseno(String flatnoplotnohouseno) {
		this.flatnoplotnohouseno = flatnoplotnohouseno;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public CurrentApplicationDetailsRes getCurrAddressRes() {
		return currAddressRes;
	}


	public void setCurrAddressRes(CurrentApplicationDetailsRes currAddressRes) {
		this.currAddressRes = currAddressRes;
	}


	@Override
	public String toString() {
		return "CurrentAppAddDetailsRes [currAddressId=" + currAddressId + ", pincode=" + pincode + ", landmark="
				+ landmark + ", state=" + state + ", countrycode=" + countrycode + ", roadnumbernamearealocality="
				+ roadnumbernamearealocality + ", bldgnumbersocietyname=" + bldgnumbersocietyname
				+ ", flatnoplotnohouseno=" + flatnoplotnohouseno + ", city=" + city + "]";
	}


}
