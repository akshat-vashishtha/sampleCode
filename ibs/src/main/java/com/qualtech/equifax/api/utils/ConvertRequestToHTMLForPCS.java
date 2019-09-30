package com.qualtech.equifax.api.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.equifax.api.common.dto.Relation;
import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;
import com.qualtech.equifax.api.entity.common.EquifaxOtherKeyInd;
import com.qualtech.equifax.api.entity.common.EquifaxPcsInquiryRequestInfo;
import com.qualtech.equifax.api.entity.common.PreviousName;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsAddressDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsEmailDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsEnquiry;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsOthers;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsPanDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsPersonalDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsPhoneDetails;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsRecentActivities;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsScoringElement;

public class ConvertRequestToHTMLForPCS implements Serializable
{
//	@Autowired
	PropertyFile resProp;
//	public static ResourceBundle resProp = PropertyFile.getInstance().getResourceBundel();
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ConvertRequestToHTMLForPCS.class);
	EquifaxPcsAllDetails equifaxPCSDetailslogs=new EquifaxPcsAllDetails();
	public void  insertCorrectDatainRequest(HttpServletRequest request,HttpServletResponse response)
	{
		try
		{
			String requestJson = request.getParameter("requestJson");
			if (requestJson == null) {
				try 
				{
					InputStream ins = request.getInputStream();
					InputStreamReader insreader = new InputStreamReader(ins);
					BufferedReader breader = new BufferedReader(insreader);
					List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()), "UTF-8");
					String completeRequest = uriResult.get(0).toString();
					StringTokenizer stk = new StringTokenizer(completeRequest, "=");
					stk.nextToken();
					requestJson = stk.nextToken();
				} catch (Exception ec) {
					logger.error("Error in Exception " + ec);
				}
			}

			JSONObject requestJsonData=new JSONObject(requestJson);
			JSONArray transaction=new JSONArray(new JSONObject(requestJsonData.get("payload").toString()).get("transaction").toString());
			JSONObject GeneralUserInfo=new JSONObject(transaction.get(0).toString());
			request.setAttribute("GeneralUserInfo", GeneralUserInfo);
			String finalResponseJson=request.getAttribute("responseFromService").toString();
			JSONObject mainJSOnObject=new JSONObject(finalResponseJson);
			JSONObject responsetransaction=new JSONObject( new JSONObject(mainJSOnObject.get("payload").toString()).get("transaction").toString());
			//			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Response Json is 1: "+finalResponseJson);
			request.setAttribute("responsetransaction", responsetransaction);
			//for getting score//
			request.setAttribute("CustomerId",request.getParameter("CustomerId"));
			try
			{
			
				//request.setAttribute("CustomerId", resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"));
				//	request.setAttribute("MemberNumber", resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber"));
					
					try
					{
						if(GeneralUserInfo.has("consumerId")){
							String consumerId=GeneralUserInfo.get("consumerId").toString();
							request.setAttribute("consumerId", consumerId);
						}else{
							request.setAttribute("consumerId", "NA");
						}

					}
					catch(Exception ec)
					{
						logger.error(ec);
					}
					
				
				//request.setAttribute("CustomerId", resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"));
				//request.setAttribute("MemberNumber", resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber"));
				////////////////////////ScoringElements : Start////////////////////
				List<EquifaxPcsScoringElement> scoringElements=new ArrayList<EquifaxPcsScoringElement>();
				try
				{
					if(responsetransaction.has("scoringelements"))
					{
						if(responsetransaction.getJSONObject("scoringelements").has("scoringelement")){
							JSONArray ScoringElements=null;
							try{
								ScoringElements=responsetransaction.getJSONObject("scoringelements").getJSONArray("scoringelement");
							}catch(Exception eee){
								ScoringElements=new JSONArray().put(responsetransaction.getJSONObject("scoringelements").getJSONObject("scoringelement"));
							}

							for(int i=0;i<ScoringElements.length();i++)
							{

								EquifaxPcsScoringElement pcsScoringElement=new EquifaxPcsScoringElement();
								JSONObject ScoringElement=ScoringElements.getJSONObject(i);
								if(ScoringElement.has("code")){
									pcsScoringElement.setCode(ScoringElement.get("code").toString());
								}else{
									pcsScoringElement.setCode("NA");
								}
								if(ScoringElement.has("description")){
									pcsScoringElement.setDescription(ScoringElement.get("description").toString());
								}else{
									pcsScoringElement.setDescription("NA");
								}
								if(ScoringElement.has("type")){
									pcsScoringElement.setType(ScoringElement.get("type").toString());
								}else{
									pcsScoringElement.setType("NA");
								}
								if(ScoringElement.has("seq")){
									pcsScoringElement.setSequence(ScoringElement.get("seq").toString());
								}else{
									pcsScoringElement.setSequence("NA");
								}
								scoringElements.add(pcsScoringElement);
							}
						}
					}
					else
					{
						EquifaxPcsScoringElement pcsScoringElement=new EquifaxPcsScoringElement();
						pcsScoringElement.setCode("NA");
						pcsScoringElement.setDescription("NA");
						pcsScoringElement.setType("NA");
						pcsScoringElement.setSequence("NA");
						scoringElements.add(pcsScoringElement);
					}
				}catch(Exception ec)
				{
					logger.error("There is error while getting array of score elements"+ec);
				}

				equifaxPCSDetailslogs.setPcsScoringElements(scoringElements);
				request.setAttribute("scoringelements", scoringElements);
			}
			catch(Exception ec)
			{
				logger.error(ec);
			}
			///////////////////////////ScoringElements : END////////////////////////

			///////////////////////////Score : Start////////////////////////////////
			try
			{
				if(responsetransaction.has("score"))
				{
					JSONObject score=responsetransaction.getJSONObject("score");
					request.setAttribute("score", score);
					equifaxPCSDetailslogs.setScore_json(score.toString());
				}
				else
				{
					request.setAttribute("score", "0");
					equifaxPCSDetailslogs.setScore_json("0");
				}
			}
			catch(Exception ec)
			{
				logger.error(ec);
			}
			///////////////////////////Score : End////////////////////////////////




			///////////////////////////IDAndContactInfo : Start///////////////////////////
			try
			{
				if(responsetransaction.has("idandcontactinfo"))
				{
					JSONObject idandcontactinfo=responsetransaction.getJSONObject("idandcontactinfo");

					////////////////
					///////for saving data for Address////////////////////////////////////////
					List<EquifaxPcsAddressDetails> pcsAddressDetails=new ArrayList<EquifaxPcsAddressDetails>();
					try
					{
						if(idandcontactinfo.has("addressinfo"))
						{

							JSONArray addressDetail=null;
							try
							{
								addressDetail=idandcontactinfo.getJSONArray("addressinfo");
							}
							catch(Exception ee)
							{
								addressDetail=new JSONArray().put(idandcontactinfo.getJSONObject("addressinfo"));
							}
							for(int i=0;i<addressDetail.length();i++)
							{
								JSONObject address=addressDetail.getJSONObject(i);
								EquifaxPcsAddressDetails pcsAddressDetail=new EquifaxPcsAddressDetails();
								try
								{
									//
									if((address.has("address"))){
										pcsAddressDetail.setAddress(address.get("address").toString());
									}else{
										pcsAddressDetail.setAddress("NA");
									}
									if((address.has("postal"))){
										pcsAddressDetail.setPostal(address.get("postal").toString());
									}else{
										pcsAddressDetail.setPostal("NA");
									}
									if((address.has("seq"))){
										pcsAddressDetail.setSeq(address.get("seq").toString());
									}else{
										pcsAddressDetail.setSeq("NA");
									}
									if((address.has("state"))){
										pcsAddressDetail.setState(address.get("state").toString());
									}else{
										pcsAddressDetail.setState("NA");
									}
									if((address.has("type"))){
										pcsAddressDetail.setType(address.get("type").toString());
									}else{
										pcsAddressDetail.setType("NA");
									}
									if((address.has("reporteddate"))){
										pcsAddressDetail.setReportedDate(address.get("reporteddate").toString());
									}else{
										pcsAddressDetail.setReportedDate("NA");
									}
								}
								catch(Exception ec)
								{
									logger.error("There is error while saving data for pcsAddressDetails"+ec);
								}
								pcsAddressDetails.add(pcsAddressDetail);
							}
						}
						else
						{
							EquifaxPcsAddressDetails pcsAddressDetail=new EquifaxPcsAddressDetails();
							pcsAddressDetail.setAddress("NA");
							pcsAddressDetail.setPostal("NA");
							pcsAddressDetail.setSeq("NA");
							pcsAddressDetail.setState("NA");
							pcsAddressDetail.setType("NA");
							pcsAddressDetail.setReportedDate("NA");
							pcsAddressDetails.add(pcsAddressDetail);
						}
					}
					catch(Exception ee)
					{
						logger.info("error while parsing address jsonObject "+ee);

					}
					equifaxPCSDetailslogs.setPcsAddressDetails(pcsAddressDetails);
					request.setAttribute("addressDetails", pcsAddressDetails);
					//////////////////////for saving data for Address///////////////////////////////////////// 


					///////////////////////for saving data for Phone////////////////////////////////////////
					List<EquifaxPcsPhoneDetails> pcsPhoneDetails=new ArrayList<EquifaxPcsPhoneDetails>();
					try
					{
						if(idandcontactinfo.has("phoneinfo"))
						{
							JSONArray phoneDetail=null;
							try
							{
								phoneDetail=idandcontactinfo.getJSONArray("phoneinfo");
							}
							catch(Exception ee)
							{
								phoneDetail=new JSONArray().put(idandcontactinfo.getJSONObject("phoneinfo"));
							}
							for(int i=0;i<phoneDetail.length();i++)
							{
								JSONObject phone=phoneDetail.getJSONObject(i);
								EquifaxPcsPhoneDetails pcsPhoneDetail=new EquifaxPcsPhoneDetails();
								try
								{
									if(phone.has("typecode")){
										pcsPhoneDetail.setPhoneTypecode(EquifaxUtill.getEquifaxAppendixEForOutput(phone.get("typecode").toString()));
									}else{
										pcsPhoneDetail.setPhoneTypecode("NA");
									}
									if(phone.has("seq")){
										pcsPhoneDetail.setPhoneSeq(phone.get("seq").toString());
									}else{
										pcsPhoneDetail.setPhoneSeq("NA");
									}
									if(phone.has("number")){
										pcsPhoneDetail.setPhoneNumber(phone.get("number").toString());
									}else{
										pcsPhoneDetail.setPhoneNumber("NA");
									}
									if(phone.has("reporteddate")){
										pcsPhoneDetail.setPhoneReportedDate(phone.get("reporteddate").toString());
									}else{
										pcsPhoneDetail.setPhoneReportedDate("NA");
									}

								}
								catch(Exception ec)
								{
									logger.error("There is error while saving data for pcsPhoneDetail"+ec);
								}

								pcsPhoneDetails.add(pcsPhoneDetail);
							}
						}
						else
						{
							EquifaxPcsPhoneDetails pcsPhoneDetail=new EquifaxPcsPhoneDetails();
							pcsPhoneDetail.setPhoneTypecode("NA");
							pcsPhoneDetail.setPhoneSeq("NA");
							pcsPhoneDetail.setPhoneNumber("NA");
							pcsPhoneDetail.setPhoneReportedDate("NA");
							pcsPhoneDetails.add(pcsPhoneDetail);
						}
					}
					catch(Exception ec)
					{
						logger.error("There is error while saving data for pcsPhoneDetail"+ec);
					}
					equifaxPCSDetailslogs.setPcsPhoneDetails(pcsPhoneDetails);
					request.setAttribute("phoneDetails", pcsPhoneDetails);
					/////////////////////for saving data for Phone////////////////////////////////////////

					///////////////////////for saving data for PAN////////////////////////////////////////
					List<EquifaxPcsPanDetails> pcsPanDetails=new ArrayList<EquifaxPcsPanDetails>();
					try
					{
						if(idandcontactinfo.has("identityinfo"))
						{
							List<String> idList = new ArrayList<String>();
							idList.add("PanId");
							idList.add("PassportID");
							idList.add("DriverLicence");
							idList.add("VoterID");
							idList.add("NationalIDCard");
							idList.add("RationCard");
							idList.add("IDCard");
							idList.add("PhotoCreditCard");
							idList.add("IDOther");
							for(int x=0; x<idList.size();x++)
							{
								try
								{
									String id = idList.get(x).toLowerCase();

									JSONArray panDetail=null;
									try
									{
										panDetail=idandcontactinfo.getJSONObject("identityinfo").getJSONArray(id);
									}
									catch(Exception ee)
									{
										panDetail=new JSONArray().put(idandcontactinfo.getJSONObject("identityinfo").getJSONObject(id));
									}
									for(int i=0;i<panDetail.length();i++)
									{
										JSONObject pan=panDetail.getJSONObject(i);
										EquifaxPcsPanDetails pcsPanDetail=new EquifaxPcsPanDetails();
										try
										{
											if(pan.has("seq"))
											{
												pcsPanDetail.setPanSeq(idList.get(x));
											}
											else
											{
												pcsPanDetail.setPanSeq("NA");
											}
											if(pan.has("idnumber")){
												pcsPanDetail.setPanNumber(pan.get("idnumber").toString());
											}else{
												pcsPanDetail.setPanNumber("NA");
											}
											if(pan.has("reporteddate")){
												pcsPanDetail.setPanReportedDate(pan.get("reporteddate").toString());
											}else{
												pcsPanDetail.setPanReportedDate("NA");
											}

										}
										catch(Exception ec)
										{
											logger.error("There is error while saving data for pcsPanDetails "+ec);
										}
										if(pcsPanDetail.getPanNumber()!=null && !pcsPanDetail.getPanNumber().equalsIgnoreCase("") && !pcsPanDetail.getPanNumber().equalsIgnoreCase("NA"))
										{
											pcsPanDetails.add(pcsPanDetail);
										}
									}
								}
								catch(Exception ex)
								{
									//Do Nothing and no want any logger as well
								}
							}
						}
						else
						{
							EquifaxPcsPanDetails pcsPanDetail=new EquifaxPcsPanDetails();
							pcsPanDetail.setPanSeq("NA");
							pcsPanDetail.setPanNumber("NA");
							pcsPanDetail.setPanReportedDate("NA");
							pcsPanDetails.add(pcsPanDetail);
						}
					}
					catch(Exception ec)
					{
						logger.error("There is error while saving data for pcsPanDetails "+ec);
					}
					equifaxPCSDetailslogs.setPcsPanDetails(pcsPanDetails);
					request.setAttribute("panDetails", pcsPanDetails);
					/////////////////////for saving data for PAN////////////////////////////////////////



					///////////////////////for saving data for Personal detail////////////////////////////////////////
					List<EquifaxPcsPersonalDetails> personalDetails=new ArrayList<EquifaxPcsPersonalDetails>();
					try
					{
						if(idandcontactinfo.has("personalinfo"))
						{
							JSONObject personalDetailValue=idandcontactinfo.getJSONObject("personalinfo");
							if(personalDetailValue!=null)
							{
								EquifaxPcsPersonalDetails personalDetail=new EquifaxPcsPersonalDetails();
								try
								{
									if(personalDetailValue.has("totalincome")){
										personalDetail.setTotalincome(personalDetailValue.get("totalincome").toString());
										request.setAttribute("totalincome", personalDetail.getTotalincome());
									}else{
										personalDetail.setTotalincome("NA");
										request.setAttribute("totalincome", "NA");
									}
									if(personalDetailValue.has("occupation")){
										personalDetail.setOccupation(personalDetailValue.get("occupation").toString());
										request.setAttribute("occupation", personalDetail.getOccupation());
									}else{
										personalDetail.setOccupation("NA");
										request.setAttribute("occupation", "NA");
									}
									if(personalDetailValue.has("age")){
										if(personalDetailValue.getJSONObject("age").has("age"))
										{
											personalDetail.setAge(personalDetailValue.getJSONObject("age").get("age").toString());
											request.setAttribute("age", personalDetail.getAge());
										}else{
											request.setAttribute("age", "NA");
										}
									}
									if(personalDetailValue.has("name"))
									{
										String fname="";
										String mname="";
										String lname="";
										if(personalDetailValue.getJSONObject("name").has("middlename"))
										{
											if(personalDetailValue.getJSONObject("name").get("middlename")!=null 
													&& !personalDetailValue.getJSONObject("name").get("middlename").toString().equals("")
													&& !personalDetailValue.getJSONObject("name").get("middlename").toString().equals("null") )
											{
												personalDetail.setMiddleName(personalDetailValue.getJSONObject("name").get("middlename").toString());
												mname=personalDetail.getMiddleName();
											}
										}
										else
										{
											personalDetail.setMiddleName("");
										}
										if(personalDetailValue.getJSONObject("name").has("lastname"))
										{
											if(personalDetailValue.getJSONObject("name").get("lastname")!=null 
													&& !personalDetailValue.getJSONObject("name").get("lastname").toString().equals("")
													&& !personalDetailValue.getJSONObject("name").get("lastname").toString().equalsIgnoreCase("null") )
											{
												personalDetail.setLastName(personalDetailValue.getJSONObject("name").get("lastname").toString());
												lname=personalDetail.getLastName();
											}
											
										}
										else
										{
											personalDetail.setLastName("");
										}
										if(personalDetailValue.getJSONObject("name").has("firstname"))
										{
											if(personalDetailValue.getJSONObject("name").get("firstname")!=null 
													&& !personalDetailValue.getJSONObject("name").get("firstname").toString().equals("")
													&& !personalDetailValue.getJSONObject("name").get("firstname").toString().equalsIgnoreCase("null") )
											{
												personalDetail.setFirstName(personalDetailValue.getJSONObject("name").get("firstname").toString());
												fname=personalDetail.getFirstName();
											}
										}
										else
										{
											personalDetail.setFirstName("");
										}
										if(personalDetailValue.getJSONObject("name").has("suffix"))
										{
											personalDetail.setSuffix(personalDetailValue.getJSONObject("name").get("suffix").toString());
											request.setAttribute("suffix", personalDetail.getSuffix());
										}
										else
										{
											request.setAttribute("suffix", "");
										}
										if(personalDetailValue.getJSONObject("name").has("additionalmiddlename"))
										{
											personalDetail.setAdditionalmiddlename(personalDetailValue.getJSONObject("name").get("additionalmiddlename").toString());
											request.setAttribute("additionalmiddlename", personalDetail.getAdditionalmiddlename());
										}
										else
										{
											request.setAttribute("additionalmiddlename", "");
										}
										
										String fullName="";
										if(personalDetailValue.getJSONObject("name").has("fullname"))
										{
											fullName=personalDetailValue.getJSONObject("name").get("fullname").toString().trim();
										}
										
										if(fullName.isEmpty())
										{
											fullName=fname+(mname.equals("")?"":" "+mname)+(lname.equals("")?"":" "+lname);
										}
										personalDetail.setFullName(fullName);
										request.setAttribute("fullName", fullName);
									}
									else
									{
										request.setAttribute("fullName", "NA");
										request.setAttribute("middlename","");
										request.setAttribute("lastname", "");
										request.setAttribute("firstname", "");
									}
									List<PreviousName> previousNamesList =new ArrayList<PreviousName>();

									if(personalDetailValue.has("previousname"))
									{

										JSONArray prevName=null;
										try {
											prevName = personalDetailValue.getJSONArray("previousname");
										} catch (JSONException e) {
											prevName =new JSONArray().put(personalDetailValue.getJSONObject("previousname")); 
										}
										for(int i=0;i<prevName.length();i++)
										{
											PreviousName preName=new PreviousName();

											if(personalDetailValue.getJSONObject("previousname").has("middlename")){
												preName.setMiddle_name(personalDetailValue.getJSONObject("previousname").get("middlename").toString());
											}else{
												preName.setMiddle_name("");
											}

											if(personalDetailValue.getJSONObject("previousname").has("lastname")){
												preName.setLast_name(personalDetailValue.getJSONObject("previousname").get("lastname").toString());
											}else{
												preName.setLast_name("");
											}

											if(personalDetailValue.getJSONObject("previousname").has("firstname")){
												preName.setFirst_name(personalDetailValue.getJSONObject("previousname").get("firstname").toString());
											}else{
												preName.setFirst_name("");
											}
											if(personalDetailValue.getJSONObject("previousname").has("additionalmiddlename")){
												preName.setAdditionalmiddlename(personalDetailValue.getJSONObject("previousname").get("additionalmiddlename").toString());
											}else{
												preName.setAdditionalmiddlename("");
											}
											if(personalDetailValue.getJSONObject("previousname").has("suffix")){
												preName.setSuffix(personalDetailValue.getJSONObject("previousname").get("suffix").toString());
											}else{
												preName.setSuffix("");
											}
											previousNamesList.add(preName);
										}

									}
									else
									{
										PreviousName preName=new PreviousName();
										preName.setMiddle_name("");
										preName.setLast_name("");
										preName.setFirst_name("NA");
										preName.setAdditionalmiddlename("");
										preName.setSuffix("");
										previousNamesList.add(preName);
									}
									if(personalDetailValue.has("gender"))
									{
										personalDetail.setGender(personalDetailValue.get("gender").toString());
										request.setAttribute("gender", personalDetail.getGender());
									}
									else
									{
										personalDetail.setGender("NA");
										request.setAttribute("gender", "NA");
									}
									if(personalDetailValue.has("dateofbirth")){
										personalDetail.setDateofbirth(personalDetailValue.get("dateofbirth").toString());
										request.setAttribute("dateofbirth", personalDetail.getDateofbirth());
									}
									else
									{
										personalDetail.setDateofbirth("NA");
										request.setAttribute("dateofbirth","NA");
									}
									if(personalDetailValue.has("maritalstatus"))
									{
										personalDetail.setMaritalStatus(personalDetailValue.get("maritalstatus").toString());
										request.setAttribute("maritalstatus", personalDetail.getMaritalStatus());
									}
									else
									{
										personalDetail.setMaritalStatus("NA");
										request.setAttribute("maritalstatus", "NA");
									}
									if(personalDetailValue.has("aliasnameinfo"))
									{
										personalDetail.setAliasNameInfo(personalDetailValue.get("aliasnameinfo").toString());
										request.setAttribute("aliasnameinfo", personalDetail.getAliasNameInfo());
									}
									else
									{
										personalDetail.setAliasNameInfo("NA");
										request.setAttribute("aliasnameinfo", "NA");
									}
									/*if(personalDetailValue.has("previousname"))
									{
										PreviousName previousName=new PreviousName();
										previousName.setPreviousName(personalDetailValue.get("previousname").toString());
										personalDetail.setPreviousNames(previousName);
										request.setAttribute("previousName", personalDetail.getPreviousNames());
									}
									else
									{
										PreviousName previousName=new PreviousName();
										previousName.setPreviousName("NA");
										personalDetail.setPreviousNames(previousName);
										request.setAttribute("previousName", personalDetail.getPreviousNames());
									}*/
								}
								catch(Exception ec)
								{
									logger.error("There is error while saving data for personalDetail "+ec);
								}
								personalDetails.add(personalDetail);
							}
						}
						else
						{
							List<PreviousName> previousNamesList =new ArrayList<PreviousName>();
							EquifaxPcsPersonalDetails personalDetail=new EquifaxPcsPersonalDetails();
							personalDetail.setAge("NA");
							personalDetail.setAliasNameInfo("NA");
							personalDetail.setDateofbirth("NA");
							personalDetail.setDateOfbirthInfo("NA");
							personalDetail.setFirstName("");
							personalDetail.setGender("NA");
							personalDetail.setLastName("NA");
							personalDetail.setMaritalStatus("NA");
							personalDetail.setMiddleName("NA");
							personalDetail.setOccupation("NA");
							personalDetail.setTotalincome("NA");
							PreviousName pre=new PreviousName();
							pre.setPreviousName("NA");
							previousNamesList.add(pre);
							personalDetail.setPreviousNames(previousNamesList);
							personalDetails.add(personalDetail);
							request.setAttribute("fullName", "");
							request.setAttribute("middlename","");
							request.setAttribute("lastname", "");
							request.setAttribute("firstname", "");
						}

					}catch(Exception ec)
					{
						logger.error("There is error while saving data for personalDetail "+ec);
					}
					equifaxPCSDetailslogs.setPcsPersonalDetails(personalDetails);
					request.setAttribute("personalDetails", personalDetails);


					//////////////////////for saving data for Personal detail///////////////////////////////////////// 

					///////////////////////for saving data for Email detail////////////////////////////////////////
					List<EquifaxPcsEmailDetails> emailsDetails=new ArrayList<EquifaxPcsEmailDetails>();
					try
					{
						if(idandcontactinfo.has("emailaddressinfo"))
						{
							JSONArray emailDetail=null;
							try
							{
								emailDetail=new JSONArray().put(idandcontactinfo.getJSONObject("emailaddressinfo"));
							}
							catch(Exception ee)
							{
								emailDetail=idandcontactinfo.getJSONArray("emailaddressinfo");
							}
							for(int i=0;i<emailDetail.length();i++)
							{
								JSONObject email=emailDetail.getJSONObject(i);
								if(email!=null){
									EquifaxPcsEmailDetails emaillDetail=new EquifaxPcsEmailDetails();
									try
									{
										if(email.has("emailaddress"))
										{
											emaillDetail.setEmailaddress(email.get("emailaddress").toString());
										}
										else
										{
											emaillDetail.setEmailaddress("NA");
										}
										if(email.has("seq")){
											emaillDetail.setSeq(email.get("seq").toString());
										}
										else
										{
											emaillDetail.setSeq("NA");
										}
										if(email.has("reporteddate"))
										{
											emaillDetail.setReporteddate(email.get("reporteddate").toString());
										}
										else
										{
											emaillDetail.setReporteddate("NA");
										}
									}
									catch(Exception ec)
									{
										logger.error("There is error while saving data for Email Detail "+ec);
									}
									emailsDetails.add(emaillDetail);
								}
							}
						}
						else
						{
							EquifaxPcsEmailDetails emaillDetail=new EquifaxPcsEmailDetails();
							emaillDetail.setEmailaddress("NA");
							emaillDetail.setSeq("NA");
							emaillDetail.setReporteddate("NA");
							emailsDetails.add(emaillDetail);
						}
					}
					catch(Exception ec)
					{
						logger.error("There is error while saving data for Email Detail "+ec);
					}
					equifaxPCSDetailslogs.setPcsEmailDetails(emailsDetails);
					request.setAttribute("emailsDetails", emailsDetails);
					//////////////////////for saving data for Email detail///////////////////////////////////////// 
					request.setAttribute("idandcontactinfo", idandcontactinfo);
				}
			}
			catch(Exception ec)
			{request.setAttribute("CustomerId", resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId"));
			//	request.setAttribute("MemberNumber", resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber"));
			
			try
			{
				if(GeneralUserInfo.has("consumerId")){
					String consumerId=GeneralUserInfo.get("consumerId").toString();
					request.setAttribute("consumerId", consumerId);
				}else{
					request.setAttribute("consumerId", "NA");
				}

			}
			catch(Exception ex)
			{
				logger.error(ex);
			}
			
				logger.error("Somthing went Wrong while processing idandcontactinfo : "+ec);
			}

			/////////////////////////IDAndContactInfo : End///////////////////////////


			//for getting Account Details //
			try
			{
				if(responsetransaction.has("accountdetails"))
				{

					JSONArray accounts=null;
					try
					{
						accounts = new JSONArray(responsetransaction.getJSONObject("accountdetails").get("account"));
					}catch(Exception ex)
					{
						accounts = new JSONArray().put(responsetransaction.getJSONObject("accountdetails").get("account"));
					}
					request.setAttribute("account", accounts);
				}
				else
				{
					request.setAttribute("account", null);
				}
			}
			catch(Exception ec)
			{
				logger.error(ec);
				request.setAttribute("account", null);
			}

			//for getting Account Details//

			//for getting Account Summary
			try
			{
				if(responsetransaction.has("accountsummary")){
					JSONObject accountSuymmary=responsetransaction.getJSONObject("accountsummary");
					request.setAttribute("accountsummary", accountSuymmary);
				}else{
					request.setAttribute("accountsummary", null);
				}
			}
			catch(Exception ec)
			{
				logger.error(ec);
			}
			//for getting account summary


			////////////////for getting Enquiries/////////////////////////
			List<EquifaxPcsEnquiry> equifaxpcsenquiries=new ArrayList<EquifaxPcsEnquiry>();
			try
			{
				if(responsetransaction.has("enquiries")){
					JSONArray enquiries=null;
					try{
						enquiries=responsetransaction.getJSONArray("enquiries");
					}catch(Exception eee){
						enquiries=new JSONArray().put(responsetransaction.getJSONObject("enquiries"));
					}

					try
					{
						for(int i=0;i<enquiries.length();i++)
						{
							EquifaxPcsEnquiry enquiryEntity=new EquifaxPcsEnquiry();
							JSONObject enquiry=enquiries.getJSONObject(i);
							if(enquiry.has("date")){
								enquiryEntity.setEnquiry_date(enquiry.get("date").toString());
							}else{
								enquiryEntity.setEnquiry_date("NA");
							}
							if(enquiry.has("seq")){
								enquiryEntity.setEnquiry_sequence(enquiry.get("seq").toString());
							}else{
								enquiryEntity.setEnquiry_sequence("NA");
							}
							if(enquiry.has("time")){
								enquiryEntity.setEnquiry_time(enquiry.get("time").toString());
							}else{
								enquiryEntity.setEnquiry_time("NA");
							}
							if(enquiry.has("institution")){
								enquiryEntity.setInstitution(enquiry.get("institution").toString());
							}else{
								enquiryEntity.setInstitution("NA");
							}
							if(enquiry.has("requestpurpose")){
								enquiryEntity.setRequest_purpose(enquiry.get("requestpurpose").toString());
							}else{
								enquiryEntity.setRequest_purpose("NA");
							}
							if(enquiry.has("amount")){
								enquiryEntity.setAmount(enquiry.get("amount").toString());
							}else{
								enquiryEntity.setAmount("NA");
							}
							equifaxpcsenquiries.add(enquiryEntity);
						}
					}
					catch(Exception ec)
					{
						logger.error("There is error while saving data for enquiry"+ec); 
					}
					equifaxPCSDetailslogs.setEquifaxPcsEnquiries(equifaxpcsenquiries);
					request.setAttribute("enquiries", equifaxPCSDetailslogs.getEquifaxPcsEnquiries());
				}else{
					EquifaxPcsEnquiry enquiryEntity=new EquifaxPcsEnquiry();
					enquiryEntity.setEnquiry_date("NA");
					enquiryEntity.setEnquiry_sequence("NA");
					enquiryEntity.setEnquiry_time("NA");
					enquiryEntity.setInstitution("NA");
					enquiryEntity.setRequest_purpose("NA");
					equifaxpcsenquiries.add(enquiryEntity);
					equifaxPCSDetailslogs.setEquifaxPcsEnquiries(equifaxpcsenquiries);
					request.setAttribute("enquiries", equifaxPCSDetailslogs.getEquifaxPcsEnquiries());
				}
			}
			catch(Exception ec)
			{
				EquifaxPcsEnquiry enquiryEntity=new EquifaxPcsEnquiry();
				enquiryEntity.setEnquiry_date("NA");
				enquiryEntity.setEnquiry_sequence("NA");
				enquiryEntity.setEnquiry_time("NA");
				enquiryEntity.setInstitution("NA");
				enquiryEntity.setRequest_purpose("NA");
				equifaxpcsenquiries.add(enquiryEntity);
				equifaxPCSDetailslogs.setEquifaxPcsEnquiries(equifaxpcsenquiries);
				request.setAttribute("enquiries", equifaxPCSDetailslogs.getEquifaxPcsEnquiries());
				logger.error(ec);
			}


			//for getting enquiries

			//for Getting Enquiry Summary
			try
			{
				if(responsetransaction.has("enquirysummary")){
					JSONObject enquirysummary=responsetransaction.getJSONObject("enquirysummary");
					request.setAttribute("enquirysummary", enquirysummary);
				}else{
					request.setAttribute("enquirysummary", null);
				}

			}
			catch(Exception ec)
			{
				logger.error(ec);
			}
			//for Getting enquiry Summary




			//for getting Disclaimer
			try
			{
				if(responsetransaction.has("disclaimer")){
					String disclaimer=responsetransaction.get("disclaimer").toString();
					request.setAttribute("disclaimer", disclaimer);
				}else{
					request.setAttribute("disclaimer", "NA");
				}

			}
			catch(Exception ec)
			{
				logger.error(ec);
			}


			//for Getting Disclaimer
            ///for report order number
			try
			{
				if(responsetransaction.has("reportorderno")){
					String reportorderno=responsetransaction.get("reportorderno").toString();
					request.setAttribute("reportorderno", reportorderno);
				}else{
					request.setAttribute("reportorderno", "NA");
				}

			}
			catch(Exception ec)
			{
				logger.error(ec);
			}
			
			///for report order number

			//for scoring Elements

			/////////////////////////////////////////////Other key Ind Type///////////////////////////////////////////
			//EquifaxPcsOthers keyInd=new EquifaxPcsOthers();
			EquifaxOtherKeyInd keyInd=new EquifaxOtherKeyInd();
			
			try{

				if(responsetransaction.has("otherkeyind>")){
					JSONArray equifaxOtherKeyInd=null;
					try {
						equifaxOtherKeyInd = responsetransaction.getJSONArray("otherkeyind");
					} catch (JSONException e) {
						equifaxOtherKeyInd =new JSONArray().put(responsetransaction.getJSONObject("otherkeyind")); 
					}
					for(int i=0;i<equifaxOtherKeyInd.length();i++)
					{
						try
						{
							JSONObject otherKey=equifaxOtherKeyInd.getJSONObject(i);

							if(otherKey.has("ageofoldesttrade")){
								//keyInd.setAge_of_oldest_trade(otherKey.get("ageofoldesttrade").toString());
								keyInd.setAgeOfOldestTrade(otherKey.get("ageofoldesttrade").toString());
							}else{
								keyInd.setAgeOfOldestTrade("NA");
							}
							if(otherKey.has("alllineseverwritten")){
								keyInd.setAllLinesEVERWritten(otherKey.get("alllineseverwritten").toString());
							}else{
								keyInd.setAllLinesEVERWritten("NA");
							}
							if(otherKey.has("alllineseverwrittenin6months")){
								keyInd.setAllLinesEVERWrittenIn6Months(otherKey.get("alllineseverwrittenin6months").toString());
							}else{
								keyInd.setAllLinesEVERWrittenIn6Months("NA");
							}
							if(otherKey.has("alllineseverwrittenin9months")){
								keyInd.setAllLinesEVERWrittenIn9Months(otherKey.get("alllineseverwrittenin9months").toString());
							}else{
								keyInd.setAllLinesEVERWrittenIn9Months("NA");
							}
							if(otherKey.has("numberofopentrades")){
								keyInd.setNumberOfOpenTrades(otherKey.get("numberofopentrades").toString());
							}else{
								keyInd.setNumberOfOpenTrades("NA");
							}
						}
						catch(Exception ec)
						{
							logger.error("error while saving data into otherKeyInd PCS"+ec);
						}

					}
				}else{
					
					keyInd.setAgeOfOldestTrade("NA");
					
					keyInd.setAllLinesEVERWritten("NA");
					
					keyInd.setAllLinesEVERWrittenIn6Months("NA");
					keyInd.setAllLinesEVERWrittenIn9Months("NA");
					keyInd.setNumberOfOpenTrades("NA");
					keyInd.setAgeOfOldestTrade("NA");
				}
			}catch(Exception ee){
				keyInd.setAgeOfOldestTrade("NA");
				keyInd.setAllLinesEVERWritten("NA");
				keyInd.setAllLinesEVERWrittenIn6Months("NA");
				keyInd.setAllLinesEVERWrittenIn9Months("NA");
				keyInd.setNumberOfOpenTrades("NA");
				keyInd.setAgeOfOldestTrade("NA");
				logger.error("error while saving data into otherkeyInd MCR "+ee);
			}
			equifaxPCSDetailslogs.setEquifaxOtherKeyInd(keyInd);
			request.setAttribute("otherkeyInd", keyInd);
			////////////////////////////////for saving data into otherkeyInd details////////////////////////////



			/////////////////////////////////////////////Other key Ind Type////////////////////////////////////////////


			////////////////////////////////////////////recent activities////////////////////////////////////////////
			if(responsetransaction.has("recentactivities")){
				JSONObject recentActivity= responsetransaction.getJSONObject("recentactivities");
				request.setAttribute("recentactivities", recentActivity);
			}else{
				request.setAttribute("recentactivities", null);
			}

			EquifaxPcsRecentActivities recentactivity=new EquifaxPcsRecentActivities();
			try
			{
				if(responsetransaction.has("recentactivities")){
					JSONObject recentActivity=responsetransaction.getJSONObject("recentactivities");
					if(recentActivity.has("totalinquiries")){
						recentactivity.setTotal_enquiries(recentActivity.get("totalinquiries").toString());
					}else{
						recentactivity.setTotal_enquiries("NA");
					}
					if(recentActivity.has("accountsupdated")){
						recentactivity.setAccounts_updated(recentActivity.get("accountsupdated").toString());
					}else{
						recentactivity.setAccounts_updated("NA");
					}
					if(recentActivity.has("accountsdeliquent")){
						recentactivity.setAccounts_deliquent(recentActivity.get("accountsdeliquent").toString());
					}else{
						recentactivity.setAccounts_deliquent("NA");
					}
					if(recentActivity.has("accountsopened")){
						recentactivity.setAccounts_opened(recentActivity.get("accountsopened").toString());
					}else{
						recentactivity.setAccounts_opened("NA");
					}
				}else{
					recentactivity.setTotal_enquiries("NA");
					recentactivity.setAccounts_updated("NA");
					recentactivity.setAccounts_deliquent("NA");
					recentactivity.setAccounts_opened("NA");
				}
			}
			catch(Exception ec)
			{
				logger.error(" There is error while saving data for recent activity "+ec);
			}
			equifaxPCSDetailslogs.setRecentActivities(recentactivity);
			request.setAttribute("recentactivities", equifaxPCSDetailslogs.getRecentActivities());

			////////////////////////////////////////////recent activities////////////////////////////////////////////

			////////////////////////////////////////////inquiryrequestinfo ////////////////////////////////////////////
			if(responsetransaction.has("inquiryrequestinfo"))
			{
				JSONObject inquiryrequestinfo= responsetransaction.getJSONObject("inquiryrequestinfo");
				request.setAttribute("inquiryrequestinfo", inquiryrequestinfo);
			}
			else
			{
				request.setAttribute("inquiryrequestinfo", null);
			}

			EquifaxPcsInquiryRequestInfo inquiryrequestinfoBean=new EquifaxPcsInquiryRequestInfo();
			try
			{
				if(responsetransaction.has("inquiryrequestinfo"))
				{
					JSONObject inquiryrequestinfo=responsetransaction.getJSONObject("inquiryrequestinfo");
					
					if(inquiryrequestinfo.has("inquirypurpose"))
					{
						try 
						{
							inquiryrequestinfoBean.setRequestPurpose(EquifaxUtill.getEquifaxAppendixA().get(inquiryrequestinfo.get("inquirypurpose").toString()));
							inquiryrequestinfoBean.setInquirypurpose(inquiryrequestinfoBean.getRequestPurpose());
						} 
						catch (Exception e) 
						{
							logger.error("Developer : Check this 100% chance is wrong input");
						}
					}
					else
					{
						inquiryrequestinfoBean.setRequestPurpose("NA");
						inquiryrequestinfoBean.setInquirypurpose("NA");
					}
					if(inquiryrequestinfo.has("firstname")){
						inquiryrequestinfoBean.setfName(inquiryrequestinfo.get("firstname").toString());
						
					}else{
						inquiryrequestinfoBean.setfName("NA");
					}
					if(inquiryrequestinfo.has("lastname")){
						inquiryrequestinfoBean.setFamilyName(inquiryrequestinfo.get("lastname").toString());
					}else{
						inquiryrequestinfoBean.setFamilyName("NA");
					}
					
					if(request.getAttribute("fullName").equals("") || request.getAttribute("fullName").equals("NA"))
					{
						request.setAttribute("fullName", inquiryrequestinfoBean.getfName()+" "+ inquiryrequestinfoBean.getFamilyName());
					}
					
					
					if(inquiryrequestinfo.has("dob")){
						inquiryrequestinfoBean.setDob(inquiryrequestinfo.get("dob").toString());
					}else{
						inquiryrequestinfoBean.setDob("NA");
					}
					
					try {
						if(request.getAttribute("dateofbirth").equals("") || request.getAttribute("dateofbirth").equals("NA"))
							{
								request.setAttribute("dateofbirth", inquiryrequestinfoBean.getDob());
							}
					} catch (Exception e) {
						request.setAttribute("dateofbirth", inquiryrequestinfoBean.getDob());
					}
					
					
					if(inquiryrequestinfo.has("gender")){
						inquiryrequestinfoBean.setGender(inquiryrequestinfo.get("gender").toString());
					}else{
						inquiryrequestinfoBean.setGender("NA");
					}
					

					try {
						if(request.getAttribute("gender").equals("") || request.getAttribute("gender").equals("NA"))
							{
								request.setAttribute("gender", inquiryrequestinfoBean.getGender());
							}
					} catch (Exception e) {
						request.setAttribute("gender", inquiryrequestinfoBean.getGender());
					}
					
					
					if(inquiryrequestinfo.has("transactionamount")){
						inquiryrequestinfoBean.setTransactionAmount(inquiryrequestinfo.get("transactionamount").toString());
					}else{
						inquiryrequestinfoBean.setTransactionAmount("0");
					}
					
					
					
					if(inquiryrequestinfo.has("panid")){
						inquiryrequestinfoBean.setPan(inquiryrequestinfo.get("panid").toString());
					}else{
						inquiryrequestinfoBean.setPan("NA");
					}
					if(inquiryrequestinfo.has("voterid")){
						inquiryrequestinfoBean.setVoterID(inquiryrequestinfo.get("voterid").toString());
					}else{
						inquiryrequestinfoBean.setVoterID("NA");
					}
					if(inquiryrequestinfo.has("passportid")){
						inquiryrequestinfoBean.setPassportId(inquiryrequestinfo.get("passportid").toString());
					}else{
						inquiryrequestinfoBean.setPassportId("NA");
					}
					if(inquiryrequestinfo.has("nationalidcard")){
						inquiryrequestinfoBean.setUid(inquiryrequestinfo.get("nationalidcard").toString());
					}else{
						inquiryrequestinfoBean.setUid("NA");
					}
					if(inquiryrequestinfo.has("driverlicense")){
						inquiryrequestinfoBean.setDriverLicense(inquiryrequestinfo.get("driverlicense").toString());
					}else{
						inquiryrequestinfoBean.setDriverLicense("NA");
					}
					
					
					if(inquiryrequestinfo.has("inquiryphones"))
					{
						JSONObject inquiryphones=inquiryrequestinfo.getJSONObject("inquiryphones");
		    			JSONArray inquiryphone=null;
		    			try
		    			{
		    				inquiryphone=inquiryphones.getJSONArray("inquiryphone");
		    			}
		    			catch(Exception ee){
		    				inquiryphone=new JSONArray().put(inquiryphones.getJSONObject("inquiryphone"));
		    			}
						if(inquiryphone!=null && inquiryphone.length()>0)
						{
						
							for(int i=0;i<inquiryphone.length();i++)
							{
								JSONObject phone = inquiryphone.getJSONObject(i);
								String typecode = ""+phone.getString("phonetype");
								if(typecode.equalsIgnoreCase("H") || typecode.equalsIgnoreCase("Home"))
								{
									inquiryrequestinfoBean.sethPhone(phone.get("number").toString());
								}
								else if(typecode.equalsIgnoreCase("M") || typecode.equalsIgnoreCase("Mobile"))
								{
									inquiryrequestinfoBean.setmPhone(phone.get("number").toString());
								}
								else if(!typecode.equalsIgnoreCase(""))
								{
									inquiryrequestinfoBean.setoPhone(phone.get("number").toString());
								}
							}
						}
					}
					
					///////////////////////////family tag start(AdditionalNameInfo tag)///////////////////////////////////////
					if(inquiryrequestinfo.has("familydetails"))
					{
						JSONObject inquiryfamily=inquiryrequestinfo.getJSONObject("familydetails");
		    			JSONArray inquiryfamilys=null;
		    			try
		    			{
		    				inquiryfamilys=inquiryfamily.getJSONArray("additionalnameinfo");
		    			}
		    			catch(Exception ee){
		    				inquiryfamilys=new JSONArray().put(inquiryfamily.getJSONObject("additionalnameinfo"));
		    			}
						if(inquiryfamilys!=null && inquiryfamilys.length()>0)
						{
						
							List<Relation> additionalNameList=new ArrayList<Relation>();
							for(int i=0;i<inquiryfamilys.length();i++)
							{
								Relation relation=new Relation();
								JSONObject additionalDetail = inquiryfamilys.getJSONObject(i);
								String additionalnametype = ""+additionalDetail.getString("additionalnametype");
								String additionalname = ""+additionalDetail.getString("additionalname");
								relation.setRelation(EquifaxUtill.getAdditionalNameType(additionalnametype));
								relation.setName(additionalname);
								additionalNameList.add(relation);
							}
							inquiryrequestinfoBean.setRelations(additionalNameList);
						}
					}
					
					////////////////////////////family tag end (AdditionalNameInfo) //////////////////////////////////////
					
					
					List<EquifaxPcsAddressDetails> address = new ArrayList<EquifaxPcsAddressDetails>();
					if(inquiryrequestinfo.has("inquiryaddresses"))
					{
						JSONObject inquiryaddresses=inquiryrequestinfo.getJSONObject("inquiryaddresses");
		    			JSONArray inquiryaddress=null;
		    			try
		    			{
		    				inquiryaddress=inquiryaddresses.getJSONArray("inquiryaddress");
		    			}
		    			catch(Exception ee){
		    				inquiryaddress=new JSONArray().put(inquiryaddresses.getJSONObject("inquiryaddress"));
		    			}
						if(inquiryaddress!=null && inquiryaddress.length()>0)
						{
						
							for(int i=0;i<inquiryaddress.length();i++)
							{
								EquifaxPcsAddressDetails addressDetails = new EquifaxPcsAddressDetails();
								JSONObject phone = inquiryaddress.getJSONObject(i);
								addressDetails.setState(phone.get("state").toString());
								addressDetails.setAddress(phone.get("addressline").toString());
								addressDetails.setPostal(phone.get("postal").toString());
								address.add(addressDetails);
							}
						}
					}
					inquiryrequestinfoBean.setAddress(address);
				}
				else
				{
					inquiryrequestinfoBean.setDob("NA");
					inquiryrequestinfoBean.setDriverLicense("NA");
					inquiryrequestinfoBean.setFamilyName("NA");
					inquiryrequestinfoBean.setfName("NA");
					inquiryrequestinfoBean.setGender("NA");
					inquiryrequestinfoBean.sethPhone("NA");
					inquiryrequestinfoBean.setInquirypurpose("NA");
					inquiryrequestinfoBean.setmPhone("NA");
					inquiryrequestinfoBean.setPan("NA");
					inquiryrequestinfoBean.setPassportId("NA");
					inquiryrequestinfoBean.setRequestPurpose("NA");
					inquiryrequestinfoBean.setUid("NA");
					inquiryrequestinfoBean.setVoterID("NA");
					inquiryrequestinfoBean.setTransactionAmount("0");
					inquiryrequestinfoBean.setInquiryAccount1("NA");
					inquiryrequestinfoBean.setInquiryAccount2("NA");
					inquiryrequestinfoBean.setInquiryAccount3("NA");
					inquiryrequestinfoBean.setInquiryAccount4("NA");
				}
			}
			catch(Exception ec)
			{
				logger.error(" There is error while saving data for recent activity "+ec);
			}
//			equifaxPCSDetailslogs.setRecentActivities(inquiryrequestinfoBean);
			request.setAttribute("inquiryrequestinfoBean", inquiryrequestinfoBean);
			////////////////////////////////////////////inquiryrequestinfo ////////////////////////////////////////////


		}
		catch(Exception ec)
		{
			ec.printStackTrace();
			logger.error("There is error while getting General Data from Request to"+ec);
		}

	}



}
