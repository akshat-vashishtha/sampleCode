package com.qualtech.equifax.api.entity;

import java.io.Serializable;
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

import com.qualtech.equifax.api.entity.common.EquifaxOtherKeyInd;
import com.qualtech.equifax.api.entity.pcs.EquifaxHistory48MonthsDetail;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsAccountDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsAccountSummary;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsAddressDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsEmailDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsEnquiry;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsEnquirySummary;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsOthers;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsPanDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsPersonalDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsPhoneDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsRecentActivities;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsScoringElement;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsVoterDetails;

@Entity
@Table(name="IB_EQ_P_DTLS_LOGS")
public class EquifaxPcsAllDetails implements Serializable
{
	private static final long serialVersionUID = -2000808297547260079L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_PCS_SQC")
	@SequenceGenerator(name="IB_EQ_PCS_SQC", sequenceName = "IB_EQ_PCS_SQC", allocationSize = 1)
	@Column(name="REQUEST_UNIQUE_ID")
	@JsonIgnore
	private int request_unique_id;
	@Column(name="TRACKER_ID")
	private String tracker_id;
	@Column(name="UPDATED_TIME")
	private Date updated_time;
	@Column(name="CREATED_TIME")
	private Date created_time;
	@Lob
	@Column(name="RESPONSE_XML")
	private String response_xml;
	@Lob
	@Column(name="REQUEST_XML")
	private String request_xml;
	@Lob
	@Column(name="REQUEST_INFO_JSON")
	private String request_info_json;
	@Lob
	@Column(name="RESPONSE_HEADER_INFO_JSON")
	private String response_header_info_json;
	@Lob
	@Column(name="RESPONSE_ERROR_INFO_JSON")
	private String response_error_info_json;
	@Lob
	@Column(name="MISCELLANEOUS_INFO_JSON")
	private String miscellaneous_info_json;
	@Lob
	@Column(name="RESPONSE_ID_CONTACT_INFO_JSON")
	private String response_id_contact_info_json;
	@Lob
	@Column(name="SCORE_JSON")
	private String score_json;
	@Lob
	@Column(name="FINAL_HTML_DATA")
	private String htmlData;
	@Lob
	@Column(name="BYTE_ARRAY_STRING")
	private String byteArrayData;
	@Lob
	@Column(name="REMARKS")
	private String remarks;
	@Lob
	@Column(name="DISCLAIMER")
	private String disclaimer;
	@Column(name="REQUEST_JSON")
	@Lob
	private String requestJson;
	@Lob
	@Column(name="RESPONSE_JSON")
	private String responseJson;
	@Column(name="RESPONSE_DATE")
	private String response_date;
	@Column(name="RESPONSE_TIME")
	private String response_time;
	@Column(name="RESPONSE_ORDER_NUM")
	private String response_order_no;
	@Column(name="MEMBER_REF_NUMBER")
	private String memberReferenceNumber;
	@Column(name="MEMBER_ID")
	private String memberId;
	@Column(name="PDFPATH")
	private String pdfpath;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxPcsAllDetails" ,cascade=CascadeType.ALL)
	private List<EquifaxPcsScoringElement> pcsScoringElements;
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "equifaxpcDetailslogs" ,cascade=CascadeType.ALL)
	private EquifaxPcsAccountSummary accountSummary;
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "equifaxpcDetailslogs" ,cascade=CascadeType.ALL)
	private EquifaxPcsRecentActivities recentActivities;
	//@Transient
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "equifaxpcDetailslogs" ,cascade=CascadeType.ALL)
	private EquifaxPcsOthers equifaxPcsOther;
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "equifaxpcDetailslogs" ,cascade=CascadeType.ALL)
	private EquifaxPcsEnquirySummary pcsEnquirySummary;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxPcsAllDetails" ,cascade=CascadeType.ALL)
	private List<EquifaxPcsEnquiry> equifaxPcsEnquiries;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxPcsAllDetails" ,cascade=CascadeType.ALL)
	private List<EquifaxPcsAccountDetails> pcsAccountDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxPcsAllDetails" ,cascade=CascadeType.ALL)
	private List<EquifaxPcsAddressDetails> pcsAddressDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxPcsAllDetails" ,cascade=CascadeType.ALL)
	private List<EquifaxPcsPhoneDetails> pcsPhoneDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxPcsAllDetails" ,cascade=CascadeType.ALL)
	private List<EquifaxPcsVoterDetails> pcsVoterDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxPcsAllDetails" ,cascade=CascadeType.ALL)
	private List<EquifaxPcsPanDetails> pcsPanDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxPcsAllDetails" ,cascade=CascadeType.ALL)
	private List<EquifaxPcsPersonalDetails> pcsPersonalDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxPcsAllDetails" ,cascade=CascadeType.ALL)
	private List<EquifaxPcsEmailDetails> pcsEmailDetails;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "equifaxPcsAllDetails" ,cascade=CascadeType.ALL)
	private List<EquifaxHistory48MonthsDetail> history48MonthsDetail;
	
	@Transient
	private EquifaxOtherKeyInd equifaxOtherKeyInd;
	
	
	
	
	
	
	
	
	
	public EquifaxOtherKeyInd getEquifaxOtherKeyInd() {
		return equifaxOtherKeyInd;
	}
	public void setEquifaxOtherKeyInd(EquifaxOtherKeyInd equifaxOtherKeyInd) {
		this.equifaxOtherKeyInd = equifaxOtherKeyInd;
	}
	public String getPdfpath() {
		return pdfpath;
	}
	public void setPdfpath(String pdfpath) {
		this.pdfpath = pdfpath;
	}
	public List<EquifaxHistory48MonthsDetail> getHistory48MonthsDetail() {
		return history48MonthsDetail;
	}
	public void setHistory48MonthsDetail(List<EquifaxHistory48MonthsDetail> history48MonthsDetail) {
		this.history48MonthsDetail = history48MonthsDetail;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberReferenceNumber(String memberReferenceNumber) {
		this.memberReferenceNumber = memberReferenceNumber;
	}
	public String getMemberReferenceNumber() {
		return memberReferenceNumber;
	}

	public String getTracker_id() {
		return tracker_id;
	}
	public void setTracker_id(String tracker_id) {
		this.tracker_id = tracker_id;
	}
	public Date getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public String getResponse_xml() {
		return response_xml;
	}
	public void setResponse_xml(String response_xml) {
		this.response_xml = response_xml;
	}
	public String getRequest_xml() {
		return request_xml;
	}
	public void setRequest_xml(String request_xml) {
		this.request_xml = request_xml;
	}
	public String getRequest_info_json() {
		return request_info_json;
	}
	public void setRequest_info_json(String request_info_json) {
		this.request_info_json = request_info_json;
	}
	public String getResponse_header_info_json() {
		return response_header_info_json;
	}
	public void setResponse_header_info_json(String response_header_info_json) {
		this.response_header_info_json = response_header_info_json;
	}
	public String getResponse_error_info_json() {
		return response_error_info_json;
	}
	public void setResponse_error_info_json(String response_error_info_json) {
		this.response_error_info_json = response_error_info_json;
	}
	public String getMiscellaneous_info_json() {
		return miscellaneous_info_json;
	}
	public void setMiscellaneous_info_json(String miscellaneous_info_json) {
		this.miscellaneous_info_json = miscellaneous_info_json;
	}
	public String getResponse_id_contact_info_json() {
		return response_id_contact_info_json;
	}
	public void setResponse_id_contact_info_json(
			String response_id_contact_info_json) {
		this.response_id_contact_info_json = response_id_contact_info_json;
	}
	public String getScore_json() {
		return score_json;
	}
	public void setScore_json(String score_json) {
		this.score_json = score_json;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	
	public int getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(int request_unique_id) {
		this.request_unique_id = request_unique_id;
	}
	public String getRequestJson() {
		return requestJson;
	}
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}
	public String getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
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
	public List<EquifaxPcsScoringElement> getPcsScoringElements() {
		return pcsScoringElements;
	}
	public void setPcsScoringElements(
			List<EquifaxPcsScoringElement> pcsScoringElements) {
		this.pcsScoringElements = pcsScoringElements;
	}
	public EquifaxPcsAccountSummary getAccountSummary() {
		return accountSummary;
	}
	public void setAccountSummary(EquifaxPcsAccountSummary accountSummary) {
		this.accountSummary = accountSummary;
	}
	public EquifaxPcsRecentActivities getRecentActivities() {
		return recentActivities;
	}
	public void setRecentActivities(EquifaxPcsRecentActivities recentActivities) {
		this.recentActivities = recentActivities;
	}
	public EquifaxPcsOthers getEquifaxPcsOther() {
		return equifaxPcsOther;
	}
	public void setEquifaxPcsOther(EquifaxPcsOthers equifaxPcsOther) {
		this.equifaxPcsOther = equifaxPcsOther;
	}
	public EquifaxPcsEnquirySummary getPcsEnquirySummary() {
		return pcsEnquirySummary;
	}
	public void setPcsEnquirySummary(EquifaxPcsEnquirySummary pcsEnquirySummary) {
		this.pcsEnquirySummary = pcsEnquirySummary;
	}
	public List<EquifaxPcsEnquiry> getEquifaxPcsEnquiries() {
		return equifaxPcsEnquiries;
	}
	public void setEquifaxPcsEnquiries(List<EquifaxPcsEnquiry> equifaxPcsEnquiries) {
		this.equifaxPcsEnquiries = equifaxPcsEnquiries;
	}
	public List<EquifaxPcsAccountDetails> getPcsAccountDetails() {
		return pcsAccountDetails;
	}
	public void setPcsAccountDetails(
			List<EquifaxPcsAccountDetails> pcsAccountDetails) {
		this.pcsAccountDetails = pcsAccountDetails;
	}
	public List<EquifaxPcsAddressDetails> getPcsAddressDetails() {
		return pcsAddressDetails;
	}
	public void setPcsAddressDetails(
			List<EquifaxPcsAddressDetails> pcsAddressDetails) {
		this.pcsAddressDetails = pcsAddressDetails;
	}
	public List<EquifaxPcsPhoneDetails> getPcsPhoneDetails() {
		return pcsPhoneDetails;
	}
	public void setPcsPhoneDetails(List<EquifaxPcsPhoneDetails> pcsPhoneDetails) {
		this.pcsPhoneDetails = pcsPhoneDetails;
	}
	public List<EquifaxPcsVoterDetails> getPcsVoterDetails() {
		return pcsVoterDetails;
	}
	public void setPcsVoterDetails(List<EquifaxPcsVoterDetails> pcsVoterDetails) {
		this.pcsVoterDetails = pcsVoterDetails;
	}
	public List<EquifaxPcsPanDetails> getPcsPanDetails() {
		return pcsPanDetails;
	}
	public void setPcsPanDetails(List<EquifaxPcsPanDetails> pcsPanDetails) {
		this.pcsPanDetails = pcsPanDetails;
	}
	public List<EquifaxPcsPersonalDetails> getPcsPersonalDetails() {
		return pcsPersonalDetails;
	}
	public void setPcsPersonalDetails(
			List<EquifaxPcsPersonalDetails> pcsPersonalDetails) {
		this.pcsPersonalDetails = pcsPersonalDetails;
	}
	public List<EquifaxPcsEmailDetails> getPcsEmailDetails() {
		return pcsEmailDetails;
	}
	public void setPcsEmailDetails(List<EquifaxPcsEmailDetails> pcsEmailDetails) {
		this.pcsEmailDetails = pcsEmailDetails;
	}
	@Override
	public String toString() {
		return "EquifaxPcsDetailsLogs [tracker_id=" + tracker_id
				+ ", updated_time=" + updated_time + ", created_time="
				+ created_time + ", response_xml=" + response_xml
				+ ", request_xml=" + request_xml + ", request_info_json="
				+ request_info_json + ", response_header_info_json="
				+ response_header_info_json + ", response_error_info_json="
				+ response_error_info_json + ", miscellaneous_info_json="
				+ miscellaneous_info_json + ", response_id_contact_info_json="
				+ response_id_contact_info_json + ", score_json=" + score_json
				+ ", htmlData=" + htmlData + ", byteArrayData=" + byteArrayData
				+ ", remarks=" + remarks + ", disclaimer=" + disclaimer
				+ ", request_unique_id=" + request_unique_id + ", requestJson="
				+ requestJson + ", responseJson=" + responseJson
				+ ", response_date=" + response_date + ", response_time="
				+ response_time + ", response_order_no=" + response_order_no
				+ ", pcsScoringElements=" + pcsScoringElements
				+ ", accountSummary=" + accountSummary + ", recentActivities="
				+ recentActivities + ", equifaxPcsOther=" + equifaxPcsOther
				+ ", pcsEnquirySummary=" + pcsEnquirySummary
				+ ", equifaxPcsEnquiries=" + equifaxPcsEnquiries
				+ ", pcsAccountDetails=" + pcsAccountDetails
				+ ", pcsAddressDetails=" + pcsAddressDetails
				+ ", pcsPhoneDetails=" + pcsPhoneDetails + ", pcsVoterDetails="
				+ pcsVoterDetails + ", pcsPanDetails=" + pcsPanDetails
				+ ", pcsPersonalDetails=" + pcsPersonalDetails
				+ ", pcsEmailDetails=" + pcsEmailDetails + "]";
	}
}
