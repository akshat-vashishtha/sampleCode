package com.qualtech.cibilv2.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

//@Embeddable
@JsonIgnoreProperties(ignoreUnknown=true)
public class ReportType implements Serializable
{
	private static final long serialVersionUID = -3747935955869205837L;
	
	
	//@OneToOne(fetch=FetchType.LAZY,mappedBy = "reportType" ,cascade=CascadeType.ALL)
	private ReportTypeInformationData informationData;


	public ReportTypeInformationData getInformationData() {
		return informationData;
	}


	public void setInformationData(ReportTypeInformationData informationData) {
		this.informationData = informationData;
	}


	
}
