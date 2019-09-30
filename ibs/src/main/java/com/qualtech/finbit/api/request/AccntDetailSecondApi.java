package com.qualtech.finbit.api.request;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.json.JSONObject;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccntDetailSecondApi
{
	private String status;
	private List<AccountList> accountList;
	private String message;
	private List errors;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<AccountList> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<AccountList> accountList) {
		this.accountList = accountList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "AccntDetailSecondApi [status=" + status + ", accountList=" + accountList + ", message=" + message
				+ ", errors=" + errors + "]";
	}
	public List getErrors() {
		return errors;
	}
	public void setErrors(List errors) {
		this.errors = errors;
	}
	
	
	/*{
		   "status":"SUCCESS",
		   "accountList":[
		      {
		         "accountDetails":"MR.  DEEPAK KUMAR | HDFC | 50100194834032",
		         "accountUID":"hcpat95cvs3m9lna7kafq3vobb",
		         "parseStatus":"CATEGORISED",
		         "parseMessage":"Statement(s) have been parsed successfully."
		      }
		   ],
		   "message":"Keep the accountUID safe and treat it like as password. Use it to access any further details about this Bank Account."
		}
	*/
	
	
}
