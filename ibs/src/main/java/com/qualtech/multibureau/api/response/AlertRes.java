package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
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
@Table(name = "IB_BUREAU_ALERT_DESC")
public class AlertRes implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4970125191233523287L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ALERT_DESC_SQC", allocationSize = 1)
	@JsonIgnore
	private int alrtId;

	private String alertdescription;
	
	@OneToOne
	@JoinColumn(name = "alertsId", nullable = false)
	@JsonIgnore
	private AlertsRes alertsRes;

	public int getAlrtId() {
		return alrtId;
	}

	public void setAlrtId(int alrtId) {
		this.alrtId = alrtId;
	}

	public String getAlertdescription() {
		return alertdescription;
	}

	public void setAlertdescription(String alertdescription) {
		this.alertdescription = alertdescription;
	}

	public AlertsRes getAlertsRes() {
		return alertsRes;
	}

	public void setAlertsRes(AlertsRes alertsRes) {
		this.alertsRes = alertsRes;
	}

	@Override
	public String toString() {
		return "AlertRes [alrtId=" + alrtId + ", alertdescription=" + alertdescription + "]";
	}

}
