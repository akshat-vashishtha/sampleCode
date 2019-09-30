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
@Table(name = "IB_BUREAU_CAIS_HLDR_ID_RES")
public class CaisHolderidDetailsRes implements Serializable {


	private static final long serialVersionUID = 612526037397077639L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAIS_HLDR_ID_SQC", allocationSize = 1)
	@JsonIgnore
	private int caisHolderIDDetailsId;

	private String passportnumber;
	private String driverlicenceissuedate;
	private String driverlicencenumber;
	private String passportissuedate;
	private String panexpirationdate;
	private String incometaxpan;
	private String driverlicenceexpirationdate;
	private String panissuedate;
	private String passportexpirationdate;
	private String emailid;
	
	@OneToOne
	@JoinColumn(name = "caisAccDetailsId", nullable = false)
	@JsonIgnore
	private CaisAccountDetailsRes caisAccountDetailsRes;

	public int getCaisHolderIDDetailsId() {
		return caisHolderIDDetailsId;
	}

	public void setCaisHolderIDDetailsId(int caisHolderIDDetailsId) {
		this.caisHolderIDDetailsId = caisHolderIDDetailsId;
	}

	public String getPassportnumber() {
		return passportnumber;
	}

	public void setPassportnumber(String passportnumber) {
		this.passportnumber = passportnumber;
	}

	public String getDriverlicenceissuedate() {
		return driverlicenceissuedate;
	}

	public void setDriverlicenceissuedate(String driverlicenceissuedate) {
		this.driverlicenceissuedate = driverlicenceissuedate;
	}

	public String getDriverlicencenumber() {
		return driverlicencenumber;
	}

	public void setDriverlicencenumber(String driverlicencenumber) {
		this.driverlicencenumber = driverlicencenumber;
	}

	public String getPassportissuedate() {
		return passportissuedate;
	}

	public void setPassportissuedate(String passportissuedate) {
		this.passportissuedate = passportissuedate;
	}

	public String getPanexpirationdate() {
		return panexpirationdate;
	}

	public void setPanexpirationdate(String panexpirationdate) {
		this.panexpirationdate = panexpirationdate;
	}

	public String getIncometaxpan() {
		return incometaxpan;
	}

	public void setIncometaxpan(String incometaxpan) {
		this.incometaxpan = incometaxpan;
	}

	public String getDriverlicenceexpirationdate() {
		return driverlicenceexpirationdate;
	}

	public void setDriverlicenceexpirationdate(String driverlicenceexpirationdate) {
		this.driverlicenceexpirationdate = driverlicenceexpirationdate;
	}

	public String getPanissuedate() {
		return panissuedate;
	}

	public void setPanissuedate(String panissuedate) {
		this.panissuedate = panissuedate;
	}

	public String getPassportexpirationdate() {
		return passportexpirationdate;
	}

	public void setPassportexpirationdate(String passportexpirationdate) {
		this.passportexpirationdate = passportexpirationdate;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public CaisAccountDetailsRes getCaisAccountDetailsRes() {
		return caisAccountDetailsRes;
	}

	public void setCaisAccountDetailsRes(CaisAccountDetailsRes caisAccountDetailsRes) {
		this.caisAccountDetailsRes = caisAccountDetailsRes;
	}

	@Override
	public String toString() {
		return "CaisHolderidDetailsRes [caisHolderIDDetailsId=" + caisHolderIDDetailsId + ", passportnumber="
				+ passportnumber + ", driverlicenceissuedate=" + driverlicenceissuedate + ", driverlicencenumber="
				+ driverlicencenumber + ", passportissuedate=" + passportissuedate + ", panexpirationdate="
				+ panexpirationdate + ", incometaxpan=" + incometaxpan + ", driverlicenceexpirationdate="
				+ driverlicenceexpirationdate + ", panissuedate=" + panissuedate + ", passportexpirationdate="
				+ passportexpirationdate + ", emailid=" + emailid + "]";
	}

	
}
