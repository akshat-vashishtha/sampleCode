package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_BUREAU_INPROCESS_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InProcessBureauRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5672339653165944177L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_INPROCESS_SQC", allocationSize = 1)
	private int id;
	
	private String product;
	private String status;
	private String tracking_id;
	private String bureau;
	
	@ManyToOne
	@JoinColumn(name="uniqueid", nullable=false)
	@JsonIgnore
	private BureauResult bureauRes;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BureauResult getBureauRes() {
		return bureauRes;
	}
	public void setBureauRes(BureauResult bureauRes) {
		this.bureauRes = bureauRes;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTracking_id() {
		return tracking_id;
	}
	public void setTracking_id(String tracking_id) {
		this.tracking_id = tracking_id;
	}
	public String getBureau() {
		return bureau;
	}
	public void setBureau(String bureau) {
		this.bureau = bureau;
	}
	@Override
	public String toString() {
		return "InProcessBureauRes [id=" + id + ", product=" + product + ", status=" + status + ", tracking_id="
				+ tracking_id + ", bureau=" + bureau + "]";
	}

}
