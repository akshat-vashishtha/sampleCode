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
@Table(name = "IB_BUREAU_CAIS_HLDR_LST_RES")
public class CaisHolderDetailsListRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAIS_HLDR_LST_SQC", allocationSize = 1)
	@JsonIgnore
	private int caisHolderListId;

	private String passportnumber;
	private String middlename2nonnormalized;
	private String gendercode;
	private String middlename1nonnormalized;
	private String alias;
	private String incometaxpan;
	private String middlename3nonnormalized;
	private String dateofbirth;
	private String firstnamenonnormalized;
	private String surnamenonnormalized;

	@ManyToOne
	@JoinColumn(name = "caisAccDetailsId", nullable = false)
	@JsonIgnore
	private CaisAccountDetailsRes caisAccountDetailsRes;

	public int getCaisHolderListId() {
		return caisHolderListId;
	}

	public void setCaisHolderListId(int caisHolderListId) {
		this.caisHolderListId = caisHolderListId;
	}

	public String getPassportnumber() {
		return passportnumber;
	}

	public void setPassportnumber(String passportnumber) {
		this.passportnumber = passportnumber;
	}

	public String getMiddlename2nonnormalized() {
		return middlename2nonnormalized;
	}

	public void setMiddlename2nonnormalized(String middlename2nonnormalized) {
		this.middlename2nonnormalized = middlename2nonnormalized;
	}

	public String getGendercode() {
		return gendercode;
	}

	public void setGendercode(String gendercode) {
		this.gendercode = gendercode;
	}

	public String getMiddlename1nonnormalized() {
		return middlename1nonnormalized;
	}

	public void setMiddlename1nonnormalized(String middlename1nonnormalized) {
		this.middlename1nonnormalized = middlename1nonnormalized;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getIncometaxpan() {
		return incometaxpan;
	}

	public void setIncometaxpan(String incometaxpan) {
		this.incometaxpan = incometaxpan;
	}

	public String getMiddlename3nonnormalized() {
		return middlename3nonnormalized;
	}

	public void setMiddlename3nonnormalized(String middlename3nonnormalized) {
		this.middlename3nonnormalized = middlename3nonnormalized;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getFirstnamenonnormalized() {
		return firstnamenonnormalized;
	}

	public void setFirstnamenonnormalized(String firstnamenonnormalized) {
		this.firstnamenonnormalized = firstnamenonnormalized;
	}

	public String getSurnamenonnormalized() {
		return surnamenonnormalized;
	}

	public void setSurnamenonnormalized(String surnamenonnormalized) {
		this.surnamenonnormalized = surnamenonnormalized;
	}

	public CaisAccountDetailsRes getCaisAccountDetailsRes() {
		return caisAccountDetailsRes;
	}

	public void setCaisAccountDetailsRes(CaisAccountDetailsRes caisAccountDetailsRes) {
		this.caisAccountDetailsRes = caisAccountDetailsRes;
	}

	@Override
	public String toString() {
		return "CaisHolderDetailsListRes [caisHolderListId=" + caisHolderListId + ", passportnumber=" + passportnumber
				+ ", middlename2nonnormalized=" + middlename2nonnormalized + ", gendercode=" + gendercode
				+ ", middlename1nonnormalized=" + middlename1nonnormalized + ", alias=" + alias + ", incometaxpan="
				+ incometaxpan + ", middlename3nonnormalized=" + middlename3nonnormalized + ", dateofbirth="
				+ dateofbirth + ", firstnamenonnormalized=" + firstnamenonnormalized + ", surnamenonnormalized="
				+ surnamenonnormalized + "]";
	}

}
