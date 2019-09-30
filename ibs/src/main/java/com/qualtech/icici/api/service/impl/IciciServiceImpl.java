package com.qualtech.icici.api.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.api.ibs.util.Header;
import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.db.DBIcici;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.response.EbiResponse;
import com.qualtech.icici.api.response.IciciInfo;
import com.qualtech.icici.api.response.MsgInfo;
import com.qualtech.icici.api.response.PremCalResponse;
import com.qualtech.icici.api.response.PremCalResponsePayload;
import com.qualtech.icici.api.response.PremiumSummary;
import com.qualtech.icici.api.response.ResponseHeader;
import com.qualtech.icici.api.service.ICICIService;
import com.qualtech.icici.api.utils.IciciSoapRequestXml;
import com.qualtech.icici.api.utils.StringConstants;


@Service
public class IciciServiceImpl implements ICICIService
{
	static Logger logger = Logger.getLogger(IciciServiceImpl.class.getName());
	@Autowired PropertyFile resProp;
	@Autowired DBIcici dbIcici;
	static ResourceBundle res=null;
	
	static
	{
		try 
		{
			res = ResourceBundle.getBundle("hardcode");
		}
		catch(Exception ex)
		{
		}
	}

	@Override
	public PremCalResponse premiumCalculationService(RequestIBs requestIBs, IciciReqRes rq) {
		logger.info("IciciServiceImpl || premiumCalculationService() || STARTS");
		PremCalResponse premCalResponse = new PremCalResponse();
		PremCalResponsePayload payload = new PremCalResponsePayload();
		EbiResponse ebiResponse=new EbiResponse();
		PremiumSummary premSummary=new PremiumSummary();
		ResponseHeader responseHeader=new ResponseHeader();
		MsgInfo msgInfo = new MsgInfo();
		IciciInfo iciciInfo=new IciciInfo();
		IciciSoapRequestXml soapRequestXml=new IciciSoapRequestXml();
		
		String response = null;
		String hardCodedEnv=getHardCoded();
		
		String errorCode = "E01";
		String errorMessage = "Failure";
		try 
		{
			String apiURL = resProp.getString("com.qualtech.icici.Request.premCalc.url");
			
			if (requestIBs != null && requestIBs.getPayload()!=null
					&& !requestIBs.getPayload().equals("")
					&& requestIBs.getPayload().getIciciRequest()!=null
							&& !requestIBs.getPayload().getIciciRequest().equals("")
							&& checkValidation(requestIBs.getHeader())) 
			{
						responseHeader.setAppId(requestIBs.getHeader().getAppId());
						responseHeader.setCorrelationId(requestIBs.getHeader().getCorrelationId());
						responseHeader.setMsgVersion(requestIBs.getHeader().getMsgVersion());
						responseHeader.setToken(requestIBs.getHeader().getToken());
						
						String xmlInput=soapRequestXml.getRequestXml(requestIBs.getPayload().getIciciRequest().getPremiumCalculation());
						if (("Y").equalsIgnoreCase(hardCodedEnv))
						{
							iciciInfo.setResponse(res.getString("ICICI_Premium_Calculation"));
							iciciInfo.setResponseCode(200);
							response = iciciInfo.getResponse();
						} 
						else
						{
							iciciInfo = httpSoapCall(xmlInput,apiURL);
							response = iciciInfo.getResponse();
						}
						setInternalReqRes(rq, xmlInput, response);
						if (response!=null && !response.equalsIgnoreCase(""))
						{
								try 
									{
										response=iciciInfo.getResponse().replaceAll("xml version=\"1.0\" encoding=\"utf-8\"", "")
												.replaceAll("soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"", "")
												.replace("<soap:Body>", "")
												.replace("<GenerateEBIDigitalResponse xmlns=\"http://tempuri.org/\">", "")
												.replace("<GenerateEBIDigitalResult>", "")
												.replace("</GenerateEBIDigitalResult>", "")
												.replace("</GenerateEBIDigitalResponse>", "")
												.replace("</soap:Body>", "")
												.replace("</soap:Envelope>", "")
												.replaceAll("\\?", "").replaceAll("\\<", "").replaceAll("\\>", "");							
										response=response.replaceAll("&lt;", "<").replaceAll("&gt;", ">")	;								
										JSONObject jsonObj = XML.toJSONObject(response);
										ObjectMapper om = new ObjectMapper();
										om.configure(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
										if (jsonObj.has("EBIResponse")  )
										{
											JSONObject ebiRes = jsonObj.getJSONObject("EBIResponse");
											ebiResponse.setFirstName(ebiRes.has("FirstName")?ebiRes.get("FirstName").toString():"");
											ebiResponse.setLastName(ebiRes.has("LastName")?ebiRes.get("LastName").toString():"");
											ebiResponse.setDateOfBirth(ebiRes.has("DateOfBirth")?ebiRes.get("DateOfBirth").toString():"");
											ebiResponse.setGender(ebiRes.has("Gender")?ebiRes.get("Gender").toString():"");
											ebiResponse.setMaritalStatus(ebiRes.has("MaritalStatus")?ebiRes.get("MaritalStatus").toString():"");
											ebiResponse.setAgeProof(ebiRes.has("AgeProof")?ebiRes.get("AgeProof").toString():"");
											ebiResponse.setIllustration(ebiRes.has("Illustration")?ebiRes.get("Illustration").toString():"");
											if( jsonObj.getJSONObject("EBIResponse").has("PremiumSummary")){
												JSONObject premiumSummary = ebiRes.getJSONObject("PremiumSummary");
												premSummary.setProductType(premiumSummary.has("ProductType")?premiumSummary.get("ProductType").toString():"");
												premSummary.setProductName(premiumSummary.has("ProductName")?premiumSummary.get("ProductName").toString():"");
												premSummary.setProductCode(premiumSummary.has("ProductCode")?premiumSummary.get("ProductCode").toString():"");
												premSummary.setState(premiumSummary.has("State")?premiumSummary.get("State").toString():"");
												premSummary.setCity(premiumSummary.has("City")?premiumSummary.get("City").toString():"");
												premSummary.setModeOfPayment(premiumSummary.has("ModeOfPayment")?premiumSummary.get("ModeOfPayment").toString():"");//ModeOfPayment
												premSummary.setPremiumPaymentOption(premiumSummary.has("PremiumPaymentOption")?premiumSummary.get("PremiumPaymentOption").toString():"");//PremiumPaymentOption
												premSummary.setPremiumPaymentTerm(premiumSummary.has("PremiumPaymentTerm")?premiumSummary.get("PremiumPaymentTerm").toString():"");//PremiumPaymentTerm
												premSummary.setMasterCode(premiumSummary.has("MasterCode")?premiumSummary.get("MasterCode").toString():"");//MasterCode
												premSummary.setAge(premiumSummary.has("Age")?premiumSummary.get("Age").toString():"");//Age
												premSummary.setAnnualPremium(premiumSummary.has("AnnualPremium")?premiumSummary.get("AnnualPremium").toString():"");//AnnualPremium
												premSummary.setTerm(premiumSummary.has("Term")?premiumSummary.get("Term").toString():"");//Term
												premSummary.setDeathBenefit(premiumSummary.has("DeathBenefit")?premiumSummary.get("DeathBenefit").toString():"");//DeathBenefit
												premSummary.setBasePremium(premiumSummary.has("BasePremium")?premiumSummary.get("BasePremium").toString():"");//BasePremium
												premSummary.setCoverageOption(premiumSummary.has("CoverageOption")?premiumSummary.get("CoverageOption").toString():"");//CoverageOption
												premSummary.setAdbrPremium(premiumSummary.has("ADBRPremium")?premiumSummary.get("ADBRPremium").toString():"");//ADBRPremium
												premSummary.setAbrPremium(premiumSummary.has("ABRPremium")?premiumSummary.get("ABRPremium").toString():"");//ABRPremium
												premSummary.setCirPremium(premiumSummary.has("CIRPremium")?premiumSummary.get("CIRPremium").toString():"");//CIRPremium
												premSummary.setIbrPremium(premiumSummary.has("IBRPremium")?premiumSummary.get("IBRPremium").toString():"");//IBRPremium
												premSummary.setWopPremium(premiumSummary.has("WOPPremium")?premiumSummary.get("WOPPremium").toString():"");//WOPPremium
												premSummary.setPremiumInstallment(premiumSummary.has("PremiumInstallment")?premiumSummary.get("PremiumInstallment").toString():"");//PremiumInstallment
												premSummary.setPremiumInstallmentWithTax(premiumSummary.has("PremiumInstallmentWithTax")?premiumSummary.get("PremiumInstallmentWithTax").toString():"");//PremiumInstallmentWithTax
												premSummary.setServiceTax(premiumSummary.has("ServiceTax")?premiumSummary.get("ServiceTax").toString():"");//ServiceTax
												premSummary.setEduCess(premiumSummary.has("EduCess")?premiumSummary.get("EduCess").toString():"");//EduCess
												premSummary.setKrishiKalyan(premiumSummary.has("KrishiKalyan")?premiumSummary.get("KrishiKalyan").toString():"");//KrishiKalyan
												premSummary.setTotalFirstPremium(premiumSummary.has("TotalFirstPremium")?premiumSummary.get("TotalFirstPremium").toString():"");//TotalFirstPremium
												premSummary.setTotalFirstPremiumShow(premiumSummary.has("TotalFirstPremiumShow")?premiumSummary.get("TotalFirstPremiumShow").toString():"");//TotalFirstPremiumShow
												premSummary.setAnnualPremiumwithoutTax(premiumSummary.has("AnnualPremiumwithoutTax")?premiumSummary.get("AnnualPremiumwithoutTax").toString():"");//AnnualPremiumwithoutTax
												premSummary.setAnnualPremiumWithTax(premiumSummary.has("AnnualPremiumWithTax")?premiumSummary.get("AnnualPremiumWithTax").toString():"");//AnnualPremiumWithTax
												premSummary.setAdbSumAssured(premiumSummary.has("ADBSumAssured")?premiumSummary.get("ADBSumAssured").toString():"");//ADBSumAssured
												premSummary.setCirSumAssured(premiumSummary.has("CIRSumAssured")?premiumSummary.get("CIRSumAssured").toString():"");//CIRSumAssured
												premSummary.setTpdSumAssured(premiumSummary.has("TPDSumAssured")?premiumSummary.get("TPDSumAssured").toString():"");//TPDSumAssured
												premSummary.setServiceTaxUS(premiumSummary.has("ServiceTax_US")?premiumSummary.get("ServiceTax_US").toString():"");//ServiceTax_US
												premSummary.setEduCessUS(premiumSummary.has("EduCess_US")?premiumSummary.get("EduCess_US").toString():"");//EduCess_US
												premSummary.setKrishiKalyanUS(premiumSummary.has("KrishiKalyan_US")?premiumSummary.get("KrishiKalyan_US").toString():"");//KrishiKalyan_US
												premSummary.setModalPremium(premiumSummary.has("ModalPremium")?premiumSummary.get("ModalPremium").toString():"");//ModalPremium
												premSummary.setSalesChannel(premiumSummary.has("SalesChannel")?premiumSummary.get("SalesChannel").toString():"");//SalesChannel
												premSummary.setStatisticalCode(premiumSummary.has("StatisticalCode")?premiumSummary.get("StatisticalCode").toString():"");//StatisticalCode
												premSummary.setBenefitOption(premiumSummary.has("BenefitOption")?premiumSummary.get("BenefitOption").toString():"");//BenefitOption
												premSummary.setLoanTenure(premiumSummary.has("LoanTenure")?premiumSummary.get("LoanTenure").toString():"");//LoanTenure
												premSummary.setLoanAmount(premiumSummary.has("LoanAmount")?premiumSummary.get("LoanAmount").toString():"");//LoanAmount
												ebiResponse.setPremiumSummary(premSummary);
											}
										payload.setEbiResponse(ebiResponse);
										premCalResponse.setPayload(payload);
										if(jsonObj.has("EBIResponse")  
												&& jsonObj.getJSONObject("EBIResponse").has("ErrorCode") 
												&& jsonObj.getJSONObject("EBIResponse").has("ErrorMessage")) {
											errorCode=jsonObj.getJSONObject("EBIResponse").getString("ErrorCode");
											errorMessage=jsonObj.getJSONObject("EBIResponse").getString("ErrorMessage");
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(StringConstants.MESSAGE200.toString());
											msgInfo.setCode(StringConstants.C_200.toString());
											msgInfo.setDescription(errorMessage +" :: with message code :: " + errorCode);
										}else {
											msgInfo.setStatus(StringConstants.SUCCESS.toString());
											msgInfo.setMessage(StringConstants.MESSAGE200.toString());
											msgInfo.setCode(StringConstants.C_200.toString());
											msgInfo.setDescription(StringConstants.MESSAGE200.toString());
										}
										}
										else {
											msgInfo.setStatus(StringConstants.FAILURE.toString());
											msgInfo.setCode(StringConstants.C_600.toString());
											msgInfo.setMessage(StringConstants.MESSAGE500.toString());
											msgInfo.setDescription(StringConstants.MESSAGE500.toString());
											logger.info("IciciServiceImpl || premiumCalculationService() || " + StringConstants.MESSAGE500.toString());
										}
									}
									catch (Exception ex) 
									{
										msgInfo.setStatus(StringConstants.FAILURE.toString());
										msgInfo.setMessage("" + StringConstants.MESSAGE600);
										msgInfo.setCode(StringConstants.C_500.toString());
										msgInfo.setDescription(StringConstants.MESSAGE600.toString());
										logger.info("error occured when convert data from xml to json : " + iciciInfo.getResponse());
										logger.error("IciciServiceImpl || || Exception occured : "+ex);
									}
									catch(Throwable t)
									{
										logger.error("IciciServiceImpl || || Error Occured : High Priority : Check it : "+t);
									}
							}
							else 
							{
								msgInfo.setStatus(StringConstants.FAILURE.toString());
								msgInfo.setCode(StringConstants.C_600.toString());
								msgInfo.setMessage(StringConstants.MESSAGE500.toString());
								msgInfo.setDescription(StringConstants.MESSAGE500.toString());
								logger.info("IciciServiceImpl || premiumCalculationService() || " + StringConstants.MESSAGE500.toString());
							}
		}else {
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setCode(StringConstants.C_601.toString());
			msgInfo.setMessage(StringConstants.MESSAGE601.toString());
			msgInfo.setDescription(StringConstants.MESSAGE601.toString());
			logger.info("IciciServiceImpl || premiumCalculationService() || " + StringConstants.MESSAGE601.toString());
		}
		}catch (Exception e) 
		{
			logger.error("We are in Exception while processing : " + e);
			msgInfo.setStatus(StringConstants.FAILURE.toString());
			msgInfo.setCode(StringConstants.C_500.toString());
			msgInfo.setMessage(StringConstants.MESSAGE600.toString());
			msgInfo.setDescription(StringConstants.MESSAGE600.toString());
			logger.info("IciciServiceImpl || premiumCalculationService() || " + StringConstants.MESSAGE600.toString());
		}
		premCalResponse.setMsgInfo(msgInfo);
		premCalResponse.setHeader(responseHeader);
		logger.info("IciciServiceImpl || premiumCalculationService() || ENDS");
		return premCalResponse;
		
	}

	private boolean checkValidation(Header header) {
		logger.info("IciciServiceImpl || checkValidation() || STARTS");
		if (header != null) {
			AuthValidator auth =dbIcici.validateAuth(header);
			if (auth.getAppid() == null) {
				logger.info("IciciServiceImpl || checkValidation() || ENDS");
				return true;
			} else {
				logger.info("IciciServiceImpl || checkValidation() || ENDS");
				return true;
			}
		} else {
			logger.info("IciciServiceImpl || checkValidation() || ENDS");
			return true;
		}
	}
	
	
	public String getHardCoded() {
		String hardCodedEnv="N";
		try 
		{
			hardCodedEnv = resProp.getString("com.icici.demo.development");	
			logger.info("Hard Coded Response generated : if not required kindly set it's value to N in configuration | present value is " + hardCodedEnv);
		}
		catch(Exception ex)
		{
			hardCodedEnv="N";
		}
		return hardCodedEnv;
	}
	
	public void setInternalReqRes(IciciReqRes rq, String xmlInput, String xmlOutput) {
		try 
		{
			String price=null;
			rq.setInternal_req(xmlInput);
			rq.setInternal_res(xmlOutput);
			
			if (xmlInput!=null && !("").equals(xmlInput))
			{
				price=resProp.getString("com.qualtech.icici.success.price");
			}else {
				price=resProp.getString("com.qualtech.icici.failure.price");
			}
			rq.setPrice(price);
		} 
		catch (Exception e)
		{
			logger.error("IciciServiceImpl || setInternalReqRes() || Error while updating ICICI internal req res:" + e);
		}
	}
	
	public  IciciInfo httpSoapCall(String reqXml, String serviceUrl)  {
		logger.info("IciciServiceImpl || httpCall() || STARTS");
		IciciInfo iciciInfo = new IciciInfo(); 
		try {
		
		URI uri = new URI(serviceUrl);
		URL url = uri.toURL();
		URLConnection connection = url.openConnection();
		OutputStream out = null;

		HttpURLConnection conn = (HttpURLConnection) connection;
		conn.setRequestMethod("POST");
		conn.setRequestProperty("accept", "text/xml");
		conn.setRequestProperty("Content-Type", "text/xml");
		conn.setConnectTimeout(240000);
		conn.setReadTimeout(420000);
		conn.setDoOutput(true);
		conn.setDoInput(true);
//		iciciInfo.setResponseCode(conn.getResponseCode());
		out = conn.getOutputStream();
		out.write(reqXml.getBytes());
		out.flush();
		
		BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
		String output = "";
		StringBuilder result = new StringBuilder();
		while ((output = br.readLine()) != null) {
			result.append(output);
		}
		br.close();
		conn.disconnect();
		iciciInfo.setResponse(result.toString());
		logger.info("IciciServiceImpl || httpCall() || ENDS");
			
		}catch(Exception e) {
			logger.error(" IciciServiceImpl || httpCall() || exce[ption ::" +e);
		}
		return iciciInfo;
	}
}
