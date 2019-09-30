package com.qualtech.equifax.api.entity.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;

@Entity
@Table(name="IB_EQ_M_OTHER_KEY_IND")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxOtherKeyInd {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_OTHERKEYIND_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_OTHERKEYIND_SQC", sequenceName = "IB_EQ_MCR_OTHERKEYIND_SQC", allocationSize = 1)
	private int otherKeyId;
	@Column
	private String ageOfOldestTrade;
	@Column
	private String numberOfOpenTrades;
	@Column
	private String allLinesEVERWritten;
	@Column
	private String allLinesEVERWrittenIn9Months ;
	@Column
	private String allLinesEVERWrittenIn6Months;
	@OneToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxMcrAllDetaills  equifaxMcrDetailsLogs;
	
	

	public EquifaxMcrAllDetaills getEquifaxMcrDetailsLogs() {
		return equifaxMcrDetailsLogs;
	}
	public void setEquifaxMcrDetailsLogs(EquifaxMcrAllDetaills equifaxMcrDetailsLogs) {
		this.equifaxMcrDetailsLogs = equifaxMcrDetailsLogs;
	}
	public int getOtherKeyId() {
		return otherKeyId;
	}
	public void setOtherKeyId(int otherKeyId) {
		this.otherKeyId = otherKeyId;
	}
	public String getAgeOfOldestTrade() {
		return ageOfOldestTrade;
	}
	public void setAgeOfOldestTrade(String ageOfOldestTrade) {
		this.ageOfOldestTrade = ageOfOldestTrade;
	}
	public String getNumberOfOpenTrades() {
		return numberOfOpenTrades;
	}
	public void setNumberOfOpenTrades(String numberOfOpenTrades) {
		this.numberOfOpenTrades = numberOfOpenTrades;
	}
	public String getAllLinesEVERWritten() {
		return allLinesEVERWritten;
	}
	public void setAllLinesEVERWritten(String allLinesEVERWritten) {
		this.allLinesEVERWritten = allLinesEVERWritten;
	}
	public String getAllLinesEVERWrittenIn9Months() {
		return allLinesEVERWrittenIn9Months;
	}
	public void setAllLinesEVERWrittenIn9Months(String allLinesEVERWrittenIn9Months) {
		this.allLinesEVERWrittenIn9Months = allLinesEVERWrittenIn9Months;
	}
	public String getAllLinesEVERWrittenIn6Months() {
		return allLinesEVERWrittenIn6Months;
	}
	public void setAllLinesEVERWrittenIn6Months(String allLinesEVERWrittenIn6Months) {
		this.allLinesEVERWrittenIn6Months = allLinesEVERWrittenIn6Months;
	}
	
	
}
