package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_EMAILAUTH_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailAuthPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
     private String uniqueid;
	 private String email;
	 private String corelationid;
    @Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Id
    @Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
    @Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}

	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	 
}
