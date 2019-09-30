package com.qualtech.cibilv2.api.response;

import java.io.Serializable;
import java.util.List;

public class Status implements Serializable{

	private static final long serialVersionUID = 1899948421973460245L;

	private List<Field> field;
	private String name;
	
	public List<Field> getField() {
		return field;
	}
	public void setField(List<Field> field) {
		this.field = field;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
