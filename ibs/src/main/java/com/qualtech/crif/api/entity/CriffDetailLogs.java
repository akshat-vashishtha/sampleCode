package com.qualtech.crif.api.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="IB_CRF_DETAIL_LOGS")
public class CriffDetailLogs 
{
	
	private Long request_unique_id;
	private String tracker_id;
	private Date	updated_time;
	private Date	created_time;
	private String	crif_api_response_xml;
	private String crif_api_request_xml;
	private String crif_final_api_response_xml;
	private String crif_final_api_request_xml;
	private String crif_html_response;
	private String criff_byte_arr;
	private String request_info_json;
	private String response_header_info_json;
	private String response_error_info_json;
	private String miscellaneous_info_json;
	private String personal_info_variation_json;
	private String remarks;
	private String serviceType;
	private Set<CriffScoreStatus> criffScoreStatus;
	private CriffScore criffscore;
	private GrpResponseSummary grpResponseNormalSummary;
	private IndvResponseNormalSummary indvNormalSummary;
	private Set<CriffLoanDetails> criffLoanDetails;
	private CriffDrivedAttribute criffDerivedAttributes;
	private PrimaryAccountSummary primaryaccountsummary;
	private SecondaryAccountSummary secondaryAccountSummary;
	private Set<IndvResponseDetails> indvResponseDetails;
	private IndvPrimarySummary indvPrimarySummary;
	private IndvSecondarySummary indvSecondarySummary;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_LOG_SQC")
	@SequenceGenerator(name="IB_CRF_LOG_SQC", sequenceName = "IB_CRF_LOG_SQC", allocationSize = 1)
	@Column(name="REQUEST_UNIQUE_ID")
	public Long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(Long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs",cascade=CascadeType.ALL) 
	public CriffScore getCriffscore() {
		return criffscore;
	}
	public void setCriffscore(CriffScore criffscore) {
		this.criffscore = criffscore;
	}

	@OneToMany(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs" ,cascade=CascadeType.ALL)
	public Set<IndvResponseDetails> getIndvResponseDetails() {
		return indvResponseDetails;
	}
	public void setIndvResponseDetails(Set<IndvResponseDetails> indvResponseDetails) {
		this.indvResponseDetails = indvResponseDetails;
	}

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs" ,cascade=CascadeType.ALL) 
	public PrimaryAccountSummary getPrimaryaccountsummary() {
		if(primaryaccountsummary!=null)
		{
			primaryaccountsummary.setCrifdetaillogs(this);
		}
		return primaryaccountsummary;
	}

	public void setPrimaryaccountsummary(PrimaryAccountSummary primaryaccountsummary) {
		this.primaryaccountsummary = primaryaccountsummary;
	}

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs" ,cascade=CascadeType.ALL) 
	public CriffDrivedAttribute getCriffDerivedAttributes() {
		return criffDerivedAttributes;
	}
	public void setCriffDerivedAttributes(CriffDrivedAttribute criffDerivedAttributes) {
		this.criffDerivedAttributes = criffDerivedAttributes;
	}

	@OneToMany(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs" ,cascade=CascadeType.ALL)
	public Set<CriffLoanDetails> getCriffLoanDetails() {
		return criffLoanDetails;
	}
	public void setCriffLoanDetails(Set<CriffLoanDetails> criffLoanDetails) {
		this.criffLoanDetails = criffLoanDetails;
	}

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs" ,cascade=CascadeType.ALL) 
	public IndvResponseNormalSummary getIndvNormalSummary() {
		return indvNormalSummary;
	}
	public void setIndvNormalSummary(IndvResponseNormalSummary indvNormalSummary) {
		this.indvNormalSummary = indvNormalSummary;
	}

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs" ,cascade=CascadeType.ALL) 
	public GrpResponseSummary getGrpResponseNormalSummary() {
		return grpResponseNormalSummary;
	}
	public void setGrpResponseNormalSummary(GrpResponseSummary grpResponseNormalSummary) {
		this.grpResponseNormalSummary = grpResponseNormalSummary;
	}

	@OneToMany(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs" ,cascade=CascadeType.ALL) 
	public Set<CriffScoreStatus> getCriffScoreStatus() {
		return criffScoreStatus;
	}
	public void setCriffScoreStatus(Set<CriffScoreStatus> criffScoreStatus) {
		this.criffScoreStatus = criffScoreStatus;
	}

	@Column(name="TRACKER_ID")
	public String getTracker_id() {
		return tracker_id;
	}
	public void setTracker_id(String tracker_id) {
		this.tracker_id = tracker_id;
	}
	@Column(name="UPDATED_TIME")
	public Date getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}
	@Column(name="CREATED_TIME")
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	@Column(name="CRIF_API_RESPONSE_XML",columnDefinition="CLOB") 
	@Lob 
	public String getCrif_api_response_xml() {
		return crif_api_response_xml;
	}
	public void setCrif_api_response_xml(String crif_api_response_xml) {
		this.crif_api_response_xml = crif_api_response_xml;
	}
	@Column(name="CRIF_API_REQUEST_XML",columnDefinition="CLOB") 
	@Lob 
	public String getCrif_api_request_xml() {
		return crif_api_request_xml;
	}
	public void setCrif_api_request_xml(String crif_api_request_xml) {
		this.crif_api_request_xml = crif_api_request_xml;
	}
	@Column(name="CRIF_FINAL_API_RESPONSE_XML",columnDefinition="CLOB") 
	@Lob 
	public String getCrif_final_api_response_xml() {
		return crif_final_api_response_xml;
	}
	public void setCrif_final_api_response_xml(String crif_final_api_response_xml) {
		this.crif_final_api_response_xml = crif_final_api_response_xml;
	}
	@Column(name="CRIF_FINAL_API_REQUEST_XML",columnDefinition="CLOB") 
	@Lob 
	public String getCrif_final_api_request_xml() {
		return crif_final_api_request_xml;
	}
	public void setCrif_final_api_request_xml(String crif_final_api_request_xml) {
		this.crif_final_api_request_xml = crif_final_api_request_xml;
	}
	@Column(name="CRIF_HTML_RESPONSE",columnDefinition="CLOB") 
	@Lob 
	public String getCrif_html_response() {
		return crif_html_response;
	}
	public void setCrif_html_response(String crif_html_response) {
		this.crif_html_response = crif_html_response;
	}
	@Column(name="REQUEST_INFO_JSON",columnDefinition="CLOB") 
	@Lob 
	public String getRequest_info_json() {
		return request_info_json;
	}
	public void setRequest_info_json(String request_info_json) {
		this.request_info_json = request_info_json;
	}
	@Column(name="RESPONSE_HEADER_INFO_JSON",columnDefinition="CLOB") 
	@Lob 
	public String getResponse_header_info_json() {
		return response_header_info_json;
	}
	public void setResponse_header_info_json(String response_header_info_json) {
		this.response_header_info_json = response_header_info_json;
	}
	@Column(name="RESPONSE_ERROR_INFO_JSON",columnDefinition="CLOB") 
	@Lob 
	public String getResponse_error_info_json() {
		return response_error_info_json;
	}
	public void setResponse_error_info_json(String response_error_info_json) {
		this.response_error_info_json = response_error_info_json;
	}
	@Column(name="MISCELLANEOUS_INFO_JSON",columnDefinition="CLOB") 
	@Lob 
	public String getMiscellaneous_info_json() {
		return miscellaneous_info_json;
	}
	public void setMiscellaneous_info_json(String miscellaneous_info_json) {
		this.miscellaneous_info_json = miscellaneous_info_json;
	}
	@Column(name="PERSONAL_INFO_VARIATION_JSON",columnDefinition="CLOB") 
	@Lob 
	public String getPersonal_info_variation_json() {
		return personal_info_variation_json;
	}
	public void setPersonal_info_variation_json(String personal_info_variation_json) {
		this.personal_info_variation_json = personal_info_variation_json;
	}
	@Column(name="REMARKS")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name="SERVICETYPE")
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	@Column(name="CRIFF_BYTE_ARR",columnDefinition="CLOB") 
	@Lob 
	public String getCriff_byte_arr() {
		return criff_byte_arr;
	}
	public void setCriff_byte_arr(String criff_byte_arr) {
		this.criff_byte_arr = criff_byte_arr;
	}
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs" ,cascade=CascadeType.ALL)
	public SecondaryAccountSummary getSecondaryAccountSummary() {
		if(secondaryAccountSummary!=null)
		{
			secondaryAccountSummary.setCrifdetaillogs(this);
		}
		return secondaryAccountSummary;
	}
	public void setSecondaryAccountSummary(SecondaryAccountSummary secondaryAccountSummary) {
		this.secondaryAccountSummary = secondaryAccountSummary;
	}
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs" ,cascade=CascadeType.ALL)
	public IndvPrimarySummary getIndvPrimarySummary() {
		return indvPrimarySummary;
	}
	public void setIndvPrimarySummary(IndvPrimarySummary indvPrimarySummary) {
		this.indvPrimarySummary = indvPrimarySummary;
	}
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "crifdetaillogs" ,cascade=CascadeType.ALL)
	public IndvSecondarySummary getIndvSecondarySummary() {
		return indvSecondarySummary;
	}
	public void setIndvSecondarySummary(IndvSecondarySummary indvSecondarySummary) {
		this.indvSecondarySummary = indvSecondarySummary;
	}
	@Override
	public String toString() {
		return "CriffDetailLogs [request_unique_id=" + request_unique_id + ", tracker_id=" + tracker_id
				+ ", updated_time=" + updated_time + ", created_time=" + created_time + ", crif_api_response_xml="
				+ crif_api_response_xml + ", crif_api_request_xml=" + crif_api_request_xml
				+ ", crif_final_api_response_xml=" + crif_final_api_response_xml + ", crif_final_api_request_xml="
				+ crif_final_api_request_xml + ", crif_html_response=" + crif_html_response + ", criff_byte_arr="
				+ criff_byte_arr + ", request_info_json=" + request_info_json + ", response_header_info_json="
				+ response_header_info_json + ", response_error_info_json=" + response_error_info_json
				+ ", miscellaneous_info_json=" + miscellaneous_info_json + ", personal_info_variation_json="
				+ personal_info_variation_json + ", remarks=" + remarks + ", serviceType=" + serviceType
				+ ", criffScoreStatus=" + criffScoreStatus + ", criffscore=" + criffscore
				+ ", grpResponseNormalSummary=" + grpResponseNormalSummary + ", indvNormalSummary=" + indvNormalSummary
				+ ", criffLoanDetails=" + criffLoanDetails + ", criffDerivedAttributes=" + criffDerivedAttributes
				+ ", primaryaccountsummary=" + primaryaccountsummary + ", secondaryAccountSummary="
				+ secondaryAccountSummary + ", indvResponseDetails=" + indvResponseDetails + ", indvPrimarySummary="
				+ indvPrimarySummary + ", indvSecondarySummary=" + indvSecondarySummary + "]";
	}

	

		
}
