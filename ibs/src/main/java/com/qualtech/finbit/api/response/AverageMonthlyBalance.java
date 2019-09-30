package com.qualtech.finbit.api.response;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_FINBIT_RES_AVG_BAL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AverageMonthlyBalance
{
	@Id
	@GeneratedValue(generator = "IB_FINBIT_RES_AVG_BAL_SQC")
	@SequenceGenerator(name = "IB_FINBIT_RES_AVG_BAL_SQC", sequenceName = "IB_FINBIT_RES_AVG_BAL_SQC")
	private int seqId;
	private String netAverageBalance;
	private String monthAndYear;
	private DayBalanceMap dayBalanceMap;
	@ManyToOne
	@JoinColumn(name="EID",nullable=false)
	@JsonIgnore
	private FinbitResResult result;
	
	
	
	public FinbitResResult getResult() {
		return result;
	}
	public void setResult(FinbitResResult result) {
		this.result = result;
	}
	public int getSeqId() {
		return seqId;
	}
	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}
	public String getNetAverageBalance() {
		return netAverageBalance;
	}
	public void setNetAverageBalance(String netAverageBalance) {
		this.netAverageBalance = netAverageBalance;
	}
	public String getMonthAndYear() {
		return monthAndYear;
	}
	public void setMonthAndYear(String monthAndYear) {
		this.monthAndYear = monthAndYear;
	}
	public DayBalanceMap getDayBalanceMap() {
		return dayBalanceMap;
	}
	public void setDayBalanceMap(DayBalanceMap dayBalanceMap) {
		this.dayBalanceMap = dayBalanceMap;
	}
	@Override
	public String toString() {
		return "AverageMonthlyBalance [netAverageBalance=" + netAverageBalance + ", monthAndYear=" + monthAndYear
				+ ", dayBalanceMap=" + dayBalanceMap + "]";
	}
	
}
