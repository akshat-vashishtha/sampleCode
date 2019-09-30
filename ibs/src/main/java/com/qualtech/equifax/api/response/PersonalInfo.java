package com.qualtech.equifax.api.response;

public class PersonalInfo {

	String dateofbirth;
	String occupation;
	String gender;
	private Name name;
	private Age age;
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}
	public Age getAge() {
		return age;
	}
	public void setAge(Age age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "PersonalInfo [dateofbirth=" + dateofbirth + ", occupation=" + occupation + ", gender=" + gender + "]";
	}
	
	
	
}
