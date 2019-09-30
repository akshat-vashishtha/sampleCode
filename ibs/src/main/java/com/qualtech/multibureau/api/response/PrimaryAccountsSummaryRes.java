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
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_PRI_ACC_RES")
public class PrimaryAccountsSummaryRes implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 4949664213358789665L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_PRI_ACC_SQC", allocationSize = 1)
	@JsonIgnore
	private int primaryAccId;

	@JsonProperty("primaryunsecurednumberofaccounts")
	private String primaryunsecurednoofacc;
	
	@JsonProperty("primaryoverduenumberofaccounts")
	private String primaryoverduenoofacc;
	
	private String primarycurrentbalance;
	private String primarydisbursedamount;
	private String primarysanctionedamount;
	
	@JsonProperty("primaryactivenumberofaccounts")
	private String primaryactivenoofacc;
	
	@JsonProperty("primaryuntaggednumberofaccounts")
	private String primaryuntaggednoofacc;
	private String primarynumberofaccounts;
	
	@JsonProperty("primarysecurednumberofaccounts")
	private String primarysecurednoofacc;

	@OneToOne
	@JoinColumn(name = "accId", nullable = false)
	@JsonIgnore
	private AccountSummaryRes accSummaryRes;

	public int getPrimaryAccId() {
		return primaryAccId;
	}

	public void setPrimaryAccId(int primaryAccId) {
		this.primaryAccId = primaryAccId;
	}

	public String getPrimaryunsecurednoofacc() {
		return primaryunsecurednoofacc;
	}

	public void setPrimaryunsecurednoofacc(String primaryunsecurednoofacc) {
		this.primaryunsecurednoofacc = primaryunsecurednoofacc;
	}

	public String getPrimaryoverduenoofacc() {
		return primaryoverduenoofacc;
	}

	public void setPrimaryoverduenoofacc(String primaryoverduenoofacc) {
		this.primaryoverduenoofacc = primaryoverduenoofacc;
	}

	public String getPrimarycurrentbalance() {
		return primarycurrentbalance;
	}

	public void setPrimarycurrentbalance(String primarycurrentbalance) {
		this.primarycurrentbalance = primarycurrentbalance;
	}

	public String getPrimarydisbursedamount() {
		return primarydisbursedamount;
	}

	public void setPrimarydisbursedamount(String primarydisbursedamount) {
		this.primarydisbursedamount = primarydisbursedamount;
	}

	public String getPrimarysanctionedamount() {
		return primarysanctionedamount;
	}

	public void setPrimarysanctionedamount(String primarysanctionedamount) {
		this.primarysanctionedamount = primarysanctionedamount;
	}

	public String getPrimaryactivenoofacc() {
		return primaryactivenoofacc;
	}

	public void setPrimaryactivenoofacc(String primaryactivenoofacc) {
		this.primaryactivenoofacc = primaryactivenoofacc;
	}

	public String getPrimaryuntaggednoofacc() {
		return primaryuntaggednoofacc;
	}

	public void setPrimaryuntaggednoofacc(String primaryuntaggednoofacc) {
		this.primaryuntaggednoofacc = primaryuntaggednoofacc;
	}

	public String getPrimarynumberofaccounts() {
		return primarynumberofaccounts;
	}

	public void setPrimarynumberofaccounts(String primarynumberofaccounts) {
		this.primarynumberofaccounts = primarynumberofaccounts;
	}

	public String getPrimarysecurednoofacc() {
		return primarysecurednoofacc;
	}

	public void setPrimarysecurednoofacc(String primarysecurednoofacc) {
		this.primarysecurednoofacc = primarysecurednoofacc;
	}

	public AccountSummaryRes getAccSummaryRes() {
		return accSummaryRes;
	}

	public void setAccSummaryRes(AccountSummaryRes accSummaryRes) {
		this.accSummaryRes = accSummaryRes;
	}

	@Override
	public String toString() {
		return "PrimaryAccountsSummaryRes [primaryAccId=" + primaryAccId + ", primaryunsecurednoofacc="
				+ primaryunsecurednoofacc + ", primaryoverduenoofacc=" + primaryoverduenoofacc
				+ ", primarycurrentbalance=" + primarycurrentbalance + ", primarydisbursedamount="
				+ primarydisbursedamount + ", primarysanctionedamount=" + primarysanctionedamount
				+ ", primaryactivenoofacc=" + primaryactivenoofacc + ", primaryuntaggednoofacc="
				+ primaryuntaggednoofacc + ", primarynumberofaccounts=" + primarynumberofaccounts
				+ ", primarysecurednoofacc=" + primarysecurednoofacc + "]";
	}

}
