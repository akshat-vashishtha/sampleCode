package com.qualtech.crif.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IB_CRF_INDV_GROUP_DETAILS")
public class IndvGrpDetails 
{
	private String indv_total_balance;
	private String indv_total_disbursed_amount;
	private String indv_toatl_dp_90;
	private String indv_toatl_account;
	private String indv_grp_id;
	private String indv_toatl_dp_60;
	private String indv_toatl_dp_30;
	private Long indv_grp_dtl_id;
	private IndvResponseDetails  indvresponsedetails;
	
	
	
	@JoinColumn(name="INDV_RSP_DTL_ID", nullable=false) 
	@OneToOne
	public IndvResponseDetails getIndvresponsedetails() {
		return indvresponsedetails;
	}
	public void setIndvresponsedetails(IndvResponseDetails indvresponsedetails) {
		this.indvresponsedetails = indvresponsedetails;
	}
	
	
	public String getIndv_total_balance() {
		return indv_total_balance;
	}
	public void setIndv_total_balance(String indv_total_balance) {
		this.indv_total_balance = indv_total_balance;
	}
	public String getIndv_total_disbursed_amount() {
		return indv_total_disbursed_amount;
	}
	public void setIndv_total_disbursed_amount(String indv_total_disbursed_amount) {
		this.indv_total_disbursed_amount = indv_total_disbursed_amount;
	}
	public String getIndv_toatl_dp_90() {
		return indv_toatl_dp_90;
	}
	public void setIndv_toatl_dp_90(String indv_toatl_dp_90) {
		this.indv_toatl_dp_90 = indv_toatl_dp_90;
	}
	public String getIndv_toatl_account() {
		return indv_toatl_account;
	}
	public void setIndv_toatl_account(String indv_toatl_account) {
		this.indv_toatl_account = indv_toatl_account;
	}
	public String getIndv_grp_id() {
		return indv_grp_id;
	}
	public void setIndv_grp_id(String indv_grp_id) {
		this.indv_grp_id = indv_grp_id;
	}
	public String getIndv_toatl_dp_60() {
		return indv_toatl_dp_60;
	}
	public void setIndv_toatl_dp_60(String indv_toatl_dp_60) {
		this.indv_toatl_dp_60 = indv_toatl_dp_60;
	}
	public String getIndv_toatl_dp_30() {
		return indv_toatl_dp_30;
	}
	public void setIndv_toatl_dp_30(String indv_toatl_dp_30) {
		this.indv_toatl_dp_30 = indv_toatl_dp_30;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_INDV_GRP_DETAIL_SQC")
	@SequenceGenerator(name = "IB_CRF_INDV_GRP_DETAIL_SQC", sequenceName = "IB_CRF_INDV_GRP_DETAIL_SQC", allocationSize = 1)
	public Long getIndv_grp_dtl_id() {
		return indv_grp_dtl_id;
	}
	public void setIndv_grp_dtl_id(Long indv_grp_dtl_id) {
		this.indv_grp_dtl_id = indv_grp_dtl_id;
	}

	
}
