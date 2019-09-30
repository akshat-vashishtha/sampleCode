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
@Table(name = "IB_BUREAU_CURR_OTHER_RES")
public class CurrentOtherDetailsRes implements Serializable {

	private static final long serialVersionUID = -487772733524459318L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CURR_OTH_SQC", allocationSize = 1)
	@JsonIgnore
	private int currOtherId;

	private String timewithemployer;
	private String numberofmajorcreditcardheld;
	private String income;
	private String maritialstatus;
	private String employmentstatus;
	
	@OneToOne
	@JoinColumn(name = "currAppDetailsId", nullable = false)
	@JsonIgnore
	private CurrentApplicationDetailsRes currAddressRes;

	public int getCurrOtherId() {
		return currOtherId;
	}

	public void setCurrOtherId(int currOtherId) {
		this.currOtherId = currOtherId;
	}

	public String getTimewithemployer() {
		return timewithemployer;
	}

	public void setTimewithemployer(String timewithemployer) {
		this.timewithemployer = timewithemployer;
	}

	public String getNumberofmajorcreditcardheld() {
		return numberofmajorcreditcardheld;
	}

	public void setNumberofmajorcreditcardheld(String numberofmajorcreditcardheld) {
		this.numberofmajorcreditcardheld = numberofmajorcreditcardheld;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getMaritialstatus() {
		return maritialstatus;
	}

	public void setMaritialstatus(String maritialstatus) {
		this.maritialstatus = maritialstatus;
	}

	public String getEmploymentstatus() {
		return employmentstatus;
	}

	public void setEmploymentstatus(String employmentstatus) {
		this.employmentstatus = employmentstatus;
	}

	public CurrentApplicationDetailsRes getCurrAddressRes() {
		return currAddressRes;
	}

	public void setCurrAddressRes(CurrentApplicationDetailsRes currAddressRes) {
		this.currAddressRes = currAddressRes;
	}

	@Override
	public String toString() {
		return "CurrentOtherDetailsRes [currOtherId=" + currOtherId + ", timewithemployer=" + timewithemployer
				+ ", numberofmajorcreditcardheld=" + numberofmajorcreditcardheld + ", income=" + income
				+ ", maritialstatus=" + maritialstatus + ", employmentstatus=" + employmentstatus + "]";
	}

}
