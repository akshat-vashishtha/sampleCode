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
@Table(name = "IB_BUREAU_ACCOUNT_SUMMARY")
public class AccountSummaryRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ACCOUNT_SQC", allocationSize = 1)
	@JsonIgnore
	private int accId;

	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "accSummaryRes" ,cascade=CascadeType.ALL)
	private DrivedAttributesRes drivedattributes;
	
//	@Transient
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "accSummaryRes" ,cascade=CascadeType.ALL)
	private SecondaryAccountSummaryRes secondaryaccountsummary;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "accSummaryRes" ,cascade=CascadeType.ALL)
	private PrimaryAccountsSummaryRes primaryaccountssummary;
	
	
	@OneToOne
	@JoinColumn(name = "indvId", nullable = false)
	@JsonIgnore
	private INDVReportRes indvReportRes;


	public int getAccId() {
		return accId;
	}


	public void setAccId(int accId) {
		this.accId = accId;
	}


	public DrivedAttributesRes getDrivedattributes() {
		
		if(drivedattributes!=null)
		{
			drivedattributes.setAccSummaryRes(this);
		}
		return drivedattributes;
	}


	public void setDrivedattributes(DrivedAttributesRes drivedattributes) {
		this.drivedattributes = drivedattributes;
	}


	public SecondaryAccountSummaryRes getSecondaryaccountsummary() {
		if(secondaryaccountsummary!=null)
		{
			secondaryaccountsummary.setAccSummaryRes(this);
		}
		return secondaryaccountsummary;
	}


	public void setSecondaryaccountsummary(SecondaryAccountSummaryRes secondaryaccountsummary) {
		this.secondaryaccountsummary = secondaryaccountsummary;
	}


	public PrimaryAccountsSummaryRes getPrimaryaccountssummary() {
		if(primaryaccountssummary!=null)
		{
			primaryaccountssummary.setAccSummaryRes(this);
		}
		return primaryaccountssummary;
	}


	public void setPrimaryaccountssummary(PrimaryAccountsSummaryRes primaryaccountssummary) {
		this.primaryaccountssummary = primaryaccountssummary;
	}


	public INDVReportRes getIndvReportRes() {
		return indvReportRes;
	}


	public void setIndvReportRes(INDVReportRes indvReportRes) {
		this.indvReportRes = indvReportRes;
	}


	@Override
	public String toString() {
		return "AccountSummaryRes [accId=" + accId + ", drivedattributes=" + drivedattributes
				+ ", secondaryaccountsummary=" + secondaryaccountsummary + ", primaryaccountssummary="
				+ primaryaccountssummary + "]";
	}

}
