package com.qualtech.equifax.api.entity;

import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.common.EquifaxIdentityInfo;
import com.qualtech.equifax.api.entity.common.EquifaxOtherKeyInd;
import com.qualtech.equifax.api.entity.mcr.EquifaxMCRAccountSummary;
import com.qualtech.equifax.api.entity.mcr.EquifaxMCRIncomeDetails;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrAccountDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrAcntDtlMfiAddress;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrAcntDtlMfiIdentification;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrAdditionalMFIDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrAddressDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrEnquiry;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrFamilyDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrHistory24MonthsDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrIdentityDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrKeyPersonDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrNomineeDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrPersonalDetail;
import com.qualtech.equifax.api.entity.mcr.EquifaxMcrPhoneDetails;



@Table(name="IB_EQ_M_DTLS_LOGS")
@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxMcrAllDetaills{
	/**
	 * 
	 */
	@Column
	private String tracker_id;
	@Column
	private Date updated_time;
	@Column
	private Date created_time;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_SQC", sequenceName = "IB_EQ_MCR_SQC", allocationSize = 1)
	@Column(name="REQUEST_UNIQUE_ID")
	@JsonIgnore
	private int request_unique_id;
	
	@Column
	private String response_date;
	@Column
	private String response_time;
	@Column(name="RESPONSE_ORDER_NUM")
	private String response_order_no;
	@Column(name="Member_Ref_Number")
	private String memberReferenceNumber; 
	@Column(name="member_id")
	private String memberId;
	@Column
	private String remarks;
	
	@Column
	@Lob
	private String response_xml;
	@Column
	@Lob
	private String request_xml;
	@Column
	@Lob
	private String request_info_json;
	@Column
	@Lob
	private String response_header_info_json;
	@Column
	@Lob
	private String response_error_info_json;
	@Column
	@Lob
	private String miscellaneous_info_json;
	@Column
	@Lob
	private String response_id_contact_info_json;
	
	@Column(name="FINAL_HTML_DATA")
	@Lob
	private String htmlData; 
	@Column(name="BYTE_ARRAY_STRING")
	@Lob
	private String byteArrayData; 
	@Column
	@Lob
	private String disclaimer;
	@Column(name="REQUEST_JSON")
	@Lob
	private String requestJson; 
	@Column(name="RESPONSE_JSON")
	@Lob
	private String responseJson;
	
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "equifaxmcrDetailslogs" ,cascade=CascadeType.ALL)
	private EquifaxMCRAccountSummary quifaxmcrAccountSummarry;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrEnquiry> equifaxMcrenquiries;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMCRIncomeDetails> equifaxMcrIncomeDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrAccountDetail> equifaxMcrAccountDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrFamilyDetail> equifaxMcrFamilyDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrIdentityDetail> equifaxMcrIdentityDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrAddressDetail> equifaxMcrAddressDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrPersonalDetail> equifaxMcrPersonalDetails;
	//@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	@Transient
	private List<EquifaxMcrPhoneDetails> equifaxMcrPhoneDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrAdditionalMFIDetail>  equifaxMcrAdditionalMFIDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrHistory24MonthsDetail>history24MonthsDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrKeyPersonDetail> equifaxMcrKeyPersonDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrNomineeDetail> equifaxMcrNomineeDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrAcntDtlMfiAddress> equifaxMcrAcntDtlMfiAddresses;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private List<EquifaxMcrAcntDtlMfiIdentification> equifaxMcrAcntDtlMfiIdentification;
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private EquifaxIdentityInfo equifaxIdentityInfo;
	//@OneToOne(fetch=FetchType.LAZY,mappedBy = "equifaxmcrDetailslogs" ,cascade=CascadeType.ALL)
	//private EquifaxMCRIdAndContactDetails mcrIdAndContactDetails;
	//@OneToOne(fetch=FetchType.LAZY,mappedBy = "equifaxmcrDetailslogs" ,cascade=CascadeType.ALL)
	//private EquifaxPersonalInfo equifaxPersonalInfo;
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	private EquifaxOtherKeyInd equifaxOtherKeyInd;
	
	
	@Column(name="PDFPATH")
	private String pdfpath;
	
	
	
	
	
	
	public String getPdfpath() {
		return pdfpath;
	}
	public void setPdfpath(String pdfpath) {
		this.pdfpath = pdfpath;
	}
	public String getMemberReferenceNumber() {
		return memberReferenceNumber;
	}
	public void setMemberReferenceNumber(String memberReferenceNumber) {
		this.memberReferenceNumber = memberReferenceNumber;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public EquifaxOtherKeyInd getEquifaxOtherKeyInd() {
		return equifaxOtherKeyInd;
	}
	public void setEquifaxOtherKeyInd(EquifaxOtherKeyInd equifaxOtherKeyInd) {
		this.equifaxOtherKeyInd = equifaxOtherKeyInd;
	}
	public EquifaxOtherKeyInd getEquifaxMcrOtherKeyInd() {
		return equifaxOtherKeyInd;
	}
	public void setEquifaxMcrOtherKeyInd(EquifaxOtherKeyInd equifaxOtherKeyInd) {
		this.equifaxOtherKeyInd = equifaxOtherKeyInd;
	}
	public List<EquifaxMcrPhoneDetails> getEquifaxMcrPhoneDetails() {
		return equifaxMcrPhoneDetails;
	}
	public void setEquifaxMcrPhoneDetails(List<EquifaxMcrPhoneDetails> equifaxMcrPhoneDetails) {
		this.equifaxMcrPhoneDetails = equifaxMcrPhoneDetails;
	}
	public List<EquifaxMcrHistory24MonthsDetail> getHistory24MonthsDetails() {
		return history24MonthsDetails;
	}
	public void setHistory24MonthsDetails(List<EquifaxMcrHistory24MonthsDetail> history24MonthsDetails) {
		this.history24MonthsDetails = history24MonthsDetails;
	}
	public List<EquifaxMcrKeyPersonDetail> getEquifaxMcrKeyPersonDetails() {
		return equifaxMcrKeyPersonDetails;
	}
	public void setEquifaxMcrKeyPersonDetails(List<EquifaxMcrKeyPersonDetail> equifaxMcrKeyPersonDetails) {
		this.equifaxMcrKeyPersonDetails = equifaxMcrKeyPersonDetails;
	}
	public List<EquifaxMcrNomineeDetail> getEquifaxMcrNomineeDetails() {
		return equifaxMcrNomineeDetails;
	}
	public void setEquifaxMcrNomineeDetails(List<EquifaxMcrNomineeDetail> equifaxMcrNomineeDetails) {
		this.equifaxMcrNomineeDetails = equifaxMcrNomineeDetails;
	}
	public List<EquifaxMcrAcntDtlMfiAddress> getEquifaxMcrAcntDtlMfiAddresses() {
		return equifaxMcrAcntDtlMfiAddresses;
	}
	public void setEquifaxMcrAcntDtlMfiAddresses(List<EquifaxMcrAcntDtlMfiAddress> equifaxMcrAcntDtlMfiAddresses) {
		this.equifaxMcrAcntDtlMfiAddresses = equifaxMcrAcntDtlMfiAddresses;
	}
	public List<EquifaxMcrAcntDtlMfiIdentification> getEquifaxMcrAcntDtlMfiIdentification() {
		return equifaxMcrAcntDtlMfiIdentification;
	}
	public void setEquifaxMcrAcntDtlMfiIdentification(
			List<EquifaxMcrAcntDtlMfiIdentification> equifaxMcrAcntDtlMfiIdentification) {
		this.equifaxMcrAcntDtlMfiIdentification = equifaxMcrAcntDtlMfiIdentification;
	}
	public List<EquifaxMcrAdditionalMFIDetail> getEquifaxMcrAdditionalMFIDetails() {
		return equifaxMcrAdditionalMFIDetails;
	}
	public void setEquifaxMcrAdditionalMFIDetails(List<EquifaxMcrAdditionalMFIDetail> equifaxMcrAdditionalMFIDetails) {
		this.equifaxMcrAdditionalMFIDetails = equifaxMcrAdditionalMFIDetails;
	}
	public List<EquifaxMcrFamilyDetail> getEquifaxMcrFamilyDetails() {
		return equifaxMcrFamilyDetails;
	}
	public void setEquifaxMcrFamilyDetails(List<EquifaxMcrFamilyDetail> equifaxMcrFamilyDetails) {
		this.equifaxMcrFamilyDetails = equifaxMcrFamilyDetails;
	}
	public List<EquifaxMcrIdentityDetail> getEquifaxMcrIdentityDetails() {
		return equifaxMcrIdentityDetails;
	}
	public void setEquifaxMcrIdentityDetails(List<EquifaxMcrIdentityDetail> equifaxMcrIdentityDetails) {
		this.equifaxMcrIdentityDetails = equifaxMcrIdentityDetails;
	}
	public List<EquifaxMcrAddressDetail> getEquifaxMcrAddressDetails() {
		return equifaxMcrAddressDetails;
	}
	public void setEquifaxMcrAddressDetails(List<EquifaxMcrAddressDetail> equifaxMcrAddressDetails) {
		this.equifaxMcrAddressDetails = equifaxMcrAddressDetails;
	}
	public List<EquifaxMcrPersonalDetail> getEquifaxMcrPersonalDetails() {
		return equifaxMcrPersonalDetails;
	}
	public void setEquifaxMcrPersonalDetails(List<EquifaxMcrPersonalDetail> equifaxMcrPersonalDetails) {
		this.equifaxMcrPersonalDetails = equifaxMcrPersonalDetails;
	}
	public String getResponse_date() {
		return response_date;
	}
	public void setResponse_date(String response_date) {
		this.response_date = response_date;
	}
	public String getResponse_time() {
		return response_time;
	}
	public void setResponse_time(String response_time) {
		this.response_time = response_time;
	}
	public String getResponse_order_no() {
		return response_order_no;
	}
	public void setResponse_order_no(String response_order_no) {
		this.response_order_no = response_order_no;
	}
	public String getRequestJson() {
		return requestJson;
	}
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
	public String getResponseJson() {
		return responseJson;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public EquifaxIdentityInfo getEquifaxIdentityInfo() {
		if(equifaxIdentityInfo!=null){
			equifaxIdentityInfo.setRequest_unique_id(this.request_unique_id);
			equifaxIdentityInfo.setEquifaxMcrDetailsLogs(this);
		}
		return equifaxIdentityInfo;
	}
	public void setEquifaxIdentityInfo(EquifaxIdentityInfo equifaxIdentityInfo) {
		this.equifaxIdentityInfo = equifaxIdentityInfo;
		if(equifaxIdentityInfo!=null){
			equifaxIdentityInfo.setRequest_unique_id(this.request_unique_id);
			equifaxIdentityInfo.setEquifaxMcrDetailsLogs(this);
		}
	}
/*	public EquifaxMCRIdAndContactDetails getMcrIdAndContactDetails() {
		return mcrIdAndContactDetails;
	}
	public void setMcrIdAndContactDetails(EquifaxMCRIdAndContactDetails mcrIdAndContactDetails) {
		this.mcrIdAndContactDetails = mcrIdAndContactDetails;
	}
	public EquifaxPersonalInfo getEquifaxPersonalInfo() {
		return equifaxPersonalInfo;
	}
	public void setEquifaxPersonalInfo(EquifaxPersonalInfo equifaxPersonalInfo) {
		this.equifaxPersonalInfo = equifaxPersonalInfo;
	}*/
	//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	public List<EquifaxMcrAccountDetail> getEquifaxMcrAccountDetails() {
		return equifaxMcrAccountDetails;
	}
	public void setEquifaxMcrAccountDetails(List<EquifaxMcrAccountDetail> equifaxMcrAccountDetails) {
		this.equifaxMcrAccountDetails = equifaxMcrAccountDetails;
	}
	
	   public String getHtmlData() {
		return htmlData;
	}
	   public void setHtmlData(String htmlData) {
		this.htmlData = htmlData;
	}
	   public String getByteArrayData() {
		return byteArrayData;
	}
	   public void setByteArrayData(String byteArrayData) {
		this.byteArrayData = byteArrayData;
	}
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	public List<EquifaxMCRIncomeDetails> getEquifaxMcrIncomeDetails() {
		return equifaxMcrIncomeDetails;
	}
	public void setEquifaxMcrIncomeDetails(List<EquifaxMCRIncomeDetails> equifaxMcrIncomeDetails) {
		this.equifaxMcrIncomeDetails = equifaxMcrIncomeDetails;
	}
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "equifaxMcrDetailsLogs" ,cascade=CascadeType.ALL)
	public List<EquifaxMcrEnquiry> getEquifaxMcrenquiries() {
		return equifaxMcrenquiries;
	}
	public void setEquifaxMcrenquiries(List<EquifaxMcrEnquiry> equifaxMcrenquiries) {
		this.equifaxMcrenquiries = equifaxMcrenquiries;
	}
	
//	@OneToOne(fetch = FetchType.LAZY, mappedBy = "equifaxmcrDetailslogs" ,cascade=CascadeType.ALL)
	public EquifaxMCRAccountSummary getQuifaxmcrAccountSummarry() {
		return quifaxmcrAccountSummarry;
	}
	public void setQuifaxmcrAccountSummarry(EquifaxMCRAccountSummary quifaxmcrAccountSummarry) {
		this.quifaxmcrAccountSummarry = quifaxmcrAccountSummarry;
	}
	
//    @Column(name="TRACKER_ID")
	public String getTracker_id() {
		return tracker_id;
	}
	public void setTracker_id(String tracker_id) {
		this.tracker_id = tracker_id;
	}
//	@Column(name="UPDATED_TIME")
	public Date getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}
//	@Column(name="CREATED_TIME")
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
//	@Column(name="RESPONSE_XML")
	public String getResponse_xml() {
		return response_xml;
	}
	public void setResponse_xml(String response_xml) {
		this.response_xml = response_xml;
	}
//	@Column(name="REQUEST_XML")
	public String getRequest_xml() {
		return request_xml;
	}
	public void setRequest_xml(String request_xml) {
		this.request_xml = request_xml;
	}
//	@Column(name="REQUEST_INFO_JSON")
	public String getRequest_info_json() {
		return request_info_json;
	}
	public void setRequest_info_json(String request_info_json) {
		this.request_info_json = request_info_json;
	}
//	@Column(name="RESPONSE_HEADER_INFO_JSON")
	public String getResponse_header_info_json() {
		return response_header_info_json;
	}
	public void setResponse_header_info_json(String response_header_info_json) {
		this.response_header_info_json = response_header_info_json;
	}
//	@Column(name="RESPONSE_ERROR_INFO_JSON")
	public String getResponse_error_info_json() {
		return response_error_info_json;
	}
	public void setResponse_error_info_json(String response_error_info_json) {
		this.response_error_info_json = response_error_info_json;
	}
//	@Column(name="MISCELLANEOUS_INFO_JSON")
	public String getMiscellaneous_info_json() {
		return miscellaneous_info_json;
	}
	public void setMiscellaneous_info_json(String miscellaneous_info_json) {
		this.miscellaneous_info_json = miscellaneous_info_json;
	}
//	@Column(name="RESPONSE_ID_CONTACT_INFO_JSON")
	public String getResponse_id_contact_info_json() {
		return response_id_contact_info_json;
	}
	public void setResponse_id_contact_info_json(String response_id_contact_info_json) {
		this.response_id_contact_info_json = response_id_contact_info_json;
	}
//	@Column(name="REMARKS")
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(int request_unique_id) {
		this.request_unique_id = request_unique_id;
	}

	
}
