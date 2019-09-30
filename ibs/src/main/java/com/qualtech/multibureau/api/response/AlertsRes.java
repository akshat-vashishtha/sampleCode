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

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_ALERTS_RES")
public class AlertsRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ALERTS_SQC", allocationSize = 1)
	@JsonIgnore
	private int alertsId;

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "alertsRes" ,cascade=CascadeType.ALL)
	private AlertRes alert;
	
	
	@OneToOne
	@JoinColumn(name = "indvId", nullable = false)
	@JsonIgnore
	private INDVReportRes indvReportRes;


	public int getAlertsId() {
		return alertsId;
	}


	public void setAlertsId(int alertsId) {
		this.alertsId = alertsId;
	}


	public AlertRes getAlert() {
		if(this.alert!=null)
		{
			this.alert.setAlertsRes(this);
		}
		return alert;
	}


	public void setAlert(AlertRes alert) {
		this.alert = alert;
	}


	public INDVReportRes getIndvReportRes() {
		return indvReportRes;
	}


	public void setIndvReportRes(INDVReportRes indvReportRes) {
		this.indvReportRes = indvReportRes;
	}


	@Override
	public String toString() {
		return "AlertsRes [alertsId=" + alertsId + ", alert=" + alert + "]";
	}
	
}
