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
@Table(name = "IB_BUREAU_NAME_JSON_RES")
public class NameJsonRes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2637066333028390203L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_NAME_JSON_RES_SQC", allocationSize = 1)
	@JsonIgnore
	private int nameId;

	private String dob;
	private String gender;
	private String name1;

	@OneToOne
	@JoinColumn(name="id", nullable=false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public int getNameId() {
		return nameId;
	}

	public void setNameId(int nameId) {
		this.nameId = nameId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	@Override
	public String toString() {
		return "NameJsonRes [nameId=" + nameId + ", dob=" + dob + ", gender=" + gender + ", name1=" + name1 + "]";
	}
	
}
