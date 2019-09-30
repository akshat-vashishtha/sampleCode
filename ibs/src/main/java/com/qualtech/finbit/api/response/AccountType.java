package com.qualtech.finbit.api.response;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountType 
{

	@Column(name="ACC_TYPE_ENUM_TYPE")
	private String enumType;
	@Column(name="ACC_TYPE_NAME")
	private String name;
	public String getEnumType() {
		return enumType;
	}
	public void setEnumType(String enumType) {
		this.enumType = enumType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "AccountType [enumType=" + enumType + ", name=" + name + "]";
	}
	
}
