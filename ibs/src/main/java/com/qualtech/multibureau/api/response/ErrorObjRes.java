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
@Table(name = "IB_BUREAU_ERROBJ_RES")
public class ErrorObjRes implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3499811079517665068L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ERR_OBJ_SQC", allocationSize = 1)
	@JsonIgnore
	private int errorObjlId;

	@OneToOne
	@JoinColumn(name = "highmarkStatusId", nullable = false)
	@JsonIgnore
	private HighMarkStatusRes highMarkStatusRes;

	public int getErrorObjlId() {
		return errorObjlId;
	}

	public void setErrorObjlId(int errorObjlId) {
		this.errorObjlId = errorObjlId;
	}

	public HighMarkStatusRes getHighMarkStatusRes() {
		return highMarkStatusRes;
	}

	public void setHighMarkStatusRes(HighMarkStatusRes highMarkStatusRes) {
		this.highMarkStatusRes = highMarkStatusRes;
	}

	@Override
	public String toString() {
		return "ErrorObjRes [errorObjlId=" + errorObjlId + "]";
	}
	
	
}
