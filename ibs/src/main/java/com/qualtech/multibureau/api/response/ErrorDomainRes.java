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

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_BUREAU_ERR_RES")
public class ErrorDomainRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;
	
	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ERR_SQC", allocationSize = 1)
	private int errorId;
	
	private String errorcategory;
	private String errordescription;
	private String errorcode;
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;
	
	
	public String getErrorcategory() {
		return errorcategory;
	}
	public void setErrorcategory(String errorcategory) {
		this.errorcategory = errorcategory;
	}
	public String getErrordescription() {
		return errordescription;
	}
	public void setErrordescription(String errordescription) {
		this.errordescription = errordescription;
	}
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	
	public int getErrorId() {
		return errorId;
	}
	public void setErrorId(int errorId) {
		this.errorId = errorId;
	}
	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}
	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}
	@Override
	public String toString() {
		return "ErrorDomainRes [errorId=" + errorId + ", errorcategory=" + errorcategory + ", errordescription="
				+ errordescription + ", errorcode=" + errorcode + "]";
	}

}
