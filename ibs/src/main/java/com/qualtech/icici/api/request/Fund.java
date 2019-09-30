package com.qualtech.icici.api.request;

import java.io.Serializable;

public class Fund implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6156854796706491150L;

	
	private String name;
	private String value;
	
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
	@Override
	public String toString() {
		return "Fund [name=" + name + ", value=" + value + "]";
	}
	
}
