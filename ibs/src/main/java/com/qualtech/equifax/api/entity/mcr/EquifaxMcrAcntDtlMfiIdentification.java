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
@Table(name="IB_EQ_M_ADMFI_ID")
@JsonIgnoreProperties(ignoreUnknown=true)

public class EquifaxMcrAcntDtlMfiIdentification {
	

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_ADDIONALMFI_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_ADDIONALMFI_SQC", sequenceName = "IB_EQ_MCR_ADDIONALMFI_SQC", allocationSize = 1)
	@Column(name="ADDIONALMFIID")
	private int addionalMfiId;
	@Column(name="MFIOTHERID")
	private String mfiOtherId;
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
	public int getAddionalMfiId() {
		return addionalMfiId;
	}
	public void setAddionalMfiId(int addionalMfiId) {
		this.addionalMfiId = addionalMfiId;
	}
	public String getMfiOtherId() {
		return mfiOtherId;
	}
	public void setMfiOtherId(String mfiOtherId) {
		this.mfiOtherId = mfiOtherId;
	}
	
	
	
}
