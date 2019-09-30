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
@Table(name = "IB_BUREAU_SECND_ACC_RES")
public class SecondaryAccountSummaryRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_SECND_ACC_SQC", allocationSize = 1)
	@JsonIgnore
	private int secondAccId;

	@JsonProperty("secondaryuntaggednumberofaccounts")
	private String secondaryuntaggednoofacc;
	private String secondarynumberofaccounts;
	private String secondarysanctionedamount;
	
	@JsonProperty("secondarysecurednumberofaccounts")
	private String secondarysecurednoofacc;
	
	@JsonProperty("secondaryactivenumberofaccounts")
	private String secondaryactivenoofacc;
	
	@JsonProperty("secondaryunsecurednumberofaccounts")
	private String secondaryunsecurednoofacc;
	private String secondarycurrentbalance;
	
	@JsonProperty("secondaryoverduenumberofaccounts")
	private String secondaryoverduenoofacc;
	
	private String secondarydisbursedamount;

	@OneToOne
	@JoinColumn(name = "accId", nullable = false)
	@JsonIgnore
	private AccountSummaryRes accSummaryRes;

	public int getSecondAccId() {
		return secondAccId;
	}

	public void setSecondAccId(int secondAccId) {
		this.secondAccId = secondAccId;
	}

	public String getSecondaryuntaggednoofacc() {
		return secondaryuntaggednoofacc;
	}

	public void setSecondaryuntaggednoofacc(String secondaryuntaggednoofacc) {
		this.secondaryuntaggednoofacc = secondaryuntaggednoofacc;
	}

	public String getSecondarynumberofaccounts() {
		return secondarynumberofaccounts;
	}

	public void setSecondarynumberofaccounts(String secondarynumberofaccounts) {
		this.secondarynumberofaccounts = secondarynumberofaccounts;
	}

	public String getSecondarysanctionedamount() {
		return secondarysanctionedamount;
	}

	public void setSecondarysanctionedamount(String secondarysanctionedamount) {
		this.secondarysanctionedamount = secondarysanctionedamount;
	}

	public String getSecondarysecurednoofacc() {
		return secondarysecurednoofacc;
	}

	public void setSecondarysecurednoofacc(String secondarysecurednoofacc) {
		this.secondarysecurednoofacc = secondarysecurednoofacc;
	}

	public String getSecondaryactivenoofacc() {
		return secondaryactivenoofacc;
	}

	public void setSecondaryactivenoofacc(String secondaryactivenoofacc) {
		this.secondaryactivenoofacc = secondaryactivenoofacc;
	}

	public String getSecondaryunsecurednoofacc() {
		return secondaryunsecurednoofacc;
	}

	public void setSecondaryunsecurednoofacc(String secondaryunsecurednoofacc) {
		this.secondaryunsecurednoofacc = secondaryunsecurednoofacc;
	}

	public String getSecondarycurrentbalance() {
		return secondarycurrentbalance;
	}

	public void setSecondarycurrentbalance(String secondarycurrentbalance) {
		this.secondarycurrentbalance = secondarycurrentbalance;
	}

	public String getSecondaryoverduenoofacc() {
		return secondaryoverduenoofacc;
	}

	public void setSecondaryoverduenoofacc(String secondaryoverduenoofacc) {
		this.secondaryoverduenoofacc = secondaryoverduenoofacc;
	}

	public String getSecondarydisbursedamount() {
		return secondarydisbursedamount;
	}

	public void setSecondarydisbursedamount(String secondarydisbursedamount) {
		this.secondarydisbursedamount = secondarydisbursedamount;
	}

	public AccountSummaryRes getAccSummaryRes() {
		return accSummaryRes;
	}

	public void setAccSummaryRes(AccountSummaryRes accSummaryRes) {
		this.accSummaryRes = accSummaryRes;
	}

	@Override
	public String toString() {
		return "SecondaryAccountSummaryRes [secondAccId=" + secondAccId + ", secondaryuntaggednoofacc="
				+ secondaryuntaggednoofacc + ", secondarynumberofaccounts=" + secondarynumberofaccounts
				+ ", secondarysanctionedamount=" + secondarysanctionedamount + ", secondarysecurednoofacc="
				+ secondarysecurednoofacc + ", secondaryactivenoofacc=" + secondaryactivenoofacc
				+ ", secondaryunsecurednoofacc=" + secondaryunsecurednoofacc + ", secondarycurrentbalance="
				+ secondarycurrentbalance + ", secondaryoverduenoofacc=" + secondaryoverduenoofacc
				+ ", secondarydisbursedamount=" + secondarydisbursedamount + "]";
	}

}
