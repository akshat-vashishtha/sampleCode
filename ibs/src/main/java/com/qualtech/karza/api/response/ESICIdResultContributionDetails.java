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
@Table(name = "IB_K_ESICID_CONT_DET_KYC_RES")
public class ESICIdResultContributionDetails implements Serializable {

	private static final long serialVersionUID = -8251784410816419882L;
	
	@Column
	@JsonIgnore
	private String correlation_Id;
	@Id
	@JsonIgnore
	@GeneratedValue(generator="ESICI_SEQ")
	@SequenceGenerator(name="ESICI_SEQ",sequenceName="IB_K_ESICI_RES_SQC")
	private int sequence_Id;
	@Column
	private String employeeConrtibution;
	@Column
	private String wagePeriod;
	@Column
	private String totalMonthlyWages;
	@Column
	private String numberOfDaysWagesPaid;
	@ManyToOne
	@JoinColumn(name="uniqueId", nullable=false)
	@JsonIgnore
	private ESICIdResult esicIdResult;

	public String getEmployeeConrtibution() {
		return employeeConrtibution;
	}

	public void setEmployeeConrtibution(String employeeConrtibution) {
		this.employeeConrtibution = employeeConrtibution;
	}

	public String getWagePeriod() {
		return wagePeriod;
	}

	public void setWagePeriod(String wagePeriod) {
		this.wagePeriod = wagePeriod;
	}

	public String getTotalMonthlyWages() {
		return totalMonthlyWages;
	}

	public void setTotalMonthlyWages(String totalMonthlyWages) {
		this.totalMonthlyWages = totalMonthlyWages;
	}

	public String getNumberOfDaysWagesPaid() {
		return numberOfDaysWagesPaid;
	}

	public void setNumberOfDaysWagesPaid(String numberOfDaysWagesPaid) {
		this.numberOfDaysWagesPaid = numberOfDaysWagesPaid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCorrelation_Id() {
		return correlation_Id;
	}

	public void setCorrelation_Id(String correlation_Id) {
		this.correlation_Id = correlation_Id;
	}

	public int getSequence_Id() {
		return sequence_Id;
	}

	public void setSequence_Id(int sequence_Id) {
		this.sequence_Id = sequence_Id;
	}

	@Override
	public String toString() {
		return "ESICIdResultContributionDetails [correlation_Id=" + correlation_Id + ", sequence_Id=" + sequence_Id
				+ ", employeeConrtibution=" + employeeConrtibution + ", wagePeriod=" + wagePeriod
				+ ", totalMonthlyWages=" + totalMonthlyWages + ", numberOfDaysWagesPaid=" + numberOfDaysWagesPaid + "]";
	}

	public ESICIdResult getEsicIdResult() {
		return esicIdResult;
	}

	public void setEsicIdResult(ESICIdResult esicIdResult) {
		this.esicIdResult = esicIdResult;
	}
	
	

}
