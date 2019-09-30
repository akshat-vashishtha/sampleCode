package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_K_EMPLOYERLOOKUP_RES")
public class EmployerLookupResult implements Serializable{
	
	private static final long serialVersionUID = -8251784410816419882L;
	@Id
	@JsonIgnore
	@GeneratedValue(generator="EMPLOYERLOOKUP")
	@SequenceGenerator(name="EMPLOYERLOOKUP",sequenceName="IB_K_EMPLR_TBL_RES_SQC")
	private int    TABLE_UNIQUEID;
	@Column
	@JsonIgnore
	private String    UNIQUEID;
	@Column
	@JsonIgnore
	private String    CORELATIONID;
	@Column(name="MEMBERSHHIP_ID")
	private String membershipId;
	@Column(name="SETTLEMENT")
	private String settelment;
	@Column(name="EMP_ORGANIZATION")
	private String estName;
	public String getSettelment() {
		return settelment;
	}
	public void setSettelment(String settelment) {
		this.settelment = settelment;
	}
	public String getEstName() {
		return estName;
	}
	public void setEstName(String estName) {
		this.estName = estName;
	}
	public String getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}
	public int getTABLE_UNIQUEID() {
		return TABLE_UNIQUEID;
	}
	public void setTABLE_UNIQUEID(int tABLE_UNIQUEID) {
		TABLE_UNIQUEID = tABLE_UNIQUEID;
	}
	public String getUNIQUEID() {
		return UNIQUEID;
	}
	public void setUNIQUEID(String uNIQUEID) {
		UNIQUEID = uNIQUEID;
	}
	public String getCORELATIONID() {
		return CORELATIONID;
	}
	public void setCORELATIONID(String cORELATIONID) {
		CORELATIONID = cORELATIONID;
	}
	@Override
	public String toString() {
		return "EmployerLookupResult [menbershipId=" + membershipId + ", settlement=" + settelment + ", estName="
				+ estName + "]";
	}

}

