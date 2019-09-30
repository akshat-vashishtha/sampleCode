package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_GSTAUTHPRADR")
@JsonIgnoreProperties(ignoreUnknown = true)
public class GSTAuthenticationPRADR implements Serializable {

	private static final long serialVersionUID = -4180206972726416019L;
	private int uniqueid;
	private String em;
	private String ntr;
	private String adr;
	private String mb;
	private int uniqueid_of_res;
	@Column(name="EM")
	public String getEm() {
		return em;
	}
	public void setEm(String em) {
		this.em = em;
	}
	@Column(name="NTR")
	public String getNtr() {
		return ntr;
	}
	public void setNtr(String ntr) {
		this.ntr = ntr;
	}
	@Column(name="ADR")
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}
	@Column(name="MB")
	public String getMb() {
		return mb;
	}
	public void setMb(String mb) {
		this.mb = mb;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "GSTAuthenticationPRADR [em=" + em + ", ntr=" + ntr + ", adr=" + adr + ", mb=" + mb + "]";
	}
	@Id
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_GSTAuthPRADR_RES_SQC")
	@Column(name="UNIQUEID")
	@JsonIgnore
	public int getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(int uniqueid) {
		this.uniqueid = uniqueid;
	}
	@Column(name="UNIQUEID_OF_QCIB_K_GSTAUTH_RES")
	@JsonIgnore
	public int getUniqueid_of_res() {
		return uniqueid_of_res;
	}
	public void setUniqueid_of_res(int uniqueid_of_res) {
		this.uniqueid_of_res = uniqueid_of_res;
	}
	
	
	
}
