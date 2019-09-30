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
@Table(name="IB_CRF_GRP_RSP_NORMAL_SMRY")
public class GrpResponseSummary 
{
	private String  total_responses;
	private String  number_of_other_mfis;
	private String  number_of_default_account;
	private String  number_of_own_mfis;
	private String  number_of_closed_accounts;
	private String  number_of_active_accounts;
	private String  status;
	private Long  grpummary_id;
	private CriffDetailLogs crifdetaillogs;
	

	@OneToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	public CriffDetailLogs getCrifdetaillogs() {
		return crifdetaillogs;
	}
	public void setCrifdetaillogs(CriffDetailLogs crifdetaillogs) {
		this.crifdetaillogs = crifdetaillogs;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_GRPNRMLSUMMARY_SQC")
	@SequenceGenerator(name="IB_CRF_GRPNRMLSUMMARY_SQC", sequenceName = "IB_CRF_GRPNRMLSUMMARY_SQC", allocationSize = 1)
	@Column(name="GRPUMMARY_ID")
	public Long getGrpummary_id() {
		return grpummary_id;
	}
	public void setGrpummary_id(Long grpummary_id) {
		this.grpummary_id = grpummary_id;
	}
	@Column(name="TOTAL_RESPONSES")
	public String getTotal_responses() {
		return total_responses;
	}
	public void setTotal_responses(String total_responses) {
		this.total_responses = total_responses;
	}
	@Column(name="NUMBER_OF_OTHER_MFIS")
	public String getNumber_of_other_mfis() {
		return number_of_other_mfis;
	}
	public void setNumber_of_other_mfis(String number_of_other_mfis) {
		this.number_of_other_mfis = number_of_other_mfis;
	}
	@Column(name="NUMBER_OF_DEFAULT_ACCOUNT")
	public String getNumber_of_default_account() {
		return number_of_default_account;
	}
	public void setNumber_of_default_account(String number_of_default_account) {
		this.number_of_default_account = number_of_default_account;
	}
	@Column(name="NUMBER_OF_OWN_MFIS")
	public String getNumber_of_own_mfis() {
		return number_of_own_mfis;
	}
	public void setNumber_of_own_mfis(String number_of_own_mfis) {
		this.number_of_own_mfis = number_of_own_mfis;
	}
	@Column(name="NUMBER_OF_CLOSED_ACCOUNTS")
	public String getNumber_of_closed_accounts() {
		return number_of_closed_accounts;
	}
	public void setNumber_of_closed_accounts(String number_of_closed_accounts) {
		this.number_of_closed_accounts = number_of_closed_accounts;
	}
	@Column(name="NUMBER_OF_ACTIVE_ACCOUNTS")
	public String getNumber_of_active_accounts() {
		return number_of_active_accounts;
	}
	public void setNumber_of_active_accounts(String number_of_active_accounts) {
		this.number_of_active_accounts = number_of_active_accounts;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
