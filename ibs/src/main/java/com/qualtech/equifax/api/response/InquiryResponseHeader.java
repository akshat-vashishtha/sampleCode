package com.qualtech.equifax.api.response;

public class InquiryResponseHeader {

	 String date;
	 String productcode;
	 String clientid;
	 String hitcode;
     String customercode;
     String custreffield;
     String time;
     String successcode;
     String reportorderno;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getProductcode() {
		return productcode;
	}
	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}
	
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getHitcode() {
		return hitcode;
	}
	public void setHitcode(String hitcode) {
		this.hitcode = hitcode;
	}
	public String getCustomercode() {
		return customercode;
	}
	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}
	public String getCustreffield() {
		return custreffield;
	}
	public void setCustreffield(String custreffield) {
		this.custreffield = custreffield;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSuccesscode() {
		return successcode;
	}
	public void setSuccesscode(String successcode) {
		this.successcode = successcode;
	}
	public String getReportorderno() {
		return reportorderno;
	}
	public void setReportorderno(String reportorderno) {
		this.reportorderno = reportorderno;
	}
	@Override
	public String toString() {
		return "InquiryResponseHeader [date=" + date + ", productCode=" + productcode + ", clientId=" + clientid
				+ ", hitcode=" + hitcode + ", customercode=" + customercode + ", custreffield=" + custreffield
				+ ", time=" + time + ", successcode=" + successcode + ", reportorderno=" + reportorderno + "]";
	}
     
     
	
}
