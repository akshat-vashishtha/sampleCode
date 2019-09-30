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
@Table(name = "IB_BUREAU_CAPSOTHER_RES")
public class CapsOtherDetailsRes implements Serializable {


	private static final long serialVersionUID = 612526037397077639L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAPSOTHER_SQC", allocationSize = 1)
	@JsonIgnore
	private int capsOtherId;

	private String timewithemployer;
	private String income;
	private String numberofmajorcreditcardheld;
	private String maritialstatus;
	private String employmentstatus;
	
	@OneToOne
	@JoinColumn(name = "capsDetailId", nullable = false)
	@JsonIgnore
	private CapsApplicationDetailListRes capsApplicationDetailListRes;

	public int getCapsOtherId() {
		return capsOtherId;
	}

	public void setCapsOtherId(int capsOtherId) {
		this.capsOtherId = capsOtherId;
	}

	public String getTimewithemployer() {
		return timewithemployer;
	}

	public void setTimewithemployer(String timewithemployer) {
		this.timewithemployer = timewithemployer;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getNumberofmajorcreditcardheld() {
		return numberofmajorcreditcardheld;
	}

	public void setNumberofmajorcreditcardheld(String numberofmajorcreditcardheld) {
		this.numberofmajorcreditcardheld = numberofmajorcreditcardheld;
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

	public CapsApplicationDetailListRes getCapsApplicationDetailListRes() {
		return capsApplicationDetailListRes;
	}

	public void setCapsApplicationDetailListRes(CapsApplicationDetailListRes capsApplicationDetailListRes) {
		this.capsApplicationDetailListRes = capsApplicationDetailListRes;
	}

	@Override
	public String toString() {
		return "CapsOtherDetailsRes [capsOtherId=" + capsOtherId + ", timewithemployer=" + timewithemployer
				+ ", income=" + income + ", numberofmajorcreditcardheld=" + numberofmajorcreditcardheld
				+ ", maritialstatus=" + maritialstatus + ", employmentstatus=" + employmentstatus + "]";
	}

}
