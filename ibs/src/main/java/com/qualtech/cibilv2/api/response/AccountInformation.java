package com.qualtech.cibilv2.api.response;

import java.io.Serializable;
import java.util.List;

public class AccountInformation implements Serializable{

	private static final long serialVersionUID = -3471802039858689717L;
	
	private List<Field> field;

	public List<Field> getField() {
		return field;
	}

	public void setField(List<Field> field) {
		this.field = field;
	}
	
}
