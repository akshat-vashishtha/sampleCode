package com.qualtech.cibilv2.api.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_C2_RES_SRCHINFO")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SearchInformationField {

	private static final long serialVersionUID = -7125367840463821821L;
	
	@ManyToOne
	@JoinColumn(name = "UNIQUEID", nullable = false)
	@JsonIgnore
	private Root root;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_Res_SrchInfo")
	@SequenceGenerator(name = "IB_Res_SrchInfo", sequenceName = "IB_Res_SrchInfo", allocationSize = 1)
	@Column(name = "TID")
	private Integer tid;
	@Column(name="NAME")
	private String name;
	@Column(name="VALUE")
	private String value;
	@Column(name="FIELDKEY")
	private String fieldKey;
	@Column(name="BACKGROUNDCOLOR")
	private String backgroundColor;
	@Column(name="COLOR")
	private String color;
	@Column(name="DISPLAYPOSITION")
	private String displayposition;
	
	
	
	public Root getRoot() {
		return root;
	}
	public void setRoot(Root root) {
		this.root = root;
	}

	
	
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getFieldKey() {
		return fieldKey;
	}
	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getDisplayposition() {
		return displayposition;
	}
	public void setDisplayposition(String displayposition) {
		this.displayposition = displayposition;
	}
	
}
