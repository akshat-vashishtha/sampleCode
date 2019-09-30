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
@Table(name = "IB_BUREAU_DRIVED_ATT_RES")
public class DrivedAttributesRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_DRIVED_ATT_RES_SQC", allocationSize = 1)
	@JsonIgnore
	private int drivedId;

	private String lengthofcredithistoryyear;
	private String inquiriesinlastsixmonth;
	private String newaccountinlastsixmonths;
	private String averageaccountageyear;
	private String averageaccountagemonth;
	private String newdlinqaccountinlastsixmonths;
	private String lengthofcredithistorymonth;

	@OneToOne
	@JoinColumn(name = "accId", nullable = false)
	@JsonIgnore
	private AccountSummaryRes accSummaryRes;

	public int getDrivedId() {
		return drivedId;
	}

	public void setDrivedId(int drivedId) {
		this.drivedId = drivedId;
	}

	public String getLengthofcredithistoryyear() {
		return lengthofcredithistoryyear;
	}

	public void setLengthofcredithistoryyear(String lengthofcredithistoryyear) {
		this.lengthofcredithistoryyear = lengthofcredithistoryyear;
	}

	public String getInquiriesinlastsixmonth() {
		return inquiriesinlastsixmonth;
	}

	public void setInquiriesinlastsixmonth(String inquiriesinlastsixmonth) {
		this.inquiriesinlastsixmonth = inquiriesinlastsixmonth;
	}

	public String getNewaccountinlastsixmonths() {
		return newaccountinlastsixmonths;
	}

	public void setNewaccountinlastsixmonths(String newaccountinlastsixmonths) {
		this.newaccountinlastsixmonths = newaccountinlastsixmonths;
	}

	public String getAverageaccountageyear() {
		return averageaccountageyear;
	}

	public void setAverageaccountageyear(String averageaccountageyear) {
		this.averageaccountageyear = averageaccountageyear;
	}

	public String getAverageaccountagemonth() {
		return averageaccountagemonth;
	}

	public void setAverageaccountagemonth(String averageaccountagemonth) {
		this.averageaccountagemonth = averageaccountagemonth;
	}

	public String getNewdlinqaccountinlastsixmonths() {
		return newdlinqaccountinlastsixmonths;
	}

	public void setNewdlinqaccountinlastsixmonths(String newdlinqaccountinlastsixmonths) {
		this.newdlinqaccountinlastsixmonths = newdlinqaccountinlastsixmonths;
	}

	public String getLengthofcredithistorymonth() {
		return lengthofcredithistorymonth;
	}

	public void setLengthofcredithistorymonth(String lengthofcredithistorymonth) {
		this.lengthofcredithistorymonth = lengthofcredithistorymonth;
	}

	public AccountSummaryRes getAccSummaryRes() {
		return accSummaryRes;
	}

	public void setAccSummaryRes(AccountSummaryRes accSummaryRes) {
		this.accSummaryRes = accSummaryRes;
	}

	@Override
	public String toString() {
		return "DrivedAttributesRes [drivedId=" + drivedId + ", lengthofcredithistoryyear=" + lengthofcredithistoryyear
				+ ", inquiriesinlastsixmonth=" + inquiriesinlastsixmonth + ", newaccountinlastsixmonths="
				+ newaccountinlastsixmonths + ", averageaccountageyear=" + averageaccountageyear
				+ ", averageaccountagemonth=" + averageaccountagemonth + ", newdlinqaccountinlastsixmonths="
				+ newdlinqaccountinlastsixmonths + ", lengthofcredithistorymonth=" + lengthofcredithistorymonth + "]";
	}

}
