
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
//    "optionstatus",
//    "errors",
//    "option"
//})
public class Statu implements Serializable
{

    //@JsonProperty("optionstatus")
    private String optionstatus;
    //@JsonProperty("errors")
    private Errors errors;
    //@JsonProperty("option")
    private String option;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4882436054059619267L;

    //@JsonProperty("optionstatus")
    public String getOptionstatus() {
        return optionstatus;
    }

    //@JsonProperty("optionstatus")
    public void setOptionstatus(String optionstatus) {
        this.optionstatus = optionstatus;
    }

    public Statu withOptionstatus(String optionstatus) {
        this.optionstatus = optionstatus;
        return this;
    }

    //@JsonProperty("errors")
    public Errors getErrors() {
        return errors;
    }

    //@JsonProperty("errors")
    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public Statu withErrors(Errors errors) {
        this.errors = errors;
        return this;
    }

    //@JsonProperty("option")
    public String getOption() {
        return option;
    }

    //@JsonProperty("option")
    public void setOption(String option) {
        this.option = option;
    }

    public Statu withOption(String option) {
        this.option = option;
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

    public Statu withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
