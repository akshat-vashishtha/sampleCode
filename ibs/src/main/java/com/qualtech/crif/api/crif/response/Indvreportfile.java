
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
//    "indvreports"
//})
public class Indvreportfile implements Serializable
{

   // @JsonProperty("indvreports")
    private Indvreports indvreports;
   // @JsonIgnore
    public Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -5095484137273937424L;

    //@JsonProperty("indvreports")
    public Indvreports getIndvreports() {
        return indvreports;
    }

    //@JsonProperty("indvreports")
    public void setIndvreports(Indvreports indvreports) {
        this.indvreports = indvreports;
    }

    public Indvreportfile withIndvreports(Indvreports indvreports) {
        this.indvreports = indvreports;
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

    public Indvreportfile withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
