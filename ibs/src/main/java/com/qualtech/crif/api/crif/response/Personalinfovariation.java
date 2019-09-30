
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
//////@JsonPropertyOrder({
//    "passportvariations",
//    "panvariations",
//    "drivinglicensevariations",
//    "rationcardvariations",
//    "addressvariations",
//    "phonenumbervariations",
//    "emailvariations",
//    "voteridvariations",
//    "dateofbirthvariations",
//    "namevariations"
//})
public class Personalinfovariation implements Serializable
{

//    @JsonProperty("passportvariations")
    private String passportvariations;
    //@JsonProperty("panvariations")
    private String panvariations;
    //@JsonProperty("drivinglicensevariations")
    private String drivinglicensevariations;
    //@JsonProperty("rationcardvariations")
    private String rationcardvariations;
    //@JsonProperty("addressvariations")
    private Addressvariations addressvariations;
    //@JsonProperty("phonenumbervariations")
    private Phonenumbervariations phonenumbervariations;
    //@JsonProperty("emailvariations")
    private String emailvariations;
    //@JsonProperty("voteridvariations")
    private Voteridvariations voteridvariations;
    //@JsonProperty("dateofbirthvariations")
    private Dateofbirthvariations dateofbirthvariations;
    //@JsonProperty("namevariations")
    private Namevariations namevariations;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5884871009691302895L;

    //@JsonProperty("passportvariations")
    public String getPassportvariations() {
        return passportvariations;
    }

    //@JsonProperty("passportvariations")
    public void setPassportvariations(String passportvariations) {
        this.passportvariations = passportvariations;
    }

    public Personalinfovariation withPassportvariations(String passportvariations) {
        this.passportvariations = passportvariations;
        return this;
    }

    //@JsonProperty("panvariations")
    public String getPanvariations() {
        return panvariations;
    }

    //@JsonProperty("panvariations")
    public void setPanvariations(String panvariations) {
        this.panvariations = panvariations;
    }

    public Personalinfovariation withPanvariations(String panvariations) {
        this.panvariations = panvariations;
        return this;
    }

    //@JsonProperty("drivinglicensevariations")
    public String getDrivinglicensevariations() {
        return drivinglicensevariations;
    }

    //@JsonProperty("drivinglicensevariations")
    public void setDrivinglicensevariations(String drivinglicensevariations) {
        this.drivinglicensevariations = drivinglicensevariations;
    }

    public Personalinfovariation withDrivinglicensevariations(String drivinglicensevariations) {
        this.drivinglicensevariations = drivinglicensevariations;
        return this;
    }

    //@JsonProperty("rationcardvariations")
    public String getRationcardvariations() {
        return rationcardvariations;
    }

    //@JsonProperty("rationcardvariations")
    public void setRationcardvariations(String rationcardvariations) {
        this.rationcardvariations = rationcardvariations;
    }

    public Personalinfovariation withRationcardvariations(String rationcardvariations) {
        this.rationcardvariations = rationcardvariations;
        return this;
    }

    //@JsonProperty("addressvariations")
    public Addressvariations getAddressvariations() {
        return addressvariations;
    }

    //@JsonProperty("addressvariations")
    public void setAddressvariations(Addressvariations addressvariations) {
        this.addressvariations = addressvariations;
    }

    public Personalinfovariation withAddressvariations(Addressvariations addressvariations) {
        this.addressvariations = addressvariations;
        return this;
    }

    //@JsonProperty("phonenumbervariations")
    public Phonenumbervariations getPhonenumbervariations() {
        return phonenumbervariations;
    }

    //@JsonProperty("phonenumbervariations")
    public void setPhonenumbervariations(Phonenumbervariations phonenumbervariations) {
        this.phonenumbervariations = phonenumbervariations;
    }

    public Personalinfovariation withPhonenumbervariations(Phonenumbervariations phonenumbervariations) {
        this.phonenumbervariations = phonenumbervariations;
        return this;
    }

    //@JsonProperty("emailvariations")
    public String getEmailvariations() {
        return emailvariations;
    }

    //@JsonProperty("emailvariations")
    public void setEmailvariations(String emailvariations) {
        this.emailvariations = emailvariations;
    }

    public Personalinfovariation withEmailvariations(String emailvariations) {
        this.emailvariations = emailvariations;
        return this;
    }

    //@JsonProperty("voteridvariations")
    public Voteridvariations getVoteridvariations() {
        return voteridvariations;
    }

    //@JsonProperty("voteridvariations")
    public void setVoteridvariations(Voteridvariations voteridvariations) {
        this.voteridvariations = voteridvariations;
    }

    public Personalinfovariation withVoteridvariations(Voteridvariations voteridvariations) {
        this.voteridvariations = voteridvariations;
        return this;
    }

    //@JsonProperty("dateofbirthvariations")
    public Dateofbirthvariations getDateofbirthvariations() {
        return dateofbirthvariations;
    }

    //@JsonProperty("dateofbirthvariations")
    public void setDateofbirthvariations(Dateofbirthvariations dateofbirthvariations) {
        this.dateofbirthvariations = dateofbirthvariations;
    }

    public Personalinfovariation withDateofbirthvariations(Dateofbirthvariations dateofbirthvariations) {
        this.dateofbirthvariations = dateofbirthvariations;
        return this;
    }

    //@JsonProperty("namevariations")
    public Namevariations getNamevariations() {
        return namevariations;
    }

    //@JsonProperty("namevariations")
    public void setNamevariations(Namevariations namevariations) {
        this.namevariations = namevariations;
    }

    public Personalinfovariation withNamevariations(Namevariations namevariations) {
        this.namevariations = namevariations;
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

    public Personalinfovariation withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
