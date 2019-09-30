package com.qualtech.equifax.api.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.equifax.api.common.dto.Address;
import com.qualtech.equifax.api.common.dto.Phone;
import com.qualtech.equifax.api.common.dto.Relation;
import com.qualtech.equifax.api.common.dto.UniqueId;
import com.qualtech.equifax.api.interfaces.EquifaxUtillInt;
import com.qualtech.equifax.api.request.EquifaxAPI_EVDRRequest;
import com.qualtech.equifax.api.request.EquifaxAPI_VIDRequest;
import com.qualtech.equifax.api.request.EquifaxApiRequest;
import com.qualtech.equifax.api.request.EquifaxEVDRRequest;
import com.qualtech.equifax.api.request.EquifaxRequest;
@Service
public class EquifaxUtill implements EquifaxUtillInt
{
	//public static ResourceBundle resProp = PropertyFile.getInstance().getResourceBundel();
	@Autowired PropertyFile resProp;
	private static Logger logger = Logger.getLogger(EquifaxUtill.class);

	public String rquestxmlforPCS(EquifaxApiRequest equifaxapiRequest)
	{
		StringBuilder requestStr = new StringBuilder();
		List<EquifaxRequest> transactions=equifaxapiRequest.getPayload().getTransaction();
		for(int i=0;i<transactions.size();i++)
		{
			EquifaxRequest equifaxRequest=transactions.get(i);
			String fname=equifaxRequest.getfName();
			String mname=equifaxRequest.getmName();
			String lname=equifaxRequest.getlName();
			String dob=equifaxRequest.getDob();
			String custmerId=equifaxRequest.getConsumerId();
			///All request xml formats are taken from soapui request getted from equifax shared url.--same discussed with anant, atul sumit as well

			requestStr.append(" 	<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://services.equifax.com/eport/ws/schemas/1.0\">	 ");
			requestStr.append(" 	   <soapenv:Header/>	 ");
			requestStr.append(" 	   <soapenv:Body>	 ");
			requestStr.append(" 	      <ns:InquiryRequest>	 ");
			requestStr.append(" 	         <ns:RequestHeader>	 ");
			try {
				requestStr.append(" 	            <ns:CustomerId>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId")+"</ns:CustomerId>	 ");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			requestStr.append(" 	            <ns:UserId>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.UserId")+"</ns:UserId>	 ");
			requestStr.append(" 	            <ns:Password>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Password")+"</ns:Password>	 ");
			requestStr.append(" 	            <ns:MemberNumber>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber")+"</ns:MemberNumber>	 ");
			requestStr.append(" 	            <ns:SecurityCode>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSSecurityCode")+"</ns:SecurityCode>	 ");
			requestStr.append(" 	            <ns:ProductCode>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSProductCode")+"</ns:ProductCode>	 ");
			requestStr.append(" 	            <ns:ProductVersion>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.ProductVersion")+"</ns:ProductVersion>	 ");
			requestStr.append(" 	            <ns:ReportFormat>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.ReportFormat")+"</ns:ReportFormat>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:CustRefField>"+custmerId+"</ns:CustRefField>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:CustomInquiryFlag></ns:CustomInquiryFlag>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:N3ConfigID></ns:N3ConfigID>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:CustomInquiryFlag1></ns:CustomInquiryFlag1>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:CustomInquiryFlag2></ns:CustomInquiryFlag2>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:CustomInquiryFlag3></ns:CustomInquiryFlag3>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:CustomInquiryFlag4></ns:CustomInquiryFlag4>	 ");
			requestStr.append(" 	         </ns:RequestHeader>	 ");

			requestStr.append(" 	         <!--Optional:-->	 ");
			requestStr.append(" 	         <ns:RequestAccountDetails seq=\"\">	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:AccountNumber></ns:AccountNumber>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:BranchIDMFI></ns:BranchIDMFI>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:KendraIDMFI></ns:KendraIDMFI>	 ");
			requestStr.append(" 	         </ns:RequestAccountDetails>	 ");
			requestStr.append(" 	         <!--Optional:-->	 ");
			requestStr.append(" 	         <ns:InquiryCommonAccountDetails>	 ");
			requestStr.append(" 	            <!--Zero or more repetitions:-->	 ");
			requestStr.append(" 	            <ns:InquiryAccount seq=\"1\">	 ");
			requestStr.append(" 	               <!--Optional:-->	 ");
			requestStr.append(" 	               <ns:AccountNumber></ns:AccountNumber>	 ");
			requestStr.append(" 	               <!--Optional:-->	 ");
			requestStr.append(" 	               <ns:BranchIDMFI></ns:BranchIDMFI>	 ");
			requestStr.append(" 	               <!--Optional:-->	 ");
			requestStr.append(" 	               <ns:KendraIDMFI></ns:KendraIDMFI>	 ");
			requestStr.append(" 	            </ns:InquiryAccount>	 ");
			requestStr.append(" 	         </ns:InquiryCommonAccountDetails>	 ");


			requestStr.append(" 	         <ns:RequestBody>	 ");
			requestStr.append(" 	            <ns:InquiryPurpose>"+equifaxRequest.getInquiryPurpose()+"</ns:InquiryPurpose>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:FullName>"+fname+(mname.equals("")?"":" "+mname)+(lname.equals("")?"":" "+lname)+"</ns:FullName>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:FirstName>"+fname+"</ns:FirstName>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:MiddleName>"+mname+"</ns:MiddleName>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:LastName>"+lname+"</ns:LastName>	 ");

			
			
			
		/*	requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:FamilyDetails>	 ");
			requestStr.append(" 	               <!--Zero or more repetitions:-->	 ");
			requestStr.append(" 	               <ns:AdditionalNameInfo seq=\"1\">	 ");
			requestStr.append(" 	                  <!--Optional:-->	 ");
			requestStr.append(" 	                  <ns:AdditionalName></ns:AdditionalName>	 ");
			requestStr.append(" 	                  <!--Optional:-->	 ");
			requestStr.append(" 	                  <ns:AdditionalNameType></ns:AdditionalNameType>	 ");
			requestStr.append(" 	               </ns:AdditionalNameInfo>	 ");
			requestStr.append(" 	               <!--Optional:-->	 ");
			*/
			try{
				requestStr.append(" 	            <!--Optional:-->	 ");
				requestStr.append(" 	            <ns:FamilyDetails>	 ");
				requestStr.append(" 	               <!--Zero or more repetitions:-->	 ");
				List<Relation> relations=equifaxRequest.getRelations();
				if(relations.size()>0){
					for(int j=0;j<relations.size();j++)
					{
									Relation relation=relations.get(j);	
									requestStr.append("<ns:AdditionalNameInfo seq=\""+j+"\">");
										requestStr.append("<ns:AdditionalName>"+relation.getName()+"</ns:AdditionalName>");
										requestStr.append("<ns:AdditionalNameType>"+relation.getRelation()+"</ns:AdditionalNameType>");
									requestStr.append("</ns:AdditionalNameInfo>");
														
					}
				}
			}catch(Exception ee){
				requestStr.append(" 	               <ns:AdditionalNameInfo seq=\"1\">	 ");
				requestStr.append(" 	                  <!--Optional:-->	 ");
				requestStr.append(" 	                  <ns:AdditionalName></ns:AdditionalName>	 ");
				requestStr.append(" 	                  <!--Optional:-->	 ");
				requestStr.append(" 	                  <ns:AdditionalNameType></ns:AdditionalNameType>	 ");
				requestStr.append(" 	               </ns:AdditionalNameInfo>	 ");
				requestStr.append(" 	               <!--Optional:-->	 ");
				ee.printStackTrace();
			}
			
			/////////////////////////////////////////////
			
			requestStr.append(" 	               <ns:NoOfDependents></ns:NoOfDependents>	 ");
			requestStr.append(" 	            </ns:FamilyDetails>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:TransactionAmount></ns:TransactionAmount>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:AdditionalSearchField></ns:AdditionalSearchField>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:AddrLine1></ns:AddrLine1>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:Street></ns:Street>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:Locality1></ns:Locality1>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:Locality2></ns:Locality2>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:City></ns:City>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:State></ns:State>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:Postal></ns:Postal>	 ");



			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:InquiryAddresses>	 ");
			List<Address> addresses=equifaxRequest.getAddresses();
			for(int j=0;j<addresses.size();j++)
			{
				Address addres=	addresses.get(j);
				requestStr.append(" 	               <!--Zero or more repetitions:-->	 ");
				requestStr.append(" 	               <ns:InquiryAddress seq=\""+(j+1)+"\">	 ");
				requestStr.append(" 	                  <ns:AddressLine>"+addres.getAddress()+"</ns:AddressLine>	 ");
				requestStr.append(" 	                  <!--Optional:-->	 ");
				requestStr.append(" 	                  <ns:Street></ns:Street>	 ");
				requestStr.append(" 	                  <!--Optional:-->	 ");
				requestStr.append(" 	                  <ns:Locality1></ns:Locality1>	 ");
				requestStr.append(" 	                  <!--Optional:-->	 ");
				requestStr.append(" 	                  <ns:Locality2></ns:Locality2>	 ");
				requestStr.append(" 	                  <!--Optional:-->	 ");
				requestStr.append(" 	                  <ns:City>"+addres.getCity()+"</ns:City>	 ");
				requestStr.append(" 	                  <ns:State>"+addres.getState()+"</ns:State>	 ");
				requestStr.append(" 	                  <ns:Postal>"+addres.getPin()+"</ns:Postal>	 ");
				requestStr.append(" 	                  <!--Optional:-->	 ");
				requestStr.append(" 	                  <ns:AddressType></ns:AddressType>	 ");
				requestStr.append(" 	               </ns:InquiryAddress>	 ");
			}
			requestStr.append(" 	            </ns:InquiryAddresses>	 ");

			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:InquiryPhones>	 ");
			List<Phone> phones=equifaxRequest.getPhones();
			for(int j=0;j<phones.size();j++)
			{
				String phoneType = getPhoneType(phones.get(j).getType());
				if(phoneType!=null && !phoneType.equalsIgnoreCase("O"))
				{
					requestStr.append(" 	               <!--Zero or more repetitions:-->	 ");
					requestStr.append(" 	               <ns:InquiryPhone seq=\""+(j+1)+"\">	 ");
					requestStr.append(" 	                  <!--Optional:-->	 ");
					requestStr.append(" 	                  <ns:CountryCode></ns:CountryCode>	 ");
					requestStr.append(" 	                  <!--Optional:-->	 ");
					requestStr.append(" 	                  <ns:AreaCode></ns:AreaCode>	 ");
					requestStr.append(" 	                  <!--Optional:-->	 ");
					requestStr.append(" 	                  <ns:Number>"+phones.get(j).getTeleNo()+"</ns:Number>	 ");
					requestStr.append(" 	                  <!--Optional:-->	 ");
					requestStr.append(" 	                  <ns:PhoneNumberExtension></ns:PhoneNumberExtension>	 ");
					requestStr.append(" 	                  <!--Optional:-->	 ");
					requestStr.append(" 	                  <ns:PhoneType>"+phoneType+"</ns:PhoneType>	 ");
					requestStr.append(" 	               </ns:InquiryPhone>	 ");
				}
			}
			requestStr.append(" 	            </ns:InquiryPhones>	 ");

			requestStr.append(" 	            <ns:DOB>"+dob+"</ns:DOB>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:MaritalStatus>"+equifaxRequest.getMaritalStatus()+"</ns:MaritalStatus>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:Gender>"+equifaxRequest.getGender()+"</ns:Gender>	 ");

			List<UniqueId> ids=equifaxRequest.getIds();
			for(int j=0;j<ids.size();j++)
			{
				if(ids.get(j).getIdName().toLowerCase().startsWith("pan"))
				{
					requestStr.append(" 	            <!--Optional:-->	 ");
					requestStr.append(" 	            <ns:PANId>"+ids.get(j).getIdNo()+"</ns:PANId>	 ");
				}
				if(ids.get(j).getIdName().toLowerCase().startsWith("passport"))
				{
					requestStr.append(" 	            <!--Optional:-->	 ");
					requestStr.append(" 	            <ns:PassportId>"+ids.get(j).getIdNo()+"</ns:PassportId>	 ");
				}
				if(ids.get(j).getIdName().toLowerCase().startsWith("voterid"))
				{
					requestStr.append(" 	            <!--Optional:-->	 ");
					requestStr.append(" 	            <ns:VoterId>"+ids.get(j).getIdNo()+"</ns:VoterId>	 ");

				}
				if(ids.get(j).getIdName().toLowerCase().startsWith("nationalid"))
				{
					requestStr.append(" 	            <!--Optional:-->	 ");
					requestStr.append(" 	            <ns:NationalIdCard>"+ids.get(j).getIdNo()+"</ns:NationalIdCard>	 ");
				}
				if(ids.get(j).getIdName().toLowerCase().startsWith("rationcard"))
				{
					requestStr.append(" 	            <!--Optional:-->	 ");
					requestStr.append(" 	            <ns:RationCard>"+ids.get(j).getIdNo()+"</ns:RationCard>	 ");
				}
				if(ids.get(j).getIdName().toLowerCase().startsWith("other1"))
				{
					requestStr.append(" 	            <!--Optional:-->	 ");
					requestStr.append(" 	            <ns:AdditionalId1>"+ids.get(j).getIdNo()+"</ns:AdditionalId1>	 ");
				}
				if(ids.get(j).getIdName().toLowerCase().startsWith("other2"))
				{
					requestStr.append(" 	            <!--Optional:-->	 ");
					requestStr.append(" 	            <ns:AdditionalId2>"+ids.get(j).getIdNo()+"</ns:AdditionalId2>	 ");
				}
				/*if(ids.get(j).getIdName().toLowerCase().startsWith("drivinglicense"))*/
					if(ids.get(j).getIdName().toLowerCase().startsWith("driverlicense"))
				{
					requestStr.append(" 	            <!--Optional:-->	 ");
					requestStr.append(" 	            <ns:DriverLicense>"+ids.get(j).getIdNo()+"</ns:DriverLicense>	 ");
				}
			}


			List<Phone> phoneList=equifaxRequest.getPhones();
			for(int j=0;j<phoneList.size();j++)
			{
				Phone phone=phoneList.get(j);
				String phoneType = getPhoneType(phones.get(j).getType());
				if(phoneType!=null && !phoneType.equalsIgnoreCase("O"))
				{
					if(phoneType.equalsIgnoreCase("H") || phoneType.equalsIgnoreCase("P"))//Personel Home or just Home
					{
						requestStr.append(" 	            <!--Optional:-->	 ");
						requestStr.append(" 	            <ns:HomePhone seq=\""+(j+1)+"\" ReportedDate=\"?\">	 ");
						requestStr.append(" 	               <ns:PhoneNumber>"+phone.getTeleNo()+"</ns:PhoneNumber>	 ");
						requestStr.append(" 	            </ns:HomePhone>	 ");
					}
					else if(phoneType.equalsIgnoreCase("M"))
					{
						requestStr.append(" 	            <!--Optional:-->	 ");
						requestStr.append(" 	            <ns:MobilePhone>"+phone.getTeleNo()+"</ns:MobilePhone>	 ");
					}
				}
			}

			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:RequestAccountDetails seq=\"1\">	 ");
			requestStr.append(" 	               <!--Optional:-->	 ");
			requestStr.append(" 	               <ns:AccountNumber></ns:AccountNumber>	 ");
			requestStr.append(" 	               <!--Optional:-->	 ");
			requestStr.append(" 	               <ns:BranchIDMFI></ns:BranchIDMFI>	 ");
			requestStr.append(" 	               <!--Optional:-->	 ");
			requestStr.append(" 	               <ns:KendraIDMFI></ns:KendraIDMFI>	 ");
			requestStr.append(" 	            </ns:RequestAccountDetails>	 ");
			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:InquiryCommonAccountDetails>	 ");
			requestStr.append(" 	               <!--Zero or more repetitions:-->	 ");
			requestStr.append(" 	               <ns:InquiryAccount seq=\"1\">	 ");
			requestStr.append(" 	                  <!--Optional:-->	 ");
			requestStr.append(" 	                  <ns:AccountNumber></ns:AccountNumber>	 ");
			requestStr.append(" 	                  <!--Optional:-->	 ");
			requestStr.append(" 	                  <ns:BranchIDMFI></ns:BranchIDMFI>	 ");
			requestStr.append(" 	                  <!--Optional:-->	 ");
			requestStr.append(" 	                  <ns:KendraIDMFI></ns:KendraIDMFI>	 ");
			requestStr.append(" 	               </ns:InquiryAccount>	 ");
			requestStr.append(" 	            </ns:InquiryCommonAccountDetails>	 ");


			requestStr.append(" 	            <!--Optional:-->	 ");
			requestStr.append(" 	            <ns:InquiryFieldsDsv></ns:InquiryFieldsDsv>	 ");
			requestStr.append(" 	         </ns:RequestBody>	 ");
			requestStr.append(" 	      </ns:InquiryRequest>	 ");
			requestStr.append(" 	   </soapenv:Body>	 ");
			requestStr.append(" 	</soapenv:Envelope>	 ");


			//  			requestxml +="	<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://services.equifax.com/eport/ws/schemas/1.0\">	"+
			//  					"<soapenv:Header />"+
			//  					"<soapenv:Body>"+
			//  					"<ns:InquiryRequest>"+
			//  					"<ns:RequestHeader>	"+
			//  					"<ns:CustomerId>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId")+"</ns:CustomerId>	"+
			//  					"<ns:UserId>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.UserId")+"</ns:UserId>	"+
			//  					"<ns:Password>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Password")+"</ns:Password>	"+
			//  					"	        <ns:MemberNumber>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSMemberNumber")+"</ns:MemberNumber>	"+
			//  					"	        <ns:SecurityCode>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSSecurityCode")+"</ns:SecurityCode>	"+
			//  					"	        <ns:ProductCode>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.PCSProductCode")+"</ns:ProductCode>	"+
			//  					"	        <ns:ProductVersion>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.ProductVersion")+"</ns:ProductVersion>	"+
			//  					"	        <ns:ReportFormat>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.ReportFormat")+"</ns:ReportFormat>	"+
			//  					"	        <!--Optional:-->	"+
			//  					"	        <ns:N3ConfigID></ns:N3ConfigID>	"+
			//  					"	        <!--Optional:-->	"+
			//  					"	        <ns:CustRefField></ns:CustRefField>	"+
			//  					"	      </ns:RequestHeader>	"+
			//  					"	      <!--start Added addtion account new slot:-->	"+
			//  					"	      <ns:InquiryCommonAccountDetails>	"+
			//  					"	        <ns:InquiryAccount seq=\"1\">	"+
			//  					"	          <ns:AccountNumber></ns:AccountNumber>	"+
			//  					"	        </ns:InquiryAccount>	"+
			//  					"	      </ns:InquiryCommonAccountDetails>	"+
			//  					"	      <!--end Added addtion account new slot:-->	"+
			//  					"	      <ns:RequestBody>	"+
			//  					"	        <ns:InquiryPurpose>"+equifaxRequest.getInquiryPurpose()+"</ns:InquiryPurpose>	"+
			//  					"	        <!--Optional:-->	"+
			//  					"	        <ns:FirstName>"+fname+"</ns:FirstName>	"+
			//  					"	        <!--Optional:-->	"+
			//  					"			<ns:MiddleName>"+mname+"</ns:MiddleName>"+
			//  					"			<!--Optional:-->	"+
			//  					"	        <ns:LastName>"+lname+"</ns:LastName>	"+
			//  					"           <ns:FullName>"+fname+(mname.equals("")?"":" "+mname)+(lname.equals("")?"":" "+lname)+"</ns:FullName>"+
			//  					"	        <ns:DOB>"+dob+"</ns:DOB>	"+
			//  					"	        <!--Optional:-->	";
			//
			//
			//  			List<UniqueId> ids=equifaxRequest.getIds();
			//  			for(int j=0;j<ids.size();j++)
			//  			{
			//  				if(ids.get(j).getIdName().toLowerCase().startsWith("pan"))
			//  				{
			//  					requestxml +="  <ns:PANId>"+ids.get(j).getIdNo()+"</ns:PANId>	";		
			//  				}
			//  				if(ids.get(j).getIdName().toLowerCase().startsWith("passport"))
			//  				{
			//  					requestxml +="  <ns:PassportId>"+ids.get(j).getIdNo()+"</ns:PassportId>";
			//  				}
			//  				if(ids.get(j).getIdName().toLowerCase().startsWith("driverlicence"))
			//  				{
			//  					requestxml +="  <ns:DriverLicence>"+ids.get(j).getIdNo()+"</ns:DriverLicence>";
			//  				}
			//  				if(ids.get(j).getIdName().toLowerCase().startsWith("voterid"))
			//  				{
			//  					requestxml +="  <ns:VoterID>"+ids.get(j).getIdNo()+"</ns:VoterID>";
			//  				}
			//  				if(ids.get(j).getIdName().toLowerCase().startsWith("nationalidcard"))
			//  				{
			//  					requestxml +="  <ns:NationalIDCard>"+ids.get(j).getIdNo()+"</ns:NationalIDCard>";
			//  				}
			//  				if(ids.get(j).getIdName().toLowerCase().startsWith("rationcard"))
			//  				{
			//  					requestxml +="  <ns:RationCard>"+ids.get(j).getIdNo()+"</ns:RationCard>";
			//  				}
			//  			}
			//
			//  			requestxml +="<!--Optional:-->	";
			//
			//  			requestxml +="   <ns:MobilePhone>"+equifaxRequest.getPhones().get(0).getTeleNo()+"</ns:MobilePhone>	";
			//  			
			//  			
			//  			
			//  			requestxml +="	<ns:InquiryAddresses>	";
			//  			List<Address> addresses=equifaxRequest.getAddresses();
			//  			for(int j=0;j<addresses.size();j++)
			//  			{
			//  				Address addres=	addresses.get(j);
			//  				requestxml +="	     <ns:InquiryAddress seq=\""+j+"\">	"+
			//  						"	            <ns:AddressLine>"+addres.getAddress()+"</ns:AddressLine>	"+
			//  						"	            <ns:State>"+addres.getState()+"</ns:State>	"+
			//  						"	            <ns:Postal>"+addres.getPin()+"</ns:Postal>	"+
			//  						"	          </ns:InquiryAddress>	";
			//  			}
			//  			requestxml +="	        </ns:InquiryAddresses>	";
			//  			
			//  			
			//  			
			//  			requestxml +="	        <ns:InquiryPhones>	";
			//  			List<Phone> phones=equifaxRequest.getPhones();
			//  			for(int j=0;j<phones.size();j++)
			//  			{
			//  				requestxml +="	          <ns:InquiryPhone seq=\""+(j+1)+"\">	"+
			//  						"	                     <ns:Number>"+phones.get(j).getTeleNo()+"</ns:Number>"+
			//  						"<ns:PhoneType>"+getPhoneType(phones.get(j).getType())+"</ns:PhoneType>	"+
			//  						"	                     </ns:InquiryPhone>	";
			//  			}
			//  			requestxml +="	        </ns:InquiryPhones>	"+
			//
			//  				"	      </ns:RequestBody>	"+
			//  				"	    </ns:InquiryRequest>	"+
			//  				"	  </soapenv:Body>	"+
			//  				"	</soapenv:Envelope>	";

		}
		logger.info("Request XML:      Request XML for PCS  "+requestStr.toString());
		return requestStr.toString();
	}
	public String rquestxmlforMCR(EquifaxApiRequest equifaxapiRequest)
	{
		String requestxml="";
		List<EquifaxRequest> transactions=equifaxapiRequest.getPayload().getTransaction();
		try{
			for(int i=0;i<transactions.size();i++)
			{
				EquifaxRequest equifaxRequest=transactions.get(i);
				String fname=equifaxRequest.getfName();
				String mName=equifaxRequest.getmName().trim();
				String lname=equifaxRequest.getlName();
				String custmerId=equifaxRequest.getConsumerId();
				String dob=equifaxRequest.getDob();
				String gender=equifaxRequest.getGender();
				String maritalStatus=equifaxRequest.getMaritalStatus();
				requestxml = "	<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://services.equifax.com/eport/ws/schemas/1.0\">	"+
						"	  <soapenv:Header />	"+
						"	  <soapenv:Body>	"+
						"	    <ns:InquiryRequest>	"+
						"	      <ns:RequestHeader> "+
						"	        <ns:CustomerId>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId")+"</ns:CustomerId>	"+
						"	        <ns:UserId>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.UserId")+"</ns:UserId>	"+
						"	        <ns:Password>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Password")+"</ns:Password>	"+
						"	        <ns:MemberNumber>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.MCRMemberNumber")+"</ns:MemberNumber>	"+
						"	        <ns:SecurityCode>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.MCRSecurityCode")+"</ns:SecurityCode>	"+
						"	        <ns:ProductCode>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.MCRProductCode")+"</ns:ProductCode>	"+
						"	        <ns:ProductVersion>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.ProductVersion")+"</ns:ProductVersion>	"+
						"	        <ns:ReportFormat>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.ReportFormat")+"</ns:ReportFormat>	"+
						"	        <ns:CustRefField>"+custmerId+"</ns:CustRefField>	"+
						"	      </ns:RequestHeader>	"+
						"	         <ns:RequestBody>	"+
						"	            <ns:InquiryPurpose>"+equifaxRequest.getInquiryPurpose()+"</ns:InquiryPurpose>	"+
						"				<ns:AdditionalSearchField></ns:AdditionalSearchField>"+
						"	            <ns:FullName>"+fname+(mName.equals("")?"":" "+mName)+(lname.equals("")?"":" "+lname)+"</ns:FullName>"+
						"	            <ns:FirstName>"+fname+"</ns:FirstName>	"+
						"	            <ns:MiddleName>"+mName+"</ns:MiddleName>	"+
						"	            <ns:LastName>"+lname+"</ns:LastName>	"+
						"	            <ns:FamilyDetails>	";


				List<Relation> relations=equifaxRequest.getRelations();
				for(int j=0;j<relations.size();j++)
				{
					Relation relation=relations.get(j);	
					requestxml+="	                   <ns:AdditionalNameInfo seq=\""+j+"\">	"+
							"	                  <ns:AdditionalName>"+relation.getName()+"</ns:AdditionalName>	"+
							"	                  <ns:AdditionalNameType>"+relation.getRelation()+"</ns:AdditionalNameType>	"+
							"	               </ns:AdditionalNameInfo>	"+
							"	            </ns:FamilyDetails>	";
				}
				Address address=equifaxRequest.getAddresses().get(0);
				if(address!=null)
				{
					requestxml+="	            <ns:AddrLine1>"+address.getAddress()+"</ns:AddrLine1>	"+
							"	            <ns:State>"+address.getState()+"</ns:State>	"+
							"	            <ns:Postal>"+address.getPin()+"</ns:Postal>	";
				}
				requestxml+="<ns:DOB>"+dob+"</ns:DOB>	"+
						"<ns:MaritalStatus>"+maritalStatus+"</ns:MaritalStatus>	"+
						"<ns:Gender>"+gender+"</ns:Gender>";

				requestxml +="<ns:InquiryPhones>	";

				List<Phone> phones=equifaxRequest.getPhones();

				for(int j=0;j<phones.size();j++)
				{
					requestxml +="	 <ns:InquiryPhone seq=\""+(j+1)+"\">	"+
							"<ns:Number>"+phones.get(j).getTeleNo()+"</ns:Number>"+
							"<ns:PhoneType>"+getPhoneType(phones.get(j).getType())+"</ns:PhoneType>	"+
							"</ns:InquiryPhone>	";
				}

				requestxml +="	</ns:InquiryPhones>	";

				/*List<Phone> phones=equifaxRequest.getPhones();
	            if(phones!=null && !phones.isEmpty())
	            {
	            	boolean moSet=false;
           		boolean  otherSet=false;
           		String otherPhones="";
           		int k=0;
	            	for(int j=0;j<phones.size();j++)
					{
	            		Phone phone=phones.get(j);
	            		if(phone.getType().equalsIgnoreCase("Mobile") && !moSet)
	            		{
	            			 requestxml+="<ns:MobilePhone>"+phone.getTeleNo()+"</ns:MobilePhone>";
	            			 moSet=true;
	            		}
	            		else if(!phone.getType().equalsIgnoreCase("") && moSet)
	            		{
	            			otherPhones="<ns:PhoneNumber>"+phone.getTeleNo()+"</ns:PhoneNumber>";
	            			otherSet=true;
	            		}
					}
	            	if(otherSet)
	            	{
	            		requestxml+="<ns:HomePhone seq=\""+(++k)+"\"> "+otherPhones+"</ns:HomePhone>";
	            	}
	            }*/




				List<UniqueId> uniquieId=equifaxRequest.getIds();
				for(int j=0;j<uniquieId.size();j++)
				{
					UniqueId uniqueId=uniquieId.get(j);	
					if(uniqueId!=null){

						String idName=uniqueId.getIdName();
						String idNo=uniqueId.getIdNo();
						if(idName!=null && (idName.startsWith("NationalIdCard")||idName.startsWith("nationalidcard")||idName.equalsIgnoreCase("NationalIdCard")))
						{
							requestxml+=	"<ns:NationalIdCard>"+idNo+"</ns:NationalIdCard>";
						}
						else if(idName!=null && (idName.startsWith("PAN")||idName.startsWith("pan")||idName.startsWith("Pan")||idName.equalsIgnoreCase("pan")))
						{
							requestxml+=	"<ns:PANId>"+idNo+"</ns:PANId>";
						}
						else  if(idName!=null && (idName.startsWith("PASSPORT")||idName.startsWith("passport")||idName.startsWith("Passport")||idName.equalsIgnoreCase("passport")))
						{
							requestxml+=	"<ns:PassportId>"+idNo+"</ns:PassportId>";
							//PassportId 
						}
						else if(idName!=null && (idName.startsWith("VOTERID")||idName.startsWith("voterid")||idName.equalsIgnoreCase("voterid")))
						{
							requestxml+=	"<ns:VoterId>"+idNo+"</ns:VoterId>";
							//VoterId 
						}
						else  if(idName!=null && (idName.startsWith("RATIONCARD")||idName.startsWith("rationcard")||idName.equalsIgnoreCase("rationcard")))
						{
							requestxml+="<ns:RationCard>"+idNo+"</ns:RationCard>";
							//RationCard 
						}
						else if(idName!=null && (idName.startsWith("DRIVERLICENSE")||idName.startsWith("driverlicense")||idName.equalsIgnoreCase("driverlicense")))
						{
							requestxml+="<ns:DriverLicense>"+idNo+"</ns:DriverLicense>";
							//DriverLicense 
						}
						else
						{
							requestxml+="<ns:"+idName+">"+idNo+"</ns:"+idName+">";
						}
					}
				}

				requestxml+=" </ns:RequestBody>	"+
						"	      </ns:InquiryRequest>	"+
						"	   </soapenv:Body>	"+
						"	</soapenv:Envelope>	";

				logger.debug("EQUIFAX service: Request XML for MCR :"+requestxml);
				logger.info("Request XML:      Request XML for MCR  "+requestxml);
			}
		}catch(Exception ee){
			ee.printStackTrace();
		}
		return requestxml;
	}
//TODO
	public String requestxmlForVID(EquifaxAPI_VIDRequest equifaxApiRequest)
	{
		StringBuilder xmlrequest=new StringBuilder("");

		xmlrequest.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlrequest.append(	"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://services.equifax.com/eport/ws/schemas/1.0\">");
		xmlrequest.append(	"<soapenv:Header />");
		xmlrequest.append(	"<soapenv:Body>");
		xmlrequest.append(	"<ns:InquiryRequest>");
		xmlrequest.append(	"<ns:RequestHeader>");
		xmlrequest.append(	"<ns:CustomerId>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId")+"</ns:CustomerId>");
		xmlrequest.append(	"<ns:UserId>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.UserId")+"</ns:UserId>");
		xmlrequest.append(	"<ns:Password>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Password")+"</ns:Password>");
		xmlrequest.append(	"<ns:MemberNumber>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.VIDMemberNumber")+"</ns:MemberNumber>");
		xmlrequest.append(	"<ns:SecurityCode>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.VIDSecurityCode")+"</ns:SecurityCode>");
		xmlrequest.append(	"<ns:ProductCode>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.VIDProductCode")+"</ns:ProductCode>");
		xmlrequest.append(	"<ns:ProductVersion>1.0</ns:ProductVersion>");
		xmlrequest.append(	"<ns:ReportFormat>XML</ns:ReportFormat>");
		xmlrequest.append(	"</ns:RequestHeader>");
		xmlrequest.append(	"<ns:RequestBody>");
		xmlrequest.append(	" <ns:InquiryPurpose>"+equifaxApiRequest.getPayload().getTransaction().get(0).getInquiryPurpose()+"</ns:InquiryPurpose>");
		xmlrequest.append(	"<ns:FirstName>"+equifaxApiRequest.getPayload().getTransaction().get(0).getfName()+"</ns:FirstName>");
		xmlrequest.append(	"<ns:LastName>"+equifaxApiRequest.getPayload().getTransaction().get(0).getlName()+"</ns:LastName>");
		String idName=equifaxApiRequest.getPayload().getTransaction().get(0).getIds().get(0).getIdName();
		String idNo=equifaxApiRequest.getPayload().getTransaction().get(0).getIds().get(0).getIdNo();

		if(idName!=null && (idName.startsWith("PAN")||idName.startsWith("pan")))
		{
			xmlrequest.append(	"<ns:PANId>"+idNo+"</ns:PANId>");
		}
		else if(idName!=null && (idName.startsWith("PASSPORT")||idName.startsWith("passport")))
		{
			xmlrequest.append(	"<ns:PassportId>"+idNo+"</ns:PassportId>");
			//PassportId 
		}
		else if(idName!=null && (idName.startsWith("VOTERID")||idName.startsWith("voterid")))
		{
			xmlrequest.append(	"<ns:VoterId>"+idNo+"</ns:VoterId>");
			//VoterId 
		}
		else if(idName!=null && (idName.startsWith("RATIONCARD")||idName.startsWith("rationcard")))
		{
			xmlrequest.append(	"<ns:RationCard>"+idNo+"</ns:RationCard>");
			//RationCard 
		}
		else if(idName!=null && (idName.startsWith("DRIVERLICENSE")||idName.startsWith("driverlicense")))
		{
			xmlrequest.append(	"<ns:DriverLicense>"+idNo+"</ns:DriverLicense>");
			//DriverLicense 
		}
		else
		{
			xmlrequest.append(	"<ns:"+idName+">"+idNo+"</ns:"+idName+">");
		}

		xmlrequest.append(	"</ns:RequestBody>");
		xmlrequest.append(	"</ns:InquiryRequest>");
		xmlrequest.append(	"</soapenv:Body>");
		xmlrequest.append(	"</soapenv:Envelope>");

		return xmlrequest.toString();
	}

	public String requestxmlForEVDR(EquifaxAPI_EVDRRequest equifaxApiRequest)
	{

		EquifaxEVDRRequest transaction=equifaxApiRequest.getPayload().getTransaction().get(0);
		StringBuffer evdrRequest=new StringBuffer("");
		evdrRequest.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");															
		evdrRequest.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns=\"http://services.equifax.com/eport/ws/schemas/1.0\">");									
		evdrRequest.append("<soapenv:Header/>");
		evdrRequest.append("<soapenv:Body>");
		evdrRequest.append("<ns:InquiryRequest>");
		evdrRequest.append("<ns:RequestHeader>");
		evdrRequest.append("<ns:CustomerId>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.CustomerId")+"</ns:CustomerId>");
		evdrRequest.append("<ns:UserId>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.UserId")+"</ns:UserId>");
		evdrRequest.append("<ns:Password>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.Password")+"</ns:Password>");
		evdrRequest.append("<ns:MemberNumber>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.EVDRMemberNo")+"</ns:MemberNumber>");
		evdrRequest.append("<ns:SecurityCode>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.EVDRSecCode")+"</ns:SecurityCode>");
		evdrRequest.append("<ns:ProductCode>"+resProp.getString("com.qualtech.pan2.resource.EQUIFAX.EVDRProductCode")+"</ns:ProductCode>");
		evdrRequest.append("<ns:ProductVersion>1.0</ns:ProductVersion>");
		evdrRequest.append("<ns:ReportFormat>XML</ns:ReportFormat>");
		evdrRequest.append("</ns:RequestHeader>");
		evdrRequest.append("<ns:RequestBody>");
		evdrRequest.append("<ns:InquiryPurpose>01</ns:InquiryPurpose>");
		evdrRequest.append("<ns:TransactionAmount>"+transaction.getAmount()+"</ns:TransactionAmount>");  ///amount
		evdrRequest.append("<ns:FirstName>"+transaction.getfName()+"</ns:FirstName>");              //first Name
		evdrRequest.append("<ns:LastName>"+transaction.getlName()+"</ns:LastName>");                        //Last Name
		evdrRequest.append("<ns:AddrLine1>"+transaction.getAddresses().get(0).getAddress()+"</ns:AddrLine1>");//Address
		evdrRequest.append("<ns:State>"+transaction.getAddresses().get(0).getState()+"</ns:State>");//State
		evdrRequest.append("<ns:Postal>"+transaction.getAddresses().get(0).getPin()+"</ns:Postal>");//Postal code
		evdrRequest.append("<ns:DOB>"+transaction.getDob()+"</ns:DOB>");//Date of Birth
		String idName=equifaxApiRequest.getPayload().getTransaction().get(0).getIds().get(0).getIdName();
		String idNo=equifaxApiRequest.getPayload().getTransaction().get(0).getIds().get(0).getIdNo();

		if(idName!=null && (idName.startsWith("PAN")||idName.startsWith("pan")))
		{
			evdrRequest.append(	"<ns:PANId>"+idNo+"</ns:PANId>");
		}
		else if(idName!=null && (idName.startsWith("PASSPORT")||idName.startsWith("passport")))
		{
			evdrRequest.append(	"<ns:PassportId>"+idNo+"</ns:PassportId>");
			//PassportId 
		}
		else if(idName!=null && (idName.startsWith("VOTERID")||idName.startsWith("voterid")))
		{
			evdrRequest.append(	"<ns:VoterId>"+idNo+"</ns:VoterId>");
			//VoterId 
		}
		else if(idName!=null && (idName.startsWith("RATIONCARD")||idName.startsWith("rationcard")))
		{
			evdrRequest.append(	"<ns:RationCard>"+idNo+"</ns:RationCard>");
			//RationCard 
		}
		else if(idName!=null && (idName.startsWith("DRIVERLICENSE")||idName.startsWith("driverlicense")))
		{
			evdrRequest.append(	"<ns:DriverLicense>"+idNo+"</ns:DriverLicense>");
			//DriverLicense 
		}
		else
		{
			evdrRequest.append(	"<ns:"+idName+">"+idNo+"</ns:"+idName+">");
		}
		// evdrRequest.append("<ns:PANId>ATCPS9170Q</ns:PANId>");//Pan Id 
		List<Phone> phoneList=transaction.getPhones();

		for(int i=0;i<phoneList.size();i++)
		{
			Phone phone=phoneList.get(i);
			String phonetype=phone.getType();
			phonetype=phonetype.toLowerCase();
			if(phonetype!=null && phonetype.startsWith("home"))
			{
				evdrRequest.append("<ns:HomePhone seq=\"1\">");//Home Phone
				evdrRequest.append("<ns:PhoneNumber>"+phone.getTeleNo()+"</ns:PhoneNumber>");
				evdrRequest.append("</ns:HomePhone>");
			}
			if(phonetype!=null && phonetype.startsWith("mobile"))
			{
				evdrRequest.append("<ns:MobilePhone>"+phone.getTeleNo()+"</ns:MobilePhone>");//Mobile Phone
			}
		}
		evdrRequest.append("</ns:RequestBody>");
		evdrRequest.append("</ns:InquiryRequest>");
		evdrRequest.append("</soapenv:Body>");
		evdrRequest.append("</soapenv:Envelope>");
		logger.info(evdrRequest.toString());

		return evdrRequest.toString();
	}
	/*Added on 13-july by Anuj*/
	public  Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
		Map<String, Object> retMap = new HashMap<String, Object>();

		if(json != JSONObject.NULL) {
			retMap = toMap(json);
		}
		return retMap;
	}

	public  Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public  List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for(int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}
	/*END*/

	public static String getPhoneType(String key)
	{
		try
		{
			if(key==null||key.equalsIgnoreCase("")||key.equalsIgnoreCase("NA"))
			{
				logger.info("Invalid Request data for type so default setting Other");
				return "O";
			}
			else
			{
				return getEquifaxAppendixE().get(key);
			}

		}
		catch(Exception ex)
		{
			logger.error("Wrong input from Request : "+ex);
		}
		logger.info("Invalid Request data for type so default setting Mobile");
		return "M";
	}

	public static Map<String,String> getEquifaxAppendixA()
	{
		Map<String,String> map=new HashMap<String,String>();
		map.put("00","Other");
		map.put("01","Auto Loan");
		map.put("02","Housing Loan");
		map.put("03","Property Loan");
		map.put("04","Loan against Shares/Securities");
		map.put("05","Personal Loan");
		map.put("06","Consumer Loan");
		map.put("07","Gold Loan");
		map.put("08","Education Loan");
		map.put("09","Loan to Professional");
		map.put("0","Other");
		map.put("1","Auto Loan");
		map.put("2","Housing Loan");
		map.put("3","Property Loan");
		map.put("4","Loan against Shares/Securities");
		map.put("5","Personal Loan");
		map.put("6","Consumer Loan");
		map.put("7","Gold Loan");
		map.put("8","Education Loan");
		map.put("9","Loan to Professional");
		map.put("10","Credit Card");
		map.put("11","Lease");
		map.put("12","Overdraft");
		map.put("13","Two-wheeler Loan");
		map.put("14","Non-Funded Credit Facility");
		map.put("15","Loan Against Bank Deposits");
		map.put("16","Fleet Card");
		map.put("17","Commercial Vehicle Loan");
		map.put("18","Telco - Wireless");
		map.put("19","Telco - Broadband");
		map.put("20","Telco - Landline");
		map.put("31","Secured Credit Card");
		map.put("32","Used Car Loan");
		map.put("33","Construction Equipment Loan");
		map.put("34","Tractor Loan");
		map.put("35","Corporate Credit Card");
		map.put("3A","Auto Lease");
		map.put("51","Business Loan");
		map.put("52","Business Loan-Priority Sector-Small Business");
		map.put("53","Business Loan - Priority Sector- Agriculture");
		map.put("54","Business Loan - Priority Sector- Others");
		map.put("55","Business Non-Funded Credit Facility");
		map.put("56","Business Non-Funded Credit Facility - Priority Sector - Small Business");
		map.put("57","Business Non-Funded Credit Facility - Priority Sector - Agriculture");
		map.put("58","Business Non-Funded Credit Facility - Priority Sector - Other");
		map.put("59","Business Loan Against Bank Deposits");
		map.put("60","Staff Loan");
		map.put("8A","Disclosure");
		
		
		map.put("0E","MicroFinance Business Loan");
		map.put("2E","MicroFinance Housing Loan");
		map.put("1G","Testing");
		map.put("1E","MicroFinance Personal Loan");
		map.put("3E","MicroFinance Others");
		return map;
	}
	
	
	public static Map<String,String> getEquifaxAppendixE()
	{
		Map<String,String> map=new HashMap<String,String>();
		map.put("Home","H");
		map.put("Mobile","M");
		map.put("Personal Home Fax","P");
		map.put("Work Fax","F");
		map.put("Work Telephone","T");
		map.put("Employer Telephone","E");
		map.put("Personal Home Fax #","P");
		map.put("Other","O");//Discussed with Equifax Team - Anant 07 Sep 2017
		map.put("H","H");
		map.put("M","M");
		map.put("P","P");
		map.put("F","F");
		map.put("T","T");
		map.put("E","E");
		map.put("O","O");
		return map;
	}

	public static String getEquifaxAppendixEForOutput(String key)
	{
		try
		{
			return getEquifaxAppendixEReverse().get(key).toString();
		}
		catch(Exception ex)
		{
			return "Other";
		}
	}

	public static Map<String,String> getEquifaxAppendixEReverse()
	{
		Map<String,String> map=new HashMap<String,String>();
		map.put("H","Home");
		map.put("M","Mobile");
		map.put("P","Personal Home Fax");
		map.put("F","Work Fax");
		map.put("T","Work Telephone");
		map.put("E","Employer Telephone");
		map.put("O","Other");
		return map;
	}
	
	public static String commaSeprated(String str) {
		double d = 0;
		DecimalFormat twoDForm = new DecimalFormat(",##");
		DecimalFormat twoDForm3 = new DecimalFormat(",###");
		DecimalFormat twoDForm1 = new DecimalFormat("000");
		try
		{
			if(str == null || str.equals("") || str.equals("NA") || str.equalsIgnoreCase("NULL"))
			{
				d  = 0;
			}
			else
				d = Double.parseDouble(str);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		if(d < 1000) {
			return twoDForm3.format(d);
		} else {
			double hundreds = d % 1000;
			long other = (long) (d / 1000);
			return twoDForm.format(other)+','+twoDForm1.format(hundreds);
		}

	}
	
public static String getAdditionalNameType(String key){
		
		Map<String, String> map=new HashMap<String, String>();
		map.put("K01","Father");
		map.put("K02",	"Husband");
		map.put("K07","Brother");
		map.put("K04","Son");
		map.put("K12","Son In Law");
		map.put("K09","Father In Law");
		map.put("K13","Brother in Law");
		map.put("K15","Other");
		map.put("K03","Mother");
		map.put("K06","Wife");
		map.put("K14","Sister");
		map.put("K05","Daughter");
		map.put("K10","Daughter-In-Law");
		map.put("K08", "Mother-In-Law");
		
		map.put("F","Father");
		map.put("H",	"Husband");
		map.put("B","Brother");
		map.put("S","Son");
		map.put("U","Son In Law");
		map.put("Y","Father In Law");
		map.put("T","Brother in Law");
		map.put("O","Other");
		map.put("M","Mother");
		map.put("W","Wife");
		map.put("C","Sister");
		map.put("D","Daughter");
		map.put("V","Daughter-In-Law");
		map.put("Z", "Mother-In-Law");
		
		map.put("Father","Father");
		map.put("Husband",	"Husband");
		map.put("Brother","Brother");
		map.put("Son","Son");
		map.put("Son In Law","Son In Law");
		map.put("Father In Law","Father In Law");
		map.put("Brother in Law","Brother in Law");
		map.put("Other","Other");
		map.put("Mother","Mother");
		map.put("Wife","Wife");
		map.put("Sister","Sister");
		map.put("Daughter","Daughter");
		map.put("Daughter-In-Law","Daughter-In-Law");
		map.put("Mother-In-Law", "Mother-In-Law");
		return map.get(key)!=null?map.get(key):key;
		
	}
}
