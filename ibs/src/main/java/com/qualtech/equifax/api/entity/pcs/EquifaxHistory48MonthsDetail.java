package com.qualtech.equifax.api.entity.pcs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;

@Entity
@Table(name="IB_EQ_P_48MONTH")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxHistory48MonthsDetail {

	@Column(name="PAYMENTSTATUS")
	private String paymentstatus;
	@Column(name="KEY")
	private String key;
	@Column(name="SUITFILED_STATUS")
	private String suitFiledStatus;
	@Column(name="ASSET_CLASSIFICATION_STATUS")
	private String assetClassificationStatus;
	
	@Transient
	private Long historyMonthId;
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxPcsAllDetails   equifaxPcsAllDetails;
	
	@Id
	@JsonIgnore
	@GeneratedValue(generator="IB_EQ_P_48MONTH_SQC")
	@SequenceGenerator(name="IB_EQ_P_48MONTH_SQC",sequenceName="IB_EQ_P_48MONTH_SQC")
	private long sequenceId;
	
	
	public EquifaxPcsAllDetails getEquifaxPcsAllDetails() {
		return equifaxPcsAllDetails;
	}
	public void setEquifaxPcsAllDetails(EquifaxPcsAllDetails equifaxPcsAllDetails) {
		this.equifaxPcsAllDetails = equifaxPcsAllDetails;
	}
	public long getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(long sequenceId) {
		this.sequenceId = sequenceId;
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
	public Long getHistoryMonthId() {
		return historyMonthId;
	}
	public void setHistoryMonthId(Long historyMonthId) {
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
