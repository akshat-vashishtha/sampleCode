package com.qualtech.experiankickoff.api.service;

import com.qualtech.experiankickoff.api.common.dto.ExperianRequest;
import com.qualtech.experiankickoff.api.common.dto.MaskExperianRequest;
import com.qualtech.experiankickoff.api.request.AuthExperianRequest;
import com.qualtech.experiankickoff.api.request.ExperianGenQuestionRequest;
import com.qualtech.experiankickoff.api.request.ExperianKickoffReqRes;
import com.qualtech.experiankickoff.api.response.AuthExperianResponse;
import com.qualtech.experiankickoff.api.response.ExperianGenQuestionResponse;
import com.qualtech.experiankickoff.api.response.ExperianMaskResponse;
import com.qualtech.experiankickoff.api.response.ExperianResponse;

public interface ExperianService {

	public ExperianResponse experianrequestService(ExperianRequest experianRequest,ExperianKickoffReqRes reqRes);
	public ExperianMaskResponse experianMaskService(MaskExperianRequest experianRequest,ExperianKickoffReqRes reqRes);
	public AuthExperianResponse experianAuthService(AuthExperianRequest experianRequest, ExperianKickoffReqRes reqRes);
	public ExperianGenQuestionResponse experianGenService(ExperianGenQuestionRequest experianRequest, ExperianKickoffReqRes reqRes);
}
