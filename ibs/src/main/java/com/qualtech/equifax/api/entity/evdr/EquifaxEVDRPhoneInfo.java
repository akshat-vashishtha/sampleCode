package com.qualtech.equifax.api.entity.evdr;

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

import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;

@Entity
@Table(name="IB_EQ_E_PHONE_INFO")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxEVDRPhoneInfo 
{
	
	@Column(name="TYPE_CODE")
	private String type_code;
	@Column(name="PHONE_NUMBER")
	private String number;
	@Column(name="REPORTED_DATA")
	private String reported_date;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_E_PHONE_SQC")
	@SequenceGenerator(name="IB_EQ_E_PHONE_SQC", sequenceName = "IB_EQ_E_PHONE_SQC", allocationSize = 1)
	@Column(name="PHONE_INFO_ID")
	private Long phone_info_id;
	@Column(name="SEQ")
	private String phoneSeq;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID")
	private EquifaxEvdrAllDetails equifaxevdrdetails_logs; 
	
	
	public String getPhoneSeq() {
		return phoneSeq;
	}
	public void setPhoneSeq(String phoneSeq) {
		this.phoneSeq = phoneSeq;
	}
	public EquifaxEvdrAllDetails getEquifaxevdrdetails_logs() {
		return equifaxevdrdetails_logs;
	}
	public void setEquifaxevdrdetails_logs(EquifaxEvdrAllDetails equifaxevdrdetails_logs) {
		this.equifaxevdrdetails_logs = equifaxevdrdetails_logs;
	}
	
//	@Column(name="TYPE_CODE")
	public String getType_code() {
		return type_code;
	}
	
	public void setType_code(String type_code) {
		this.type_code = type_code;
	}
	
//	@Column(name="PHONE_NUMBER")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
//	@Column(name="REPORTED_DATA")
public String getReported_date() {
	return reported_date;
}
public void setReported_date(String reported_date) {
	this.reported_date = reported_date;
}
	
//	@Id
//	@Column(name="PHONE_INFO_ID")
	public Long getPhone_info_id() {
		return phone_info_id;
	}
	public void setPhone_info_id(Long phone_info_id) {
		this.phone_info_id = phone_info_id;
	}
	
	
}
