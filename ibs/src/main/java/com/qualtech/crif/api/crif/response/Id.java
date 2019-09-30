
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
//    "type",
//    "value"
//})
public class Id implements Serializable
{

    //@JsonProperty("type")
    private String type;
    //@JsonProperty("value")
    private String value;
   // @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1815245855845525128L;

    //@JsonProperty("type")
    public String getType() {
        return type;
    }

    //@JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Id withType(String type) {
        this.type = type;
        return this;
    }

    //@JsonProperty("value")
    public String getValue() {
        return value;
    }

    //@JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    public Id withValue(String value) {
        this.value = value;
        return this;
    }

   // @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    //@JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Id withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
