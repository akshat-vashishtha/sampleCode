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
@Table(name = "IB_BUREAU_CURRENT_APP_RES")
public class CurrentApplicationRes implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2687283033456955149L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CURRENT_APP_SQC", allocationSize = 1)
	@JsonIgnore
	private int currAppId;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "currAppRes", cascade = CascadeType.ALL)
	private CurrentApplicationDetailsRes currentapplicationdetails;


	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;


	public int getCurrAppId() {
		return currAppId;
	}


	public void setCurrAppId(int currAppId) {
		this.currAppId = currAppId;
	}


	public CurrentApplicationDetailsRes getCurrentapplicationdetails() {
		if (this.currentapplicationdetails != null) {
			this.currentapplicationdetails.setCurrAppRes(this);
		}
		return currentapplicationdetails;
	}


	public void setCurrentapplicationdetails(CurrentApplicationDetailsRes currentapplicationdetails) {
		this.currentapplicationdetails = currentapplicationdetails;
	}


	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}


	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}


	@Override
	public String toString() {
		return "CurrentApplicationRes [currAppId=" + currAppId + ", currentapplicationdetails="
				+ currentapplicationdetails + "]";
	}

}
