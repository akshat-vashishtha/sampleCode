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
@Table(name="IB_EQ_M_INCM_DTLS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxMCRIncomeDetails 
{
	
		
		  @Column(name="MONTHLY_INCOME")
		  private String monthly_income;
		  @Column(name="OCCUPATION")
		  private String occupation;
		  @Column(name="MONTHLY_EXPENSE")
		  private String monthly_expense;
		  @Column(name="REPORTED_DATE")
		  private String reported_date;
		  @Column(name="SEQUENCE")
		  private String sequence;
		  @Id
			@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_INCOME_SQC")
			@SequenceGenerator(name="IB_EQ_MCR_INCOME_SQC", sequenceName = "IB_EQ_MCR_INCOME_SQC", allocationSize = 1)
			@Column(name="INCOME_DTLS_ID")
		  private int income_details_id;
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
		
		public String getMonthly_income() {
			return monthly_income;
		}
		public void setMonthly_income(String monthly_income) {
			this.monthly_income = monthly_income;
		}
		public String getOccupation() {
			return occupation;
		}
		public void setOccupation(String occupation) {
			this.occupation = occupation;
		}
		public String getMonthly_expense() {
			return monthly_expense;
		}
		public void setMonthly_expense(String monthly_expense) {
			this.monthly_expense = monthly_expense;
		}
		public String getReported_date() {
			return reported_date;
		}
		public void setReported_date(String reported_date) {
			this.reported_date = reported_date;
		}
		public String getSequence() {
			return sequence;
		}
		public void setSequence(String sequence) {
			this.sequence = sequence;
		}
	public int getIncome_details_id() {
			return income_details_id;
		}
		public void setIncome_details_id(int income_details_id) {
			this.income_details_id = income_details_id;
		}
	
}
