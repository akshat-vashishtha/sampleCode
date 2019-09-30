
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
//    "currentbal",
//    "disburseddate",
//    "disbursedamt",
//    "overdueamt",
//    "accttype",
//    "securitydetails",
//    "writeoffamt",
//    "linkedaccounts",
//    "interestrate",
//    "accountstatus",
//    "acctnumber",
//    "creditguarantor",
//    "ownershipind",
//    "matchedtype",
//    "combinedpaymenthistory",
//    "datereported",
//    "lastpaymentdate",
//    "closeddate"
//})
public class Loandetails implements Serializable
{

    //@JsonProperty("currentbal")
    private Integer currentbal;
    //@JsonProperty("disburseddate")
    private String disburseddate;
    //@JsonProperty("disbursedamt")
    private String disbursedamt;
    //@JsonProperty("overdueamt")
    private Integer overdueamt;
    //@JsonProperty("accttype")
    private String accttype;
    //@JsonProperty("securitydetails")
    private String securitydetails;
    //@JsonProperty("writeoffamt")
    private Integer writeoffamt;
    //@JsonProperty("linkedaccounts")
    private String linkedaccounts;
    //@JsonProperty("interestrate")
    private Integer interestrate;
    //@JsonProperty("accountstatus")
    private String accountstatus;
    //@JsonProperty("acctnumber")
    private String acctnumber;
    //@JsonProperty("creditguarantor")
    private String creditguarantor;
    //@JsonProperty("ownershipind")
    private String ownershipind;
    //@JsonProperty("matchedtype")
    private String matchedtype;
    //@JsonProperty("combinedpaymenthistory")
    private String combinedpaymenthistory;
    //@JsonProperty("datereported")
    private String datereported;
    //@JsonProperty("lastpaymentdate")
    private String lastpaymentdate;
    //@JsonProperty("closeddate")
    private String closeddate;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -841824826743269238L;

    //@JsonProperty("currentbal")
    public Integer getCurrentbal() {
        return currentbal;
    }

    //@JsonProperty("currentbal")
    public void setCurrentbal(Integer currentbal) {
        this.currentbal = currentbal;
    }

    public Loandetails withCurrentbal(Integer currentbal) {
        this.currentbal = currentbal;
        return this;
    }

    //@JsonProperty("disburseddate")
    public String getDisburseddate() {
        return disburseddate;
    }

    //@JsonProperty("disburseddate")
    public void setDisburseddate(String disburseddate) {
        this.disburseddate = disburseddate;
    }

    public Loandetails withDisburseddate(String disburseddate) {
        this.disburseddate = disburseddate;
        return this;
    }

    //@JsonProperty("disbursedamt")
    public String getDisbursedamt() {
        return disbursedamt;
    }

    //@JsonProperty("disbursedamt")
    public void setDisbursedamt(String disbursedamt) {
        this.disbursedamt = disbursedamt;
    }

    public Loandetails withDisbursedamt(String disbursedamt) {
        this.disbursedamt = disbursedamt;
        return this;
    }

    //@JsonProperty("overdueamt")
    public Integer getOverdueamt() {
        return overdueamt;
    }

    //@JsonProperty("overdueamt")
    public void setOverdueamt(Integer overdueamt) {
        this.overdueamt = overdueamt;
    }

    public Loandetails withOverdueamt(Integer overdueamt) {
        this.overdueamt = overdueamt;
        return this;
    }

    //@JsonProperty("accttype")
    public String getAccttype() {
        return accttype;
    }

    //@JsonProperty("accttype")
    public void setAccttype(String accttype) {
        this.accttype = accttype;
    }

    public Loandetails withAccttype(String accttype) {
        this.accttype = accttype;
        return this;
    }

    //@JsonProperty("securitydetails")
    public String getSecuritydetails() {
        return securitydetails;
    }

    //@JsonProperty("securitydetails")
    public void setSecuritydetails(String securitydetails) {
        this.securitydetails = securitydetails;
    }

    public Loandetails withSecuritydetails(String securitydetails) {
        this.securitydetails = securitydetails;
        return this;
    }

    //@JsonProperty("writeoffamt")
    public Integer getWriteoffamt() {
        return writeoffamt;
    }

    //@JsonProperty("writeoffamt")
    public void setWriteoffamt(Integer writeoffamt) {
        this.writeoffamt = writeoffamt;
    }

    public Loandetails withWriteoffamt(Integer writeoffamt) {
        this.writeoffamt = writeoffamt;
        return this;
    }

    //@JsonProperty("linkedaccounts")
    public String getLinkedaccounts() {
        return linkedaccounts;
    }

    //@JsonProperty("linkedaccounts")
    public void setLinkedaccounts(String linkedaccounts) {
        this.linkedaccounts = linkedaccounts;
    }

    public Loandetails withLinkedaccounts(String linkedaccounts) {
        this.linkedaccounts = linkedaccounts;
        return this;
    }

    //@JsonProperty("interestrate")
    public Integer getInterestrate() {
        return interestrate;
    }

    //@JsonProperty("interestrate")
    public void setInterestrate(Integer interestrate) {
        this.interestrate = interestrate;
    }

    public Loandetails withInterestrate(Integer interestrate) {
        this.interestrate = interestrate;
        return this;
    }

    //@JsonProperty("accountstatus")
    public String getAccountstatus() {
        return accountstatus;
    }

    //@JsonProperty("accountstatus")
    public void setAccountstatus(String accountstatus) {
        this.accountstatus = accountstatus;
    }

    public Loandetails withAccountstatus(String accountstatus) {
        this.accountstatus = accountstatus;
        return this;
    }

    //@JsonProperty("acctnumber")
    public String getAcctnumber() {
        return acctnumber;
    }

    //@JsonProperty("acctnumber")
    public void setAcctnumber(String acctnumber) {
        this.acctnumber = acctnumber;
    }

    public Loandetails withAcctnumber(String acctnumber) {
        this.acctnumber = acctnumber;
        return this;
    }

    //@JsonProperty("creditguarantor")
    public String getCreditguarantor() {
        return creditguarantor;
    }

    //@JsonProperty("creditguarantor")
    public void setCreditguarantor(String creditguarantor) {
        this.creditguarantor = creditguarantor;
    }

    public Loandetails withCreditguarantor(String creditguarantor) {
        this.creditguarantor = creditguarantor;
        return this;
    }

    //@JsonProperty("ownershipind")
    public String getOwnershipind() {
        return ownershipind;
    }

    //@JsonProperty("ownershipind")
    public void setOwnershipind(String ownershipind) {
        this.ownershipind = ownershipind;
    }

    public Loandetails withOwnershipind(String ownershipind) {
        this.ownershipind = ownershipind;
        return this;
    }

    //@JsonProperty("matchedtype")
    public String getMatchedtype() {
        return matchedtype;
    }

    //@JsonProperty("matchedtype")
    public void setMatchedtype(String matchedtype) {
        this.matchedtype = matchedtype;
    }

    public Loandetails withMatchedtype(String matchedtype) {
        this.matchedtype = matchedtype;
        return this;
    }

    //@JsonProperty("combinedpaymenthistory")
    public String getCombinedpaymenthistory() {
        return combinedpaymenthistory;
    }

    //@JsonProperty("combinedpaymenthistory")
    public void setCombinedpaymenthistory(String combinedpaymenthistory) {
        this.combinedpaymenthistory = combinedpaymenthistory;
    }

    public Loandetails withCombinedpaymenthistory(String combinedpaymenthistory) {
        this.combinedpaymenthistory = combinedpaymenthistory;
        return this;
    }

    //@JsonProperty("datereported")
    public String getDatereported() {
        return datereported;
    }

    //@JsonProperty("datereported")
    public void setDatereported(String datereported) {
        this.datereported = datereported;
    }

    public Loandetails withDatereported(String datereported) {
        this.datereported = datereported;
        return this;
    }

    //@JsonProperty("lastpaymentdate")
    public String getLastpaymentdate() {
        return lastpaymentdate;
    }

    //@JsonProperty("lastpaymentdate")
    public void setLastpaymentdate(String lastpaymentdate) {
        this.lastpaymentdate = lastpaymentdate;
    }

    public Loandetails withLastpaymentdate(String lastpaymentdate) {
        this.lastpaymentdate = lastpaymentdate;
        return this;
    }

    //@JsonProperty("closeddate")
    public String getCloseddate() {
        return closeddate;
    }

    ////@JsonProperty("closeddate")
    public void setCloseddate(String closeddate) {
        this.closeddate = closeddate;
    }

    public Loandetails withCloseddate(String closeddate) {
        this.closeddate = closeddate;
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

    public Loandetails withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
