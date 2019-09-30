package com.qualtech.equifax.api.entity.pcs;

import java.io.Serializable;

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

import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;

@Entity
@Table(name="IB_EQ_P_ENQRS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsEnquiry implements Serializable
{
	private static final long serialVersionUID = -4460040913067378247L;
	@Column(name="ENQUIRY_DATE")
	private String	enquiry_date;
	@Column(name="INSTITUTION")
	private String	institution;
	@Column(name="ENQUIRY_TIME")
	private String	enquiry_time;
	@Column(name="ENQUIRY_SEQ")
	private String	enquiry_sequence;
	@Column(name="REQUEST_PURPOSE")
	private String request_purpose;
	@Column(name="AMOUNT")
	private String amount;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_PCS_ENQUIRY_SQC")
	@SequenceGenerator(name="IB_EQ_PCS_ENQUIRY_SQC", sequenceName = "IB_EQ_PCS_ENQUIRY_SQC", allocationSize = 1)
	@Column(name="INQUIRY_ID")
	private Long inquiry_id;
	
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxPcsAllDetails   equifaxPcsAllDetails;
	
	
	
	
	public EquifaxPcsAllDetails getEquifaxPcsAllDetails() {
		return equifaxPcsAllDetails;
	}
	public void setEquifaxPcsAllDetails(EquifaxPcsAllDetails equifaxPcsAllDetails) {
		this.equifaxPcsAllDetails = equifaxPcsAllDetails;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public EquifaxPcsAllDetails getEquifaxPcsDetailsLogs() {
		return equifaxPcsAllDetails;
	}
	public void setEquifaxPcsDetailsLogs(EquifaxPcsAllDetails equifaxPcsAllDetails) {
		this.equifaxPcsAllDetails = equifaxPcsAllDetails;
	}
	
	public String getEnquiry_date() {
		return enquiry_date;
	}
	public void setEnquiry_date(String enquiry_date) {
		this.enquiry_date = enquiry_date;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getEnquiry_time() {
		return enquiry_time;
	}
	public void setEnquiry_time(String enquiry_time) {
		this.enquiry_time = enquiry_time;
	}
	public String getEnquiry_sequence() {
		return enquiry_sequence;
	}
	public void setEnquiry_sequence(String enquiry_sequence) {
		this.enquiry_sequence = enquiry_sequence;
	}
	public String getRequest_purpose() {
		return request_purpose;
	}
	public void setRequest_purpose(String request_purpose) {
		this.request_purpose = request_purpose;
	}
	public Long getInquiry_id() {
		return inquiry_id;
	}
	public void setInquiry_id(Long inquiry_id) {
		this.inquiry_id = inquiry_id;
	}
	
}
