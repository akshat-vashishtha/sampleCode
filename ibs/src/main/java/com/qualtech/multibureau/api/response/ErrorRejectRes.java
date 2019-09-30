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
@Table(name="IB_BUREAU_ERR_REJECT_RES")
public class ErrorRejectRes implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3829963518563583863L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ERR_REJECT_SQC", allocationSize = 1)
	@JsonIgnore
	private int errorRejId;
	
	private String description;
	private String code;
	
	@ManyToOne
	@JoinColumn(name="id", nullable=false)
	@JsonIgnore
	private RejectBureauRes rejBureauRes;
	
	
	
	public int getErrorRejId() {
		return errorRejId;
	}
	public void setErrorRejId(int errorRejId) {
		this.errorRejId = errorRejId;
	}
	public RejectBureauRes getRejBureauRes() {
		return rejBureauRes;
	}
	public void setRejBureauRes(RejectBureauRes rejBureauRes) {
		this.rejBureauRes = rejBureauRes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "ErrorRejectRes [errorRejId=" + errorRejId + ", description=" + description + ", code=" + code + "]";
	}

	
}
