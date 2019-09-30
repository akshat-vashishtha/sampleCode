
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
//    "dateofrequest",
//    "reportid",
//    "dateofissue",
//    "batchid",
//    "preparedforid"
//})
public class Header implements Serializable
{

    //@JsonProperty("dateofrequest")
    private String dateofrequest;
    //@JsonProperty("reportid")
    private String reportid;
    //@JsonProperty("dateofissue")
    private String dateofissue;
    //@JsonProperty("batchid")
    private Integer batchid;
    //@JsonProperty("preparedforid")
    private String preparedforid;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1744697944121113325L;

    //@JsonProperty("dateofrequest")
    public String getDateofrequest() {
        return dateofrequest;
    }

    //@JsonProperty("dateofrequest")
    public void setDateofrequest(String dateofrequest) {
        this.dateofrequest = dateofrequest;
    }

    public Header withDateofrequest(String dateofrequest) {
        this.dateofrequest = dateofrequest;
        return this;
    }

    //@JsonProperty("reportid")
    public String getReportid() {
        return reportid;
    }

    //@JsonProperty("reportid")
    public void setReportid(String reportid) {
        this.reportid = reportid;
    }

    public Header withReportid(String reportid) {
        this.reportid = reportid;
        return this;
    }

    //@JsonProperty("dateofissue")
    public String getDateofissue() {
        return dateofissue;
    }

    //@JsonProperty("dateofissue")
    public void setDateofissue(String dateofissue) {
        this.dateofissue = dateofissue;
    }

    public Header withDateofissue(String dateofissue) {
        this.dateofissue = dateofissue;
        return this;
    }

    //@JsonProperty("batchid")
    public Integer getBatchid() {
        return batchid;
    }

    //@JsonProperty("batchid")
    public void setBatchid(Integer batchid) {
        this.batchid = batchid;
    }

    public Header withBatchid(Integer batchid) {
        this.batchid = batchid;
        return this;
    }

    //@JsonProperty("preparedforid")
    public String getPreparedforid() {
        return preparedforid;
    }

    //@JsonProperty("preparedforid")
    public void setPreparedforid(String preparedforid) {
        this.preparedforid = preparedforid;
    }

    public Header withPreparedforid(String preparedforid) {
        this.preparedforid = preparedforid;
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

    public Header withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
