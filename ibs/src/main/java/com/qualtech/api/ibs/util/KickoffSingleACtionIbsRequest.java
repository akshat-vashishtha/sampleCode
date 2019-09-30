package com.qualtech.api.ibs.util;

import java.io.Serializable;
//@JsonIgnoreProperties(ignoreUnknown=true)
public class KickoffSingleACtionIbsRequest implements Serializable {
	
	
	    private String clientName;
	    private String allowInput;
	    private String allowEdit;
	    private String allowCaptcha;
	    private String allowConsent;
	    private String allowConsent_additional;
	    private String allowEmailVerify;
	    private String allowVoucher;
	    private String voucherCode;
	    private String noValidationByPass;
	    private String emailConditionalByPass;
	    private String flatno;
	    private String buildingName;
	    private String roadName;
	    private String reason;
	    
	    
		public String getClientName() {
			return clientName;
		}


		public void setClientName(String clientName) {
			this.clientName = clientName;
		}


		public String getAllowInput() {
			return allowInput;
		}


		public void setAllowInput(String allowInput) {
			this.allowInput = allowInput;
		}


		public String getAllowEdit() {
			return allowEdit;
		}


		public void setAllowEdit(String allowEdit) {
			this.allowEdit = allowEdit;
		}


		public String getAllowCaptcha() {
			return allowCaptcha;
		}


		public void setAllowCaptcha(String allowCaptcha) {
			this.allowCaptcha = allowCaptcha;
		}


		public String getAllowConsent() {
			return allowConsent;
		}


		public void setAllowConsent(String allowConsent) {
			this.allowConsent = allowConsent;
		}


		public String getAllowConsent_additional() {
			return allowConsent_additional;
		}


		public void setAllowConsent_additional(String allowConsent_additional) {
			this.allowConsent_additional = allowConsent_additional;
		}


		public String getAllowEmailVerify() {
			return allowEmailVerify;
		}


		public void setAllowEmailVerify(String allowEmailVerify) {
			this.allowEmailVerify = allowEmailVerify;
		}


		public String getAllowVoucher() {
			return allowVoucher;
		}


		public void setAllowVoucher(String allowVoucher) {
			this.allowVoucher = allowVoucher;
		}


		public String getVoucherCode() {
			return voucherCode;
		}


		public void setVoucherCode(String voucherCode) {
			this.voucherCode = voucherCode;
		}


		public String getNoValidationByPass() {
			return noValidationByPass;
		}


		public void setNoValidationByPass(String noValidationByPass) {
			this.noValidationByPass = noValidationByPass;
		}


		public String getEmailConditionalByPass() {
			return emailConditionalByPass;
		}


		public void setEmailConditionalByPass(String emailConditionalByPass) {
			this.emailConditionalByPass = emailConditionalByPass;
		}


		public String getFlatno() {
			return flatno;
		}


		public void setFlatno(String flatno) {
			this.flatno = flatno;
		}


		public String getBuildingName() {
			return buildingName;
		}


		public void setBuildingName(String buildingName) {
			this.buildingName = buildingName;
		}


		public String getRoadName() {
			return roadName;
		}


		public void setRoadName(String roadName) {
			this.roadName = roadName;
		}


		public String getReason() {
			return reason;
		}


		public void setReason(String reason) {
			this.reason = reason;
		}


		@Override
		public String toString() {
			return "KickoffSingleACtionIbsRequest [clientName=" + clientName + ", allowInput=" + allowInput
					+ ", allowEdit=" + allowEdit + ", allowCaptcha=" + allowCaptcha + ", allowConsent=" + allowConsent
					+ ", allowConsent_additional=" + allowConsent_additional + ", allowEmailVerify=" + allowEmailVerify
					+ ", allowVoucher=" + allowVoucher + ", voucherCode=" + voucherCode + ", noValidationByPass="
					+ noValidationByPass + ", emailConditionalByPass=" + emailConditionalByPass + ", flatno=" + flatno
					+ ", buildingName=" + buildingName + ", roadName=" + roadName + ", reason=" + reason + "]";
		}
	    
	    

}
