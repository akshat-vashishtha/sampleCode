package com.qualtech.karza.api.response;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/*import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)*/

@Embeddable
public class KycOcrResFront {
	 
	 @Column(name="DATE_")
	 private String date;
	 @Column(name="FATHER")
	 private String father;
	 @Column(name="NAME")
	 private String name;
	 @Column(name="PAN_NO")
	 private String pan_no;
	 @Column(name="AADHAAR")
	 private String aadhaar;
	 @Column(name="DOB")
	 private String dob;
	 @Column(name="YOB")
	 private String yob;
	 @Column(name="GENDER")
	 private String gender;
	 @Column(name="COUNTRY_CODE")
	 private String country_code;
	 @Column(name="DOE")
	 private String doe;
	 @Column(name="DOI")
	 private String doi;
	 @Column(name="GIVEN_NAME")
	 private String given_name;
	 @Column(name="MRZ")
	 private String[] mrz;
	 @Column(name="NATIONALITY")
	 private String nationality;
	 @Column(name="PASSPORT_NUM")
	 private String passport_num;
	 @Column(name="PLACE_OF_BIRTH")
	 private String place_of_birth;
	 @Column(name="PLACE_OF_ISSUE")
	 private String place_of_issue;
	 @Column(name="SURNAME")
	 private String surname;
	 @Column(name="TYPE")
	 private String type;
	 @Column(name="AGE")
	 private String age;
	 @Column(name="DOC")
	 private String doc;
	 @Column(name="VOTERID")
	 private String voterid;
	 
	 

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPan_no() {
		return pan_no;
	}
	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public String getDoe() {
		return doe;
	}
	public void setDoe(String doe) {
		this.doe = doe;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getGiven_name() {
		return given_name;
	}
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	public String[] getMrz() {
		return mrz;
	}
	public void setMrz(String[] mrz) {
		this.mrz = mrz;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPassport_num() {
		return passport_num;
	}
	public void setPassport_num(String passport_num) {
		this.passport_num = passport_num;
	}
	public String getPlace_of_birth() {
		return place_of_birth;
	}
	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}
	public String getPlace_of_issue() {
		return place_of_issue;
	}
	public void setPlace_of_issue(String place_of_issue) {
		this.place_of_issue = place_of_issue;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDoc() {
		return doc;
	}
	public void setDoc(String doc) {
		this.doc = doc;
	}
	public String getVoterid() {
		return voterid;
	}
	public void setVoterid(String voterid) {
		this.voterid = voterid;
	}
	
	@Override
	public String toString() {
		return "KycOcrResFront [date=" + date + ", father=" + father + ", name=" + name + ", pan_no=" + pan_no
				+ ", aadhaar=" + aadhaar + ", dob=" + dob + ", yob=" + yob + ", gender=" + gender + ", country_code="
				+ country_code + ", doe=" + doe + ", doi=" + doi + ", given_name=" + given_name + ", mrz="
				+ Arrays.toString(mrz) + ", nationality=" + nationality + ", passport_num=" + passport_num
				+ ", place_of_birth=" + place_of_birth + ", place_of_issue=" + place_of_issue + ", surname=" + surname
				+ ", type=" + type + ", age=" + age + ", doc=" + doc + ", voterid=" + voterid + "]";
	}
	public String getYob() {
		return yob;
	}
	public void setYob(String yob) {
		this.yob = yob;
	}
	 
	 
}
