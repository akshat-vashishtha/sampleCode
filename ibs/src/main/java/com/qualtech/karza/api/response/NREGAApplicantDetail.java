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
@Table(name = "IB_K_NREGAAPLICANTDETAIL_RES")
public class NREGAApplicantDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5579934154681178904L;

	@Id
	@JsonIgnore
	@GeneratedValue(generator="NREGA_APP_SQC")
	@SequenceGenerator(name="NREGA_APP_SQC",sequenceName="IB_K_NREGAAPLICANTDETAIL_SQC")
	private int SEQUENCE_ID;
	@Column
	private String name;
	@Column
	private String gender;
	@Column
	private String age;
	@Column(name = "BANK_OR_POSTOFFICE")
	private String bankorpostoffice;
	@Column(name = "AADHAAR_NO")
	private String aadhaarNo;
	@Column(name = "ACCOUNT_NO")
	private String accountNo;
	@ManyToOne
	@JoinColumn(name="uniqueId", nullable=false)
	@JsonIgnore
	private NREGAResult nregaResultApplicant;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getBankorpostoffice() {
		return bankorpostoffice;
	}

	public void setBankorpostoffice(String bankorpostoffice) {
		this.bankorpostoffice = bankorpostoffice;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public int getSEQUENCE_ID() {
		return SEQUENCE_ID;
	}

	public void setSEQUENCE_ID(int sEQUENCE_ID) {
		SEQUENCE_ID = sEQUENCE_ID;
	}

	@Override
	public String toString() {
		return "NREGAApplicantDetail [name=" + name + ", gender=" + gender
				+ ", age=" + age + ", bankorpostoffice=" + bankorpostoffice
				+ ", aadhaarNo=" + aadhaarNo + ", accountNo=" + accountNo + "]";
	}

	public NREGAResult getNregaResultApplicant() {
		return nregaResultApplicant;
	}

	public void setNregaResultApplicant(NREGAResult nregaResultApplicant) {
		this.nregaResultApplicant = nregaResultApplicant;
	}
	
	

}
