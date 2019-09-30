package com.qualtech.equifax.api.entity.pcs;

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

import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;

@Entity
@Table(name="IB_EQ_P_ENQ_SMRY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsEnquirySummary 
{
	 private String	past_thirty_days;
	 private String	total;
	 private String	purpose;
	 private String	past_twelve_month;
	 private String	past_twenty_four_month;
	 private String recent;
	 @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_PCS_ENQUIRYSUMMARY_SQC")
	  @SequenceGenerator(name="IB_EQ_PCS_ENQUIRYSUMMARY_SQC", sequenceName = "IB_EQ_PCS_ENQUIRYSUMMARY_SQC", allocationSize = 1)
	  private Long enquiry_id;
	 @OneToOne
	 @JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	 @JsonIgnore
     private  EquifaxPcsAllDetails equifaxpcDetailslogs;
	 

	public EquifaxPcsAllDetails getEquifaxpcDetailslogs() {
		return equifaxpcDetailslogs;
    }
    public void setEquifaxpcDetailslogs(EquifaxPcsAllDetails equifaxpcDetailslogs) {
		this.equifaxpcDetailslogs = equifaxpcDetailslogs;
    }	
 
	public String getPast_thirty_days() {
		return past_thirty_days;
	}
	public void setPast_thirty_days(String past_thirty_days) {
		this.past_thirty_days = past_thirty_days;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getPast_twelve_month() {
		return past_twelve_month;
	}
	public void setPast_twelve_month(String past_twelve_month) {
		this.past_twelve_month = past_twelve_month;
	}
	public String getPast_twenty_four_month() {
		return past_twenty_four_month;
	}
	public void setPast_twenty_four_month(String past_twenty_four_month) {
		this.past_twenty_four_month = past_twenty_four_month;
	}
	public String getRecent() {
		return recent;
	}
	public void setRecent(String recent) {
		this.recent = recent;
	}
	public Long getEnquiry_id() {
		return enquiry_id;
	}
	public void setEnquiry_id(Long enquiry_id) {
		this.enquiry_id = enquiry_id;
	}
 
}
