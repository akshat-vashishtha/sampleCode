package com.qualtech.equifax.api.entity.evdr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;


@Entity
@Table(name="IB_EQ_E_VOTER_REQ")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxEVDRVoterRequest 
{            
	
	

	@Column(name="VOTER_ID")
	private String voter_id;
	@Column(name="SOURCE")
	private String source;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_E_VOTER_REQ_SQC")
	@SequenceGenerator(name="IB_EQ_E_VOTER_REQ_SQC", sequenceName = "IB_EQ_E_VOTER_REQ_SQC", allocationSize = 1)
	@Column(name="VOTER_REQUEST_ID")
	private Long voter_request_id;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID")
	private EquifaxEvdrAllDetails  equifaxevdrdetails_logs; 
	
	
	
	
   
	//	@Id
//	@Column(name="VOTER_REQUEST_ID")
	public Long getVoter_request_id() {
		return voter_request_id;
	}
	public void setVoter_request_id(Long voter_request_id) {
		this.voter_request_id = voter_request_id;
	}
  
//	@Column(name="VOTER_ID")
	public String getVoter_id() {
		return voter_id;
	}
	public void setVoter_id(String voter_id) {
		this.voter_id = voter_id;
	}
//	@Column(name="SOURCE")
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public EquifaxEvdrAllDetails getEquifaxevdrdetails_logs() {
		return equifaxevdrdetails_logs;
	}
	public void setEquifaxevdrdetails_logs(EquifaxEvdrAllDetails equifaxevdrdetails_logs) {
		this.equifaxevdrdetails_logs = equifaxevdrdetails_logs;
	}
	
}
