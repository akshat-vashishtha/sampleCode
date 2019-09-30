package com.qualtech.multibureau.api.request;

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
@Table(name="IB_BUREAU_PHONE_REQ")
public class PhoneBureau {

	
	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_PHONE_SQC", allocationSize = 1)
	private int id;
	
	private String phone_type;
	private String phone_number;
	private String phone_extn;
	private String std_code;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="UNIQUEID", nullable=false)
	private BureauPayload bureauPayload;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BureauPayload getBureauPayload() {
		return bureauPayload;
	}
	public void setBureauPayload(BureauPayload bureauPayload) {
		this.bureauPayload = bureauPayload;
	}
	public String getPhone_type() {
		return phone_type;
	}
	public void setPhone_type(String phone_type) {
		this.phone_type = phone_type;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getPhone_extn() {
		return phone_extn;
	}
	public void setPhone_extn(String phone_extn) {
		this.phone_extn = phone_extn;
	}
	public String getStd_code() {
		return std_code;
	}
	public void setStd_code(String std_code) {
		this.std_code = std_code;
	}
	@Override
	public String toString() {
		return "PhoneBureau [id=" + id + ", phone_type=" + phone_type + ", phone_number=" + phone_number
				+ ", phone_extn=" + phone_extn + ", std_code=" + std_code + "]";
	}
}
