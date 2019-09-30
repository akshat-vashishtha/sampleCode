
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
//    "variation"
//})
public class Phonenumbervariations implements Serializable
{

    //@JsonProperty("variation")
    private Variation_ variation;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4777708008518955623L;

    //@JsonProperty("variation")
    public Variation_ getVariation() {
        return variation;
    }

    //@JsonProperty("variation")
    public void setVariation(Variation_ variation) {
        this.variation = variation;
    }

    public Phonenumbervariations withVariation(Variation_ variation) {
        this.variation = variation;
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

    public Phonenumbervariations withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
