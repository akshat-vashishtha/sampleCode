
package com.qualtech.hdfc.api.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@Table(name="IB_HDFC_REQ_META_DATA")
@JsonIgnoreProperties(ignoreUnknown=true)
public class HdfcRequestPayload implements Serializable{
	@Id
	@JsonIgnore
	private long uniqueid;
	@Embedded
    private MetaData metaData;
	@OneToOne (fetch=FetchType.LAZY,mappedBy="customerDetailsmap", cascade=CascadeType.ALL)
    private CustomerDetails customerDetails;
    
    

    public long getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(long uniqueid) {
		this.uniqueid = uniqueid;
	}
	@Transient
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public HdfcRequestPayload() {
    }

    /**
     * 
     * @param customerDetails
     * @param metaData
     */
    public HdfcRequestPayload(MetaData metaData, CustomerDetails customerDetails) {
        super();
        this.metaData = metaData;
        this.customerDetails = customerDetails;
    }

    //@JsonProperty("metaData")
    public MetaData getMetaData() {
        return metaData;
    }

    //@JsonProperty("metaData")
    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    //@JsonProperty("customerDetails")
    public CustomerDetails getCustomerDetails() {
    	if(customerDetails!=null) {
    		customerDetails.setCustomerDetailsmap(this);
		}
        return customerDetails;
    }

    //@JsonProperty("customerDetails")
    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }

    //@JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

   // @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
