package com.qualtech.hdfc.api.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.hdfc.api.request.ApiRequest;
import com.qualtech.hdfc.api.request.MedicalQuestionnaire;
import com.qualtech.hdfc.api.request.NomineeDetails;
import com.qualtech.hdfc.api.response.ApiResponseHdfcPayload;
import com.qualtech.hdfc.api.response.MsgInfo;
@Service
public class ConvertRequest implements ConvertRequestInt {
	Logger logger = Logger.getLogger(ConvertRequest.class.getName());
	@Autowired
	EncryptionUtil encryptionUtil;

	@Autowired PropertyFile resProp;

	public String hdfcRequest(ApiRequest hdfcRequest) {

		String partnerId = hdfcRequest.getPayload().getMetaData().getPartnerId();

		String encryptedAuthSalt = encryptionUtil.encryptString(partnerId);
		/*
		 * String serviceurl = res.getString("url");
		 * logger.info("SERVICE URL :-"+serviceurl); URL url = new URL(serviceurl); conn
		 * = (HttpURLConnection) url.openConnection();
		 * HttpsURLConnection.setFollowRedirects(true); conn.setDoInput(true);
		 * conn.setDoOutput(true); conn.setRequestMethod("POST");
		 * conn.setRequestProperty("Content-Type", "application/json");
		 */
		try{
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			if (hdfcRequest.getPayload() != null) {
				sb.append("\"metaData\": {");
				if (hdfcRequest.getPayload().getMetaData() != null) {
					sb.append("\"partnerId\": \"" + (hdfcRequest.getPayload().getMetaData().getPartnerId()!=null? hdfcRequest.getPayload().getMetaData().getPartnerId():"")+ "\",");
					sb.append("\"encryptedAuthSalt\": \"" + encryptedAuthSalt + "\",");
					sb.append("\"plan\": \"" + (hdfcRequest.getPayload().getMetaData().getPlan()!=null?hdfcRequest.getPayload().getMetaData().getPlan():"") + "\",");
					sb.append("\"prn\": \"" + UniqueId.prnGenerator() + "\",");
					sb.append("\"customerCommunication\": {");
					sb.append("\"sendMIFToEmail\": \""+resProp.getString("com.qualtech.hdfcRequest.sendMIFToEmail")+"\",");
					sb.append("\"sendMIFToSms\": \""+resProp.getString("com.qualtech.hdfcRequest.sendMIFToSms")+"\",");
					sb.append("\"sendCOIToEmail\": \""+resProp.getString("com.qualtech.hdfcRequest.sendCOIToEmail")+"\",");
					sb.append("\"sendCOIUrlToSms\": \""+resProp.getString("com.qualtech.hdfcRequest.sendCOIUrlToSms")+"\",");
					sb.append("\"sendCOIToSms\": \""+resProp.getString("com.qualtech.hdfcRequest.sendCOIToSms")+"\"");

					/*sb.append("\"sendMIFToSms\": \"Yes\",");
				sb.append("\"sendCOIToEmail\": \"Yes\",");
				sb.append("\"sendCOIUrlToSms\": \"Yes\",");
				sb.append("\"sendCOIToSms\": \"Yes\"");*/
					sb.append(" },");
					/*sb.append("\"action\": \"issue\",");
				sb.append("\"channel\": \"GOP\",");*/
					sb.append("\"action\": \""+resProp.getString("com.qualtech.hdfcRequest.action")+"\",");
					sb.append("\"channel\": \""+resProp.getString("com.qualtech.hdfcRequest.channel")+"\",");

					sb.append("\"ipAddress\":\"" +( hdfcRequest.getPayload().getMetaData().getIpAddress()!=null?hdfcRequest.getPayload().getMetaData().getIpAddress():"") + "\",");
					sb.append("\"domain\":\"" + (hdfcRequest.getPayload().getMetaData().getDomain()!=null?hdfcRequest.getPayload().getMetaData().getDomain():"" )+ "\",");
					if (hdfcRequest.getPayload().getMetaData().getPartnerData() != null) {
						sb.append("\"partnerData\": {");
						sb.append("\"fundingOption\": \""
								+ (hdfcRequest.getPayload().getMetaData().getPartnerData().getFundingOption()!=null?hdfcRequest.getPayload().getMetaData().getPartnerData().getFundingOption():"" )+ "\",");
						sb.append("\"sumAssuredType\": \""
								+( hdfcRequest.getPayload().getMetaData().getPartnerData().getSumAssuredType()!=null?hdfcRequest.getPayload().getMetaData().getPartnerData().getSumAssuredType():"" )+ "\"");
						sb.append(" }");
					}
				}
				sb.append(" },");
				sb.append("\"customerDetails\": {");
				if (hdfcRequest.getPayload().getCustomerDetails() != null) {
					sb.append("\"planId\": \"" +( hdfcRequest.getPayload().getCustomerDetails().getPlanId()!=null?hdfcRequest.getPayload().getCustomerDetails().getPlanId():"" )+ "\",");
					sb.append("\"partnerId\": \"" + (hdfcRequest.getPayload().getCustomerDetails().getPartnerId()!=null?hdfcRequest.getPayload().getCustomerDetails().getPartnerId():"" )+ "\",");
					sb.append("\"sumAssured\": " + (hdfcRequest.getPayload().getCustomerDetails().getSumAssured() )+ ",");
					sb.append("\"rider\": \"None\",");
					sb.append("\"dob\": \"" +( hdfcRequest.getPayload().getCustomerDetails().getDob()!=null?hdfcRequest.getPayload().getCustomerDetails().getDob():"" )+ "\",");
					sb.append("\"loanType\": \""+( hdfcRequest.getPayload().getCustomerDetails().getLoanType()!=null?hdfcRequest.getPayload().getCustomerDetails().getLoanType():"" )+ "\",");
					sb.append("\"loanDisbursement_date\": \""+( hdfcRequest.getPayload().getCustomerDetails().getLoanDisbursement_date()!=null?hdfcRequest.getPayload().getCustomerDetails().getLoanDisbursement_date():"" )+ "\",");
					sb.append("\"loanAmount\": \"" +( hdfcRequest.getPayload().getCustomerDetails().getLoanAmount()!=null ?hdfcRequest.getPayload().getCustomerDetails().getLoanAmount():"" )+ "\",");
					sb.append("\"basicPremium\": \"" +( hdfcRequest.getPayload().getCustomerDetails().getBasicPremium()!=null?hdfcRequest.getPayload().getCustomerDetails().getBasicPremium():"")+ "\",");
					sb.append("\"policyTerm\": \"" +( hdfcRequest.getPayload().getCustomerDetails().getPolicyTerm()!=null?hdfcRequest.getPayload().getCustomerDetails().getPolicyTerm():"" )+ "\",");
					sb.append("\"loanTenure\": \"" +( hdfcRequest.getPayload().getCustomerDetails().getLoanTenure()!=null?hdfcRequest.getPayload().getCustomerDetails().getLoanTenure():"" )+ "\",");
					sb.append("\"premiumPayable\": \"" +( hdfcRequest.getPayload().getCustomerDetails().getPremiumPayable()!=null?hdfcRequest.getPayload().getCustomerDetails().getPremiumPayable():"")+ "\",");
					sb.append("\"isAgreementGenerated\": \""+( hdfcRequest.getPayload().getCustomerDetails().getIsAgreementGenerated()!=null?hdfcRequest.getPayload().getCustomerDetails().getIsAgreementGenerated():"" )+ "\",");
					sb.append("\"personalDetails\": {"
							+ "");
					if (hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails() != null) {
						sb.append("\"title\": \""
								+( hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getTitle()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getTitle():"" )+ "\",");
						sb.append("\"gender\": \""
								+( hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getGender()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getGender():"" )+ "\",");
						sb.append("\"firstName\": \""
								+ (hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getFirstName()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getFirstName():""
										)+ "\",");
						sb.append("\"lastName\": \""
								+( hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getLastName()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getLastName():"" )+ "\",");
						sb.append("\"dob\": \""
								+( hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getDob()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getDob():"" )+ "\",");
						sb.append("\"mobile\": \""
								+( hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getMobile()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getMobile():"" )+ "\",");
						sb.append("\"email\": \""
								+( hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getEmail()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getEmail():"" )+ "\",");
						sb.append("\"address\": {");
						sb.append("\"area\": \""
								+ (hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getAddress().getArea()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getAddress().getArea():"")+ "\",");
						sb.append("\"city\": \""
								+( hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getAddress().getCity()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getAddress().getCity():""
										)+ "\",");
						sb.append("\"houseOrFlat\": \"" +( hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails()
								.getAddress().getHouseOrFlat()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails()
										.getAddress().getHouseOrFlat():"") + "\",");
						sb.append("\"pinCode\": \"" + (hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails()
								.getAddress().getPinCode()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails()
										.getAddress().getPinCode():"" )+ "\",");
						sb.append("\"state\": \""
								+ (hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getAddress().getState()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getAddress().getState():""
										)+ "\",");
						sb.append("\"street\": \"" +( hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails()
								.getAddress().getStreet()!=null?hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails()
										.getAddress().getStreet():"" )+ "\"");
						sb.append(" },");
						sb.append("\"nomineeDetails\": [");
						int count=0;
						for (NomineeDetails nomineeDetail : hdfcRequest.getPayload().getCustomerDetails()
								.getPersonalDetails().getNomineeDetails()) {
							if (hdfcRequest.getPayload().getCustomerDetails().getPersonalDetails().getNomineeDetails() != null) {
								if(count>0 && count <=hdfcRequest.getPayload().getCustomerDetails()
										.getPersonalDetails().getNomineeDetails().size()) {
									sb.append(",");
								}
								count++;
								sb.append("{");
								sb.append("\"title\": \"" + (nomineeDetail.getTitle()!=null?nomineeDetail.getTitle():"" )+ "\",");
								sb.append("\"gender\": \"" + (nomineeDetail.getGender()!=null?nomineeDetail.getGender():"") + "\",");
								sb.append("\"firstName\": \"" +( nomineeDetail.getFirstName()!=null?nomineeDetail.getFirstName():"" )+ "\",");
								sb.append("\"lastName\": \"" +( nomineeDetail.getLastName()!=null?nomineeDetail.getLastName():"" )+ "\",");
								sb.append("\"dob\": \"" + (nomineeDetail.getDob()!=null?nomineeDetail.getDob():"" )+ "\",");
								// sb.append("\"mobile\": \"" + nomineeDetail.getMobile() + "\",");
								sb.append("\"relationshipToCustomer\": \"" + (nomineeDetail.getRelationshipToCustomer()!=null?nomineeDetail.getRelationshipToCustomer():""
										)+ "\",");
								sb.append("\"percentageAllocation\": \"" + (nomineeDetail.getPercentageAllocation()!=null?nomineeDetail.getPercentageAllocation():"" )+ "\",");
								sb.append("\"address\": {");
								sb.append("\"area\": \"" +( nomineeDetail.getAdress().getArea()!=null?nomineeDetail.getAdress().getArea():"" )+ "\",");
								sb.append("\"city\": \"" + nomineeDetail.getAdress().getCity() + "\",");
								sb.append("\"houseOrFlat\": \"" + nomineeDetail.getAdress().getHouseOrFlat() + "\",");
								sb.append("\"pinCode\": \"" + nomineeDetail.getAdress().getPinCode() + "\",");
								sb.append("\"state\": \"" + nomineeDetail.getAdress().getState() + "\",");
								sb.append("\"street\": \"" + nomineeDetail.getAdress().getStreet() + "\"");
								sb.append("},");
								sb.append("\"appointeeDetails\":{   ");
								if (nomineeDetail.getAppointeeDetails() != null) {
									sb.append(
											"\"title\":\"" + ((nomineeDetail.getAppointeeDetails().getTitle() == null) ? ""
													: nomineeDetail.getAppointeeDetails().getTitle().toString() )+ "\",");
									sb.append("\"gender\":\""
											+ ((nomineeDetail.getAppointeeDetails().getGender() == null) ? ""
													: nomineeDetail.getAppointeeDetails().getGender().toString() )+ "\",");
									sb.append("\"firstName\":\""
											+ ((nomineeDetail.getAppointeeDetails().getFirstName() == null) ? ""
													: nomineeDetail.getAppointeeDetails().getFirstName().toString()
													)+ "\",");
									sb.append("\"lastName\":\""
											+ ((nomineeDetail.getAppointeeDetails().getLastName() == null) ? ""
													: nomineeDetail.getAppointeeDetails().getLastName() )+ "\",");
									sb.append("\"dob\":\"" + ((nomineeDetail.getAppointeeDetails().getDob() == null) ? ""
											: nomineeDetail.getAppointeeDetails().getDob() )+ "\",");
									sb.append("\"mobile\":\""
											+ ((nomineeDetail.getAppointeeDetails().getMobile() == null) ? ""
													: nomineeDetail.getAppointeeDetails().getMobile() )+ "\",");
									sb.append("\"relationshipToNominee\":\""
											+ ((nomineeDetail.getAppointeeDetails().getRelationshipToNominee() == null) ? ""
													: nomineeDetail.getAppointeeDetails().getRelationshipToNominee()
													)+ "\",");
									sb.append("\"address\":{");
									if (nomineeDetail.getAppointeeDetails().getAdress() != null) {
										sb.append("\"area\":\""
												+ ((nomineeDetail.getAppointeeDetails().getAdress().getArea() == null) ? ""
														: nomineeDetail.getAppointeeDetails().getAdress().getArea()
														)+ "\",");
										sb.append("\"city\":\""
												+ ((nomineeDetail.getAppointeeDetails().getAdress().getCity() == null) ? ""
														: nomineeDetail.getAppointeeDetails().getAdress().getCity()
														)+ "\",");
										sb.append("\"houseOrFlat\":\"" + ((nomineeDetail.getAppointeeDetails().getAdress()
												.getHouseOrFlat() == null) ? ""
														: nomineeDetail.getAppointeeDetails().getAdress().getHouseOrFlat()
												)+ "\",");
										sb.append("\"pinCode\":\""
												+ ((nomineeDetail.getAppointeeDetails().getAdress().getPinCode() == null)? "": nomineeDetail.getAppointeeDetails().getAdress().getPinCode())+ "\",");
										sb.append("\"state\":\""
												+ ((nomineeDetail.getAppointeeDetails().getAdress().getState() == null) ? "": nomineeDetail.getAppointeeDetails().getAdress().getState())+ "\",");
										sb.append("\"street\":\""
												+ ((nomineeDetail.getAppointeeDetails().getAdress().getStreet() == null)? "": nomineeDetail.getAppointeeDetails().getAdress().getStreet())+ "\"");
									}
									sb.append("}");
									/*sb.append("}");
								sb.append("}");*/
								}
								sb.append("}");
							}
							sb.append("}");
						}
						sb.append("]");
					}
					sb.append("},");

					sb.append("\"medicalQuestionnaire\": {");
					if(hdfcRequest.getPayload().getCustomerDetails().getMedicalQuestionnaire()!=null) {
						MedicalQuestionnaire medicalQuestionNaire=hdfcRequest.getPayload().getCustomerDetails().getMedicalQuestionnaire();
						sb.append("\"questionnaireId\":  \"" +((medicalQuestionNaire.getQuestionnaireId() == null)? "": medicalQuestionNaire.getQuestionnaireId()) + "\",");
						sb.append("\"response\": {");
						sb.append("\"1\": \""+((medicalQuestionNaire.getResponse().getOne() == null)? "0": medicalQuestionNaire.getResponse().getOne())+"\",");
						sb.append("\"2\": \""+((medicalQuestionNaire.getResponse().getTwo() == null)? "0": medicalQuestionNaire.getResponse().getTwo())+"\",");
						sb.append("\"3\": \""+((medicalQuestionNaire.getResponse().getThree() == null)? "0": medicalQuestionNaire.getResponse().getThree())+"\",");
						sb.append("\"4\": \""+((medicalQuestionNaire.getResponse().getFour() == null)? "0": medicalQuestionNaire.getResponse().getFour())+"\",");
						sb.append("\"5\": \""+((medicalQuestionNaire.getResponse().getFive() == null)? "0": medicalQuestionNaire.getResponse().getFive())+"\",");
						sb.append("\"6\": \""+((medicalQuestionNaire.getResponse().getSix() == null)? "0": medicalQuestionNaire.getResponse().getSix())+"\",");
						sb.append("\"7\": \""+((medicalQuestionNaire.getResponse().getSeven() == null)? "0": medicalQuestionNaire.getResponse().getSeven())+"\",");
						sb.append("\"8\": \""+((medicalQuestionNaire.getResponse().getEight() == null)? "0": medicalQuestionNaire.getResponse().getEight())+"\",");
						sb.append("\"9\": \""+((medicalQuestionNaire.getResponse().getNine() == null)? "0": medicalQuestionNaire.getResponse().getNine())+"\"");
						sb.append("}");
					}//if medicalques
					sb.append("}");

				}
				sb.append("}");

				/*
				 * sb.append("}" ); sb.append("}" );
				 */


				//
				/*	sb.append("\"medicalQuestionnaire\": {");
		sb.append("\"questionnaireId\": 1,");
		sb.append("\"response\": {");
		sb.append("\"1\": 0,");
		sb.append("\"2\": 0,");
		sb.append("\"3\": 0,");
		sb.append("\"4\": 0,");
		sb.append("\"5\": 0,");
		sb.append("\"6\": 0,");
		sb.append("\"7\": 0,");
		sb.append("\"8\": 0,");
		sb.append("\"9\": 0");
		sb.append("}");
		sb.append("}");*/
				sb.append("}");
			}

			logger.info("HDFC generated Request For Service "+sb.toString());
			return sb.toString();
		}
		catch(Exception e){
			logger.error("exception in converting request ");
		}
		return "";
	}

	public ApiResponseHdfcPayload httpCall(String req, String url) {
		MsgInfo msgInfo = new MsgInfo();
		ObjectMapper om = new ObjectMapper();
		StringBuilder resultData = new StringBuilder();
		String output = new String();
		ApiResponseHdfcPayload hdfcPayLoad = new ApiResponseHdfcPayload();

		HttpsURLConnection conn = null;
		try {
			URL url1 = new URL(url);
			conn = (HttpsURLConnection) url1.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(req.toString());
			writer.flush();
			writer.close();

			try {
				writer.close();
			} catch (Exception e1) {
			}
			int apiResponseCode = conn.getResponseCode();
			msgInfo.setCode(apiResponseCode+"");

			logger.info("API Response Code : hdfcissueaction" + apiResponseCode);
			if (apiResponseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					resultData.append(output);

				}
				logger.info("API Response  : hdfcissueaction" + resultData.toString());
				try {
					hdfcPayLoad = om.readValue(resultData.toString(), ApiResponseHdfcPayload.class);
				} catch (Exception ex) {
					logger.error("error while mapping response from issue action API to response Bean:::: " + ex);
				}
				conn.disconnect();
				br.close();
				logger.info("END External API Call : hdfc issue Action");

			}

		}

		catch (Exception e) {
			logger.error("In Exception"+e);
			e.printStackTrace();

		}
		return hdfcPayLoad;
	}

}
