package com.qualtech.multibureau.api.controller;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qualtech.api.db.ServiceMaster;
import com.qualtech.api.db.UniqueId;
import com.qualtech.multibureau.api.common.dto.AllBureauDto;
import com.qualtech.multibureau.api.common.dto.BureauRequest;
import com.qualtech.multibureau.api.db.DBBureau;
import com.qualtech.multibureau.api.request.BureauFullReqRes;
import com.qualtech.multibureau.api.request.BureauReqRes;
import com.qualtech.multibureau.api.response.BureauResponse;
import com.qualtech.multibureau.api.response.BureauResponsePayload;
import com.qualtech.multibureau.api.response.MessageInfo;
import com.qualtech.multibureau.api.service.MultiBureauService;
import com.qualtech.multibureau.api.util.PDFConvertBureau;

@Controller
@RequestMapping("/multibureau/api")
public class MultiBureauRestWebService {

	static Logger logger = Logger.getLogger(MultiBureauRestWebService.class.getName());
	
	@Autowired
	MultiBureauService bureauService;
	@Autowired
	DBBureau dbBureau;
	
	@Autowired
	PDFConvertBureau fileConvert;
	

	@RequestMapping(value = "/v1/requestMultiBureau", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public BureauResponse bureauProcess(@RequestBody BureauRequest apiRequest) {

		BureauResponse bRes = null;
		ObjectMapper om = new ObjectMapper();
		BureauReqRes reqRes = new BureauReqRes();
		final BureauFullReqRes fullReqRes = new BureauFullReqRes();
		Calendar cal = Calendar.getInstance();
		MessageInfo msgInfo = null;
		ServiceMaster service = null;
		String uniqueId = UniqueId.getUniqueId();
		try {
			service = dbBureau.getServiceCredential("/v1/requestMultiBureau");
			NDC.push("MULTI BUREAU : "+apiRequest.getHeader().getCorrelationId());
			logger.info("MultiBureauRestWebService || Controller STARTS :: RequestJson :: " + om.writeValueAsString(apiRequest));
		} catch (Exception ex) {
			logger.error("Error While mapping response header : " + ex);
		}
		try {

			reqRes.setUniqueId(uniqueId);
			reqRes.setRequest(om.writeValueAsString(apiRequest));
			reqRes.setTag(service.getService_tag());
			reqRes.setCorelationid(apiRequest.getHeader().getCorrelationId());
			reqRes.setAppid(apiRequest.getHeader().getAppId());
			reqRes.setToken(apiRequest.getHeader().getToken());
			reqRes.setCreatedTime(cal.getTime());
		} catch (Exception ex) {
			logger.info("Error While setting fianl req,res from service : " + ex);
		}
		try {
			AllBureauDto kDto=null;
			bRes = bureauService.bureauService(apiRequest, reqRes);
			msgInfo = bRes.getMsgInfo();
//			if (bRes.getMsgInfo().getCode() != null) {
//				if (bRes.getMsgInfo().getCode().equals("200")) {
//					if (bRes.getPayload() != null) {

				kDto =fileConvert.getPdfByteArrayQC(bRes,apiRequest,service.getPdf_name());

				if (kDto != null) 
				{
					if(bRes!=null && bRes.getPayload()!=null) 
					{
					bRes.getPayload().setPdfPath(kDto.getPdfPath());
					bRes.getPayload().setByteArray(kDto.getByteArray());
					}else 
					{
						BureauResponsePayload payload=new BureauResponsePayload();
						payload.setByteArray(kDto.getByteArray());
						payload.setPdfPath(kDto.getPdfPath());
						bRes.setPayload(payload);
					}
				}
//			}
//			}
//			}
			try {
				reqRes.setStatus(msgInfo.getStatus());
				reqRes.setUniqueId(uniqueId);
				reqRes.setResponse(om.writeValueAsString(bRes));
				reqRes.setUpdated_Time(cal.getTime());
				//rq.setPdfPath(pdfPath);
				final AllBureauDto dto=kDto;
				fullReqRes.setReqRes(reqRes);
				fullReqRes.setBureauRequest(apiRequest);
				fullReqRes.setBureauResponse(bRes);

				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							saveBureauReqRes(fullReqRes,dto);
						} catch (Exception e) {
							logger.error("There is no data saved in database : " + e);
						}
					}
				}).start();

			} catch (Exception e) {
				logger.error("Error while saving bureau req res:" + e);
			}
		} catch (Exception ex) {
			logger.error("Error While getting response from service : " + ex);
		}

		finally {
			NDC.pop();
			
		}

		return bRes;
	}

	protected int saveBureauReqRes(BureauFullReqRes fullReqRes,AllBureauDto dto) {
		logger.info("MULTI BUREAU  :: insertBureauReqRes():: START");
		int count = dbBureau.insertBureauReqRes(fullReqRes,dto);
		logger.info("MULTI BUREAU:: insertBureauReqRes() :: END");
		return count;
	}

}
