package com.qualtech.equifax.api.entity;

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
import javax.persistence.Transient;

import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRAddresInfo;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDREmailDetails;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRIdentity;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRNsdlResponse;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRPanDetails;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRPersonalInfo;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRPhoneInfo;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRVoterDetails;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRVoterRequest;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRVoterResponse;
import com.qualtech.equifax.api.entity.evdr.EquifaxEVDRnsdlRequestEntity;
@Entity
@Table(name="IB_EQ_E_DTLS_LOGS")
public class EquifaxEvdrAllDetails 
{
	
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_EVDR_SQC")
 @SequenceGenerator(name="IB_EQ_EVDR_SQC", sequenceName = "IB_EQ_EVDR_SQC", allocationSize = 1)
 @Column(name="REQUEST_UNIQUE_ID")
 private Long	request_unique_id;	
 @Column(name="CREATED_TIME")
 private Date created_time;

 @Column(name="REMARKS")
 private String	remarks;
 @Column(name="RESPONSE_DATE")
 private String response_date;
 @Column(name="RESPONSE_TIME")
 private String response_time;
 @Column(name="RESPONSE_ORDER_NO")
 private String response_order_no;
 @Column(name="TRACKER_ID")
 private String	tracker_id;
 @Column(name="UPDATED_TIME")
 private Date updated_time;

 
 @OneToMany(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
 private Set<EquifaxEVDRAddresInfo> addresses;
 @Transient
 private Set<EquifaxEVDRIdentity> identies;
 @OneToMany(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
 private Set<EquifaxEVDRPanDetails> evdrPanDetails;
 @OneToMany(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
 private Set<EquifaxEVDRVoterDetails> evdrVoterDetails;
 @OneToMany(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
 private Set<EquifaxEVDRPhoneInfo> evdrPhoneDetails;
 @OneToMany(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
 private Set<EquifaxEVDREmailDetails> evdrEmailDetails;
 @OneToMany(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
 private Set<EquifaxEVDRnsdlRequestEntity> nsdlRequestEntity;
 @OneToMany(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
 private Set<EquifaxEVDRNsdlResponse> nsdlResponseEntity;
 @OneToOne(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
 private EquifaxEVDRPersonalInfo nsdlPersonalInfoEntity;
 @OneToOne(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
 private EquifaxEVDRVoterRequest equifaxevdrvoterrequest;
 @OneToOne(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
 private EquifaxEVDRVoterResponse equifaxevdrvoterresponse; 


 @Column(name="RESPONSE_XML")
 @Lob
 private String response_xml;
 @Column(name="REQUEST_XML")
 @Lob
 private String request_xml;
 @Column(name="REQUEST_INFO_JSON")
 @Lob
 private String request_info_json;
 @Column(name="RESPONSE_ERROR_INFO_JSON")
 @Lob
 private String response_error_info_json;
 @Column(name="DISCLAIMER")
 @Lob
 private String	disclaimer;
 @Column(name="REQUEST_JSON")
 @Lob
 private String request_json;
 @Column(name="RESPONSE_JSON")
 @Lob
 private String response_json;
 @Column(name="HTMLDATA",columnDefinition="CLOB" )
 @Lob
 private String htmlData;
 @Column(name="PDF_BYTE_ARRAY",columnDefinition="CLOB" )
 private String pdf_byte_Array;
 
public String getRequest_json() {
	return request_json;
}
public void setRequest_json(String request_json) {
	this.request_json = request_json;
}
public String getResponse_json() {
	return response_json;
}
public void setResponse_json(String response_json) {
	this.response_json = response_json;
}
public String getHtmlData() {
	return htmlData;
}
public void setHtmlData(String htmlData) {
	this.htmlData = htmlData;
}
public String getPdf_byte_Array() {
	return pdf_byte_Array;
}
public void setPdf_byte_Array(String pdf_byte_Array) {
	this.pdf_byte_Array = pdf_byte_Array;
}
public void setEvdrEmailDetails(Set<EquifaxEVDREmailDetails> evdrEmailDetails) {
	for(EquifaxEVDREmailDetails equifaxEVDREmailDetails:evdrEmailDetails)
	{
		equifaxEVDREmailDetails.setEquifaxevdrdetails_logs(this);
	}
	
	this.evdrEmailDetails = evdrEmailDetails;
}
public Set<EquifaxEVDREmailDetails> getEvdrEmailDetails() {
	return evdrEmailDetails;
}

public Set<EquifaxEVDRPhoneInfo> getEvdrPhoneDetails() {
	for(EquifaxEVDRPhoneInfo equifaxEVDRPhoneInfo:evdrPhoneDetails)
	{
		equifaxEVDRPhoneInfo.setEquifaxevdrdetails_logs(this);
	}
	
	return evdrPhoneDetails;
}
public void setEvdrPhoneDetails(Set<EquifaxEVDRPhoneInfo> evdrPhoneDetails) {
	this.evdrPhoneDetails = evdrPhoneDetails;
}
public Set<EquifaxEVDRVoterDetails> getEvdrVoterDetails() {
	for(EquifaxEVDRVoterDetails equifaxEVDRVoterDetails:evdrVoterDetails)
	{
		equifaxEVDRVoterDetails.setEquifaxevdrdetails_logs(this);
	}
	return evdrVoterDetails;
}
public void setEvdrVoterDetails(Set<EquifaxEVDRVoterDetails> evdrVoterDetails) {
	this.evdrVoterDetails = evdrVoterDetails;
}
public Set<EquifaxEVDRPanDetails> getEvdrPanDetails() {
	for(EquifaxEVDRPanDetails equifaxEVDRPanDetails:evdrPanDetails)
	{
		equifaxEVDRPanDetails.setEquifaxevdrdetails_logs(this);
	}

	return evdrPanDetails;
}
public void setEvdrPanDetails(Set<EquifaxEVDRPanDetails> evdrPanDetails) {
	this.evdrPanDetails = evdrPanDetails;
}
//@Id
// @Column(name="REQUEST_UNIQUE_ID")
 public Long getRequest_unique_id() {
	return request_unique_id;
}
public void setRequest_unique_id(Long request_unique_id) {
	this.request_unique_id = request_unique_id;
}
//@Column(name="TRACKER_ID")
public String getTracker_id() {
	return tracker_id;
}
public void setTracker_id(String tracker_id) {
	this.tracker_id = tracker_id;
}
//@Column(name="UPDATED_TIME")
public Date getUpdated_time() {
	return updated_time;
}
public void setUpdated_time(Date updated_time) {
	this.updated_time = updated_time;
}
//@Column(name="CREATED_TIME")
public Date getCreated_time() {
	return created_time;
}
public void setCreated_time(Date created_time) {
	this.created_time = created_time;
}
//@Column(name="RESPONSE_XML")
public String getResponse_xml() {
	return response_xml;
}
public void setResponse_xml(String response_xml) {
	this.response_xml = response_xml;
}
//@Column(name="REQUEST_XML")
public String getRequest_xml() {
	return request_xml;
}

public void setRequest_xml(String request_xml) {
	this.request_xml = request_xml;
}
//@Column(name="REQUEST_INFO_JSON")
public String getRequest_info_json() {
	return request_info_json;
}
public void setRequest_info_json(String request_info_json) {
	this.request_info_json = request_info_json;
}
//@Column(name="RESPONSE_ERROR_INFO_JSON")
public String getResponse_error_info_json() {
	return response_error_info_json;
}
public void setResponse_error_info_json(String response_error_info_json) {
	this.response_error_info_json = response_error_info_json;
}
//@Column(name="DISCLAIMER")
public String getDisclaimer() {
	return disclaimer;
}
public void setDisclaimer(String disclaimer) {
	this.disclaimer = disclaimer;
}
//@Column(name="REMARKS")
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
//@Column(name="RESPONSE_DATE")
public String getResponse_date() {
	return response_date;
}
public void setResponse_date(String response_date) {
	this.response_date = response_date;
}
//@Column(name="RESPONSE_TIME")
public String getResponse_time() {
	return response_time;
}
public void setResponse_time(String response_time) {
	this.response_time = response_time;
}
//@Column(name="RESPONSE_ORDER_NO")
public String getResponse_order_no() {
	return response_order_no;
}
public void setResponse_order_no(String response_order_no) {
	this.response_order_no = response_order_no;
}


public Set<EquifaxEVDRAddresInfo> getAddresses() {
	for(EquifaxEVDRAddresInfo equifaxEVDRAddresInfo:addresses)
	{
		equifaxEVDRAddresInfo.setEquifaxevdrdetails_logs(this);
	}
		
	return addresses;
}
public void setAddresses(Set<EquifaxEVDRAddresInfo> addresses) {
	this.addresses = addresses;
}
public Set<EquifaxEVDRIdentity> getIdenties() {
	
	return identies;
}
public void setIdenties(Set<EquifaxEVDRIdentity> identies) {
	this.identies = identies;
}
//@OneToOne(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
public void setNsdlRequestEntity(Set<EquifaxEVDRnsdlRequestEntity> nsdlRequestEntity) {
	for(EquifaxEVDRnsdlRequestEntity equifaxEVDRnsdlRequestEntity:nsdlRequestEntity)
	{
		equifaxEVDRnsdlRequestEntity.setEquifaxevdrdetails_logs(this);
	}
	
	
	this.nsdlRequestEntity = nsdlRequestEntity;
}
public Set<EquifaxEVDRnsdlRequestEntity> getNsdlRequestEntity() {
	return nsdlRequestEntity;
}

//@OneToOne(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
public Set<EquifaxEVDRNsdlResponse> getNsdlResponseEntity() {
	for(EquifaxEVDRNsdlResponse equifaxEVDRNsdlResponse:nsdlResponseEntity)
	{
		equifaxEVDRNsdlResponse.setEquifaxevdrdetails_logs(this);
	}
	
	return nsdlResponseEntity;
}
public void setNsdlResponseEntity(Set<EquifaxEVDRNsdlResponse> nsdlResponseEntity) {
	this.nsdlResponseEntity = nsdlResponseEntity;
}

//@OneToOne(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
public EquifaxEVDRPersonalInfo getNsdlPersonalInfoEntity() {
	return nsdlPersonalInfoEntity;
}
public void setNsdlPersonalInfoEntity(EquifaxEVDRPersonalInfo nsdlPersonalInfoEntity) {
	this.nsdlPersonalInfoEntity = nsdlPersonalInfoEntity;
}

//@OneToOne(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)	
public EquifaxEVDRVoterRequest getEquifaxevdrvoterrequest() {
	return equifaxevdrvoterrequest;
}
public void setEquifaxevdrvoterrequest(EquifaxEVDRVoterRequest equifaxevdrvoterrequest) {
	this.equifaxevdrvoterrequest = equifaxevdrvoterrequest;
}

//@OneToOne(fetch=FetchType.LAZY,mappedBy="equifaxevdrdetails_logs",cascade=CascadeType.ALL)
public EquifaxEVDRVoterResponse getEquifaxevdrvoterresponse() {
	return equifaxevdrvoterresponse;
}
public void setEquifaxevdrvoterresponse(EquifaxEVDRVoterResponse equifaxevdrvoterresponse) {
	this.equifaxevdrvoterresponse = equifaxevdrvoterresponse;
}

@Override
public String toString() {
	return "EquifaxEvdrAllDetails [request_unique_id=" + request_unique_id + ", created_time=" + created_time
			+ ", response_xml=" + response_xml + ", request_xml=" + request_xml + ", request_info_json="
			+ request_info_json + ", response_error_info_json=" + response_error_info_json + ", disclaimer="
			+ disclaimer + ", remarks=" + remarks + ", response_date=" + response_date + ", response_time="
			+ response_time + ", response_order_no=" + response_order_no + ", tracker_id=" + tracker_id
			+ ", updated_time=" + updated_time + ", request_json=" + request_json + ", response_json="
			+ response_json + ", htmlData=" + htmlData + ", pdf_byte_Array=" + pdf_byte_Array + ", addresses="
			+ addresses + ", identies=" + identies + ", evdrPanDetails=" + evdrPanDetails + ", evdrVoterDetails="
			+ evdrVoterDetails + ", evdrPhoneDetails=" + evdrPhoneDetails + ", evdrEmailDetails=" + evdrEmailDetails
			+ ", nsdlRequestEntity=" + nsdlRequestEntity + ", nsdlResponseEntity=" + nsdlResponseEntity
			+ ", nsdlPersonalInfoEntity=" + nsdlPersonalInfoEntity + ", equifaxevdrvoterrequest="
			+ equifaxevdrvoterrequest + ", equifaxevdrvoterresponse=" + equifaxevdrvoterresponse + "]";
}

}
