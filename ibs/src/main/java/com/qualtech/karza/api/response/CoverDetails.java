package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name="IB_K_DL_KYC_COV_DTL")
public class CoverDetails implements Serializable{
	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator="KARZA_DL_COV_SQC")
	@SequenceGenerator(name="KARZA_DL_COV_SQC",sequenceName="IB_K_DL_COV_SQC",allocationSize=1)
    @JsonIgnore
	private int kid;
    
	@Column(name="LMV")
	private String cov;
	
	private String issue_date;
	
	@ManyToOne
	@JoinColumn(name="uniqueId", nullable=false)
	@JsonIgnore
	private DlDrivingLicense dlDrivingLicensecov;
	
	public String getCov() {
		return cov;
	}
	@Override
	public String toString() {
		return "CoverDetails [cov=" + cov + ", issue_date=" + issue_date + "]";
	}
	public void setCov(String cov) {
		this.cov = cov;
	}
	public String getIssue_date() {
		return issue_date;
	}
	public void setIssue_date(String issue_date) {
		this.issue_date = issue_date;
	}
	
	public int getKid() {
		return kid;
	}
	public void setKid(int kid) {
		this.kid = kid;
	}

	public DlDrivingLicense getDlDrivingLicensecov() {
		return dlDrivingLicensecov;
	}
	public void setDlDrivingLicensecov(DlDrivingLicense dlDrivingLicensecov) {
		this.dlDrivingLicensecov = dlDrivingLicensecov;
	}
	
	
}
