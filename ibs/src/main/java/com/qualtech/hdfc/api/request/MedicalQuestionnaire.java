
package com.qualtech.hdfc.api.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({
    "questionnaireId",
    "response"
})
@JsonIgnoreProperties(ignoreUnknown=true)
@Embeddable
public class MedicalQuestionnaire implements Serializable {

    @JsonProperty("questionnaireId")
    private String questionnaireId;
    @JsonProperty("response")
    @Embedded
    private Response response;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public MedicalQuestionnaire() {
    }

    /**
     * 
     * @param response
     * @param questionnaireId
     */
    public MedicalQuestionnaire(String questionnaireId, Response response) {
        super();
        this.questionnaireId = questionnaireId;
        this.response = response;
    }

    @JsonProperty("questionnaireId")
    public String getQuestionnaireId() {
        return questionnaireId;
    }

    @JsonProperty("questionnaireId")
    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    @JsonProperty("response")
    public Response getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(Response response) {
        this.response = response;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
