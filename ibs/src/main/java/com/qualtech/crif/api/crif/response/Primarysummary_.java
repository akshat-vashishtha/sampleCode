
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
//    "totalresponses",
//    "noofothermfis",
//    "noofdefaultaccounts",
//    "noofownmfis",
//    "noofclosedaccounts",
//    "noofactiveaccounts"
//})
public class Primarysummary_ implements Serializable
{

    //@JsonProperty("totalresponses")
    private Integer totalresponses;
    //@JsonProperty("noofothermfis")
    private Integer noofothermfis;
    //@JsonProperty("noofdefaultaccounts")
    private Integer noofdefaultaccounts;
    //@JsonProperty("noofownmfis")
    private Integer noofownmfis;
    //@JsonProperty("noofclosedaccounts")
    private Integer noofclosedaccounts;
    //@JsonProperty("noofactiveaccounts")
    private Integer noofactiveaccounts;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2192343451727716584L;

    //@JsonProperty("totalresponses")
    public Integer getTotalresponses() {
        return totalresponses;
    }

    //@JsonProperty("totalresponses")
    public void setTotalresponses(Integer totalresponses) {
        this.totalresponses = totalresponses;
    }

    public Primarysummary_ withTotalresponses(Integer totalresponses) {
        this.totalresponses = totalresponses;
        return this;
    }

    //@JsonProperty("noofothermfis")
    public Integer getNoofothermfis() {
        return noofothermfis;
    }

    //@JsonProperty("noofothermfis")
    public void setNoofothermfis(Integer noofothermfis) {
        this.noofothermfis = noofothermfis;
    }

    public Primarysummary_ withNoofothermfis(Integer noofothermfis) {
        this.noofothermfis = noofothermfis;
        return this;
    }

    //@JsonProperty("noofdefaultaccounts")
    public Integer getNoofdefaultaccounts() {
        return noofdefaultaccounts;
    }

    //@JsonProperty("noofdefaultaccounts")
    public void setNoofdefaultaccounts(Integer noofdefaultaccounts) {
        this.noofdefaultaccounts = noofdefaultaccounts;
    }

    public Primarysummary_ withNoofdefaultaccounts(Integer noofdefaultaccounts) {
        this.noofdefaultaccounts = noofdefaultaccounts;
        return this;
    }

    //@JsonProperty("noofownmfis")
    public Integer getNoofownmfis() {
        return noofownmfis;
    }

    //@JsonProperty("noofownmfis")
    public void setNoofownmfis(Integer noofownmfis) {
        this.noofownmfis = noofownmfis;
    }

    public Primarysummary_ withNoofownmfis(Integer noofownmfis) {
        this.noofownmfis = noofownmfis;
        return this;
    }

    //@JsonProperty("noofclosedaccounts")
    public Integer getNoofclosedaccounts() {
        return noofclosedaccounts;
    }

    //@JsonProperty("noofclosedaccounts")
    public void setNoofclosedaccounts(Integer noofclosedaccounts) {
        this.noofclosedaccounts = noofclosedaccounts;
    }

    public Primarysummary_ withNoofclosedaccounts(Integer noofclosedaccounts) {
        this.noofclosedaccounts = noofclosedaccounts;
        return this;
    }

    //@JsonProperty("noofactiveaccounts")
    public Integer getNoofactiveaccounts() {
        return noofactiveaccounts;
    }

    //@JsonProperty("noofactiveaccounts")
    public void setNoofactiveaccounts(Integer noofactiveaccounts) {
        this.noofactiveaccounts = noofactiveaccounts;
    }

    public Primarysummary_ withNoofactiveaccounts(Integer noofactiveaccounts) {
        this.noofactiveaccounts = noofactiveaccounts;
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

    public Primarysummary_ withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
