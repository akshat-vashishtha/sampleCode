package com.qualtech.equifax.api.entity.mcr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;

@Entity
@Table(name="IB_EQ_M_ADDI_MFI_DTL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxMcrAdditionalMFIDetail {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_ADDIONALMFI_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_ADDIONALMFI_SQC", sequenceName = "IB_EQ_MCR_ADDIONALMFI_SQC", allocationSize = 1)
	@Column(name="AD_MFI_UNIQUE_ID")
	private int unique_id;
	@Column(name="ID")
	private String id;
	@Column(name="memberid")
	private String memberid;
	@Column(name="mficlientfullname")
	private String mficlientfullname;
	@Column(name="mfigender")
	private String mfigender;
	@Column(name="mfidob")
	private String mfidob;
	@Transient
	private EquifaxMcrAcntDtlMfiAddress mcrAcntDtlMfiAddress;
	@Transient
	private EquifaxMcrAcntDtlMfiIdentification mcrAcntDtlMfiIdentification;
	
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxMcrAllDetaills  equifaxMcrDetailsLogs;
	
	
	public EquifaxMcrAllDetaills getEquifaxMcrDetailsLogs() {
		return equifaxMcrDetailsLogs;
	}
	public void setEquifaxMcrDetailsLogs(EquifaxMcrAllDetaills equifaxMcrDetailsLogs) {
		this.equifaxMcrDetailsLogs = equifaxMcrDetailsLogs;
	}
	public int getUnique_id() {
		return unique_id;
	}
	public void setUnique_id(int unique_id) {
		this.unique_id = unique_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getMficlientfullname() {
		return mficlientfullname;
	}
	public void setMficlientfullname(String mficlientfullname) {
		this.mficlientfullname = mficlientfullname;
	}
	public String getMfigender() {
		return mfigender;
	}
	public void setMfigender(String mfigender) {
		this.mfigender = mfigender;
	}
	public String getMfidob() {
		return mfidob;
	}
	public void setMfidob(String mfidob) {
		this.mfidob = mfidob;
	}
	public EquifaxMcrAcntDtlMfiAddress getMcrAcntDtlMfiAddress() {
		return mcrAcntDtlMfiAddress;
	}
	public void setMcrAcntDtlMfiAddress(EquifaxMcrAcntDtlMfiAddress mcrAcntDtlMfiAddress) {
		this.mcrAcntDtlMfiAddress = mcrAcntDtlMfiAddress;
	}
	public EquifaxMcrAcntDtlMfiIdentification getMcrAcntDtlMfiIdentification() {
		return mcrAcntDtlMfiIdentification;
	}
	public void setMcrAcntDtlMfiIdentification(EquifaxMcrAcntDtlMfiIdentification mcrAcntDtlMfiIdentification) {
		this.mcrAcntDtlMfiIdentification = mcrAcntDtlMfiIdentification;
	}
	
	
}
