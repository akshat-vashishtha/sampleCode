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
@Table(name="IB_EQ_M_24MONTH")
@JsonIgnoreProperties(ignoreUnknown=true)

public class EquifaxMcrHistory24MonthsDetail {
	
	@Column(name="paymentstatus")
	private String paymentstatus;
	@Column(name="KEY")
	private String key;
	@Column(name="suitfiled_status")
	private String suitFiledStatus;
	@Column(name="asset_classification_status")
	private String assetClassificationStatus;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_24MONTH_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_24MONTH_SQC", sequenceName = "IB_EQ_MCR_24MONTH_SQC", allocationSize = 1)
	@Column(name="historyMonthId")
	private int historyMonthId;
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
	public String getSuitFiledStatus() {
		return suitFiledStatus;
	}
	public void setSuitFiledStatus(String suitFiledStatus) {
		this.suitFiledStatus = suitFiledStatus;
	}
	public String getAssetClassificationStatus() {
		return assetClassificationStatus;
	}
	public void setAssetClassificationStatus(String assetClassificationStatus) {
		this.assetClassificationStatus = assetClassificationStatus;
	}
	public int getHistoryMonthId() {
		return historyMonthId;
	}
	public void setHistoryMonthId(int historyMonthId) {
		this.historyMonthId = historyMonthId;
	}
	public String getPaymentstatus() {
		return paymentstatus;
	}
	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
