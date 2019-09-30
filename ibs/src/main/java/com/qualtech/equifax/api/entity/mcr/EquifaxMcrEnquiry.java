package com.qualtech.equifax.api.entity.mcr;

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

import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;


@Entity
@Table(name="IB_EQ_M_ENQRS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxMcrEnquiry  implements Serializable

{
		 /**
	 * 
	 */
	private static final long serialVersionUID = 240037543311702610L;
	
	@Column
	private String inquiry_date;
	@Column
	private String institution;
	@Column
	private String  inquiry_time;
	@Column
	private String  sequence;
	@Column
	private String  request_purpose;
	@Column
	private String  amount;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_ENQUIRY_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_ENQUIRY_SQC", sequenceName = "IB_EQ_MCR_ENQUIRY_SQC", allocationSize = 1)
	private int  inquiry_id;
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxMcrAllDetaills equifaxMcrDetailsLogs;
	

	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public EquifaxMcrAllDetaills getEquifaxMcrDetailsLogs() {
		return equifaxMcrDetailsLogs;
	}
	public void setEquifaxMcrDetailsLogs(EquifaxMcrAllDetaills equifaxMcrDetailsLogs) {
		this.equifaxMcrDetailsLogs = equifaxMcrDetailsLogs;
	}
	
	public String getInquiry_date() {
		return inquiry_date;
	}
	public void setInquiry_date(String inquiry_date) {
		this.inquiry_date = inquiry_date;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	public String getInquiry_time() {
		return inquiry_time;
	}
	public void setInquiry_time(String inquiry_time) {
		this.inquiry_time = inquiry_time;
	}
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getRequest_purpose() {
		return request_purpose;
	}
	public void setRequest_purpose(String request_purpose) {
		this.request_purpose = request_purpose;
	}
	public int getInquiry_id() {
		return inquiry_id;
	}
	public void setInquiry_id(int inquiry_id) {
		this.inquiry_id = inquiry_id;
	}
}
