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
@Table(name="IB_EQ_E_ADRS_INFO")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxEVDRAddresInfo 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_E_ADRSS_SQC")
	@SequenceGenerator(name="IB_EQ_E_ADRSS_SQC", sequenceName = "IB_EQ_E_ADRSS_SQC", allocationSize = 1)
	@Column(name="ADDRESS_INFO_ID")
	  private Long	 address_info_id;
	  @Column(name="ADDRESS")
	  private String address;
	  @Column(name="REPORTED_DATE")
	  private String reported_date;
	  @Column(name="STATE")
	  private String state;
	  @Column(name="POSTAL")
	  private String postal;
	  @Column(name="TYPE")
	  private String type;
	  @Transient
	  private String seq;
	  @JsonIgnore
	  @ManyToOne
	  @JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	  private EquifaxEvdrAllDetails  equifaxevdrdetails_logs;
	  
	  
	  
	public String getSeq() {
		return seq;
	}
	  public void setSeq(String seq) {
		this.seq = seq;
	}
	
    public EquifaxEvdrAllDetails getEquifaxevdrdetails_logs() {
		return equifaxevdrdetails_logs;
	}
	public void setEquifaxevdrdetails_logs(EquifaxEvdrAllDetails equifaxevdrdetails_logs) {
		this.equifaxevdrdetails_logs = equifaxevdrdetails_logs;
	}
	
//	@Id
//	@Column(name="ADDRESS_INFO_ID")
	public Long getAddress_info_id() {
		return address_info_id;
	}
	public void setAddress_info_id(Long address_info_id) {
		this.address_info_id = address_info_id;
	}
	
//	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
//	@Column(name="REPORTED_DATE")
	public String getReported_date() {
		return reported_date;
	}
	public void setReported_date(String reported_date) {
		this.reported_date = reported_date;
	}
//	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
//	@Column(name="POSTAL")
	public String getPostal() {
		return postal;
	}
	
	public void setPostal(String postal) {
		this.postal = postal;
	}
//	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
