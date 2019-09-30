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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;

@Entity
@Table(name="IB_EQ_M_ADMFI_ADRS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxMcrAcntDtlMfiAddress {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_ADDIONALMFI_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_ADDIONALMFI_SQC", sequenceName = "IB_EQ_MCR_ADDIONALMFI_SQC", allocationSize = 1)
	@Column(name="MfiAddressId")
	private int additionalMfiAddressId;
	@Column(name="mfistate")
	private String mfistate;
	@Column(name="seq")
	private String  seq;
	@Column(name="mfiaddressline")
	private String mfiaddressline;
	@Column(name="mfipostalpin")
	private String mfipostalpin;
	
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
	public int getAdditionalMfiAddressId() {
		return additionalMfiAddressId;
	}
	public void setAdditionalMfiAddressId(int additionalMfiAddressId) {
		this.additionalMfiAddressId = additionalMfiAddressId;
	}
	public String getMfistate() {
		return mfistate;
	}
	public void setMfistate(String mfistate) {
		this.mfistate = mfistate;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getMfiaddressline() {
		return mfiaddressline;
	}
	public void setMfiaddressline(String mfiaddressline) {
		this.mfiaddressline = mfiaddressline;
	}
	public String getMfipostalpin() {
		return mfipostalpin;
	}
	public void setMfipostalpin(String mfipostalpin) {
		this.mfipostalpin = mfipostalpin;
	}
	
	
	
}
