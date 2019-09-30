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
@Table(name="IB_CRF_INDV_SEC_NORMAL_SMRY")
public class IndvSecondarySummary {

	private Long indvSecondarySummary_ID; 
	private String no_of_other_mfis; 
	private String no_of_closed_accounts; 
	private String total_responses; 
    private String no_of_default_accounts; 
    private String no_of_own_mfis; 
    private String no_of_active_accounts;
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
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_IDVSECSUMMARY_SQC")
	@SequenceGenerator(name="IB_CRF_IDVSECSUMMARY_SQC", sequenceName = "IB_CRF_IDVSECSUMMARY_SQC", allocationSize = 1)
	@Column(name="INDVSECONDARYSUMMARY_ID")
	public Long getIndvSecondarySummary_ID() {
		return indvSecondarySummary_ID;
	}
	public void setIndvSecondarySummary_ID(Long mfiPrimarySummary_ID) {
		this.indvSecondarySummary_ID = mfiPrimarySummary_ID;
	}
	
	@Column(name="NO_OF_OTHER_MFIS")
	public String getNo_of_other_mfis() {
		return no_of_other_mfis;
	}
	public void setNo_of_other_mfis(String no_of_other_mfis) {
		this.no_of_other_mfis = no_of_other_mfis;
	}
	
	@Column(name="NO_OF_CLOSED_ACCOUNTS")
	public String getNo_of_closed_accounts() {
		return no_of_closed_accounts;
	}
	public void setNo_of_closed_accounts(String no_of_closed_accounts) {
		this.no_of_closed_accounts = no_of_closed_accounts;
	}
	
	@Column(name="TOTAL_RESPONSES")
	public String getTotal_responses() {
		return total_responses;
	}
	public void setTotal_responses(String total_responses) {
		this.total_responses = total_responses;
	}
	
	@Column(name="NO_OF_DEFAULT_ACCOUNTS")
	public String getNo_of_default_accounts() {
		return no_of_default_accounts;
	}
	public void setNo_of_default_accounts(String no_of_default_accounts) {
		this.no_of_default_accounts = no_of_default_accounts;
	}
	
	@Column(name="NO_OF_OWN_MFIS")
	public String getNo_of_own_mfis() {
		return no_of_own_mfis;
	}
	public void setNo_of_own_mfis(String no_of_own_mfis) {
		this.no_of_own_mfis = no_of_own_mfis;
	}
	
	@Column(name="NO_OF_ACTIVE_ACCOUNTS")
	public String getNo_of_active_accounts() {
		return no_of_active_accounts;
	}
	public void setNo_of_active_accounts(String no_of_active_accounts) {
		this.no_of_active_accounts = no_of_active_accounts;
	}
	@Override
	public String toString() {
		return "IndvSecondarySummary [indvSecondarySummary_ID=" + indvSecondarySummary_ID + ", no_of_other_mfis="
				+ no_of_other_mfis + ", no_of_closed_accounts=" + no_of_closed_accounts + ", total_responses="
				+ total_responses + ", no_of_default_accounts=" + no_of_default_accounts + ", no_of_own_mfis="
				+ no_of_own_mfis + ", no_of_active_accounts=" + no_of_active_accounts + ", crifdetaillogs="
				+ crifdetaillogs + "]";
	}

 
}
