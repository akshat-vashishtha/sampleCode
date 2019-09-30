package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "IB_BUREAU_NONCR_SUMM_RES")
public class NonCreditCAPSSummaryRes implements Serializable {

	private static final long serialVersionUID = -253021057991052198L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_NONCR_SUMM_SQC", allocationSize = 1)
	@JsonIgnore
	private int nonCreditCAPSSUMMARYId;

	private String noncreditcapslast30days;
	private String noncreditcapslast180days;
	private String noncreditcapslast7days;
	private String noncreditcapslast90days;
	
	@OneToOne
	@JoinColumn(name = "nonCreditCAPSId", nullable = false)
	@JsonIgnore
	private NonCreditCAPSRes nonCreditCAPSRes;

	public int getNonCreditCAPSSUMMARYId() {
		return nonCreditCAPSSUMMARYId;
	}

	public void setNonCreditCAPSSUMMARYId(int nonCreditCAPSSUMMARYId) {
		this.nonCreditCAPSSUMMARYId = nonCreditCAPSSUMMARYId;
	}

	public String getNoncreditcapslast30days() {
		return noncreditcapslast30days;
	}

	public void setNoncreditcapslast30days(String noncreditcapslast30days) {
		this.noncreditcapslast30days = noncreditcapslast30days;
	}

	public String getNoncreditcapslast180days() {
		return noncreditcapslast180days;
	}

	public void setNoncreditcapslast180days(String noncreditcapslast180days) {
		this.noncreditcapslast180days = noncreditcapslast180days;
	}

	public String getNoncreditcapslast7days() {
		return noncreditcapslast7days;
	}

	public void setNoncreditcapslast7days(String noncreditcapslast7days) {
		this.noncreditcapslast7days = noncreditcapslast7days;
	}

	public String getNoncreditcapslast90days() {
		return noncreditcapslast90days;
	}

	public void setNoncreditcapslast90days(String noncreditcapslast90days) {
		this.noncreditcapslast90days = noncreditcapslast90days;
	}

	public NonCreditCAPSRes getNonCreditCAPSRes() {
		return nonCreditCAPSRes;
	}

	public void setNonCreditCAPSRes(NonCreditCAPSRes nonCreditCAPSRes) {
		this.nonCreditCAPSRes = nonCreditCAPSRes;
	}

	@Override
	public String toString() {
		return "NonCreditCAPSSummaryRes [nonCreditCAPSSUMMARYId=" + nonCreditCAPSSUMMARYId
				+ ", noncreditcapslast30days=" + noncreditcapslast30days + ", noncreditcapslast180days="
				+ noncreditcapslast180days + ", noncreditcapslast7days=" + noncreditcapslast7days
				+ ", noncreditcapslast90days=" + noncreditcapslast90days + "]";
	}


}