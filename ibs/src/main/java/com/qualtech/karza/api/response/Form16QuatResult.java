package com.qualtech.karza.api.response;

import java.io.Serializable;

public class Form16QuatResult implements Serializable{

	
	 private static final long serialVersionUID = -8251784410816419882L;
	 
	 private Form16QuatResultRecords quarterly_records_count_for_next_fiscal;
	
	public Form16QuatResultRecords getQuarterly_records_count_for_next_fiscal() {
		return quarterly_records_count_for_next_fiscal;
	}
	public void setQuarterly_records_count_for_next_fiscal(
			Form16QuatResultRecords quarterly_records_count_for_next_fiscal) {
		this.quarterly_records_count_for_next_fiscal = quarterly_records_count_for_next_fiscal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Form16QuatResult [quarterly_records_count_for_next_fiscal=" + quarterly_records_count_for_next_fiscal
				+ "]";
	}
	 
}
