package com.qualtech.equifax.api.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.NDC;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qualtech.equifax.api.db.DBEquifax;
import com.qualtech.equifax.api.request.EquifaxAPI_EVDRRequest;
import com.qualtech.equifax.api.request.EquifaxAPI_VIDRequest;
import com.qualtech.equifax.api.request.EquifaxApiRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;
import com.qualtech.equifax.api.service.EquifaxEVDRService;
import com.qualtech.equifax.api.service.EquifaxService;
import com.qualtech.equifax.api.service.EquifaxVIDService;
import com.qualtech.equifax.api.utils.UniqueId;

@Controller
@RequestMapping("/equifax/api")
public class EquiFaxRestController 
{
	Logger logger = Logger.getLogger(EquiFaxRestController.class.getName());
	@Autowired DBEquifax dbEquifax;
	@Autowired EquifaxService equifaxService;
	@Autowired EquifaxVIDService equifaxvidservice;
	@Autowired EquifaxEVDRService evdrService;
	@RequestMapping(value ="/v1/equifaxrequest", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public EquifaxApiResponse equifaxRequestAction(@RequestBody EquifaxApiRequest equifaxApiRequest) 
	{
		ObjectMapper om = new ObjectMapper();
		String uniqueId = UniqueId.getUniqueId();
		EquifaxApiResponse equifaxApiResponse=null;
		
		logger.info("EquiFaxRestController || equifaxRequestAction() || Controller STARTS");
		try 
		{
			NDC.push("equifaxrequest : "+uniqueId);
			NDC.push(equifaxApiRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+ om.writeValueAsString(equifaxApiRequest));
		}
		catch(Exception ex)
		{
			logger.error("EquiFaxRestController || equifaxRequestAction() || Exception While mapping response header : "+ex);
		}
		try 
		{
			equifaxApiResponse = equifaxService.callEquifaxRequest(equifaxApiRequest,"PCS");
			
		}
		catch (Exception ex) 
		{
			logger.error("EquiFaxRestController || equifaxRequestAction() || Exception Occurs " + ex);
		}
		finally
		{
			NDC.pop();
			NDC.pop();
		}
		
		logger.info("EquiFaxRestController || equifaxRequestAction() || Controller ENDS");
		return equifaxApiResponse;
	}
	@RequestMapping(value ="/v1/equifaxMFIrequest", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public EquifaxApiResponse equifaxMFIRequestAction(@RequestBody EquifaxApiRequest eqifaxApiRequest) 
	{
		EquifaxApiResponse equifaxApiResponse=null;
		
		logger.info("EquiFaxRestController || equifaxMFIRequestAction() || Controller STARTS");
		try 
		{
			ObjectMapper om = new ObjectMapper();
			NDC.push("equifaxMFIrequest : "+UniqueId.getUniqueId());
			NDC.push(eqifaxApiRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+om.writeValueAsString(eqifaxApiRequest));
		}
		catch(Exception ex)
		{
			logger.error("EquiFaxRestController || equifaxMFIRequestAction() || Exception While mapping response header : "+ex);
		}
		try 
		{
			equifaxApiResponse = equifaxService.callEquifaxRequest(eqifaxApiRequest,"MCR");
		}
		catch (Exception ex) 
		{
			logger.error("EquiFaxRestController || equifaxMFIRequestAction() || Exception oocurs : " + ex);
		}
		finally
		{
			NDC.pop();
			NDC.pop();
		}
		logger.info("EquiFaxRestController || equifaxMFIRequestAction() || Controller ENDS");
		return equifaxApiResponse;
	}
	@RequestMapping(value ="/v1/equifaxrequest/verificationId", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public EquifaxApiResponse equifaxServiceForVID(@RequestBody EquifaxAPI_VIDRequest eqifaxApiRequest) 
	{
		
		EquifaxApiResponse equifaxApiResponse=null;
		ObjectMapper om = new ObjectMapper();
		
		logger.info("EquiFaxRestController || equifaxServiceForVID() || Controller STARTS");
		try 
		{
			
			NDC.push("equifaxrequest-verificationId : "+UniqueId.getUniqueId());
			NDC.push(eqifaxApiRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+om.writeValueAsString(eqifaxApiRequest));
		}
		catch(Exception ex)
		{
			logger.info("EquiFaxRestController || equifaxServiceForVID() || Exception While mapping response header :"+ex);
		}
		try 
		{
			equifaxApiResponse = equifaxvidservice.processEquifaxVIDRequest(eqifaxApiRequest);
		}
		catch (Exception ex) 
		{
			logger.error("EquiFaxRestController || equifaxServiceForVID() || Exception occurs : " + ex);
		}
		finally
		{
			NDC.pop();
			NDC.pop();
		}
		logger.info("EquiFaxRestController || equifaxServiceForVID() || Controller ENDS");
		return equifaxApiResponse;
	}
	@RequestMapping(value ="/v1/equifaxrequest/enhanceVerificationId", method = RequestMethod.POST, consumes="application/json", produces = "application/json")
	@ResponseBody public EquifaxApiResponse equifaxServiceForEVDR(@RequestBody EquifaxAPI_EVDRRequest eqifaxApiEvdrRequest) 
	{
		
		EquifaxApiResponse equifaxEVDRResponse=null;
		ObjectMapper om = new ObjectMapper();
		String uniqueId = UniqueId.getUniqueId();
		
		logger.info("EquiFaxRestController || equifaxServiceForEVDR() || Controller STARTS");
		try 
		{
			NDC.push("equifaxrequest-enhanceVerificationId : "+uniqueId);
			NDC.push(eqifaxApiEvdrRequest.getHeader().getCorrelationId());
			logger.info("RequestJson :: "+om.writeValueAsString(eqifaxApiEvdrRequest));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.info("EquiFaxRestController || equifaxServiceForEVDR() || Error While mapping response header : "+ex);
		}
		try 
		{                                     
			equifaxEVDRResponse = evdrService.processEquifaxVIDRequest(eqifaxApiEvdrRequest);
		}
		catch (Exception ex) 
		{
			logger.error("EquiFaxRestController || equifaxServiceForEVDR() || Exception occurs : " + ex);
		}
		finally
		{
			NDC.pop();
			NDC.pop();
		}
		logger.info("EquiFaxRestController || equifaxServiceForEVDR() || Controller ENDS");
		return equifaxEVDRResponse;
	}

}
