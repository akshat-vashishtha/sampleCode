package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_ADD_JSON_RES")
public class AddressJsonRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ADD_JSON_RES_SQC", allocationSize = 1)
	@JsonIgnore
	private int addId;

	private String enrichedthroughtenquiry;
	
	private String pincode;
	
	private String datereported;
	
	private String statecode;
	
	private String addresscategory;
	
	private String residencecode;
	
	private String addressline2;
	
	private String addressline1;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;


	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	public int getAddId() {
		return addId;
	}

	public void setAddId(int addId) {
		this.addId = addId;
	}

	public String getEnrichedthroughtenquiry() {
		return enrichedthroughtenquiry;
	}

	public void setEnrichedthroughtenquiry(String enrichedthroughtenquiry) {
		this.enrichedthroughtenquiry = enrichedthroughtenquiry;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getDatereported() {
		return datereported;
	}

	public void setDatereported(String datereported) {
		this.datereported = datereported;
	}

	public String getStatecode() {
		return statecode;
	}

	public void setStatecode(String statecode) {
		this.statecode = statecode;
	}

	public String getAddresscategory() {
		return addresscategory;
	}

	public void setAddresscategory(String addresscategory) {
		this.addresscategory = addresscategory;
	}

	public String getResidencecode() {
		return residencecode;
	}

	public void setResidencecode(String residencecode) {
		this.residencecode = residencecode;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	@Override
	public String toString() {
		return "AddressJsonRes [addId=" + addId + ", enrichedthroughtenquiry=" + enrichedthroughtenquiry + ", pincode="
				+ pincode + ", datereported=" + datereported + ", statecode=" + statecode + ", addresscategory="
				+ addresscategory + ", residencecode=" + residencecode + ", addressline2=" + addressline2
				+ ", addressline1=" + addressline1 + "]";
	}
}
