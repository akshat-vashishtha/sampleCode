
package com.qualtech.api.ibs.util;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({
    "questionnaireId",
    "response"
})
@JsonIgnoreProperties(ignoreUnknown=true)
public class IBSMedicalQuestionnaire {

    @JsonProperty("questionnaireId")
    private String questionnaireId;
    @JsonProperty("response")
    private IbsHDFCResponse response;
   


    @JsonProperty("questionnaireId")
    public String getQuestionnaireId() {
        return questionnaireId;
    }

    @JsonProperty("questionnaireId")
    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    @JsonProperty("response")
    public IbsHDFCResponse getResponse() {
        return response;
    }

    @JsonProperty("response")
    public void setResponse(IbsHDFCResponse response) {
        this.response = response;
    }

   

}
