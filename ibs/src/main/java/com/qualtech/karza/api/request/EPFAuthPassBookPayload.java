package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_EPFAUTHPASSBOOK_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EPFAuthPassBookPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
        private String uniqueid;
		private String request_id;
		private String otp;
		private String corelationid;
		@Column(name="REQUEST_ID")
		public String getRequest_id() {
			return request_id;
		}
		public void setRequest_id(String request_id) {
			this.request_id = request_id;
		}
		@Column(name="OTP")
		public String getOtp() {
			return otp;
		}
		public void setOtp(String otp) {
			this.otp = otp;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "EPFAuthPassBookPayload [request_id=" + request_id + ", otp=" + otp + "]";
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
