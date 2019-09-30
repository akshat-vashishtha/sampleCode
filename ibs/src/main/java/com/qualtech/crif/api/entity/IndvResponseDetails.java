package com.qualtech.crif.api.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IB_CRF_INDV_RESPONSE_DETAILS")
public class IndvResponseDetails 
{
	private String indv_mfiid;
	private String indv_kendra;
	private String indv_branch;
	private String indv_mfi;
	private String indv_dob;
	private String indv_name;
	private String indv_cnsmbrid;
	private String indv_inserted_date;
	private String indv_matched_type;
	private Long indv_rsp_dtl_id;
	private CriffDetailLogs crifdetaillogs;
	private IndvResponseAddress IndvResponseAddress; 
	private Set<IndvIds>  indvids; 
	private Set<IndvRelation> indvrelations; 
	private IndvLoanDetails indvloandetails;
	private IndvGrpDetails indvgrpdetails; 
	
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	public CriffDetailLogs getCrifdetaillogs() {
		return crifdetaillogs;
	}
	public void setCrifdetaillogs(CriffDetailLogs crifdetaillogs) {
		this.crifdetaillogs = crifdetaillogs;
	}
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvresponsedetails" ,cascade=CascadeType.ALL)
	public IndvGrpDetails getIndvgrpdetails() {
		return indvgrpdetails;
	}
	
	public void setIndvgrpdetails(IndvGrpDetails indvgrpdetails) {
		this.indvgrpdetails = indvgrpdetails;
	}
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvresponsedetails" ,cascade=CascadeType.ALL)
	public IndvLoanDetails getIndvloandetails() {
		return indvloandetails;
	}
	public void setIndvloandetails(IndvLoanDetails indvloandetails) {
		this.indvloandetails = indvloandetails;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "indvresponsedetails" ,cascade=CascadeType.ALL) 
	public Set<IndvIds> getIndvids() {
		return indvids;
	}
	public void setIndvids(Set<IndvIds> indvids) {
		this.indvids = indvids;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "indvresponsedetails" ,cascade=CascadeType.ALL)
	public Set<IndvRelation> getIndvrelations() {
		return indvrelations;
	}
	public void setIndvrelations(Set<IndvRelation> indvrelations) {
		this.indvrelations = indvrelations;
	}
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvresponsedetails" ,cascade=CascadeType.ALL)
	public IndvResponseAddress getIndvResponseAddress() {
		return IndvResponseAddress;
	}
	public void setIndvResponseAddress(IndvResponseAddress indvResponseAddress) {
		IndvResponseAddress = indvResponseAddress;
	}
	public String getIndv_mfiid() {
		return indv_mfiid;
	}
	public void setIndv_mfiid(String indv_mfiid) {
		this.indv_mfiid = indv_mfiid;
	}
	
	public String getIndv_kendra() {
		return indv_kendra;
	}
	public void setIndv_kendra(String indv_kendra) {
		this.indv_kendra = indv_kendra;
	}
	
	public String getIndv_branch() {
		return indv_branch;
	}
	public void setIndv_branch(String indv_branch) {
		this.indv_branch = indv_branch;
	}
	
	public String getIndv_mfi() {
		return indv_mfi;
	}
	
	public void setIndv_mfi(String indv_mfi) {
		this.indv_mfi = indv_mfi;
	}
	public String getIndv_dob() {
		return indv_dob;
	}
	
	public void setIndv_dob(String indv_dob) {
		this.indv_dob = indv_dob;
	}
	public String getIndv_name() {
		return indv_name;
	}
	
	public void setIndv_name(String indv_name) {
		this.indv_name = indv_name;
	}
	public String getIndv_cnsmbrid() {
		return indv_cnsmbrid;
	}
	
	public void setIndv_cnsmbrid(String indv_cnsmbrid) {
		this.indv_cnsmbrid = indv_cnsmbrid;
	}
	public String getIndv_inserted_date() {
		return indv_inserted_date;
	}
	
	public void setIndv_inserted_date(String indv_inserted_date) {
		this.indv_inserted_date = indv_inserted_date;
	}
	public String getIndv_matched_type() {
		return indv_matched_type;
	}
	
	public void setIndv_matched_type(String indv_matched_type) {
		this.indv_matched_type = indv_matched_type;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_INDV_RSPNS_DETAILS_SQC")
	@SequenceGenerator(name = "IB_CRF_INDV_RSPNS_DETAILS_SQC", sequenceName = "IB_CRF_INDV_RSPNS_DETAILS_SQC", allocationSize = 1)
	public Long getIndv_rsp_dtl_id() {
		return indv_rsp_dtl_id;
	}
	public void setIndv_rsp_dtl_id(Long indv_rsp_dtl_id) {
		this.indv_rsp_dtl_id = indv_rsp_dtl_id;
	}

	
}
