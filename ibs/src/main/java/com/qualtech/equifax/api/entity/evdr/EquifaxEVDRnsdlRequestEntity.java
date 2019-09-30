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
@Table(name="IB_EQ_E_NSDL_REQ")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxEVDRnsdlRequestEntity 
{
	
	
	
	@Column(name="SOURCE")
    private String source;
	@Column(name="PAN_NUMBER")
    private String pan_number;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_E_NSDL_REQ_SQC")
	@SequenceGenerator(name="IB_EQ_E_NSDL_REQ_SQC", sequenceName = "IB_EQ_E_NSDL_REQ_SQC", allocationSize = 1)
	@Column(name="NSDL_REQUEST_ID")
    private Long nsdl_request_id;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	private EquifaxEvdrAllDetails  equifaxevdrdetails_logs;
	  
   
    public EquifaxEvdrAllDetails getEquifaxevdrdetails_logs() {
		return equifaxevdrdetails_logs;
	}

	public void setEquifaxevdrdetails_logs(EquifaxEvdrAllDetails equifaxevdrdetails_logs) {
		this.equifaxevdrdetails_logs = equifaxevdrdetails_logs;
	}

	//	@Column(name="SOURCE")
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
//	@Column(name="PAN_NUMBER")
	public String getPan_number() {
		return pan_number;
	}
	public void setPan_number(String pan_number) {
		this.pan_number = pan_number;
	}
	
//	@Id
//	@Column(name="NSDL_REQUEST_ID")
	public Long getNsdl_request_id() {
		return nsdl_request_id;
	}
	public void setNsdl_request_id(Long nsdl_request_id) {
		this.nsdl_request_id = nsdl_request_id;
	}
	

}
