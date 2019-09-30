
package com.qualtech.hdfc.api.request;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NomineeDetail {

   // @JsonProperty("title")
    private String title;
   // @JsonProperty("gender")
    private String gender;
   // @JsonProperty("firstName")
    private String firstName;
   // @JsonProperty("lastName")
    private String lastName;
   // @JsonProperty("dob")
    private String dob;
   // @JsonProperty("mobile")
    private String mobile;
   // @JsonProperty("relationshipToCustomer")
    private String relationshipToCustomer;
   // @JsonProperty("percentageAllocation")
    private String percentageAllocation;
   // @JsonProperty("address")
    private Address address;
   // @JsonProperty("appointeeDetails")
    private AppointeeDetails appointeeDetails;
   // @JsonProperty("title2")

   // @JsonProperty("title")
    public String getTitle() {
        return title;
    }

   // @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

   // @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

   // @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

   // @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

   // @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

   // @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

   // @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   // @JsonProperty("dob")
    public String getDob() {
        return dob;
    }

   // @JsonProperty("dob")
    public void setDob(String dob) {
        this.dob = dob;
    }

   // @JsonProperty("mobile")
    public String getMobile() {
        return mobile;
    }

   // @JsonProperty("mobile")
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

   // @JsonProperty("relationshipToCustomer")
    public String getRelationshipToCustomer() {
        return relationshipToCustomer;
    }

   // @JsonProperty("relationshipToCustomer")
    public void setRelationshipToCustomer(String relationshipToCustomer) {
        this.relationshipToCustomer = relationshipToCustomer;
    }

   // @JsonProperty("percentageAllocation")
    public String getPercentageAllocation() {
        return percentageAllocation;
    }

   // @JsonProperty("percentageAllocation")
    public void setPercentageAllocation(String percentageAllocation) {
        this.percentageAllocation = percentageAllocation;
    }

   // @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

   // @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

   // @JsonProperty("appointeeDetails")
    public AppointeeDetails getAppointeeDetails() {
        return appointeeDetails;
    }

   // @JsonProperty("appointeeDetails")
    public void setAppointeeDetails(AppointeeDetails appointeeDetails) {
        this.appointeeDetails = appointeeDetails;
    }


}
