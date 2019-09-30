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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;



@Entity
@Table(name="IB_EQ_E_VOTER_RES")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxEVDRVoterResponse 
{
  
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_E_VOTER_RES_SQC")
	@SequenceGenerator(name="IB_EQ_E_VOTER_RES_SQC", sequenceName = "IB_EQ_E_VOTER_RES_SQC", allocationSize = 1)
	@Column(name="VOTER_RESPONSE_ID")
	private Long	voter_response_id;
	@Column(name="RETURN_CODE")
    private String 	return_code;
	@Column(name="VOTER")
    private String	voter;
	@Column(name="TITLE")
    private String 	title;
	@OneToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID")
	private EquifaxEvdrAllDetails  equifaxevdrdetails_logs; 
    
    public Long getVoter_response_id() {
	return voter_response_id;
	}
	public void setVoter_response_id(Long voter_response_id) {
		this.voter_response_id = voter_response_id;
	}
//	@Column(name="RETURN_CODE")
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
//	@Column(name="VOTER")
	public String getVoter() {
		return voter;
	}
	public void setVoter(String voter) {
		this.voter = voter;
	}
//	@Column(name="TITLE")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
 
	public EquifaxEvdrAllDetails getEquifaxevdrdetails_logs() {
		return equifaxevdrdetails_logs;
	}
	public void setEquifaxevdrdetails_logs(EquifaxEvdrAllDetails equifaxevdrdetails_logs) {
		this.equifaxevdrdetails_logs = equifaxevdrdetails_logs;
	}
	
	
}
