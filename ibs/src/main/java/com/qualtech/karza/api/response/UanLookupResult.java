package com.qualtech.karza.api.response;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_K_UANLOOKUP_RES")
public class UanLookupResult implements Serializable {

	private static final long serialVersionUID = -8251784410816419882L;
	@Id
	@JsonIgnore
	private String uniqueid;
	@Column
	@JsonIgnore
	private String corelationid;
	@Column
	private String[] uan;

	public String[] getUan() {
		return uan;
	}

	public void setUan(String[] uan) {
		this.uan = uan;
	}
    
	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getCorelationid() {
		return corelationid;
	}

	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

	@Override
	public String toString() {
		return "UanLookupResult [uan=" + Arrays.toString(uan) + "]";
	}

}
