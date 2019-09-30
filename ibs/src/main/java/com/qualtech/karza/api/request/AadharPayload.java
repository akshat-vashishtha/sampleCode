package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_AADHAR_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AadharPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
       
	    @Id
	    @JsonIgnore
	    private String uniqueId;
	    @Column
	    @JsonIgnore
        private String corelationId;
	    @Column(name="AADHAR_ID")
		private String aadhaarId;
	    @Column
		private String consent;
	    @Embedded
		private AadharDemographics demographics;
		
		
		public String getAadhaarId() {
			return aadhaarId;
		}
		public void setAadhaarId(String aadhaarId) {
			this.aadhaarId = aadhaarId;
		}
		public String getConsent() {
			return consent;
		}
		public void setConsent(String consent) {
			this.consent = consent;
		}
		public AadharDemographics getDemographics() {
			return demographics;
		}
		public void setDemographics(AadharDemographics demographics) {
			this.demographics = demographics;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		public String getUniqueId() {
			return uniqueId;
		}
		public void setUniqueId(String uniqueId) {
			this.uniqueId = uniqueId;
		}
		public String getCorelationId() {
			return corelationId;
		}
		public void setCorelationId(String corelationId) {
			this.corelationId = corelationId;
		}
		@Override
		public String toString() {
			return "AadharPayload [aadhaarId=" + aadhaarId + ", consent=" + consent + ", demographics=" + demographics
					+ "]";
		}
		
		
	
		
		
		

		
	 
	 
}
