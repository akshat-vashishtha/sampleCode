package com.qualtech.crif.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IB_CRF_DERIVED_ATTRIBUTE")
public class CriffDrivedAttribute 
{

	
	 private String	del_in_last_six_months;
	 private String	new_accounts_in_last_six_months;
	 private String	length_of_credit_history_month;
	 private String	average_account_age_month;
	 private String	length_of_credit_history_year;
	 private String	average_account_age_year;
	 private String	inquiries_in_last_six_months;
	 private Long	derived_attribute_id;
     private CriffDetailLogs crifdetaillogs;
     
     @OneToOne
     @JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
     public CriffDetailLogs getCrifdetaillogs() {
 		return crifdetaillogs;
 	 }
 	 public void setCrifdetaillogs(CriffDetailLogs crifdetaillogs) {
 		this.crifdetaillogs = crifdetaillogs;
 	 } 
	 
	@Column(name="DEL_IN_SIX_MONTHS") 
	public String getDel_in_last_six_months() {
		return del_in_last_six_months;
	}
	
	public void setDel_in_last_six_months(String del_in_last_six_months) {
		this.del_in_last_six_months = del_in_last_six_months;
	}
	@Column(name="NEW_ACCOUNTS_SIX_MONTHS")
	public String getNew_accounts_in_last_six_months() {
		return new_accounts_in_last_six_months;
	}
	public void setNew_accounts_in_last_six_months(String new_accounts_in_last_six_months) {
		this.new_accounts_in_last_six_months = new_accounts_in_last_six_months;
	}
	@Column(name="CREDIT_HISTORY_MONTH")
	public String getLength_of_credit_history_month() {
		return length_of_credit_history_month;
	}
	public void setLength_of_credit_history_month(String length_of_credit_history_month) {
		this.length_of_credit_history_month = length_of_credit_history_month;
	}
	@Column(name="AVERAGE_ACCOUNT_AGE_MONTH")
	public String getAverage_account_age_month() {
		return average_account_age_month;
	}
	public void setAverage_account_age_month(String average_account_age_month) {
		this.average_account_age_month = average_account_age_month;
	}
	@Column(name="CREDIT_HISTORY_YEAR")
	public String getLength_of_credit_history_year() {
		return length_of_credit_history_year;
	}
	public void setLength_of_credit_history_year(String length_of_credit_history_year) {
		this.length_of_credit_history_year = length_of_credit_history_year;
	}
	@Column(name="ACCOUNT_AGE_YEAR")
	public String getAverage_account_age_year() {
		return average_account_age_year;
	}
	public void setAverage_account_age_year(String average_account_age_year) {
		this.average_account_age_year = average_account_age_year;
	}
	@Column(name="LAST_SIX_MONTHS")
	public String getInquiries_in_last_six_months() {
		return inquiries_in_last_six_months;
	}
	public void setInquiries_in_last_six_months(String inquiries_in_last_six_months) {
		this.inquiries_in_last_six_months = inquiries_in_last_six_months;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_DRIVEDSEQUENCE_SQC")
	@SequenceGenerator(name="IB_CRF_DRIVEDSEQUENCE_SQC", sequenceName = "IB_CRF_DRIVEDSEQUENCE_SQC", allocationSize = 1)
	@Column(name="DERIVED_ATTRIBUTE_ID")
	public Long getDerived_attribute_id() {
		return derived_attribute_id;
	}
	public void setDerived_attribute_id(Long derived_attribute_id) {
		this.derived_attribute_id = derived_attribute_id;
	}
	
	
	
}
