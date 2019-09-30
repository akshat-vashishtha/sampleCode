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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;
@Entity
@Table(name="IB_EQ_E_NSDL_RES")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxEVDRNsdlResponse 
{
	
		  @Id
		  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_E_NSDL_RES_SQC")
		  @SequenceGenerator(name="IB_EQ_E_NSDL_RES_SQC", sequenceName = "IB_EQ_E_NSDL_RES_SQC", allocationSize = 1)
		  @Column(name="NSDL_RESPONSE_ID")
		  private Long nsdl_response_id;
		  @Column(name="FIRST_NAME")
		  private String first_name;
		  @Column(name="PERCENTAGE_MATCH")
		  private String percentage_match;
		  @Column(name="LAST_UPDATED_DATE")
		  private String last_updated_date;
		  @Column(name="RETURN_CODE")
		  private String return_code;
		  @Column(name="TITLE")
		  private String title;
		  @Column(name="PAN")
		  private String pan;
		  @Column(name="RETURN_CODE_DESC")
		  private String return_code_desc;
		  @Column(name="LAST_NAME")
		  private String last_name;
		  @Transient
		  private String panStatus;
		  @Column(name="NSDL_RES_ID")
		  private String nsdlResId;
		  @JsonIgnore
		  @ManyToOne
		  @JoinColumn(name="REQUEST_UNIQUE_ID")
		  private EquifaxEvdrAllDetails equifaxevdrdetails_logs;
		  
		  
		  
		
		public void setNsdlResId(String nsdlResId) {
			this.nsdlResId = nsdlResId;
		}
		  public String getNsdlResId() {
			return nsdlResId;
		}
		  
		  public String getPanStatus() {
			return panStatus;
		}
		  public void setPanStatus(String panStatus) {
			this.panStatus = panStatus;
		}
	  
		public EquifaxEvdrAllDetails getEquifaxevdrdetails_logs() {
			return equifaxevdrdetails_logs;
		}
		public void setEquifaxevdrdetails_logs(EquifaxEvdrAllDetails equifaxevdrdetails_logs) {
			this.equifaxevdrdetails_logs = equifaxevdrdetails_logs;
		}
		
//		@Id  
//		@Column(name="NSDL_RESPONSE_ID")  
	    public Long getNsdl_response_id() {
			return nsdl_response_id;
		}
		public void setNsdl_response_id(Long nsdl_response_id) {
			this.nsdl_response_id = nsdl_response_id;
		}
		
		
//		@Column(name="FIRST_NAME")  
	    public String getFirst_name() {
			return first_name;
		}
		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}
		
//		@Column(name="PERCENTAGE_MATCH")  
	    public String getPercentage_match() {
			return percentage_match;
		}
		public void setPercentage_match(String percentage_match) {
			this.percentage_match = percentage_match;
		}
//		@Column(name="LAST_UPDATED_DATE")
		public String getLast_updated_date() {
			return last_updated_date;
		}
		public void setLast_updated_date(String last_updated_date) {
			this.last_updated_date = last_updated_date;
		}
//		@Column(name="RETURN_CODE")
		public String getReturn_code() {
			return return_code;
		}
		public void setReturn_code(String return_code) {
			this.return_code = return_code;
		}
//		@Column(name="TITLE")
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
//		@Column(name="PAN")
		public String getPan() {
			return pan;
		}
		public void setPan(String pan) {
			this.pan = pan;
		}
//		@Column(name="RETURN_CODE_DESC")
		public String getReturn_code_desc() {
			return return_code_desc;
		}
		public void setReturn_code_desc(String return_code_desc) {
			this.return_code_desc = return_code_desc;
		}
//		@Column(name="LAST_NAME")
		public String getLast_name() {
			return last_name;
		}
		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
}
