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
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_CAIS_HLDR_ADD_RES")
public class CaisHolderAddressDetailsListRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAIS_HLDR_ADD_SQC", allocationSize = 1)
	@JsonIgnore
	private int caisHolderAddressId;

	@JsonProperty("fifthlineofaddressnonnormalized")
	private String fifthLnOfAddNonNormalized;
	
	@JsonProperty("addressindicatornonnormalized")
	private String addindicatornonnormalized;
	
	@JsonProperty("firstlineofaddressnonnormalized")
	private String firstLnOfAddNonNormalized;
	private String zippostalcodenonnormalized;
	
	@JsonProperty("thirdlineofaddressnonnormalized")
	private String thirdLnOfAddNonNormalized;
	
	private String residencecodenonnormalized;
	private String countrycodenonnormalized;
	private String citynonnormalized;
	private String statenonnormalized;
	
	@JsonProperty("secondlineofaddressnonnormalized")
	private String secondLnOfAddNonNormalized;

	@ManyToOne
	@JoinColumn(name = "caisAccDetailsId", nullable = false)
	@JsonIgnore
	private CaisAccountDetailsRes caisAccountDetailsRes;

	public int getCaisHolderAddressId() {
		return caisHolderAddressId;
	}

	public void setCaisHolderAddressId(int caisHolderAddressId) {
		this.caisHolderAddressId = caisHolderAddressId;
	}

	public String getFifthLnOfAddNonNormalized() {
		return fifthLnOfAddNonNormalized;
	}

	public void setFifthLnOfAddNonNormalized(String fifthLnOfAddNonNormalized) {
		this.fifthLnOfAddNonNormalized = fifthLnOfAddNonNormalized;
	}

	public String getAddindicatornonnormalized() {
		return addindicatornonnormalized;
	}

	public void setAddindicatornonnormalized(String addindicatornonnormalized) {
		this.addindicatornonnormalized = addindicatornonnormalized;
	}

	public String getFirstLnOfAddNonNormalized() {
		return firstLnOfAddNonNormalized;
	}

	public void setFirstLnOfAddNonNormalized(String firstLnOfAddNonNormalized) {
		this.firstLnOfAddNonNormalized = firstLnOfAddNonNormalized;
	}

	public String getZippostalcodenonnormalized() {
		return zippostalcodenonnormalized;
	}

	public void setZippostalcodenonnormalized(String zippostalcodenonnormalized) {
		this.zippostalcodenonnormalized = zippostalcodenonnormalized;
	}

	public String getThirdLnOfAddNonNormalized() {
		return thirdLnOfAddNonNormalized;
	}

	public void setThirdLnOfAddNonNormalized(String thirdLnOfAddNonNormalized) {
		this.thirdLnOfAddNonNormalized = thirdLnOfAddNonNormalized;
	}

	public String getResidencecodenonnormalized() {
		return residencecodenonnormalized;
	}

	public void setResidencecodenonnormalized(String residencecodenonnormalized) {
		this.residencecodenonnormalized = residencecodenonnormalized;
	}

	public String getCountrycodenonnormalized() {
		return countrycodenonnormalized;
	}

	public void setCountrycodenonnormalized(String countrycodenonnormalized) {
		this.countrycodenonnormalized = countrycodenonnormalized;
	}

	public String getCitynonnormalized() {
		return citynonnormalized;
	}

	public void setCitynonnormalized(String citynonnormalized) {
		this.citynonnormalized = citynonnormalized;
	}

	public String getStatenonnormalized() {
		return statenonnormalized;
	}

	public void setStatenonnormalized(String statenonnormalized) {
		this.statenonnormalized = statenonnormalized;
	}

	public String getSecondLnOfAddNonNormalized() {
		return secondLnOfAddNonNormalized;
	}

	public void setSecondLnOfAddNonNormalized(String secondLnOfAddNonNormalized) {
		this.secondLnOfAddNonNormalized = secondLnOfAddNonNormalized;
	}

	public CaisAccountDetailsRes getCaisAccountDetailsRes() {
		return caisAccountDetailsRes;
	}

	public void setCaisAccountDetailsRes(CaisAccountDetailsRes caisAccountDetailsRes) {
		this.caisAccountDetailsRes = caisAccountDetailsRes;
	}

	@Override
	public String toString() {
		return "CaisHolderAddressDetailsListRes [caisHolderAddressId=" + caisHolderAddressId
				+ ", fifthLnOfAddNonNormalized=" + fifthLnOfAddNonNormalized + ", addindicatornonnormalized="
				+ addindicatornonnormalized + ", firstLnOfAddNonNormalized=" + firstLnOfAddNonNormalized
				+ ", zippostalcodenonnormalized=" + zippostalcodenonnormalized + ", thirdLnOfAddNonNormalized="
				+ thirdLnOfAddNonNormalized + ", residencecodenonnormalized=" + residencecodenonnormalized
				+ ", countrycodenonnormalized=" + countrycodenonnormalized + ", citynonnormalized=" + citynonnormalized
				+ ", statenonnormalized=" + statenonnormalized + ", secondLnOfAddNonNormalized="
				+ secondLnOfAddNonNormalized + "]";
	}

}
