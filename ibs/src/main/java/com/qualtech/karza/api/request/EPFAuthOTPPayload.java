package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_EPFAUTHOTPREQUEST_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EPFAuthOTPPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
        private String uniqueid;
        private String corelationid;
		private String uanid;
		private String consent;
		private String mobile_no;
		@Column(name="UANID")
		public String getUanid() {
			return uanid;
		}
		public void setUanid(String uanid) {
			this.uanid = uanid;
		}
		@Column(name="CONSENT")
		public String getConsent() {
			return consent;
		}
		public void setConsent(String consent) {
			this.consent = consent;
		}
		@Column(name="MOBILE_NO")
		public String getMobile_no() {
			return mobile_no;
		}
		public void setMobile_no(String mobile_no) {
			this.mobile_no = mobile_no;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "EPFAuthOTPPayload [uanid=" + uanid + ", consent=" + consent + ", mobile_no=" + mobile_no + "]";
		}
		@JsonIgnore
		@Id
		@Column(name="UNIQUEID")
		public String getUniqueid() {
			return uniqueid;
		}
		public void setUniqueid(String uniqueid) {
			this.uniqueid = uniqueid;
		}
		@JsonIgnore
		@Column(name="CORELATIONID")
		public String getCorelationid() {
			return corelationid;
		}
		public void setCorelationid(String corelationid) {
			this.corelationid = corelationid;
		}
	
	 
	 
}
