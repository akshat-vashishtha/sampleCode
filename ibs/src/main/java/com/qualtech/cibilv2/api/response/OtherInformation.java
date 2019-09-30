package com.qualtech.cibilv2.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;


public class OtherInformation implements Serializable{

	private static final long serialVersionUID = -3201889702355730381L;
	
	//@OneToOne(fetch=FetchType.LAZY,mappedBy = "reportType" ,cascade=CascadeType.ALL)
	private InformationData informationData;
	
	/*@OneToOne
	@JoinColumn(name = "UNIQUEID", nullable = false)
	@JsonIgnore
	private Root root;*/
	
	//private String content;
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_Res_OthrInfo")
	@SequenceGenerator(name = "IB_Res_OthrInfo", sequenceName = "IB_Res_OthrInfo", allocationSize = 1)
	@Column(name = "TID")
	private String tid;*/
	
	public InformationData getInformationData() {
		return informationData;
	}
	public void setInformationData(InformationData informationData) {
		this.informationData = informationData;
	}
	
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}
	
}
