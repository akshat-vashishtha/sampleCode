package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_INDV_REPORTS_RES")
public class INDVReportsJsonRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_INDV_REPORTS_SQC", allocationSize = 1)
	@JsonIgnore
	private int indvRepId;

	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "indvBureauRes" ,cascade=CascadeType.ALL)
	private INDVReportRes indvreport;
	

	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;


	public int getIndvRepId() {
		return indvRepId;
	}


	public void setIndvRepId(int indvRepId) {
		this.indvRepId = indvRepId;
	}


	public INDVReportRes getIndvreport() {
		if(this.indvreport!=null)
		{
			this.indvreport.setIndvBureauRes(this);
		}
		return indvreport;
	}


	public void setIndvreport(INDVReportRes indvreport) {
		this.indvreport = indvreport;
	}


	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}


	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}


	@Override
	public String toString() {
		return "INDVReportsJsonRes [indvRepId=" + indvRepId + ", indvreport=" + indvreport + "]";
	}


}
