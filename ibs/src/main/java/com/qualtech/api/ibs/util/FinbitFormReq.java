package com.qualtech.api.ibs.util;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FinbitFormReq {

	private List<MultipartFile> bankStatement;
	private List<String> bankName;
	private List<String> accountType;
	private List<String> email;
	private List<String> psw;
	
	
	public List<MultipartFile> getBankStatement() {
		return bankStatement;
	}


	public void setBankStatement(List<MultipartFile> bankStatement) {
		this.bankStatement = bankStatement;
	}


	public List<String> getBankName() {
		return bankName;
	}


	public void setBankName(List<String> bankName) {
		this.bankName = bankName;
	}


	public List<String> getAccountType() {
		return accountType;
	}


	public void setAccountType(List<String> accountType) {
		this.accountType = accountType;
	}


	public List<String> getEmail() {
		return email;
	}


	public void setEmail(List<String> email) {
		this.email = email;
	}


	public List<String> getPsw() {
		return psw;
	}


	public void setPsw(List<String> psw) {
		this.psw = psw;
	}


	@Override
	public String toString() {
		return "FinbitFormReq [bankStatement=" + bankStatement + ", bankName=" + bankName + ", accountType="
				+ accountType + ", email=" + email + ", psw=" + psw + "]";
	}
	
}
