package com.qualtech.icici.api.utils;

import com.qualtech.icici.api.request.IciciPdf;
import com.qualtech.icici.api.request.PolicyStatusPayload;
import com.qualtech.icici.api.request.PolicyStatusRequest;
import com.qualtech.icici.api.response.PolicyStatusResponse;

public interface IciciUtil {

	public String policyStatusIntReq(PolicyStatusPayload payload);
	public IciciPdf pdfPolicyStatus(PolicyStatusRequest policyStatusRequest, PolicyStatusResponse policyStatusResponse,
			String pdfName);
}
