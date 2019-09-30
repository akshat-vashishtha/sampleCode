
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
//    "scorecomments",
//    "scoretype",
//    "scorevalue"
//})
public class Score implements Serializable
{

    //@JsonProperty("scorecomments")
    private String scorecomments;
    //@JsonProperty("scoretype")
    private String scoretype;
    //@JsonProperty("scorevalue")
    private Integer scorevalue;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6051193499923675128L;

    //@JsonProperty("scorecomments")
    public String getScorecomments() {
        return scorecomments;
    }

    //@JsonProperty("scorecomments")
    public void setScorecomments(String scorecomments) {
        this.scorecomments = scorecomments;
    }

    public Score withScorecomments(String scorecomments) {
        this.scorecomments = scorecomments;
        return this;
    }

    //@JsonProperty("scoretype")
    public String getScoretype() {
        return scoretype;
    }

    //@JsonProperty("scoretype")
    public void setScoretype(String scoretype) {
        this.scoretype = scoretype;
    }

    public Score withScoretype(String scoretype) {
        this.scoretype = scoretype;
        return this;
    }

    //@JsonProperty("scorevalue")
    public Integer getScorevalue() {
        return scorevalue;
    }

    //@JsonProperty("scorevalue")
    public void setScorevalue(Integer scorevalue) {
        this.scorevalue = scorevalue;
    }

    public Score withScorevalue(Integer scorevalue) {
        this.scorevalue = scorevalue;
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

    public Score withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
