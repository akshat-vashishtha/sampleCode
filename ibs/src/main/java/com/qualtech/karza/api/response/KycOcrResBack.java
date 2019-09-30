package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/*import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)*/

@Entity
@Table(name="QCIB_K_KYC_OCR_RES_BACK")
@JsonIgnoreProperties(ignoreUnknown=true)
public class KycOcrResBack implements Serializable {


	 @Column(name="AADHAAR")
	 private String aadhaar;
	 @Column(name="ADDRESS")
	 private String address;
	 @Column(name="FATHER")
	 private String father;
	 @Column(name="PIN")
	 private String pin;
	 @Column(name="TYPE")
	 private String type;
	 @Column(name="VOTERID")
	 private String voterid;
	 @Id
	 @OneToOne
	 @JoinColumn(name="UNIQUEID")
	 @JsonIgnore
	 private KycOcrResResult kycocrresresult;
	 
	 
	 
	public KycOcrResResult getKycocrresresult() {
		return kycocrresresult;
	}
	public void setKycocrresresult(KycOcrResResult kycocrresresult) {
		this.kycocrresresult = kycocrresresult;
	}
	
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getVoterid() {
		return voterid;
	}
	public void setVoterid(String voterid) {
		this.voterid = voterid;
	}
	@Override
	public String toString() {
		return "KycOcrResBack [aadhaar=" + aadhaar + ", address=" + address + ", father=" + father + ", pin=" + pin
				+ ", type=" + type + ", voterid=" + voterid + "]";
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	 
	 
}
