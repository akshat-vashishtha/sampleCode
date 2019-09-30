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
@Table(name = "IB_BUREAU_CAIS_ACC_HSTRY_RES")
public class CaisAccountHistoryListRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAIS_ACC_HSTRY_SQC", allocationSize = 1)
	@JsonIgnore
	private int caisAccountHistoryId;

	private String dayspastdue;
	private String month;
	private String year;
	private String assetclassification;

	@ManyToOne
	@JoinColumn(name = "caisAccDetailsId", nullable = false)
	@JsonIgnore
	private CaisAccountDetailsRes caisAccountDetailsRes;

	public int getCaisAccountHistoryId() {
		return caisAccountHistoryId;
	}

	public void setCaisAccountHistoryId(int caisAccountHistoryId) {
		this.caisAccountHistoryId = caisAccountHistoryId;
	}

	public String getDayspastdue() {
		return dayspastdue;
	}

	public void setDayspastdue(String dayspastdue) {
		this.dayspastdue = dayspastdue;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAssetclassification() {
		return assetclassification;
	}

	public void setAssetclassification(String assetclassification) {
		this.assetclassification = assetclassification;
	}

	public CaisAccountDetailsRes getCaisAccountDetailsRes() {
		return caisAccountDetailsRes;
	}

	public void setCaisAccountDetailsRes(CaisAccountDetailsRes caisAccountDetailsRes) {
		this.caisAccountDetailsRes = caisAccountDetailsRes;
	}

	@Override
	public String toString() {
		return "CaisAccountHistoryListRes [caisAccountHistoryId=" + caisAccountHistoryId + ", dayspastdue="
				+ dayspastdue + ", month=" + month + ", year=" + year + ", assetclassification=" + assetclassification
				+ "]";
	}

}
