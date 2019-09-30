package com.qualtech.equifax.api.response;

import java.util.List;

public class InquiryPhones {

	List<PhoneInfo>  phoneInfo;

	public List<PhoneInfo> getPhoneInfo() {
		return phoneInfo;
	}

	public void setPhoneInfo(List<PhoneInfo> phoneInfo) {
		this.phoneInfo = phoneInfo;
	}

	@Override
	public String toString() {
		return "InquiryPhones [phoneInfo=" + phoneInfo + "]";
	}

	
}
