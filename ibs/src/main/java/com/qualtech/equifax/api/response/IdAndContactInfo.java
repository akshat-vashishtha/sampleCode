package com.qualtech.equifax.api.response;

import java.util.List;

public class IdAndContactInfo {

	private PersonalInfo personalInfo;
	List<AddressInfo>  addressInfo;
	List<PhoneInfo>  phoneInfo;
	private IdentityInfo identityInfo;
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}
	public List<AddressInfo> getAddressInfo() {
		return addressInfo;
	}
	public void setAddressInfo(List<AddressInfo> addressInfo) {
		this.addressInfo = addressInfo;
	}
	public List<PhoneInfo> getPhoneInfo() {
		return phoneInfo;
	}
	public void setPhoneInfo(List<PhoneInfo> phoneInfo) {
		this.phoneInfo = phoneInfo;
	}
	public IdentityInfo getIdentityInfo() {
		return identityInfo;
	}
	public void setIdentityInfo(IdentityInfo identityInfo) {
		this.identityInfo = identityInfo;
	}
	@Override
	public String toString() {
		return "IdAndContactInfo [personalInfo=" + personalInfo + ", addressInfo=" + addressInfo + ", phoneInfo="
				+ phoneInfo + ", identityInfo=" + identityInfo + "]";
	}
	
	
	
	
}
