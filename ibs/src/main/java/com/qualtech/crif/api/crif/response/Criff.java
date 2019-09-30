
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
////@JsonPropertyOrder({
//    "indvreportfile"
//})
public class Criff implements Serializable
{

    //@JsonProperty("indvreportfile")
    private Indvreportfile indvreportfile;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 224584648968359022L;

    //@JsonProperty("indvreportfile")
    public Indvreportfile getIndvreportfile() {
        return indvreportfile;
    }

    //@JsonProperty("indvreportfile")
    public void setIndvreportfile(Indvreportfile indvreportfile) {
        this.indvreportfile = indvreportfile;
    }

    public Criff withIndvreportfile(Indvreportfile indvreportfile) {
        this.indvreportfile = indvreportfile;
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

    public Criff withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
