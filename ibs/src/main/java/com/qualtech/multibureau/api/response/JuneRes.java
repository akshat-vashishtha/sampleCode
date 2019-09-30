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
@Table(name = "IB_BUREAU_JUN_RES")
public class JuneRes implements Serializable {


	private static final long serialVersionUID = 612526037397077639L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_JUN_SQC", allocationSize = 1)
	@JsonIgnore
	private int junId;

	private String assetclss;
	private String dpd;
	
	@OneToOne
	@JoinColumn(name = "yrId", nullable = false)
	@JsonIgnore
	private YearRes yearRes;

	public int getJunId() {
		return junId;
	}

	public void setJunId(int junId) {
		this.junId = junId;
	}

	public String getAssetclss() {
		return assetclss;
	}

	public void setAssetclss(String assetclss) {
		this.assetclss = assetclss;
	}

	public String getDpd() {
		return dpd;
	}

	public void setDpd(String dpd) {
		this.dpd = dpd;
	}

	public YearRes getYearRes() {
		return yearRes;
	}

	public void setYearRes(YearRes yearRes) {
		this.yearRes = yearRes;
	}

	@Override
	public String toString() {
		return "JuneRes [junId=" + junId + ", assetclss=" + assetclss + ", dpd=" + dpd + "]";
	}

}
