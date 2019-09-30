package com.qualtech.equifax.api.entity.pcs;

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

import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;

@Entity
@Table(name="IB_EQ_P_RCNT_ACTVTY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsRecentActivities 
{
		  @Column(name="TOTAL_ENQUIRIES")
		  private String total_enquiries;
		  @Column(name="ACCOUNTS_UPDATED")
		  private String accounts_updated;
		  @Column(name="ACCOUNTS_DELIQUENT")
		  private String accounts_deliquent;
		  @Column(name="ACCOUNTS_OPENED")
		  private String accounts_opened;
		  @Id
		  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_PCS_RECENTACTIVITY_SQC")
		  @SequenceGenerator(name="IB_EQ_PCS_RECENTACTIVITY_SQC", sequenceName = "IB_EQ_PCS_RECENTACTIVITY_SQC", allocationSize = 1)
		  @Column(name="ACTIVITY_ID")
		  private Long activity_id;
		  @OneToOne
		  @JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
		  @JsonIgnore
	      private EquifaxPcsAllDetails equifaxpcDetailslogs;
		  
	    
		public EquifaxPcsAllDetails getEquifaxpcDetailslogs() {
			return equifaxpcDetailslogs;
		}
		public void setEquifaxpcDetailslogs(EquifaxPcsAllDetails equifaxpcDetailslogs) {
			this.equifaxpcDetailslogs = equifaxpcDetailslogs;
		}
		
		public String getTotal_enquiries() {
			return total_enquiries;
		}
		public void setTotal_enquiries(String total_enquiries) {
			this.total_enquiries = total_enquiries;
		}
		public String getAccounts_updated() {
			return accounts_updated;
		}
		public void setAccounts_updated(String accounts_updated) {
			this.accounts_updated = accounts_updated;
		}
		public String getAccounts_deliquent() {
			return accounts_deliquent;
		}
		public void setAccounts_deliquent(String accounts_deliquent) {
			this.accounts_deliquent = accounts_deliquent;
		}
		public String getAccounts_opened() {
			return accounts_opened;
		}
		public void setAccounts_opened(String accounts_opened) {
			this.accounts_opened = accounts_opened;
		}
		public Long getActivity_id() {
			return activity_id;
		}
		public void setActivity_id(Long activity_id) {
			this.activity_id = activity_id;
		}
	
}
