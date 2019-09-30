
package com.qualtech.crif.api.crif.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "mfiind",
//    "ioi",
//    "addresses",
//    "creditinqpurpstyp",
//    "cnsind",
//    "mfigroup",
//    "father",
//    "cnsscore",
//    "loanamount",
//    "phones",
//    "mfiscore",
//    "ageason",
//    "branch",
//    "emails",
//    "creditinquirystage",
//    "dob",
//    "aka",
//    "losappid",
//    "name",
//    "ids",
//    "creditreqtyp",
//    "age"
//})
public class Request implements Serializable
{

    //@JsonProperty("mfiind")
    private Boolean mfiind;
    //@JsonProperty("ioi")
    private Boolean ioi;
    //@JsonProperty("addresses")
    private Addresses addresses;
    //@JsonProperty("creditinqpurpstyp")
    private String creditinqpurpstyp;
    //@JsonProperty("cnsind")
    private Boolean cnsind;
    //@JsonProperty("mfigroup")
    private Boolean mfigroup;
    //@JsonProperty("father")
    private String father;
    //@JsonProperty("cnsscore")
    private Boolean cnsscore;
    //@JsonProperty("loanamount")
    private Integer loanamount;
    //@JsonProperty("phones")
    private Phones phones;
    //@JsonProperty("mfiscore")
    private Boolean mfiscore;
    //@JsonProperty("ageason")
    private String ageason;
    //@JsonProperty("branch")
    private String branch;
    //@JsonProperty("emails")
    private String emails;
    //@JsonProperty("creditinquirystage")
    private String creditinquirystage;
    //@JsonProperty("dob")
    private String dob;
    //@JsonProperty("aka")
    private String aka;
    //@JsonProperty("losappid")
    private String losappid;
    //@JsonProperty("name")
    private String name;
    //@JsonProperty("ids")
    private Ids ids;
    //@JsonProperty("creditreqtyp")
    private String creditreqtyp;
    //@JsonProperty("age")
    private Integer age;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3542197100699789041L;

    //@JsonProperty("mfiind")
    public Boolean getMfiind() {
        return mfiind;
    }

    //@JsonProperty("mfiind")
    public void setMfiind(Boolean mfiind) {
        this.mfiind = mfiind;
    }

    public Request withMfiind(Boolean mfiind) {
        this.mfiind = mfiind;
        return this;
    }

    //@JsonProperty("ioi")
    public Boolean getIoi() {
        return ioi;
    }

    //@JsonProperty("ioi")
    public void setIoi(Boolean ioi) {
        this.ioi = ioi;
    }

    public Request withIoi(Boolean ioi) {
        this.ioi = ioi;
        return this;
    }

    //@JsonProperty("addresses")
    public Addresses getAddresses() {
        return addresses;
    }

    //@JsonProperty("addresses")
    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    public Request withAddresses(Addresses addresses) {
        this.addresses = addresses;
        return this;
    }

    //@JsonProperty("creditinqpurpstyp")
    public String getCreditinqpurpstyp() {
        return creditinqpurpstyp;
    }

    //@JsonProperty("creditinqpurpstyp")
    public void setCreditinqpurpstyp(String creditinqpurpstyp) {
        this.creditinqpurpstyp = creditinqpurpstyp;
    }

    public Request withCreditinqpurpstyp(String creditinqpurpstyp) {
        this.creditinqpurpstyp = creditinqpurpstyp;
        return this;
    }

    //@JsonProperty("cnsind")
    public Boolean getCnsind() {
        return cnsind;
    }

    //@JsonProperty("cnsind")
    public void setCnsind(Boolean cnsind) {
        this.cnsind = cnsind;
    }

    public Request withCnsind(Boolean cnsind) {
        this.cnsind = cnsind;
        return this;
    }

    //@JsonProperty("mfigroup")
    public Boolean getMfigroup() {
        return mfigroup;
    }

    //@JsonProperty("mfigroup")
    public void setMfigroup(Boolean mfigroup) {
        this.mfigroup = mfigroup;
    }

    public Request withMfigroup(Boolean mfigroup) {
        this.mfigroup = mfigroup;
        return this;
    }

    //@JsonProperty("father")
    public String getFather() {
        return father;
    }

    //@JsonProperty("father")
    public void setFather(String father) {
        this.father = father;
    }

    public Request withFather(String father) {
        this.father = father;
        return this;
    }

    //@JsonProperty("cnsscore")
    public Boolean getCnsscore() {
        return cnsscore;
    }

    //@JsonProperty("cnsscore")
    public void setCnsscore(Boolean cnsscore) {
        this.cnsscore = cnsscore;
    }

    public Request withCnsscore(Boolean cnsscore) {
        this.cnsscore = cnsscore;
        return this;
    }

    //@JsonProperty("loanamount")
    public Integer getLoanamount() {
        return loanamount;
    }

    //@JsonProperty("loanamount")
    public void setLoanamount(Integer loanamount) {
        this.loanamount = loanamount;
    }

    public Request withLoanamount(Integer loanamount) {
        this.loanamount = loanamount;
        return this;
    }

    //@JsonProperty("phones")
    public Phones getPhones() {
        return phones;
    }

    //@JsonProperty("phones")
    public void setPhones(Phones phones) {
        this.phones = phones;
    }

    public Request withPhones(Phones phones) {
        this.phones = phones;
        return this;
    }

    //@JsonProperty("mfiscore")
    public Boolean getMfiscore() {
        return mfiscore;
    }

    //@JsonProperty("mfiscore")
    public void setMfiscore(Boolean mfiscore) {
        this.mfiscore = mfiscore;
    }

    public Request withMfiscore(Boolean mfiscore) {
        this.mfiscore = mfiscore;
        return this;
    }

    //@JsonProperty("ageason")
    public String getAgeason() {
        return ageason;
    }

    //@JsonProperty("ageason")
    public void setAgeason(String ageason) {
        this.ageason = ageason;
    }

    public Request withAgeason(String ageason) {
        this.ageason = ageason;
        return this;
    }

    //@JsonProperty("branch")
    public String getBranch() {
        return branch;
    }

    //@JsonProperty("branch")
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public Request withBranch(String branch) {
        this.branch = branch;
        return this;
    }

    //@JsonProperty("emails")
    public String getEmails() {
        return emails;
    }

    //@JsonProperty("emails")
    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Request withEmails(String emails) {
        this.emails = emails;
        return this;
    }

    //@JsonProperty("creditinquirystage")
    public String getCreditinquirystage() {
        return creditinquirystage;
    }

    //@JsonProperty("creditinquirystage")
    public void setCreditinquirystage(String creditinquirystage) {
        this.creditinquirystage = creditinquirystage;
    }

    public Request withCreditinquirystage(String creditinquirystage) {
        this.creditinquirystage = creditinquirystage;
        return this;
    }

    //@JsonProperty("dob")
    public String getDob() {
        return dob;
    }

    //@JsonProperty("dob")
    public void setDob(String dob) {
        this.dob = dob;
    }

    public Request withDob(String dob) {
        this.dob = dob;
        return this;
    }

    //@JsonProperty("aka")
    public String getAka() {
        return aka;
    }

    //@JsonProperty("aka")
    public void setAka(String aka) {
        this.aka = aka;
    }

    public Request withAka(String aka) {
        this.aka = aka;
        return this;
    }

    //@JsonProperty("losappid")
    public String getLosappid() {
        return losappid;
    }

    //@JsonProperty("losappid")
    public void setLosappid(String losappid) {
        this.losappid = losappid;
    }

    public Request withLosappid(String losappid) {
        this.losappid = losappid;
        return this;
    }

    //@JsonProperty("name")
    public String getName() {
        return name;
    }

    //@JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public Request withName(String name) {
        this.name = name;
        return this;
    }

    //@JsonProperty("ids")
    public Ids getIds() {
        return ids;
    }

    //@JsonProperty("ids")
    public void setIds(Ids ids) {
        this.ids = ids;
    }

    public Request withIds(Ids ids) {
        this.ids = ids;
        return this;
    }

    //@JsonProperty("creditreqtyp")
    public String getCreditreqtyp() {
        return creditreqtyp;
    }

    //@JsonProperty("creditreqtyp")
    public void setCreditreqtyp(String creditreqtyp) {
        this.creditreqtyp = creditreqtyp;
    }

    public Request withCreditreqtyp(String creditreqtyp) {
        this.creditreqtyp = creditreqtyp;
        return this;
    }

    //@JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    //@JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    public Request withAge(Integer age) {
        this.age = age;
        return this;
    }

//    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

//    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Request withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
