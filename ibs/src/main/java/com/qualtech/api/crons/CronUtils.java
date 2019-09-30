package com.qualtech.api.crons;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.api.db.UniqueId;
import com.qualtech.api.ibs.util.ErrorInfo;
import com.qualtech.api.ibs.util.FibitRequest;
import com.qualtech.api.ibs.util.NomineeDetails;
import com.qualtech.cibilv2.api.request.Address;
import com.qualtech.cibilv2.api.request.CibilHeader2;
import com.qualtech.cibilv2.api.request.CibilRequest2;
import com.qualtech.cibilv2.api.request.CibilRequestPayload2;
import com.qualtech.cibilv2.api.request.MemberRelationship;
import com.qualtech.cibilv2.api.request.TelephoneNumberTypeIndicator;
import com.qualtech.creditvidya.api.common.dto.EmailSaveRequest;
import com.qualtech.creditvidya.api.common.dto.EmailVerificationRequest;
import com.qualtech.creditvidya.api.request.ClientReference;
import com.qualtech.creditvidya.api.request.EmailSaveReqPayload;
import com.qualtech.creditvidya.api.request.EmailVerificationReqPayload;
import com.qualtech.creditvidya.api.response.EmailSaveResponse;
import com.qualtech.experiankickoff.api.common.dto.ExperianRequest;
import com.qualtech.experiankickoff.api.request.AuthExperianRequest;
import com.qualtech.experiankickoff.api.request.AuthExperianRequestPayload;
import com.qualtech.experiankickoff.api.request.ExperianGenQuestionReqPayload;
import com.qualtech.experiankickoff.api.request.ExperianGenQuestionRequest;
import com.qualtech.experiankickoff.api.request.ExperianKickOffPayload;
import com.qualtech.experiankickoff.api.request.Header;
import com.qualtech.experiankickoff.api.response.ExperianMaskResPayloadResult;
import com.qualtech.experiankickoff.api.response.ExperianMaskResponse;
import com.qualtech.experiankickoff.api.response.ExperianMaskResponsePayload;
import com.qualtech.finbit.api.request.AccountDetail;
import com.qualtech.finbit.api.request.FinbitApiRequest;
import com.qualtech.finbit.api.request.FinbitHeader;
import com.qualtech.finbit.api.request.FinbitRequestPayload;
import com.qualtech.hdfc.api.request.ApiRequest;
import com.qualtech.hdfc.api.request.CustomerCommunication;
import com.qualtech.hdfc.api.request.CustomerDetails;
import com.qualtech.hdfc.api.request.HdfcRequestPayload;
import com.qualtech.hdfc.api.request.MedicalQuestionnaire;
import com.qualtech.hdfc.api.request.MetaData;
import com.qualtech.hdfc.api.request.PartnerData;
import com.qualtech.hdfc.api.request.PersonalDetails;
import com.qualtech.hdfc.api.request.Response;
import com.qualtech.karza.api.common.dto.AadharRequest;
import com.qualtech.karza.api.common.dto.AddressRequest;
import com.qualtech.karza.api.common.dto.BankAccountRequest;
import com.qualtech.karza.api.common.dto.CAMemberShipAuthRequest;
import com.qualtech.karza.api.common.dto.CompSearchRequest;
import com.qualtech.karza.api.common.dto.CompanyLLPCINLookUpRequest;
import com.qualtech.karza.api.common.dto.CompanyLLPIdentificationRequest;
import com.qualtech.karza.api.common.dto.DlRequest;
import com.qualtech.karza.api.common.dto.EPFAuthOTPRequest;
import com.qualtech.karza.api.common.dto.EPFAuthPassBookRequest;
import com.qualtech.karza.api.common.dto.ESICIdRequest;
import com.qualtech.karza.api.common.dto.ElectricalRequest;
import com.qualtech.karza.api.common.dto.ElectricalRequest2;
import com.qualtech.karza.api.common.dto.EmailAuthRequest;
import com.qualtech.karza.api.common.dto.EmolpyerLookupRequest;
import com.qualtech.karza.api.common.dto.FSSAILicenceRequest;
import com.qualtech.karza.api.common.dto.Form16QuatRequest;
import com.qualtech.karza.api.common.dto.Form16Request;
import com.qualtech.karza.api.common.dto.GSTAuthenticationRequest;
import com.qualtech.karza.api.common.dto.GstIdentificationRequest;
import com.qualtech.karza.api.common.dto.HSNRequest;
import com.qualtech.karza.api.common.dto.ICSIMemberShipRequest;
import com.qualtech.karza.api.common.dto.ICWAIFirmAuthRequest;
import com.qualtech.karza.api.common.dto.ICWAIMembershipRequest;
import com.qualtech.karza.api.common.dto.IECRequest;
import com.qualtech.karza.api.common.dto.IFSCRequest;
import com.qualtech.karza.api.common.dto.ITRAuthRequest;
import com.qualtech.karza.api.common.dto.LpgRequest2;
import com.qualtech.karza.api.common.dto.MCASignatureRequest;
import com.qualtech.karza.api.common.dto.NREGARequest;
import com.qualtech.karza.api.common.dto.NameSimilarityRequest;
import com.qualtech.karza.api.common.dto.PanRequest;
import com.qualtech.karza.api.common.dto.PassportRequest;
import com.qualtech.karza.api.common.dto.PngRequest;
import com.qualtech.karza.api.common.dto.RCAuthRequest;
import com.qualtech.karza.api.common.dto.RCSearchRequest;
import com.qualtech.karza.api.common.dto.ShopEstablishmentRequest;
import com.qualtech.karza.api.common.dto.TanRequest;
import com.qualtech.karza.api.common.dto.TelephoneRequest2;
import com.qualtech.karza.api.common.dto.UanLookupRequest;
import com.qualtech.karza.api.common.dto.UdyogAadharRequest;
import com.qualtech.karza.api.common.dto.VoterRequest;
import com.qualtech.karza.api.common.dto.WebsiteDomainRequest;
import com.qualtech.karza.api.request.AadharDemographics;
import com.qualtech.karza.api.request.AadharPayload;
import com.qualtech.karza.api.request.AddressPayload;
import com.qualtech.karza.api.request.BankAccountPayload;
import com.qualtech.karza.api.request.CAMemberShipAuthPayload;
import com.qualtech.karza.api.request.CompSearchPayload;
import com.qualtech.karza.api.request.CompanyLLPCINLookUpPayload;
import com.qualtech.karza.api.request.CompanyLLPIdentificationPayload;
import com.qualtech.karza.api.request.DlPayload;
import com.qualtech.karza.api.request.EPFAuthOTPPayload;
import com.qualtech.karza.api.request.EPFAuthPassBookPayload;
import com.qualtech.karza.api.request.ESICIdPayload;
import com.qualtech.karza.api.request.ElectricalPayload;
import com.qualtech.karza.api.request.ElectricalPayload2;
import com.qualtech.karza.api.request.EmailAuthPayload;
import com.qualtech.karza.api.request.EmployerLookupPayload;
import com.qualtech.karza.api.request.FSSAILicencePayload;
import com.qualtech.karza.api.request.Form16Payload;
import com.qualtech.karza.api.request.Form16QuatPayload;
import com.qualtech.karza.api.request.GSTAuthenticationPayload;
import com.qualtech.karza.api.request.GstIdentificationPayload;
import com.qualtech.karza.api.request.HSNPayload;
import com.qualtech.karza.api.request.ICSIMemberShipPayload;
import com.qualtech.karza.api.request.ICWAIFirmAuthPayload;
import com.qualtech.karza.api.request.ICWAIMembershipPayload;
import com.qualtech.karza.api.request.IECPayload;
import com.qualtech.karza.api.request.IFSCPayload;
import com.qualtech.karza.api.request.ITRAuthPayload;
import com.qualtech.karza.api.request.LpgPayload2;
import com.qualtech.karza.api.request.MCASignaturePayload;
import com.qualtech.karza.api.request.NREGAPayload;
import com.qualtech.karza.api.request.NameSimilarityPayload;
import com.qualtech.karza.api.request.PanPayload;
import com.qualtech.karza.api.request.PassportPayload;
import com.qualtech.karza.api.request.PngPayload;
import com.qualtech.karza.api.request.RCAuthPayload;
import com.qualtech.karza.api.request.RCSearchPayload;
import com.qualtech.karza.api.request.ShopEstablishmentPayload;
import com.qualtech.karza.api.request.TanPayload;
import com.qualtech.karza.api.request.TelephonePayload2;
import com.qualtech.karza.api.request.UanLookupPayload;
import com.qualtech.karza.api.request.UdyogAadharPayload;
import com.qualtech.karza.api.request.VoterPayload;
import com.qualtech.karza.api.request.WebsiteDomainPayload;
import com.qualtech.kotak.api.dto.KotakRequest;
import com.qualtech.kotak.api.dto.KotakRequestReversal;
import com.qualtech.kotak.api.request.KotakPayEnrichmentSet;
import com.qualtech.kotak.api.request.KotakPayInstrument;
import com.qualtech.kotak.api.request.KotakPayInstrumentList;
import com.qualtech.kotak.api.request.KotakPayRequestHeader;
import com.qualtech.kotak.api.request.KotakPayload;
import com.qualtech.kotak.api.request.KotakPayment;
import com.qualtech.kotak.api.request.KotakReversal;
import com.qualtech.kotak.api.request.KotakReversalDtls;
import com.qualtech.kotak.api.request.KotakReversalHeader;
import com.qualtech.kotak.api.request.KotakReversalPayload;
import com.qualtech.multibureau.api.common.dto.BureauRequest;
import com.qualtech.multibureau.api.request.AddressBureau;
import com.qualtech.multibureau.api.request.BureauPayload;
import com.qualtech.multibureau.api.request.IdTypes;
import com.qualtech.multibureau.api.request.PhoneBureau;
import com.qualtech.pan.api.entity.Pan;
import com.qualtech.pan.api.request.PanApiRequest;
import com.qualtech.pan.api.response.PanValidationRes;
import com.qualtech.pan.api.response.PanValidationResPayload;

@SuppressWarnings("deprecation")
public class CronUtils {
	static Logger logger = Logger.getLogger(CronUtils.class.getName());
	private static  HashMap<String, String> stateCode=new HashMap<String, String>();
	private static  HashMap<String, String> stateName=new HashMap<String, String>();
	private static  HashMap<String, String> genderREVCode=new HashMap<String, String>();
	private static  HashMap<String, String> genderCode=new HashMap<String, String>();
	private static  HashMap<String, String> genderCodeBureau=new HashMap<String, String>();
	private static  HashMap<String, String> phoneTypeMap=new HashMap<String, String>();
	private static  HashMap<String, String> phoneTypeEquifax=new HashMap<String, String>();
	private static  HashMap<String, String> phoneTypeCrif=new HashMap<String, String>();
	private static  HashMap<String, String> phoneTypeCibil=new HashMap<String, String>();
	//private static  HashMap<String, String> addressTypeEquifax=new HashMap<String, String>();
	private static  HashMap<String, String> addressTypeCibil=new HashMap<String, String>();
	private static  HashMap<String, String> addressTypeCrif=new HashMap<String, String>();
	private static  HashMap<String, String> idMasterTableForCibil=new HashMap<String, String>();
	private static  HashMap<String, String> genderCodecibil=new HashMap<String, String>();
	private PropertyFile resProp;
	
	public PropertyFile getResProp() {
		return resProp;
	}

	public void setResProp(PropertyFile resProp) {
		this.resProp = resProp;
	}
	
	public String dateFormatForHdfc(String str) {
        String splitdate[]=str.split("-");
        String day=splitdate[0];
        String month=splitdate[1];
        String year=splitdate[2];
        return month+"/"+day+"/"+year;
    }
	
	public static void putGenderCibil() {
		genderCodecibil.put("F","1");
		genderCodecibil.put("M","2");
		genderCodecibil.put("T","3");
	}
	public static void putIdMasterTableForCibil(){
	idMasterTableForCibil.put("PANCARD","01");
	idMasterTableForCibil.put("PASSPORT","02");
	idMasterTableForCibil.put("VOTERID","03");
	idMasterTableForCibil.put("DL","04");
	idMasterTableForCibil.put("RATIONCARD","05");
	idMasterTableForCibil.put("AADHAAR","06");
	/*idMasterTableForCibil.put("uan","03");
	idMasterTableForCibil.put("tan","04");
	idMasterTableForCibil.put("cin","05");
	*/
	}

	public static void putAddressTypeCrif(){
		addressTypeCrif.put("RESIDENCE","D01");
		addressTypeCrif.put("PERMANENT","D04");
		addressTypeCrif.put("COMPANY","D03");
		addressTypeCrif.put("RESCUMOFF","D04");
		addressTypeCrif.put("CURRENT","D05");
		addressTypeCrif.put("FOREIGN","D06");
		addressTypeCrif.put("MILITARY","D07");
		addressTypeCrif.put("OTHER","D08");
	}

	public static void putAddressTypeCibil(){
		addressTypeCibil.put("PERMANENT","01");
		addressTypeCibil.put("RESIDENCE","02");
		addressTypeCibil.put("OFFICE","03");
		addressTypeCibil.put("NOTCATEGORIZE","04");

	}
	public static void putPhoneTypeCibil(){
		phoneTypeCibil.put("MOBILE","01");
		phoneTypeCibil.put("HOME","02");
		phoneTypeCibil.put("OFFICE","03");
		phoneTypeCibil.put("NOTCLASSIFIED","00");
	}

	public static void putPhoneTypeCrif(){
		phoneTypeCrif.put("RESIDENCE","P01");
		phoneTypeCrif.put("MOBILE","P03");
		phoneTypeCrif.put("COMPANY","P02");
		phoneTypeCrif.put("PERMANENT","P04");
		phoneTypeCrif.put("FOREIGN","P05");
		phoneTypeCrif.put("OTHER","P07");
		phoneTypeCrif.put("UNTAGGED","P08");
	}

	public static void putPhoneTypeEquifax(){
		phoneTypeEquifax.put("HOME","H");
		phoneTypeEquifax.put("MOBILE","M");
		phoneTypeEquifax.put("PERSONAL","P");
		phoneTypeEquifax.put("FAX","F");
		phoneTypeEquifax.put("TELEPHONE","T");
		phoneTypeEquifax.put("EMPLOYE","E");
	}
	public static void putPhoneTypeMap(){
		phoneTypeMap.put("Residence", "P01");
		phoneTypeMap.put("Company", "P02");
		phoneTypeMap.put("Mobile", "P03");
		phoneTypeMap.put("Permanent", "P04");
		phoneTypeMap.put("Foreign", "P05");
		phoneTypeMap.put("Other", "P07");
		phoneTypeMap.put("Untagged", "P08");
	}
	//By Default F =1  m=2 T=3
	public static void putGenderRevMode() {
		genderREVCode.put("1","2");
		genderREVCode.put("2","1");
		genderREVCode.put("3","3");
	}
	public static void putGenderCode() {
		genderCode.put("1","F");
		genderCode.put("2","M");
		genderCode.put("3","T");
	}
	public static void putGenderCodeBureau() {
		genderCodeBureau.put("F","FEMALE");
		genderCodeBureau.put("M","MALE");
		genderCodeBureau.put("T","TRANSGENDER");
	}
	public static void putStateName() {
		stateName.put("AP","Andhra Pradesh");
		stateName.put("TS","Telegana");
		stateName.put("BR","Bihar");
		stateName.put("HR","Haryana");
		stateName.put("RJ","Rajasthan");
		stateName.put("AS","Assam");
		stateName.put("DD","Daman and Diu");
		stateName.put("AR","Arunachal Pradesh");
		stateName.put("UK","Uttaranchal");
		stateName.put("TN","Tamil Nadu");
		stateName.put("MH","Maharashtra");
		stateName.put("PY","Pondicherry");
		stateName.put("APO","APO Address");
		stateName.put("TR","Tripura");
		stateName.put("JH","Jharkhand");
		stateName.put("GJ","Gujarat");
		stateName.put("KA","Karnataka");
		stateName.put("LD","Lakshadweep");
		stateName.put("JK","Jammu and Kashmir");
		stateName.put("NL","Nagaland");
		stateName.put("CH","Chandigarh");
		stateName.put("AN","Andaman and Nicobar");
		stateName.put("PB","Punjab");
		stateName.put("HP","Himachal Pradesh");
		stateName.put("MN","Manipur");
		stateName.put("DN","Dadra and Nagar Haveli");
		stateName.put("MP","Madhya Pradesh");
		stateName.put("UP","Uttar Pradesh");
		stateName.put("DL","Delhi");
		stateName.put("ML","Meghalaya");
		stateName.put("MZ","Mizoram");
		stateName.put("CG","Chattisgarh");
		stateName.put("WB","West Bengal");
		stateName.put("SK","Sikkim");
		stateName.put("OR","Orissa");
		stateName.put("KL","Kerala");
		stateName.put("GA","Goa");
	}

	public static void putStateCode(){
		stateCode.put("AP","28");
		stateCode.put("AR","12");
		stateCode.put("AS","18");
		stateCode.put("BR","10");
		stateCode.put("CG","22");
		stateCode.put("GA","30");
		stateCode.put("GJ","24");
		stateCode.put("HR","6");
		stateCode.put("HP","2");
		stateCode.put("JK","1");
		stateCode.put("JH","20");
		stateCode.put("KA","29");
		stateCode.put("KL","32");
		stateCode.put("MP","23");
		stateCode.put("MH","27");
		stateCode.put("MN","14");
		stateCode.put("ML","17");
		stateCode.put("MZ","15");
		stateCode.put("NL","13");
		stateCode.put("OR","21");
		stateCode.put("PB","3");
		stateCode.put("RJ","8");
		stateCode.put("SK","11");
		stateCode.put("TN","33");
		stateCode.put("TS","36");
		stateCode.put("TR","16");
		stateCode.put("UK","5");
		stateCode.put("UP","9");
		stateCode.put("WB","19");
		stateCode.put("AN","35");
		stateCode.put("CH","4");
		stateCode.put("DN","26");
		stateCode.put("DD","25");
		stateCode.put("DL","7");
		stateCode.put("LD","31");
		stateCode.put("PY","34");
	}
	///// Date Formate
	private  String dateFormatCibil(String  date) {
		return date.replaceAll("-", "");
	}
	private  String dateFormatPassport(String  date) {
		return date.replaceAll("-", "/");
	}
	private  String dateFormatEquifax(String  date) {
		String splitdate[]=date.split("-");
		String day=splitdate[0];
		String month=splitdate[1];
		String year=splitdate[2];
		return year+"-"+month+"-"+day;
	}
	private String dateFormatSingleAction(String date) {
		String date2=null;
		try {
			SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
			Date dt = format1.parse(date);
			date2  =format2.format(dt);
		} catch (ParseException e) {
			logger.error(" error while parsing single action date :: "+e);
		}
		return date2;
	}

	public String getHdfcRequest(IbRequestMaster result,List<NomineeDetails> ibsNomineeDetail) {
		String reqJson=null;
		putStateName();
		putGenderCodeBureau();
		ObjectMapper om=new ObjectMapper();
		logger.info(" inside getHDFCRequest() :: Start");
		ApiRequest apiRequest= new ApiRequest();
		com.qualtech.hdfc.api.request.Header header=new com.qualtech.hdfc.api.request.Header();
		HdfcRequestPayload requestPayload=new HdfcRequestPayload();
		MetaData metaData=new MetaData();
		CustomerDetails customerDetails=new CustomerDetails();
		PersonalDetails personalDetails=new PersonalDetails();
		MedicalQuestionnaire medicalQuestionnaire=new MedicalQuestionnaire();
		PartnerData partnerData=new PartnerData();
		CustomerCommunication customerCommunication=new CustomerCommunication();
		List<com.qualtech.hdfc.api.request.NomineeDetails> ibsNomineeDetailbkp=new ArrayList<com.qualtech.hdfc.api.request.NomineeDetails>(); 
		com.qualtech.hdfc.api.request.Address address=new com.qualtech.hdfc.api.request.Address();
		 
		try {
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			
			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
				
				metaData.setPartnerId(result.getPartnerId()!=null?result.getPartnerId():"");
				metaData.setPrn(result.getPrn()!=null?result.getPrn():"");
				metaData.setPlan(result.getPlan()!=null?result.getPlan():"");
				metaData.setIpAddress(result.getIpAddress()!=null?result.getIpAddress():"");
				metaData.setDomain(result.getDomain()!=null?result.getDomain():"");
				customerCommunication.setSendCOIToEmail(result.getSendCOIToEmail()!=null?result.getSendCOIToEmail():"");
				customerCommunication.setSendCOIToSms(result.getSendCOIToSms()!=null?result.getSendCOIToSms():"");
				customerCommunication.setSendCOIUrlToSms(result.getSendCOIUrlToSms()!=null?result.getSendCOIUrlToSms():"");
				customerCommunication.setSendMIFToEmail(result.getSendMIFToEmail()!=null?result.getSendMIFToEmail():"");
				customerCommunication.setSendMIFToSms(result.getSendMIFToSms()!=null?result.getSendMIFToSms():"");
				
				
				partnerData.setSumAssuredType(result.getSumAssuredType()!=null?result.getSumAssuredType():"");
				partnerData.setFundingOption(result.getFundingOption()!=null?result.getFundingOption():"");
				//metaData.getPartnerData().setSumAssuredType(result.getSumAssuredType()!=null?result.getSumAssuredType():"");
				metaData.setPartnerData(partnerData);
			
				requestPayload.setMetaData(metaData);
				
				customerDetails.setPlanId(result.getPlan()!=null?result.getPlan():"");
				customerDetails.setPartnerId(result.getPartnerId()!=null?result.getPartnerId():"");
				customerDetails.setSumAssured(result.getSumAssured()!=null?result.getSumAssured():"");
				//////////change dob in hdfc format
				customerDetails.setDob(result.getDob()!=null?dateFormatPassport(result.getDob()):"");
				customerDetails.setLoanType(result.getLoanType()!=null?result.getLoanType():"");
				customerDetails.setLoanDisbursement_date(result.getLoanDisbursement_date()!=null?dateFormatForHdfc(result.getLoanDisbursement_date()):"");
				customerDetails.setLoanAmount(result.getLoanAmount()!=null?result.getLoanAmount():"");
				customerDetails.setBasicPremium(result.getBasicPremium()!=null?result.getBasicPremium():"");
				customerDetails.setPolicyTerm(result.getPolicyTerm()!=null?result.getPolicyTerm():"");
				customerDetails.setLoanTenure(result.getLoanTenure()!=null?result.getLoanTenure():"");
				customerDetails.setPremiumPayable(result.getPremiumPayable()!=null?result.getPremiumPayable():"");
				customerDetails.setIsAgreementGenerated(result.getIsAgreementGenerated()!=null?result.getIsAgreementGenerated():"");
					
				
				
				
				
					personalDetails.setTitle(result.getTitle()!=null?result.getTitle():"");
					/////////////change gender in hdfc format
					personalDetails.setGender(result.getGender()!=null?genderCodeBureau.get(result.getGender()):"");
					personalDetails.setFirstName(result.getF_name()!=null?result.getF_name():"");
					personalDetails.setLastName(result.getL_name()!=null?result.getL_name():"");
					personalDetails.setDob(result.getDob()!=null?dateFormatPassport(result.getDob()):"");
					personalDetails.setMobile(result.getTele_no1()!=null?result.getTele_no1():"");
					personalDetails.setEmail(result.getActual_email_addr()!=null?result.getActual_email_addr():"");
						address.setCity(result.getCity1()!=null?result.getCity1():"");
						address.setHouseOrFlat(result.getHouse()!=null?result.getHouse():"");
						address.setPinCode(result.getPin1()!=null?result.getPin1():"");
						address.setState(result.getState()!=null?(stateName.get(result.getState()).toUpperCase()):"");
						address.setStreet(result.getStreet()!=null?result.getStreet():"");
					personalDetails.setAddress(address);
				customerDetails.setPersonalDetails(personalDetails);
				
				
			for (NomineeDetails nomineeDetails : ibsNomineeDetail) {
				com.qualtech.hdfc.api.request.NomineeDetails nomineedtl=new com.qualtech.hdfc.api.request.NomineeDetails();
				com.qualtech.hdfc.api.request.Address addressnominee=new com.qualtech.hdfc.api.request.Address();
				com.qualtech.hdfc.api.request.AppointeeDetails appointeeDetails=new com.qualtech.hdfc.api.request.AppointeeDetails();
				com.qualtech.hdfc.api.request.AppointeeAddress appointaddress=new com.qualtech.hdfc.api.request.AppointeeAddress();
				nomineedtl.setFirstName(nomineeDetails.getFirstName()!=null?nomineeDetails.getFirstName():"");
				nomineedtl.setTitle(nomineeDetails.getTitle()!=null?nomineeDetails.getTitle():"");
				nomineedtl.setLastName(nomineeDetails.getLastName()!=null?nomineeDetails.getLastName():"");
				nomineedtl.setMobile(nomineeDetails.getMobile()!=null?nomineeDetails.getMobile():"");
				nomineedtl.setPercentageAllocation(nomineeDetails.getPercentageAllocation()!=null?nomineeDetails.getPercentageAllocation():"");
				nomineedtl.setRelationshipToCustomer(nomineeDetails.getRelationshipToCustomer()!=null?nomineeDetails.getRelationshipToCustomer():"");
				
				nomineedtl.setDob(nomineeDetails.getDob()!=null?dateFormatPassport(nomineeDetails.getDob()):"");
				nomineedtl.setGender(nomineeDetails.getGender()!=null?genderCodeBureau.get(nomineeDetails.getGender()):"");
				if(nomineeDetails.getAdress()!=null) {
					addressnominee.setArea(nomineeDetails.getAdress().getArea()!=null?nomineeDetails.getAdress().getArea():"");
					addressnominee.setCity(nomineeDetails.getAdress().getCity()!=null?nomineeDetails.getAdress().getCity():"");
					addressnominee.setHouseOrFlat(nomineeDetails.getAdress().getHouseOrFlat()!=null?nomineeDetails.getAdress().getHouseOrFlat():"");
					addressnominee.setPinCode(nomineeDetails.getAdress().getPinCode()!=null?nomineeDetails.getAdress().getPinCode():"");
					addressnominee.setStreet(nomineeDetails.getAdress().getStreet()!=null?nomineeDetails.getAdress().getStreet():"");
					addressnominee.setState(nomineeDetails.getAdress().getState()!=null?(stateName.get(nomineeDetails.getAdress().getState()).toUpperCase()):"");
					nomineedtl.setAdress(addressnominee);
				}
				if(nomineeDetails.getAppointeeDetails()!=null) {
					appointeeDetails.setFirstName(nomineeDetails.getAppointeeDetails().getFirstName()!=null?nomineeDetails.getAppointeeDetails().getFirstName():"");
					appointeeDetails.setLastName(nomineeDetails.getAppointeeDetails().getLastName()!=null?nomineeDetails.getAppointeeDetails().getLastName():"");
					appointeeDetails.setMobile(nomineeDetails.getAppointeeDetails().getMobile()!=null?nomineeDetails.getAppointeeDetails().getMobile():"");
					appointeeDetails.setRelationshipToNominee(nomineeDetails.getAppointeeDetails().getRelationshipToNominee()!=null?nomineeDetails.getAppointeeDetails().getRelationshipToNominee():"");
					appointeeDetails.setTitle(nomineeDetails.getAppointeeDetails().getTitle()!=null?nomineeDetails.getAppointeeDetails().getTitle():"");
					appointeeDetails.setDob(nomineeDetails.getAppointeeDetails().getDob()!=null?dateFormatPassport(nomineeDetails.getAppointeeDetails().getDob()):"");
					appointeeDetails.setGender(nomineeDetails.getAppointeeDetails().getGender()!=null?genderCodeBureau.get(nomineeDetails.getAppointeeDetails().getGender()):"");
					
				  if(nomineeDetails.getAppointeeDetails().getAdress()!=null) {
					  appointaddress.setArea(nomineeDetails.getAppointeeDetails().getAdress().getArea()!=null?nomineeDetails.getAppointeeDetails().getAdress().getArea():"");
					  appointaddress.setCity(nomineeDetails.getAppointeeDetails().getAdress().getCity()!=null?nomineeDetails.getAppointeeDetails().getAdress().getCity():"");
					  appointaddress.setHouseOrFlat(nomineeDetails.getAppointeeDetails().getAdress().getHouseOrFlat()!=null?nomineeDetails.getAppointeeDetails().getAdress().getHouseOrFlat():"");
					  appointaddress.setPinCode(nomineeDetails.getAppointeeDetails().getAdress().getPinCode()!=null?nomineeDetails.getAppointeeDetails().getAdress().getPinCode():"");
					  appointaddress.setStreet(nomineeDetails.getAppointeeDetails().getAdress().getStreet()!=null?nomineeDetails.getAppointeeDetails().getAdress().getStreet():"");
					  
					  appointaddress.setState(nomineeDetails.getAppointeeDetails().getAdress().getState()!=null?(stateName.get(nomineeDetails.getAppointeeDetails().getAdress().getState()).toUpperCase()):"");
					  appointeeDetails.setAdress(appointaddress);
				  }
				nomineedtl.setAppointeeDetails(appointeeDetails);
				}
				
				ibsNomineeDetailbkp.add(nomineedtl);
			}//for
			/////////////////////////////////
			medicalQuestionnaire.setQuestionnaireId(result.getQuestionnaireId()!=null?result.getQuestionnaireId():"");
			 
			Response response=new Response();
			response.setOne(result.getHdfcQueOne()!=null?result.getHdfcQueOne():"");
			response.setTwo(result.getHdfcQueTwo()!=null?result.getHdfcQueTwo():"");
			response.setThree(result.getHdfcQueThree()!=null?result.getHdfcQueThree():"");
			response.setFour(result.getHdfcQueFour()!=null?result.getHdfcQueFour():"");
			response.setFive(result.getHdfcQueFive()!=null?result.getHdfcQueFive():"");
			response.setSix(result.getHdfcQueSix()!=null?result.getHdfcQueSix():"");
			response.setSeven(result.getHdfcQueSeven()!=null?result.getHdfcQueSeven():"");
			response.setEight(result.getHdfcQueEight()!=null?result.getHdfcQueEight():"");
			response.setNine(result.getHdfcQueNine()!=null?result.getHdfcQueNine():"");
			medicalQuestionnaire.setResponse(response);
			//////////////////////////////////			
			 personalDetails.setNomineeDetails(ibsNomineeDetailbkp);
			customerDetails.setPersonalDetails(personalDetails);
			customerDetails.setMedicalQuestionnaire(medicalQuestionnaire);
			requestPayload.setCustomerDetails(customerDetails);
			apiRequest.setHeader(header);
			apiRequest.setPayload(requestPayload);
			reqJson=om.writeValueAsString(apiRequest);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return reqJson;
	}
	public String getCibilV2Request(IbRequestMaster result) {

		logger.info(" inside getCibilV2Request() :: Start");
		CibilRequest2 cibilapirequest=new CibilRequest2();
		CibilRequestPayload2 payload=new CibilRequestPayload2();
		CibilHeader2 header = new CibilHeader2();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));



			payload.setEnquiryAmount(result.getTxnAmnt()!=null?result.getTxnAmnt():"");
			payload.setLoanPurpose(result.getPayMode()!=null?result.getPayMode():"");
			payload.setReportType(result.getPassportType()!=null?result.getPassportType():"");
			payload.setFutureUse(result.getExchDoc()!=null?result.getExchDoc():"");
			payload.setfName(result.getF_name()!=null?result.getF_name():"");
			payload.setmName(result.getM_name()!=null?result.getM_name():"");
			payload.setlName(result.getL_name()!=null?result.getL_name():"");
			payload.setAlternateName(result.getFullname()!=null?result.getFullname():"");
			payload.setVoterId(result.getIdno3()!=null?result.getIdno3():"");
			payload.setUid(result.getUan()!=null?result.getUan():"");
			payload.setPan(result.getIdno1()!=null?result.getIdno1():"");
			payload.setRationCard(result.getIdno5()!=null?result.getIdno5():"");

			List<TelephoneNumberTypeIndicator>  phones = new ArrayList<TelephoneNumberTypeIndicator>();
			if(result.getTele_type1()!=null) {
				TelephoneNumberTypeIndicator phone=new TelephoneNumberTypeIndicator();
				phone.setTelephoneNumber(result.getTele_no1()!=null?result.getTele_no1():"");
				phone.setTypeIndicator(result.getTele_type1()!=null?result.getTele_type1():"");
				phones.add(phone);
			}
			if(result.getTele_type2()!=null) {
				TelephoneNumberTypeIndicator phone=new TelephoneNumberTypeIndicator();
				phone.setTelephoneNumber(result.getTele_no2()!=null?result.getTele_no2():"");
				phone.setTypeIndicator(result.getTele_type2()!=null?result.getTele_type2():"");
				phones.add(phone);
			}
			if(result.getTele_type3()!=null) {
				TelephoneNumberTypeIndicator phone=new TelephoneNumberTypeIndicator();
				phone.setTelephoneNumber(result.getTele_no3()!=null?result.getTele_no3():"");
				phone.setTypeIndicator(result.getTele_type3()!=null?result.getTele_type3():"");
				phones.add(phone);
			}
			if(result.getTele_type4()!=null) {
				TelephoneNumberTypeIndicator phone=new TelephoneNumberTypeIndicator();
				phone.setTelephoneNumber(result.getTele_no4()!=null?result.getTele_no4():"");
				phone.setTypeIndicator(result.getTele_type4()!=null?result.getTele_type4():"");
				phones.add(phone);
			}
			if(result.getTele_type5()!=null) {
				TelephoneNumberTypeIndicator phone=new TelephoneNumberTypeIndicator();
				phone.setTelephoneNumber(result.getTele_no5()!=null?result.getTele_no5():"");
				phone.setTypeIndicator(result.getTele_type5()!=null?result.getTele_type5():"");
				phones.add(phone);
			}
			payload.setTelephoneNumberTypeIndicator(phones);


			List <MemberRelationship> memberRelationship=new ArrayList<MemberRelationship>();
			if(result.getRelation1()!=null) {
				MemberRelationship relation=new MemberRelationship();
				relation.setRelationshipName(result.getRelation_name1()!=null?result.getRelation_name1():"");
				relation.setRelationshipType(result.getRelation1()!=null?result.getRelation1():"");
				memberRelationship.add(relation);
			}
			if(result.getRelation2()!=null) {
				MemberRelationship relation=new MemberRelationship();
				relation.setRelationshipName(result.getRelation_name2()!=null?result.getRelation_name2():"");
				relation.setRelationshipType(result.getRelation2()!=null?result.getRelation2():"");
				memberRelationship.add(relation);
			}
			if(result.getRelation3()!=null) {
				MemberRelationship relation=new MemberRelationship();
				relation.setRelationshipName(result.getRelation_name3()!=null?result.getRelation_name3():"");
				relation.setRelationshipType(result.getRelation3()!=null?result.getRelation3():"");
				memberRelationship.add(relation);
			}
			if(result.getRelation4()!=null) {
				MemberRelationship relation=new MemberRelationship();
				relation.setRelationshipName(result.getRelation_name4()!=null?result.getRelation_name4():"");
				relation.setRelationshipType(result.getRelation4()!=null?result.getRelation4():"");
				memberRelationship.add(relation);
			}
			if(result.getRelation5()!=null) {
				MemberRelationship relation=new MemberRelationship();
				relation.setRelationshipName(result.getRelation_name5()!=null?result.getRelation_name5():"");
				relation.setRelationshipType(result.getRelation5()!=null?result.getRelation5():"");
				memberRelationship.add(relation);
			}
			payload.setMemberRelationship(memberRelationship);


			List<Address> address=new ArrayList<Address>();
			if(result.getState1()!=null || result.getPin1()!=null  ) {
				Address addr=new Address();
				addr.setAddressLine(result.getAddress1()!=null?result.getAddress1():"");
				//addr.setAddressType(result.getAddress_type1()!=null?result.getAddress_type1():"");
				//Need To Check
				addr.setAddressType("C");
				addr.setCityTown(result.getCity1()!=null?result.getCity1():"");
				addr.setPinCode(result.getPin1()!=null?result.getPin1():"");
				addr.setState(result.getState1()!=null?result.getState1():"");
				address.add(addr);
			}
			payload.setAddress(address);

			payload.setMemberBirthDate(result.getDob()!=null?result.getDob():"");
			payload.setMemberGenderType(result.getGender()!=null?result.getGender():"");
			payload.setKeyPersonName(result.getKey_person_name()!=null?result.getKey_person_name():"");
			payload.setKeyPersonsRelationship(result.getKey_person_type()!=null?result.getKey_person_type():"");
			payload.setNomineeName(result.getNominee_name()!=null?result.getNominee_name():"");
			payload.setNomineeRelationship(result.getNominee_type()!=null?result.getNominee_type():"");
			payload.setAccount1(result.getAccountNo()!=null?result.getAccountNo():"");
			payload.setGstStateCode(result.getBeneCode()!=null?result.getCountry():"");
			payload.setBranchReferenceNo(result.getBatchRefNmbr()!=null?result.getBatchRefNmbr():"");
			payload.setCentreReferenceNo(result.getInstRefNo()!=null?result.getInstRefNo():"");

			cibilapirequest.setHeader(header);
			cibilapirequest.setPayload(payload);

			reqJson=om.writeValueAsString(cibilapirequest);
		} catch (Exception e) {
			logger.error(" error while make json Cibil V2 Request :: "+e);
		}
		logger.info(" inside getCibilV2Request() :: End");
		return reqJson;


	}
	 public  String panValidationDate(){
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String formatted = format.format(cal.getTime());
			return formatted;
	  }
	public String getPanValidationPdf(PanValidationRes panRes, PanApiRequest panRequest,String pdfName) {
		try {
			String logoPath=null;
			String filePath=resProp.getString("com.karza.pdf.qc.save");
			try
			{
				logoPath=resProp.getString("com.karza.logo.qc");
			}
			catch(Exception e)
			{
				logger.error("Error occured inside cronutils in pan vaidation logoPath");
			}
			String kycPath=null;
			try
			{
				kycPath=resProp.getString("com.karza.logo.kyc");
			}
			catch(Exception e)
			{
				logger.error("Error occured inside cronutils in pan vaidation kycPath");
			}
			String finalPathPdf=filePath;//getPath(filePath);
			Document document = new Document(PageSize.A4,36,36,36,36);
			PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf +File.separator+pdfName+ panRes.getHeader().getCorrelationId()+".pdf"));
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter writer = PdfWriter.getInstance(document, bStream);
			document.open();
			StringBuffer sb = new StringBuffer();
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");
			sb.append("<body>");

			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'>");sb.append("<img src='"+kycPath+"' width='100' align='left'>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");
			sb.append("<img src='"+logoPath+"' width='100' align='right'></td>");
			sb.append("</tr>");
			sb.append("</table>"); 

			sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' width='50%'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<strong><u>PAN Card Authentication Report</u></strong>");sb.append("</font>");
			sb.append("</td>");
			sb.append("<td align='right' width='50%'>");sb.append("<font size='0.5' face='arial' color='#337BB6'>");sb.append(panValidationDate());sb.append("</font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10' >&nbsp;");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<br/>");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Pan Report Summary");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			
			
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document Name</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Document number</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status as per source</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Status Message</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("Pan Card");sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(panRequest.getPayload().getPanNo().get( 0).getPan());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(panRes.getMsgInfo().getStatus());sb.append("</font>");sb.append("</td>");
			sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(panRes.getMsgInfo().getMessage());sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='20'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

		
			sb.append("<table width='100%'  >");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");
			sb.append("<b>");sb.append("Input Data");sb.append("</b>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'  >");
			sb.append("<tr>");
			sb.append("<td bgcolor='#b3e6ff' align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append("<strong>Pan No</strong>");sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			
			for(Pan pan: panRequest.getPayload().getPanNo())
			{
				sb.append("<tr>");
				sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>");sb.append(pan.getPan());sb.append("</font>");sb.append("</td>");
				sb.append("</tr>");
			}
			
			sb.append("</table>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			sb.append("<table width='100%' cellpading='0'>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td align='left'>");
			sb.append("<font size='3' face='arial' font color=\"#005580\">");sb.append("<b>");
			sb.append("Detailed Report");sb.append("</b>");
			sb.append("</font>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<!--Details-->");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table width='100%'>");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td>");
			sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='4'>");
			int i=0;
			for(PanValidationResPayload payload: panRes.getPayload())
			{
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Name</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(payload.getTitle()!=null?payload.getTitle():"");
				sb.append(payload.getfName()!=null?payload.getfName():"");
				sb.append(payload.getmName()!=null?" "+payload.getmName():"");
				sb.append(payload.getlName()!=null?" "+payload.getlName():"");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5'  face='arial'>");sb.append("<b>PAN no.</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(payload.getPan()!=null?payload.getPan():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Incorporation Date</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(payload.getDobIncorporationITD()!=null?payload.getDobIncorporationITD():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Father Name</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");
				sb.append(payload.getFatherFName()!=null?payload.getFatherFName():"");
				sb.append(payload.getFatherMName()!=null?" "+payload.getFatherMName():"");
				sb.append(payload.getFatherLName()!=null?" "+payload.getFatherLName():"");
				sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Pan Status</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(payload.getPanStatus()!=null?payload.getPanStatus():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("<tr>");
				sb.append("<td bgcolor='#b3e6ff' align='left' width='30%'>");
				sb.append("<font size='0.5'  face='arial'>");sb.append("<b>Unique ID</b>");sb.append("</font>");
				sb.append("</td>");
				sb.append("<td width='0.1' bgcolor='#000000'>&nbsp;");
				sb.append("</td>");
				sb.append("<td align='left'>");
				sb.append("<font size='0.5' face='arial'>");sb.append(payload.getUniqueId()!=null?payload.getUniqueId():"");sb.append("</font>");
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");

			sb.append("</td>");
			sb.append("</tr>");
			
			sb.append("</table>");
			
			sb.append("</td>");
			sb.append("</tr>");
			
	   		sb.append("</table>");

			
			sb.append("</body>");
			sb.append("</html>");

			HTMLWorker worker = new HTMLWorker(document);
			worker.parse(new StringReader(sb.toString()));
			document.close();
			String pdfpathforIbs=finalPathPdf +File.separator+pdfName+ panRes.getHeader().getCorrelationId()+".pdf";
			return pdfpathforIbs;
		}
		catch (Exception e) 
		{
			logger.error("PDFConverter || getPdfByteArrayPan || error:: "+e);
			return null;
		}
	}


	public String getCriffPcsRequest(IbRequestMaster result) {
		logger.info(" inside getCriffPcsRequest() :: Start");
		putPhoneTypeEquifax();
		putPhoneTypeCrif();
		putAddressTypeCrif();
		StringBuilder sb=new StringBuilder();
		boolean flag=false;
		try{


			sb.append("	{");
			sb.append("    \"header\": {");
			sb.append("        \"msgVersion\": \""+resProp.getString("com.qc.ibs.msgVersion")+"\",");
			sb.append("        \"appId\": \"1048\",");
			sb.append("        \"correlationId\": \""+(result.getCorrelationid()+":"+result.getTransaction())+"\",");
			sb.append("        \"userId\": \""+resProp.getString("com.qc.ibs.appid")+"\",");
			sb.append("        \"password\": \"12345\",");
			sb.append("        \"rollId\": \"0\",");
			sb.append("        \"token\":\""+resProp.getString("com.qc.ibs.token")+"\"");
			sb.append("    },");
			sb.append("    \"payload\": {");
			sb.append("        \"transaction\": [");
			sb.append("            {");
			sb.append("                \"fName\": \""+(result.getF_name()!=null?result.getF_name():"")+"\",");
			sb.append("                \"mName\": \""+(result.getM_name()!=null?result.getM_name():"")+"\",");
			sb.append("                \"lName\": \""+(result.getL_name()!=null?result.getL_name():"")+"\",");

			if(result.getService_provider().contains("EQUIFAX")){
				sb.append("                \"dob\": \""+(dateFormatEquifax(result.getDob())!=null?dateFormatEquifax(result.getDob()):"")+"\",");
			}
			else {
				sb.append("                \"dob\": \""+(result.getDob()!=null?result.getDob():"")+"\",");	
			}
			sb.append("                \"keyPersonName\": \""+(result.getKey_person_name()!=null?result.getKey_person_name():"")+"\",");
			sb.append("                \"keyPersonType\": \""+(result.getKey_person_type()!=null?result.getKey_person_type():"")+"\",");
			sb.append("                \"nomineeName\": \""+(result.getNominee_name()!=null?result.getNominee_name():"")+"\",");
			sb.append("                \"nomineeType\": \""+(result.getNominee_type()!=null?result.getNominee_type():"")+"\",");

			if(result.getService_provider().equals("EQUIFAX PCS")){
				sb.append("                \"requestType\": \"PCS\",");
				sb.append("                \"consumerId\": \""+(result.getConsumer_id()!=null?result.getConsumer_id():"")+"\",");
				sb.append("                \"inquiryPurpose\": \""+(result.getInquiry_purpose()!=null?result.getInquiry_purpose():"")+"\",");

			}else if(result.getService_provider().equals("EQUIFAX MCR")){
				sb.append("                \"requestType\": \"MCR\",");
				sb.append("                \"consumerId\": \""+(result.getConsumer_id()!=null?result.getConsumer_id():"")+"\",");
				sb.append("                \"inquiryPurpose\": \""+(result.getInquiry_purpose()!=null?result.getInquiry_purpose():"")+"\",");

			}else if(result.getService_provider().equals("EQUIFAX EVDR")){
				sb.append("                \"inquiryPurpose\": \""+(result.getInquiry_purpose()!=null?result.getInquiry_purpose():"")+"\",");
				sb.append("                \"amount\": \""+(result.getAmount()!=null?result.getAmount():"")+"\",");
				sb.append("                \"email\": \""+(result.getActual_email_addr()!=null?result.getActual_email_addr():"")+"\",");
			}else if(result.getService_provider().equals("EQUIFAX VID")){
				sb.append("                \"inquiryPurpose\": \""+(result.getInquiry_purpose()!=null?result.getInquiry_purpose():"")+"\",");
			}
			sb.append("                \"ids\": [");

			if(result.getIdno1()!=null && result.getIdname1()!=null){
				sb.append("                    {");
				sb.append("                        \"idName\": \""+(result.getIdname1()!=null?result.getIdname1():"")+"\",");
				sb.append("                        \"idNo\": \""+(result.getIdno1()!=null?result.getIdno1():"")+"\"");
				sb.append("                    }");
				flag=true;
			}
			if(result.getIdno2()!=null && result.getIdname2()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"idName\": \""+(result.getIdname2()!=null?result.getIdname2():"")+"\",");
				sb.append("                        \"idNo\": \""+(result.getIdno2()!=null?result.getIdno2():"")+"\"");
				sb.append("                    }");
			}
			if(result.getIdno3()!=null && result.getIdname3()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"idName\": \""+(result.getIdname3()!=null?result.getIdname3():"")+"\",");
				sb.append("                        \"idNo\": \""+(result.getIdno3()!=null?result.getIdno3():"")+"\"");
				sb.append("                    }");
			}
			if(result.getIdno4()!=null && result.getIdname4()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"idName\": \""+(result.getIdname4()!=null?result.getIdname4():"")+"\",");
				sb.append("                        \"idNo\": \""+(result.getIdno4()!=null?result.getIdno4():"")+"\"");
				sb.append("                    }");
			}
			if(result.getIdno5()!=null && result.getIdname5()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"idName\": \""+(result.getIdname5()!=null?result.getIdname5():"")+"\",");
				sb.append("                        \"idNo\": \""+(result.getIdno5()!=null?result.getIdno5():"")+"\"");
				sb.append("                    }");
			}
			flag=false;
			sb.append("                ],");
			sb.append("                \"relations\": [");
			if(result.getRelation_name1()!=null && result.getRelation1()!=null){
				sb.append("                    {");
				sb.append("                        \"name\": \""+(result.getRelation_name1()!=null?result.getRelation_name1():"")+"\",");
				sb.append("                        \"relation\": \""+(result.getRelation1()!=null?result.getRelation1():"")+"\"");
				sb.append("                    }");
				flag=true;
			}
			if(result.getRelation_name2()!=null && result.getRelation2()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"name\": \""+(result.getRelation_name2()!=null?result.getRelation_name2():"")+"\",");
				sb.append("                        \"relation\": \""+(result.getRelation2()!=null?result.getRelation2():"")+"\"");
				sb.append("                    }");
			}
			if(result.getRelation_name3()!=null && result.getRelation3()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"name\": \""+(result.getRelation_name3()!=null?result.getRelation_name3():"")+"\",");
				sb.append("                        \"relation\": \""+(result.getRelation3()!=null?result.getRelation3():"")+"\"");
				sb.append("                    }");
			}
			if(result.getRelation_name4()!=null && result.getRelation4()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"name\": \""+(result.getRelation_name4()!=null?result.getRelation_name4():"")+"\",");
				sb.append("                        \"relation\": \""+(result.getRelation4()!=null?result.getRelation4():"")+"\"");
				sb.append("                    }");
			}
			if(result.getRelation_name5()!=null && result.getRelation5()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"name\": \""+(result.getRelation_name5()!=null?result.getRelation_name5():"")+"\",");
				sb.append("                        \"relation\": \""+(result.getRelation5()!=null?result.getRelation5():"")+"\"");
				sb.append("                    }");
			}
			flag=false;
			sb.append("                ],");
			sb.append("                \"phones\": [");

			if(result.getTele_no1()!=null && result.getTele_type1()!=null){
				sb.append("                    {");
				sb.append("                        \"teleNo\": \""+(result.getTele_no1()!=null?result.getTele_no1():"")+"\",");
				if(result.getService_provider().contains("EQUIFAX")){
					sb.append("                        \"type\": \""+(phoneTypeEquifax.get(result.getTele_type1().toUpperCase())!=null?phoneTypeEquifax.get(result.getTele_type1().toUpperCase()):"")+"\"");
				}
				else {
					sb.append("                        \"type\": \""+(phoneTypeCrif.get(result.getTele_type1().toUpperCase())!=null?phoneTypeCrif.get(result.getTele_type1().toUpperCase()):"")+"\"");	
				}
				sb.append("                    }");
				flag=true;
			}
			if(result.getTele_no2()!=null && result.getTele_type2()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"teleNo\": \""+(result.getTele_no2()!=null?result.getTele_no2():"")+"\",");
				if(result.getService_provider().contains("EQUIFAX")){
					sb.append("                        \"type\": \""+(phoneTypeEquifax.get(result.getTele_type2().toUpperCase())!=null?phoneTypeEquifax.get(result.getTele_type2().toUpperCase()):"")+"\"");
				}
				else {
					sb.append("                        \"type\": \""+(phoneTypeCrif.get(result.getTele_type2().toUpperCase())!=null?phoneTypeCrif.get(result.getTele_type2().toUpperCase()):"")+"\"");	
				}
				sb.append("                    }");
			}
			if(result.getTele_no3()!=null && result.getTele_type3()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"teleNo\": \""+(result.getTele_no3()!=null?result.getTele_no3():"")+"\",");
				if(result.getService_provider().contains("EQUIFAX")){
					sb.append("                        \"type\": \""+(phoneTypeEquifax.get(result.getTele_type3().toUpperCase())!=null?phoneTypeEquifax.get(result.getTele_type3().toUpperCase()):"")+"\"");
				}
				else {
					sb.append("                        \"type\": \""+(phoneTypeCrif.get(result.getTele_type3().toUpperCase())!=null?phoneTypeCrif.get(result.getTele_type3().toUpperCase()):"")+"\"");	
				}
				sb.append("                    }");
			}
			if(result.getTele_no4()!=null && result.getTele_type4()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"teleNo\": \""+(result.getTele_no4()!=null?result.getTele_no4():"")+"\",");
				if(result.getService_provider().contains("EQUIFAX")){
					sb.append("                        \"type\": \""+(phoneTypeEquifax.get(result.getTele_type4().toUpperCase())!=null?phoneTypeEquifax.get(result.getTele_type4().toUpperCase()):"")+"\"");
				}
				else {
					sb.append("                        \"type\": \""+(phoneTypeCrif.get(result.getTele_type4().toUpperCase())!=null?phoneTypeCrif.get(result.getTele_type4().toUpperCase()):"")+"\"");	
				}
				sb.append("                    }");
			}
			if(result.getTele_no5()!=null && result.getTele_type5()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"teleNo\": \""+(result.getTele_no5()!=null?result.getTele_no5():"")+"\",");
				if(result.getService_provider().contains("EQUIFAX")){
					sb.append("                        \"type\": \""+(phoneTypeEquifax.get(result.getTele_type5().toUpperCase())!=null?phoneTypeEquifax.get(result.getTele_type5().toUpperCase()):"")+"\"");
				}
				else {
					sb.append("                        \"type\": \""+(phoneTypeCrif.get(result.getTele_type5().toUpperCase())!=null?phoneTypeCrif.get(result.getTele_type5().toUpperCase()):"")+"\"");	
				}
				sb.append("                    }");
			}
			flag=false;
			sb.append("                ],");
			sb.append("                \"addresses\": [");

			if(result.getAddress1()!=null){
				sb.append("                    {");
				sb.append("                        \"city\": \""+(result.getCity1()!=null?result.getCity1():"")+"\",");
				sb.append("                        \"state\": \""+(result.getState1()!=null?result.getState1():"")+"\",");
				sb.append("                        \"pin\": \""+(result.getPin1()!=null?result.getPin1():"")+"\",");
				sb.append("                        \"address\": \""+(result.getAddress1()!=null?result.getAddress1():"")+"\",");
				if(result.getService_provider().contains("EQUIFAX")){
					sb.append("                        \"type\": \""+(addressTypeCrif.get(result.getAddress_type1().toUpperCase())!=null?addressTypeCrif.get(result.getAddress_type1().toUpperCase()):"")+"\"");
				}else {
					sb.append("                        \"type\": \""+(addressTypeCrif.get(result.getAddress_type1().toUpperCase())!=null?addressTypeCrif.get(result.getAddress_type1().toUpperCase()):"")+"\"");	
				}
				sb.append("                    }");
				flag=true;
			}
			if(result.getAddress2()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"city\": \""+(result.getCity2()!=null?result.getCity2():"")+"\",");
				sb.append("                        \"state\": \""+(result.getState2()!=null?result.getState2():"")+"\",");
				sb.append("                        \"pin\": \""+(result.getPin2()!=null?result.getPin2():"")+"\",");
				sb.append("                        \"address\": \""+(result.getAddress2()!=null?result.getAddress2():"")+"\",");

				if(result.getService_provider().contains("EQUIFAX")){
					sb.append("                        \"type\": \""+(addressTypeCrif.get(result.getAddress_type2().toUpperCase())!=null?addressTypeCrif.get(result.getAddress_type2().toUpperCase()):"")+"\"");
				}
				else {
					sb.append("                        \"type\": \""+(addressTypeCrif.get(result.getAddress_type2().toUpperCase())!=null?addressTypeCrif.get(result.getAddress_type2().toUpperCase()):"")+"\"");	
				}

				sb.append("                    }");
			}
			if(result.getAddress3()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"city\": \""+(result.getCity3()!=null?result.getCity3():"")+"\",");
				sb.append("                        \"state\": \""+(result.getState3()!=null?result.getState3():"")+"\",");
				sb.append("                        \"pin\": \""+(result.getPin3()!=null?result.getPin3():"")+"\",");
				sb.append("                        \"address\": \""+(result.getAddress3()!=null?result.getAddress3():"")+"\",");
				if(result.getService_provider().contains("EQUIFAX")){
					sb.append("                        \"type\": \""+(addressTypeCrif.get(result.getAddress_type3().toUpperCase())!=null?addressTypeCrif.get(result.getAddress_type3().toUpperCase()):"")+"\"");
				}
				else {
					sb.append("                        \"type\": \""+(addressTypeCrif.get(result.getAddress_type3().toUpperCase())!=null?addressTypeCrif.get(result.getAddress_type3().toUpperCase()):"")+"\"");	
				}
				sb.append("                    }");
			}
			if(result.getAddress4()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"city\": \""+(result.getCity4()!=null?result.getCity4():"")+"\",");
				sb.append("                        \"state\": \""+(result.getState4()!=null?result.getState4():"")+"\",");
				sb.append("                        \"pin\": \""+(result.getPin4()!=null?result.getPin4():"")+"\",");
				sb.append("                        \"address\": \""+(result.getAddress4()!=null?result.getAddress4():"")+"\",");
				if(result.getService_provider().contains("EQUIFAX")){
					sb.append("                        \"type\": \""+(addressTypeCrif.get(result.getAddress_type4().toUpperCase())!=null?addressTypeCrif.get(result.getAddress_type4().toUpperCase()):"")+"\"");
				}
				else {
					sb.append("                        \"type\": \""+(addressTypeCrif.get(result.getAddress_type4().toUpperCase())!=null?addressTypeCrif.get(result.getAddress_type4().toUpperCase()):"")+"\"");	
				}
				sb.append("                    }");
			}
			if(result.getAddress5()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"city\": \""+(result.getCity5()!=null?result.getCity5():"")+"\",");
				sb.append("                        \"state\": \""+(result.getState5()!=null?result.getState5():"")+"\",");
				sb.append("                        \"pin\": \""+(result.getPin5()!=null?result.getPin5():"")+"\",");
				sb.append("                        \"address\": \""+(result.getAddress5()!=null?result.getAddress5():"")+"\",");
				if(result.getService_provider().contains("EQUIFAX")){
					sb.append("                        \"type\": \""+(addressTypeCrif.get(result.getAddress_type5().toUpperCase())!=null?addressTypeCrif.get(result.getAddress_type5().toUpperCase()):"")+"\"");
				}
				else {
					sb.append("                        \"type\": \""+(addressTypeCrif.get(result.getAddress_type5().toUpperCase())!=null?addressTypeCrif.get(result.getAddress_type5().toUpperCase()):"")+"\"");	
				}
				sb.append("                    }");
			}
			sb.append("                ]");
			sb.append("            }");
			sb.append("       ]");
			sb.append("    }");
			sb.append("}");
		}catch (Exception e) {
			logger.error(" error while make crif pcs json Request :: "+e);
		}
		logger.info(" inside getCriffPcsRequest() :: END");
		return sb.toString();
	}

	public String getCibilRequest(IbRequestMaster result) {
		putStateCode();
		putGenderCibil();
		putPhoneTypeCibil();
		putAddressTypeCibil();
		putIdMasterTableForCibil();
		logger.info(" inside getCibilRequest() :: Start");
		StringBuilder sb=new StringBuilder();
		boolean flag=false;
		try{
			sb.append("{");
			sb.append("  \"header\": {");
			sb.append("    \"appId\": \""+resProp.getString("com.qc.ibs.appid")+"\",");
			sb.append("    \"correlationId\": \""+(result.getCorrelationid()+":"+result.getTransaction())+"\",");
			sb.append("    \"msgVersion\": \""+resProp.getString("com.qc.ibs.msgVersion")+"\",");
			sb.append("    \"password\": \"x34678\",");
			sb.append("    \"rollId\": \"ADMIN\",");
			sb.append("    \"token\":\""+resProp.getString("com.qc.ibs.token")+"\",");
			sb.append("    \"userId\": \"admin\"");
			sb.append("  },");
			sb.append("  \"payload\": {");
			sb.append("    \"transaction\": [");
			sb.append("      {");
			sb.append("	\"scoreType\": \""+(result.getScoretype()!=null?result.getScoretype():"")+"\",");
			sb.append("        \"addresses\": [");
			sb.append("          {");
			sb.append("            \"addressline1\":\""+(result.getAddress1()!=null?result.getAddress1():"")+"\",");
			sb.append("            \"addressline2\":\""+(result.getAddress2()!=null?result.getAddress2():"")+"\",");
			sb.append("            \"addressline3\":\""+(result.getAddress3()!=null?result.getAddress3():"")+"\",");
			sb.append("            \"addressline4\":\""+(result.getAddress4()!=null?result.getAddress4():"")+"\",");
			sb.append("            \"addressline5\":\""+(result.getAddress5()!=null?result.getAddress5():"")+"\",");
			sb.append("            \"addresscategory\": \""+(addressTypeCibil.get(result.getAddress_type1())!=null?addressTypeCibil.get(result.getAddress_type1()):"")+"\",");
			sb.append("            \"pincode\": \""+(result.getPin1()!=null?result.getPin1():"")+"\",");
			sb.append("            \"residencecode\": \""+(result.getResidence_code1()!=null?result.getResidence_code1():"")+"\",");
			sb.append("            \"statecode\": \""+(stateCode.get(result.getState1())!=null?stateCode.get(result.getState1()):"")+"\"");
			sb.append("          }");
			sb.append("       ");
			sb.append("       ],");
			sb.append("        \"dob\": \""+(dateFormatCibil(result.getDob())!=null?dateFormatCibil(result.getDob()):"")+"\",");
			sb.append("        \"firstName\": \""+(result.getF_name()!=null?result.getF_name():"")+"\",");
			sb.append("        \"gender\": \""+(genderCodecibil.get(result.getGender())!=null?genderCodecibil.get(result.getGender()):"")+"\",");
			sb.append("        \"lastName\": \""+(result.getL_name()!=null?result.getL_name():"")+"\",");
			sb.append("        \"middleName\": \""+(result.getM_name()!=null?result.getM_name():"")+"\",");
			sb.append("        \"telephones\": [");


			if(result.getTele_no1()!=null && result.getTele_type1()!=null){
				sb.append("                    {");
				sb.append("            			   \"telephoneExtn\": \""+(result.getTele_extn1()!=null?result.getTele_extn1():"")+"\",");
				sb.append("                        \"telephoneNumber\": \""+(result.getTele_no1()!=null?result.getTele_no1():"")+"\",");
				sb.append("                        \"telephoneType\": \""+(phoneTypeCibil.get(result.getTele_type1().toUpperCase())!=null?phoneTypeCibil.get(result.getTele_type1().toUpperCase()):"")+"\"");
				sb.append("                    }");
				flag=true;
			}
			if(result.getTele_no2()!=null && result.getTele_type2()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("            			   \"telephoneExtn\": \""+(result.getTele_extn2()!=null?result.getTele_extn2():"")+"\",");
				sb.append("                        \"telephoneNumber\": \""+(result.getTele_no2()!=null?result.getTele_no2():"")+"\",");
				sb.append("                        \"telephoneType\": \""+(phoneTypeCibil.get(result.getTele_type2().toUpperCase())!=null?phoneTypeCibil.get(result.getTele_type2().toUpperCase()):"")+"\"");
				sb.append("                    }");
			}
			if(result.getTele_no3()!=null && result.getTele_type3()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("            			   \"telephoneExtn\": \""+(result.getTele_extn3()!=null?result.getTele_extn3():"")+"\",");
				sb.append("                        \"telephoneNumber\": \""+(result.getTele_no3()!=null?result.getTele_no3():"")+"\",");
				sb.append("                        \"telephoneType\": \""+(phoneTypeCibil.get(result.getTele_type3().toUpperCase())!=null?phoneTypeCibil.get(result.getTele_type3().toUpperCase()):"")+"\"");
				sb.append("                    }");
			}
			if(result.getTele_no4()!=null && result.getTele_type4()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("            			   \"telephoneExtn\": \""+(result.getTele_extn4()!=null?result.getTele_extn4():"")+"\",");
				sb.append("                        \"telephoneNumber\": \""+(result.getTele_no4()!=null?result.getTele_no4():"")+"\",");
				sb.append("                        \"telephoneType\": \""+(phoneTypeCibil.get(result.getTele_type4().toUpperCase())!=null?phoneTypeCibil.get(result.getTele_type4().toUpperCase()):"")+"\"");
				sb.append("                    }");
			}
			if(result.getTele_no5()!=null && result.getTele_type5()!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("            			   \"telephoneExtn\": \""+(result.getTele_extn5()!=null?result.getTele_extn5():"")+"\",");
				sb.append("                        \"telephoneNumber\": \""+(result.getTele_no5()!=null?result.getTele_no5():"")+"\",");
				sb.append("                        \"telephoneType\": \""+(phoneTypeCibil.get(result.getTele_type5().toUpperCase())!=null?phoneTypeCibil.get(result.getTele_type5().toUpperCase()):"")+"\"");
				sb.append("                    }");
			}
			flag=false;


			sb.append("        ],");
			sb.append("        \"uniqueids\": [");
			if(result.getIdno1()!=null && idMasterTableForCibil.get(result.getIdname1().toUpperCase())!=null){
				sb.append("                    {");
				sb.append("                        \"idType\": \""+(result.getIdname1()!=null?idMasterTableForCibil.get(result.getIdname1().toUpperCase()):"")+"\",");
				sb.append("                        \"idNo\": \""+(result.getIdno1()!=null?result.getIdno1():"")+"\"");
				sb.append("                    }");
				flag=true;
			}
			if(result.getIdno2()!=null && idMasterTableForCibil.get(result.getIdname2().toUpperCase())!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"idType\": \""+(result.getIdname2()!=null?idMasterTableForCibil.get(result.getIdname2().toUpperCase()):"")+"\",");
				sb.append("                        \"idNo\": \""+(result.getIdno2()!=null?result.getIdno2():"")+"\"");
				sb.append("                    }");
			}
			if(result.getIdno3()!=null && idMasterTableForCibil.get(result.getIdname3().toUpperCase())!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"idType\": \""+(result.getIdname3()!=null?idMasterTableForCibil.get(result.getIdname3().toUpperCase()):"")+"\",");
				sb.append("                        \"idNo\": \""+(result.getIdno3()!=null?result.getIdno3():"")+"\"");
				sb.append("                    }");
			}
			if(result.getIdno4()!=null && idMasterTableForCibil.get(result.getIdname4().toUpperCase())!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"idType\": \""+(result.getIdname4()!=null?idMasterTableForCibil.get(result.getIdname4().toUpperCase()):"")+"\",");
				sb.append("                        \"idNo\": \""+(result.getIdno4()!=null?result.getIdno4():"")+"\"");
				sb.append("                    }");
			}
			if(result.getIdno5()!=null && idMasterTableForCibil.get(result.getIdname5().toUpperCase())!=null){
				if(flag){
					sb.append("                    ,");
				}
				sb.append("                    {");
				sb.append("                        \"idType\": \""+(result.getIdname5()!=null?idMasterTableForCibil.get(result.getIdname5().toUpperCase()):"")+"\",");
				sb.append("                        \"idNo\": \""+(result.getIdno5()!=null?result.getIdno5():"")+"\"");
				sb.append("                    }");
			}
			flag=false;
			sb.append("        ]");
			sb.append("      }");
			sb.append("    ]");
			sb.append("  }");
			sb.append("}");
		}catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getCibilRequest() :: END");
		return sb.toString();
	}
	public CronInfo httpCall(String reqjson,String strUrl)
	{
		logger.info(" inside httpCall() :: Start");
		CronInfo info=new CronInfo();
		try 
		{
			URI uri = new URI(strUrl);
			URL url = uri.toURL();
			URLConnection connection = url.openConnection();
			OutputStream out = null;
			if (connection instanceof HttpsURLConnection) 
			{
				logger.info("HTTPS Connection tring to connect");
				HttpsURLConnection conn = (HttpsURLConnection) connection;
				conn.setSSLSocketFactory(new TLSSocketConnectionFactory());
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");	
				conn.setConnectTimeout(240000);
				conn.setReadTimeout(420000);
				conn.setDoOutput(true);
				conn.setDoInput(true);
				
				out = conn.getOutputStream();
				out.write(reqjson.getBytes());
				out.flush();
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output="";
				StringBuilder result=new StringBuilder();
				while ((output = br.readLine()) != null) 
				{
					result.append(output);
				}
				br.close();
				logger.info(" IBS response :: "+result);
				info.setResponseCode(conn.getResponseCode());
				info.setResponse(result.toString());
				conn.disconnect();
			}
			else if (connection instanceof HttpURLConnection) 
			{
				logger.info("HTTP Connection tring to connect");
				HttpURLConnection conn = (HttpURLConnection) connection;
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");	
				conn.setConnectTimeout(240000);
				conn.setReadTimeout(420000);
				conn.setDoOutput(true);
				conn.setDoInput(true);
				
				out = conn.getOutputStream();
				out.write(reqjson.getBytes());
				out.flush();
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				String output="";
				StringBuilder result=new StringBuilder();
				while ((output = br.readLine()) != null) 
				{
					result.append(output);
				}
				br.close();
				logger.info(" IBS response :: "+result);
				info.setResponseCode(conn.getResponseCode());
				info.setResponse(result.toString());
				conn.disconnect();
			}
			else
			{
				logger.info("connection is not an instanceof HttpsURLConnection or HttpURLConnection kindly check further : "+strUrl);
			}
			
			try 
			{
				if(out!=null)
				{
					out.close();
				}
			}
			catch (Exception e) 
			{
				logger.error("error while closing resources  :: "+e);
			}
		} 
		catch (Exception e) 
		{
			logger.error("error while calling IBS  :: "+e);
		}
		logger.info(" inside httpCall() :: END");
		return info;
	}    
	/*
	public CronInfo httpCall(String reqjson,String url){
		logger.info(" inside httpCall() :: Start");
		CronInfo info=new CronInfo();
		try {

			URL urlconnection = new URI(url).toURL();
			HttpURLConnection conn = (HttpURLConnection) urlconnection.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");	
			conn.setConnectTimeout(240000);
			conn.setReadTimeout(420000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			OutputStream out = conn.getOutputStream();
			out.write(reqjson.getBytes());
			out.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output="";
			StringBuilder result=new StringBuilder();
			while ((output = br.readLine()) != null) 
			{
				result.append(output);
			}

			br.close();
			logger.info(" IBS response :: "+result);
			info.setResponseCode(conn.getResponseCode());
			info.setResponse(result.toString());
		} catch (Exception e) {
			logger.error("error while calling IBS  :: "+e);
		}
		logger.info(" inside httpCall() :: END");
		return info;
	}
*/
	public String getKarzaShopEstablishmentV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaShopEstablishmentV2Request() :: Start");
		ShopEstablishmentRequest shopEstablishmentRequest=new ShopEstablishmentRequest();
		ShopEstablishmentPayload payload=new ShopEstablishmentPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setReg_no(result.getReg_no()!=null?result.getReg_no():"");
			payload.setArea_code(result.getArea_code()!=null?result.getArea_code():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			shopEstablishmentRequest.setHeader(header);
			shopEstablishmentRequest.setPayload(payload);

			reqJson=om.writeValueAsString(shopEstablishmentRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaShopEstablishmentV2Request() :: End");
		return reqJson;


	}

	public String getKarzaUdyogAadharV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaUdyogAdharV2Request() :: Start");
		UdyogAadharRequest udyogAadharRequest=new UdyogAadharRequest();
		UdyogAadharPayload payload=new UdyogAadharPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setAadhar(result.getAadhar()!=null?result.getAadhar():"");
			payload.setUan(result.getUan()!=null?result.getUan():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			udyogAadharRequest.setHeader(header);
			udyogAadharRequest.setPayload(payload);

			reqJson=om.writeValueAsString(udyogAadharRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaUdyogAadharV2Request() :: End");
		return reqJson;


	}

	public String getKarzaNregaV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaNregaV2Request() :: Start");
		NREGARequest nregaRequest=new NREGARequest();
		NREGAPayload payload=new NREGAPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";

		try {
			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setJobcardid(result.getJobcardid()!=null?result.getJobcardid():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			nregaRequest.setHeader(header);
			nregaRequest.setPayload(payload);

			reqJson=om.writeValueAsString(nregaRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaNregaV2Request() :: End");
		return reqJson;


	}
	public String getKarzaRCSearchV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaRCSearchV2Request() :: Start");
		RCSearchRequest rcSearchRequest=new RCSearchRequest();
		RCSearchPayload payload=new RCSearchPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setEngine_no(result.getEngineNo()!=null?result.getEngineNo():"");
			payload.setState(result.getState()!=null?result.getState():"");
			payload.setChassis_no(result.getChassisNo()!=null?result.getChassisNo():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");	

			rcSearchRequest.setHeader(header);
			rcSearchRequest.setPayload(payload);

			reqJson=om.writeValueAsString(rcSearchRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaRCSearchV2Request() :: End");
		return reqJson;


	}

	public String getKarzaRCAUTHV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaRCAUTHV2Request() :: Start");
		RCAuthRequest rcAuthRequest=new RCAuthRequest();
		RCAuthPayload payload=new RCAuthPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setReg_no(result.getRcAuthRegNumber()!=null?result.getRcAuthRegNumber():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");	

			rcAuthRequest.setHeader(header);
			rcAuthRequest.setPayload(payload);

			reqJson=om.writeValueAsString(rcAuthRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaRCAUTHV2Request() :: End");
		return reqJson;


	}

	
	
	public String getKarzaITRAuthV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaITRAuthV2Request() :: Start");
		ITRAuthRequest itrRequest=new ITRAuthRequest();
		ITRAuthPayload payload=new ITRAuthPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setPan(result.getIdno1()!=null?result.getIdno1():"");
			payload.setAck(result.getAck()!=null?result.getAck():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");	

			itrRequest.setHeader(header);
			itrRequest.setPayload(payload);

			reqJson=om.writeValueAsString(itrRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaITRAuthV2Request() :: End");
		return reqJson;


	}
	public String getKarzaFSSAILicenceV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaFSSAILicenceV2Request() :: Start");
		FSSAILicenceRequest fssaiLicenceRequest=new FSSAILicenceRequest();
		FSSAILicencePayload payload=new FSSAILicencePayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));


			payload.setReg_no(result.getReg_no()!=null?result.getReg_no():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");	

			fssaiLicenceRequest.setHeader(header);
			fssaiLicenceRequest.setPayload(payload);

			reqJson=om.writeValueAsString(fssaiLicenceRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaFSSAILicenceV2Request() :: End");
		return reqJson;


	}

	public String getKarzaEPFAuthOtpV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaEPFAuthOtpV2Request() :: Start");
		EPFAuthOTPRequest epfAuthOTPRequest=new EPFAuthOTPRequest();
		EPFAuthOTPPayload payload=new EPFAuthOTPPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));


			payload.setMobile_no(result.getTele_no1()!=null?result.getTele_no1():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");	
			payload.setUanid(result.getUan()!=null?result.getUan():"");

			epfAuthOTPRequest.setHeader(header);
			epfAuthOTPRequest.setPayload(payload);

			reqJson=om.writeValueAsString(epfAuthOTPRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaEPFAuthOtpV2Request() :: End");
		return reqJson;


	}
	public String getKarzaICSIRequest(IbRequestMaster result) {

		logger.info(" inside getKarzaICSIRequest() :: Start");
		ICSIMemberShipRequest icsiMemberShipRequest=new ICSIMemberShipRequest();
		ICSIMemberShipPayload payload=new ICSIMemberShipPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
			payload.setMembership_no(result.getMembership_no()!=null?result.getMembership_no():"");
			payload.setCp_no(result.getCp_no()!=null?result.getCp_no():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");


			icsiMemberShipRequest.setHeader(header);
			icsiMemberShipRequest.setPayload(payload);

			reqJson=om.writeValueAsString(icsiMemberShipRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaICSIRequest() :: End");
		return reqJson;
	}

	public String getKarzaIWAIFirmAuthRequest(IbRequestMaster result) {

		logger.info(" inside getKarzaIWAIFirmAuthRequest() :: Start");
		ICWAIFirmAuthRequest icwaiFirmAuthRequest=new ICWAIFirmAuthRequest();
		ICWAIFirmAuthPayload payload=new ICWAIFirmAuthPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
			payload.setReg_no(result.getReg_no()!=null?result.getReg_no():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");


			icwaiFirmAuthRequest.setHeader(header);
			icwaiFirmAuthRequest.setPayload(payload);

			reqJson=om.writeValueAsString(icwaiFirmAuthRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaIWAIFirmAuthRequest() :: End");
		return reqJson;


	}

	public String getKarzaLPGV3Request(IbRequestMaster result) {

		logger.info(" inside getKarzaLPGV3Request() :: Start");
		LpgRequest2 lpgRequest=new LpgRequest2();
		LpgPayload2 payload=new LpgPayload2();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setLpg_id(result.getLpg_id()!=null?result.getLpg_id():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");


			lpgRequest.setHeader(header);
			lpgRequest.setPayload(payload);

			reqJson=om.writeValueAsString(lpgRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaLPGV3Request() :: End");
		return reqJson;


	}

	public String getKarzaGSTIdentificationV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaGSTIdentificationV2Request() :: Start");
		GstIdentificationRequest gstIdentificationRequest=new GstIdentificationRequest();
		GstIdentificationPayload payload=new GstIdentificationPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setGstin(result.getGstin()!=null?result.getGstin():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");
			payload.setState(result.getState1()!=null?result.getState1():"");
			payload.setInput(result.getInput()!=null?result.getInput():"");

			gstIdentificationRequest.setHeader(header);
			gstIdentificationRequest.setPayload(payload);

			reqJson=om.writeValueAsString(gstIdentificationRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaGSTIdentificationV2Request() :: End");
		return reqJson;


	}
	public String getKarzaEPFAuthPassV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaEPFAuthPassV2Request() :: Start");
		EPFAuthPassBookRequest epfAuthPassBookRequest=new EPFAuthPassBookRequest();
		EPFAuthPassBookPayload payload=new EPFAuthPassBookPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setRequest_id(result.getRequest_id()!=null?result.getRequest_id():"");
			payload.setOtp(result.getOtp()!=null?result.getOtp():"");


			epfAuthPassBookRequest.setHeader(header);
			epfAuthPassBookRequest.setPayload(payload);

			reqJson=om.writeValueAsString(epfAuthPassBookRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaEPFAuthPassV2Request() :: End");
		return reqJson;


	}
	public String getSingleActionRequest(IbRequestMaster result) {
		putStateCode();
		putGenderRevMode();
		logger.info(" inside getSingleActionRequest() :: Start");
		ExperianRequest experianRequest = new ExperianRequest();
		Header header = new Header();
		ExperianKickOffPayload payload = new ExperianKickOffPayload();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));


			payload.setClientName(result.getClientName()!=null?result.getClientName():"");
			payload.setAllowInput(result.getAllowInput()!=null?result.getAllowInput():"");
			payload.setAllowEdit(result.getAllowEdit()!=null?result.getAllowEdit():"");
			payload.setAllowCaptcha(result.getAllowCaptcha()!=null?result.getAllowCaptcha():"");
			payload.setAllowConsent_additional(result.getAllowConsent_additional()!=null?result.getAllowConsent_additional():"");
			payload.setAllowConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");
			payload.setAllowEmailVerify(result.getAllowEmailVerify()!=null?result.getAllowEmailVerify():"");
			payload.setAllowVoucher(result.getAllowVoucher()!=null?result.getAllowVoucher():"");
			payload.setVoucherCode(result.getVoucherCode()!=null?result.getVoucherCode():"");
			payload.setNoValidationByPass(result.getNoValidationByPass()!=null?result.getNoValidationByPass():"");
			payload.setEmailConditionalByPass(result.getEmailConditionalByPass()!=null?result.getEmailConditionalByPass():"");
			payload.setFirstName(result.getF_name()!=null?result.getF_name():"");
			payload.setMiddleName(result.getM_name()!=null?result.getM_name():"");
			payload.setSurName(result.getL_name()!=null?result.getL_name():"");
			payload.setDateOfBirth(dateFormatSingleAction(result.getDob())!=null?dateFormatSingleAction(result.getDob()):"");
			payload.setGender(genderREVCode.get(result.getGender())!=null?genderREVCode.get(result.getGender()):"");
			payload.setMobileNo(result.getTele_no1()!=null?result.getTele_no1():"");
			payload.setTelephoneNo(result.getTele_no2()!=null?result.getTele_no2():"");
			payload.setTelephoneType(result.getTele_type1()!=null?result.getTele_type1():"");
			payload.setEmail(result.getActual_email_addr()!=null?result.getActual_email_addr():"");
			payload.setFlatno(result.getFlatno()!=null?result.getFlatno():"");
			payload.setBuildingName(result.getBuildingName()!=null?result.getBuildingName():"");
			payload.setRoadName(result.getRoadName()!=null?result.getRoadName():"");
			payload.setCity(result.getCity1()!=null?result.getCity1():"");
			payload.setState(stateCode.get(result.getState1())!=null?stateCode.get(result.getState1()):"");
			payload.setPincode(result.getPin1()!=null?result.getPin1():"");
			payload.setPan(result.getIdno1()!=null?result.getIdno1():"");
			payload.setVoterid(result.getIdno3()!=null?result.getIdno3():"");
			payload.setPassport(result.getIdno2()!=null?result.getIdno2():"");
			payload.setAadhaar(result.getIdno4()!=null?result.getIdno4():"");
			payload.setDriverlicense(result.getIdno4()!=null?result.getIdno4():"");
			payload.setRationcard(result.getIdno5()!=null?result.getIdno5():"");
			payload.setReason(result.getReason()!=null?result.getReason():"");

			experianRequest.setHeader(header);
			experianRequest.setPayload(payload);

			reqJson=om.writeValueAsString(experianRequest);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getSingleActionRequest() :: END");
		return reqJson;
	}

	public String getAuthDeliveryRequest(IbRequestMaster result, ExperianMaskResponse maskresponse) {
		logger.info(" inside getAuthDeliveryRequest() :: Start");
		AuthExperianRequest experianRequest=new AuthExperianRequest();
		AuthExperianRequestPayload payload=new AuthExperianRequestPayload();
		Header header = new Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";

		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId("12345");
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			ExperianMaskResponsePayload exrespay=maskresponse.getPayload();
			if(exrespay!=null){
				ExperianMaskResPayloadResult exrespayres=exrespay.getResult();
				payload.setStgOneHitId(exrespayres.getStgOneHitId()!=null?exrespayres.getStgOneHitId():"");
				payload.setStgTwoHitId(exrespayres.getStgTwoHitId()!=null?exrespayres.getStgTwoHitId():"");

				payload.setActualMobileNumber(result.getTele_no2()!=null?result.getTele_no2():"");
				payload.setActualEmailADDR(result.getActual_email_addr()!=null?result.getActual_email_addr():"");
				payload.setSingleActionSessionId(exrespayres.getSessionId()!=null?exrespayres.getSessionId():"");
			}
			experianRequest.setHeader(header);
			experianRequest.setPayload(payload);

			reqJson=om.writeValueAsString(experianRequest);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getAuthDeliveryRequest() :: End");
		return reqJson;
	}
	/*public CronInfo okHttpCall(String reqjson,String url) {
		logger.info(" inside okHttpCall() :: Start");
		CronInfo info=new CronInfo();
		try {
			OkHttpClient client = new OkHttpClient();
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, reqjson);
			Request request = new Request.Builder()
			  .url(url)
			  .post(body)
			  .addHeader("content-type", "application/json")
			  .build();
			client.setConnectTimeout(120, TimeUnit.SECONDS);
			client.setReadTimeout(240, TimeUnit.SECONDS);
			logger.info(" calling IBS for request :: "+reqjson);
			Response response = client.newCall(request).execute();

			String output="";
			StringBuilder result=new StringBuilder();
			BufferedReader br = new BufferedReader(new InputStreamReader(response.body().byteStream()));
			while ((output = br.readLine()) != null) {
				result.append(output);
			}

			br.close();
			logger.info(" IBS response :: "+result);
			info.setResponseCode(response.code());
			info.setResponse(result.toString());
		} catch (Exception e) {
			logger.error("error while calling IBS  :: "+e);
			e.printStackTrace();
		}
		logger.info(" inside okHttpCall() :: END");
		return info;
	}*/
	public String getCreditVidyaEmailVerifyRequest(EmailSaveResponse response) {
		logger.info(" inside getCreditVidyaEmailVerifyRequest() :: Start");
		EmailVerificationRequest request=new EmailVerificationRequest();
		EmailVerificationReqPayload payload=new EmailVerificationReqPayload();
		com.qualtech.creditvidya.api.request.Header header = new com.qualtech.creditvidya.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(response.getHeader().getCorrelationId()!=null?response.getHeader().getCorrelationId():"");
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setUniqueId(response.getPayload().getUniqueId()!=null?response.getPayload().getUniqueId():"");

			request.setHeader(header);
			request.setPayload(payload);

			reqJson=om.writeValueAsString(request);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getCreditVidyaEmailVerifyRequest() :: End");
		return reqJson;
	}
	public String getKarzaDlV3Request(IbRequestMaster result) {
		logger.info(" inside getKarzaDlV3Request() :: Start");
		DlRequest dlRequest=new DlRequest();
		DlPayload payload=new DlPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
			//payload.setName((result.getF_name()!=null?result.getF_name():"")+(result.getM_name()!=null?" "+result.getM_name():"")+(result.getL_name()!=null?" "+result.getL_name():""));
			payload.setDob(result.getDob()!=null?result.getDob():"");

			payload.setDl_no(result.getIdno4()!=null?result.getIdno4():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			dlRequest.setHeader(header);
			dlRequest.setPayload(payload);

			reqJson=om.writeValueAsString(dlRequest);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaDlV3Request() :: End");
		return reqJson;
	}



	public String getCRQRequest(IbRequestMaster result) {
		logger.info(" inside getCRQRequest() :: Start");
		ExperianGenQuestionRequest experianRequest=new ExperianGenQuestionRequest();
		ExperianGenQuestionReqPayload payload=new ExperianGenQuestionReqPayload();
		Header header = new Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));


			payload.setStgOneHitId(result.getStg_one_hit_id()!=null?result.getStg_one_hit_id():"");
			payload.setStgTwoHitId(result.getStg_two_hit_id()!=null?result.getStg_two_hit_id():"");

			payload.setCaptchCode(result.getCaptch_code()!=null?result.getCaptch_code():"");
			payload.setSingleActionSessionId(result.getSingle_action_session_id()!=null?result.getSingle_action_session_id():"");

			experianRequest.setHeader(header);
			experianRequest.setPayload(payload);

			reqJson=om.writeValueAsString(experianRequest);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getCRQRequest() :: End");
		return reqJson;
	}
	public String getCreditVidyaEmailSaveRequest(IbRequestMaster result) {
		putStateName();
		logger.info(" inside getCreditVidyaEmailSaveRequest() :: Start");
		EmailSaveRequest request=new EmailSaveRequest();
		EmailSaveReqPayload payload=new EmailSaveReqPayload();
		com.qualtech.creditvidya.api.request.Header header = new com.qualtech.creditvidya.api.request.Header();
		ClientReference clientReference=new ClientReference();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try
		{
			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setFirstName(result.getF_name()!=null?result.getF_name():"");
			payload.setMiddleName(result.getM_name()!=null?result.getM_name():"");
			payload.setLastName(result.getL_name()!=null?result.getL_name():"");
			payload.setEmail(result.getActual_email_addr()!=null?result.getActual_email_addr():"");
			payload.setMobileNumber(result.getTele_no1()!=null?result.getTele_no1():"");
			payload.setUniqueId(result.getUniqueId()!=null?result.getUniqueId():"");

			clientReference.setLeadId(result.getLeadId()!=null?result.getLeadId():"");
			clientReference.setLosId(result.getLosId()!=null?result.getLosId():"");
			clientReference.setTransactionId(result.getTransactionId()!=null?result.getTransactionId():"");

			payload.setClientReference(clientReference);

			payload.setCompanyName(result.getCompanyName()!=null?result.getCompanyName():"");
			payload.setOfficeAddressLine1(result.getOfficeAddressLine1()!=null?result.getOfficeAddressLine1():"");
			payload.setOfficeAddressLine2(result.getOfficeAddressLine2()!=null?result.getOfficeAddressLine2():"");
			payload.setOfficeAddressLine3(result.getOfficeAddressLine3()!=null?result.getOfficeAddressLine3():"");

			payload.setOfficeCity(result.getOfficeCity()!=null?result.getOfficeCity():"");
			payload.setOfficeState(stateName.get(result.getOfficeState())!=null?stateName.get(result.getOfficeState()):"");
			payload.setOfficePinCode(result.getOfficePinCode()!=null?result.getOfficePinCode():"");

			request.setHeader(header);
			request.setPayload(payload);

			reqJson=om.writeValueAsString(request);
		} 
		catch (Exception e)
		{
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getCreditVidyaEmailSaveRequest() :: End");
		return reqJson;
	}
	public String getKotakReversalRequest(IbRequestMaster result) {

		logger.info(" inside getKotakReversalRequest() :: Start");
		KotakReversalPayload kotakReversalPayload= new KotakReversalPayload();
		com.qualtech.kotak.api.request.Header header = new com.qualtech.kotak.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";

		try {
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setToken(resProp.getString("com.qc.ibs.token"));

			KotakReversal krev = new KotakReversal();
			KotakReversalHeader revhedr = new KotakReversalHeader();
			revhedr.setRequestId(result.getMessageId());
			revhedr.setMsgSource(result.getMsgSource());
			revhedr.setClientCode(result.getClientCode());
			revhedr.setDatePost(result.getPaymentDt());
			krev.setReversalHeader(revhedr);
			KotakReversalDtls revdtl = new KotakReversalDtls();
			revdtl.setMessageId(result.getMessageId());
			krev.setDetails(revdtl);

			kotakReversalPayload.setReversal(krev);
			KotakRequestReversal request=new KotakRequestReversal();
			request.setHeader(header);
			request.setPayload(kotakReversalPayload);

			reqJson=om.writeValueAsString(request);



		}
		catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}

		return reqJson;
	}
	public String getKotakPaymentRequest(IbRequestMaster result) {

		logger.info(" inside getKotakPaymentRequest() :: Start");
		KotakRequest request=new KotakRequest();
		KotakPayment kotakPayment=new KotakPayment();
		com.qualtech.kotak.api.request.Header header = new com.qualtech.kotak.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";

		try {

			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setToken(resProp.getString("com.qc.ibs.token"));
			KotakPayRequestHeader kpheader=new KotakPayRequestHeader();
			kpheader.setMessageId(result.getMessageId()!=null?result.getMessageId():"");
			kpheader.setMsgSource(result.getMsgSource()!=null?result.getMsgSource():"");
			kpheader.setClientCode(result.getClientCode()!=null?result.getClientCode():"");
			kpheader.setBatchRefNmbr(result.getBatchRefNmbr()!=null?result.getBatchRefNmbr():"");
			kpheader.setHeaderChecksum(result.getHeaderChecksum()!=null?result.getHeaderChecksum():"");
			kpheader.setReqRF1(result.getReqRF1()!=null?result.getReqRF1():"");
			kpheader.setReqRF2(result.getReqRF2()!=null?result.getReqRF2():"");
			kpheader.setReqRF3(result.getReqRF3()!=null?result.getReqRF3():"");
			kpheader.setReqRF4(result.getReqRF4()!=null?result.getReqRF4():"");
			kpheader.setReqRF5(result.getReqRF5()!=null?result.getReqRF5():"");
			KotakPayInstrument kpinstrumnt=new KotakPayInstrument();
			kpinstrumnt.setInstRefNo(result.getInstRefNo()!=null?result.getInstRefNo():"");
			kpinstrumnt.setCompanyId(result.getCompanyId()!=null?result.getCompanyId():"");
			kpinstrumnt.setCompBatchId(result.getCompBatchId()!=null?result.getCompBatchId():"");
			kpinstrumnt.setConfidentialInd(result.getConfidentialInd()!=null?result.getConfidentialInd():"");
			kpinstrumnt.setMyProdCode(result.getMyProdCode()!=null?result.getMyProdCode():"");
			kpinstrumnt.setCompTransNo(result.getCompTransNo()!=null?result.getCompTransNo():"");
			kpinstrumnt.setPayMode(result.getPayMode()!=null?result.getPayMode():"");
			kpinstrumnt.setTxnAmnt(result.getTxnAmnt()!=null?result.getTxnAmnt():"");
			kpinstrumnt.setAccountNo(result.getAccountNo()!=null?result.getAccountNo():"");
			kpinstrumnt.setDrRefNmbr(result.getDrRefNmbr()!=null?result.getDrRefNmbr():"");
			kpinstrumnt.setDrDesc(result.getDrDesc()!=null?result.getDrDesc():"");
			kpinstrumnt.setPaymentDt(result.getPaymentDt()!=null?result.getPaymentDt():"");
			kpinstrumnt.setBankCdInd(result.getBankCdInd()!=null?result.getBankCdInd():"");
			kpinstrumnt.setBeneBnkCd(result.getBeneBnkCd()!=null?result.getBeneBnkCd():"");
			kpinstrumnt.setRecBrCd(result.getRecBrCd()!=null?result.getRecBrCd():"");
			kpinstrumnt.setBeneAcctNo(result.getBeneAcctNo()!=null?result.getBeneAcctNo():"");
			kpinstrumnt.setBeneName(result.getBeneName()!=null?result.getBeneName():"");
			kpinstrumnt.setBeneCode(result.getBeneCode()!=null?result.getBeneCode():"");
			kpinstrumnt.setBeneEmail(result.getBeneEmail()!=null?result.getBeneEmail():"");
			kpinstrumnt.setBeneFax(result.getBeneFax()!=null?result.getBeneFax():"");
			kpinstrumnt.setBeneMb(result.getBeneMb()!=null?result.getBeneMb():"");
			kpinstrumnt.setBeneAddr1(result.getBeneAddr1()!=null?result.getBeneAddr1():"");
			kpinstrumnt.setBeneAddr2(result.getBeneAddr2()!=null?result.getBeneAddr2():"");
			kpinstrumnt.setBeneAddr3(result.getBeneAddr3()!=null?result.getBeneAddr3():"");
			kpinstrumnt.setBeneAddr4(result.getBeneAddr4()!=null?result.getBeneAddr4():"");
			kpinstrumnt.setBeneAddr5(result.getBeneAddr5()!=null?result.getBeneAddr5():"");

			kpinstrumnt.setCity(result.getCity()!=null?result.getCity():"");
			kpinstrumnt.setZip(result.getZip()!=null?result.getZip():"");
			kpinstrumnt.setCountry(result.getCountry()!=null?result.getCountry():"");
			kpinstrumnt.setState(result.getState()!=null?result.getState():"");

			kpinstrumnt.setTelephoneNo(result.getTelephoneNo()!=null?result.getTelephoneNo():"");
			kpinstrumnt.setBeneId(result.getBeneId()!=null?result.getBeneId():"");

			kpinstrumnt.setBeneTaxId(result.getBeneTaxId()!=null?result.getBeneTaxId():"");
			kpinstrumnt.setAuthPerson(result.getAuthPerson()!=null?result.getAuthPerson():"");
			kpinstrumnt.setAuthPersonId(result.getAuthPersonId()!=null?result.getAuthPersonId():"");

			kpinstrumnt.setDeliveryMode(result.getDeliveryMode()!=null?result.getDeliveryMode():"");
			kpinstrumnt.setPayoutLoc(result.getPayoutLoc()!=null?result.getPayoutLoc():"");
			kpinstrumnt.setPickupBr(result.getPickupBr()!=null?result.getPickupBr():"");

			kpinstrumnt.setPaymentRef(result.getPaymentRef()!=null?result.getPaymentRef():"");
			kpinstrumnt.setChgBorneBy(result.getChgBorneBy()!=null?result.getChgBorneBy():"");

			kpinstrumnt.setInstDt(result.getChgBorneBy()!=null?result.getChgBorneBy():"");
			kpinstrumnt.setMicrNo(result.getChgBorneBy()!=null?result.getChgBorneBy():"");
			kpinstrumnt.setCreditRefNo(result.getChgBorneBy()!=null?result.getChgBorneBy():"");
			kpinstrumnt.setPaymentDtl(result.getPaymentDtl()!=null?result.getPaymentDtl():"");
			kpinstrumnt.setPaymentDtl1(result.getPaymentDtl1()!=null?result.getPaymentDtl1():"");
			kpinstrumnt.setPaymentDtl2(result.getPaymentDtl2()!=null?result.getPaymentDtl2():"");
			kpinstrumnt.setPaymentDtl3(result.getPaymentDtl3()!=null?result.getPaymentDtl3():"");


			kpinstrumnt.setMailToAddr1(result.getMailToAddr1()!=null?result.getMailToAddr1():"");
			kpinstrumnt.setMailToAddr2(result.getMailToAddr2()!=null?result.getMailToAddr2():"");
			kpinstrumnt.setMailToAddr3(result.getMailToAddr3()!=null?result.getMailToAddr3():"");
			kpinstrumnt.setMailToAddr4(result.getMailToAddr4()!=null?result.getMailToAddr4():"");

			kpinstrumnt.setMailTo(result.getMailTo()!=null?result.getMailTo():"");

			kpinstrumnt.setExchDoc(result.getExchDoc()!=null?result.getExchDoc():"");
			kpinstrumnt.setInstChecksum(result.getInstChecksum()!=null?result.getInstChecksum():"");
			kpinstrumnt.setInstRF1(result.getInstRF1()!=null?result.getInstRF1():"");
			kpinstrumnt.setInstRF2(result.getInstRF3()!=null?result.getInstRF2():"");
			kpinstrumnt.setInstRF3(result.getInstRF3()!=null?result.getInstRF3():"");
			kpinstrumnt.setInstRF4(result.getInstRF4()!=null?result.getInstRF4():"");
			kpinstrumnt.setInstRF5(result.getInstRF5()!=null?result.getInstRF5():"");
			kpinstrumnt.setInstRF6(result.getInstRF6()!=null?result.getInstRF6():"");
			kpinstrumnt.setInstRF7(result.getInstRF7()!=null?result.getInstRF7():"");
			kpinstrumnt.setInstRF8(result.getInstRF8()!=null?result.getInstRF8():"");
			kpinstrumnt.setInstRF9(result.getInstRF9()!=null?result.getInstRF9():"");
			kpinstrumnt.setInstRF10(result.getInstRF10()!=null?result.getInstRF10():"");
			kpinstrumnt.setInstRF11(result.getInstRF11()!=null?result.getInstRF11():"");
			kpinstrumnt.setInstRF12(result.getInstRF12()!=null?result.getInstRF12():"");
			kpinstrumnt.setInstRF13(result.getInstRF13()!=null?result.getInstRF13():"");
			kpinstrumnt.setInstRF14(result.getInstRF14()!=null?result.getInstRF14():"");
			kpinstrumnt.setInstRF15(result.getInstRF15()!=null?result.getInstRF15():"");
			KotakPayEnrichmentSet kotakPayEnrichmentSet=new KotakPayEnrichmentSet();
			kotakPayEnrichmentSet.setEnrichment(result.getEnrichment()!=null?result.getEnrichment():"");
			kpinstrumnt.setEnrichmentSet(kotakPayEnrichmentSet);
			kotakPayment.setPayheader(kpheader);
			KotakPayInstrumentList instrumentList=new KotakPayInstrumentList();
			instrumentList.setInstrument(kpinstrumnt);
			kotakPayment.setInstrumentList(instrumentList);


			KotakPayload payload=new KotakPayload();
			payload.setKotakPayment(kotakPayment);


			request.setHeader(header);
			request.setPayload(payload);

			reqJson=om.writeValueAsString(request);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}


		return reqJson;
	}
	public String getKarzaElecV2Request(IbRequestMaster result) {
		logger.info(" inside getKarzaElecV2Request() :: Start");
		ElectricalRequest request=new ElectricalRequest();
		ElectricalPayload payload=new ElectricalPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));


			payload.setName((result.getF_name()!=null?result.getF_name():"")+(result.getM_name()!=null?" "+result.getM_name():"")+(result.getL_name()!=null?" "+result.getL_name():""));
			payload.setConsumer_id(result.getConsumer_id()!=null?result.getConsumer_id():"");

			payload.setService_provider(result.getKarza_service_provider()!=null?result.getKarza_service_provider():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			request.setHeader(header);
			request.setPayload(payload);

			reqJson=om.writeValueAsString(request);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaElecV2Request() :: End");
		return reqJson;
	}
	public String getKarzaTanV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaTanV2Request() :: Start");
		TanRequest tanRequest=new TanRequest();
		TanPayload payload=new TanPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setTan(result.getTan()!=null?result.getTan():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");


			tanRequest.setHeader(header);
			tanRequest.setPayload(payload);

			reqJson=om.writeValueAsString(tanRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaTanV2Request() :: End");
		return reqJson;


	}
	public String getKarzaVoterV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaVoterV2Request() :: Start");
		VoterRequest voterRequest=new VoterRequest();
		VoterPayload payload=new VoterPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setEpic_no(result.getIdno3()!=null?result.getIdno3():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			voterRequest.setHeader(header);
			voterRequest.setPayload(payload);

			reqJson=om.writeValueAsString(voterRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaVoterV2Request() :: End");
		return reqJson;


	}
	public String getKarzaPanRequest(IbRequestMaster result) {

		logger.info(" inside getKarzaPanRequest() :: Start");
		PanRequest request=new PanRequest();
		PanPayload payload=new PanPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));


			payload.setPan(result.getIdno1()!=null?result.getIdno1():"");

			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			request.setHeader(header);
			request.setPayload(payload);

			reqJson=om.writeValueAsString(request);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaPanRequest() :: End");
		return reqJson;

	}
	
	public String getKarzaEMailAUTHRequest(IbRequestMaster result) {

		logger.info(" inside getKarzaEMailAUTHRequest() :: Start");
		EmailAuthRequest emailAuthRequest=new EmailAuthRequest();
		EmailAuthPayload payload=new EmailAuthPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setEmail(result.getActual_email_addr()!=null?result.getActual_email_addr():"");
			
			
			emailAuthRequest.setHeader(header);
			emailAuthRequest.setPayload(payload);

			reqJson=om.writeValueAsString(emailAuthRequest);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaEMailAUTHRequest() :: End");
		return reqJson;

	}
	
	public String getKarzaEPFAuthOTPRequest(IbRequestMaster result) {

		logger.info(" inside getKarzaEPFAuthOTPRequest() :: Start");
		EPFAuthOTPRequest epfAuthOTPRequest=new EPFAuthOTPRequest();
		EPFAuthOTPPayload payload=new EPFAuthOTPPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setUanid(result.getUan()!=null?result.getUan():"");
			payload.setMobile_no(result.getTele_no1()!=null?result.getTele_no1():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");
			
			epfAuthOTPRequest.setHeader(header);
			epfAuthOTPRequest.setPayload(payload);

			reqJson=om.writeValueAsString(epfAuthOTPRequest);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaEPFAuthOTPRequest() :: End");
		return reqJson;

	}
	
	public String getKarzaMCASignatureV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaMCASignatureV2Request() :: Start");
		MCASignatureRequest mcaSignatureRequest=new MCASignatureRequest();
		MCASignaturePayload payload=new MCASignaturePayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setCin(result.getCin()!=null?result.getCin():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			mcaSignatureRequest.setHeader(header);
			mcaSignatureRequest.setPayload(payload);

			reqJson=om.writeValueAsString(mcaSignatureRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaMCASignatureV2Request() :: End");
		return reqJson;


	}

	public String getKarzaCompSearchV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaCompSearchV2Request() :: Start");
		CompSearchRequest compSearchRequest=new CompSearchRequest();
		CompSearchPayload payload=new CompSearchPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setCompany_name(result.getCompanyNameKarza()!=null?result.getCompanyNameKarza():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			compSearchRequest.setHeader(header);
			compSearchRequest.setPayload(payload);

			reqJson=om.writeValueAsString(compSearchRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaCompSearchV2Request() :: End");
		return reqJson;


	}

	public String getKarzaIecV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaIecV2Request() :: Start");
		IECRequest iecRequest=new IECRequest();
		IECPayload payload=new IECPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setIec(result.getIec()!=null?result.getIec():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			iecRequest.setHeader(header);
			iecRequest.setPayload(payload);

			reqJson=om.writeValueAsString(iecRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaIecV2Request() :: End");
		return reqJson;


	}

	public String getKarzaLLPCinLookupV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaLLPCinLookupV2Request() :: Start");
		CompanyLLPCINLookUpRequest companyLLPCINLookUpRequest=new CompanyLLPCINLookUpRequest();
		CompanyLLPCINLookUpPayload payload=new CompanyLLPCINLookUpPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setCompany_name(result.getCompanyNameKarza()!=null?result.getCompanyNameKarza():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			companyLLPCINLookUpRequest.setHeader(header);
			companyLLPCINLookUpRequest.setPayload(payload);

			reqJson=om.writeValueAsString(companyLLPCINLookUpRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaLLPCinLookupV2Request() :: End");
		return reqJson;


	}

	public String getKarzaLLPIdentificationV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaLLPIdentificationV2Request() :: Start");
		CompanyLLPIdentificationRequest companyLLPIdentificationRequest=new CompanyLLPIdentificationRequest();
		CompanyLLPIdentificationPayload payload=new CompanyLLPIdentificationPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setCin(result.getCin()!=null?result.getCin():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			companyLLPIdentificationRequest.setHeader(header);
			companyLLPIdentificationRequest.setPayload(payload);

			reqJson=om.writeValueAsString(companyLLPIdentificationRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaLLPIdentificationV2Request() :: End");
		return reqJson;


	}

	public String getKarzaGSTAuthenticationV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaGSTAuthenticationV2Request() :: Start");
		GSTAuthenticationRequest authenticationRequest=new GSTAuthenticationRequest();
		GSTAuthenticationPayload payload=new GSTAuthenticationPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setGstin(result.getGstin()!=null?result.getGstin():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			authenticationRequest.setHeader(header);
			authenticationRequest.setPayload(payload);

			reqJson=om.writeValueAsString(authenticationRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaGSTAuthenticationV2Request() :: End");
		return reqJson;


	}
	public String getKarzaAddressMatchingV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaAddressMatchingV2Request() :: Start");
		AddressRequest addressRequest=new AddressRequest();
		AddressPayload payload=new AddressPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setAddress1(result.getAddressMatch1()!=null?result.getAddressMatch1():"");
			payload.setAddress2(result.getAddressMatch2()!=null?result.getAddressMatch2():"");

			addressRequest.setHeader(header);
			addressRequest.setPayload(payload);

			reqJson=om.writeValueAsString(addressRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaAddressMatchingV2Request() :: End");
		return reqJson;


	}

	public String getKarzaNameSimilarityV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaNameSimilarityV2Request() :: Start");
		NameSimilarityRequest nameSimilarityRequest=new NameSimilarityRequest();
		NameSimilarityPayload payload=new NameSimilarityPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setType(result.getNameType()!=null?result.getNameType():"");
			payload.setName1(result.getFullname()!=null?result.getFullname():"");
			payload.setName2(result.getShortname()!=null?result.getShortname():"");

			nameSimilarityRequest.setHeader(header);
			nameSimilarityRequest.setPayload(payload);

			reqJson=om.writeValueAsString(nameSimilarityRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaNameSimilarityV2Request() :: End");
		return reqJson;


	}

	public String getKarzaBankAccountV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaBankAccountV2Request() :: Start");
		BankAccountRequest bankAccountRequest=new BankAccountRequest();
		BankAccountPayload payload=new BankAccountPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");
			payload.setIfsc(result.getIfsc()!=null?result.getIfsc():"");
			payload.setAccountNumber(result.getAccountNo()!=null?result.getAccountNo():"");

			bankAccountRequest.setHeader(header);
			bankAccountRequest.setPayload(payload);

			reqJson=om.writeValueAsString(bankAccountRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaBankAccountV2Request() :: End");
		return reqJson;


	}

	public String getKarzaWebsiteDomainV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaWebsiteDomainV2Request() :: Start");
		WebsiteDomainRequest websiteDomainRequest=new WebsiteDomainRequest();
		WebsiteDomainPayload payload=new WebsiteDomainPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));


			payload.setDomain(result.getDomain()!=null?result.getDomain():"");

			websiteDomainRequest.setHeader(header);
			websiteDomainRequest.setPayload(payload);

			reqJson=om.writeValueAsString(websiteDomainRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaWebsiteDomainV2Request() :: End");
		return reqJson;


	}

	public String getKarzaUanLookupV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaNregaV2Request() :: Start");
		UanLookupRequest uanLookupRequest=new UanLookupRequest();
		UanLookupPayload payload=new UanLookupPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");
			payload.setMobile(result.getTele_no1()!=null?result.getTele_no1():"");

			uanLookupRequest.setHeader(header);
			uanLookupRequest.setPayload(payload);

			reqJson=om.writeValueAsString(uanLookupRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaUANLookupV2Request() :: End");
		return reqJson;


	}

	public String getKarzaForm16QuarterlyV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaForm16QuarterlyV2Request() :: Start");
		Form16QuatRequest form16QuatRequest=new Form16QuatRequest();
		Form16QuatPayload payload=new Form16QuatPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");
			payload.setTan(result.getTan()!=null?result.getTan():"");
			payload.setPan(result.getIdno1()!=null?result.getIdno1():"");
			payload.setFiscal_year(result.getFiscal_year()!=null?result.getFiscal_year():"");

			form16QuatRequest.setHeader(header);
			form16QuatRequest.setPayload(payload);

			reqJson=om.writeValueAsString(form16QuatRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaForm16QuarterlyV2Request() :: End");
		return reqJson;
	}

	public String getPanValidationRequest(IbRequestMaster result) {
		logger.info(" inside getPanValidationRequest() :: Start");
		StringBuilder sb=new StringBuilder();
		try{
			sb.append("	{");
			sb.append("    \"header\": {");
			sb.append("        \"msgVersion\": \"1.0\",");
			sb.append("        \"appId\": \"PanAppid\",");
			sb.append("        \"correlationId\": \""+(result.getCorrelationid()+":"+result.getTransaction())+"\",");
			sb.append("        \"token\":\"120fb604-0642-46ae-949c-9a84cd21455e\"");
			sb.append("    },");
			sb.append("    \"payload\": {");

			sb.append("                \"panNo\": [");

			if(result.getIdno1()!=null ){
				sb.append("                    {");
				sb.append("                        \"pan\": \""+(result.getIdno1()!=null?result.getIdno1():"")+"\"");
				sb.append("                    }");
			}
			sb.append("        ]");
			sb.append("    }");
			sb.append("}");
		}catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getPanValidationRequest() :: END");
		return sb.toString();
	}

	public String getKarzaForm16AuthenticationV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaForm16AuthenticationV2Request() :: Start");
		Form16Request form16Request=new Form16Request();
		Form16Payload payload=new Form16Payload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setTan(result.getTan()!=null?result.getTan():"");
			payload.setPan(result.getIdno1()!=null?result.getIdno1():"");
			payload.setCert_no(result.getCert_no()!=null?result.getCert_no():"");
			payload.setAmount(result.getAmount()!=null?result.getAmount():"");
			payload.setFiscal_year(result.getFiscal_year()!=null?result.getFiscal_year():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");	

			form16Request.setHeader(header);
			form16Request.setPayload(payload);

			reqJson=om.writeValueAsString(form16Request);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaForm16AuthenticationV2Request() :: End");
		return reqJson;


	}

	public String getKarzaESICIdV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaESICIdV2Request() :: Start");
		ESICIdRequest esicIdRequest=new ESICIdRequest();
		ESICIdPayload payload=new ESICIdPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setEsic_id(result.getEsic_id()!=null?result.getEsic_id():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");	

			esicIdRequest.setHeader(header);
			esicIdRequest.setPayload(payload);

			reqJson=om.writeValueAsString(esicIdRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaESICIdV2Request() :: End");
		return reqJson;


	}
	public String getKarzaEmployerLookupRequest(IbRequestMaster result) {

		logger.info(" inside getKarzaEmployerLookupRequest() :: Start");
		EmolpyerLookupRequest request=new EmolpyerLookupRequest();
		EmployerLookupPayload payload=new EmployerLookupPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setUan(result.getUan()!=null?result.getUan():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			request.setHeader(header);
			request.setPayload(payload);

			reqJson=om.writeValueAsString(request);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaEmployerLookupRequest() :: End");
		return reqJson;

	}

	public String getKarzaElecV3Request(IbRequestMaster result) {
		logger.info(" inside getKarzaElecV3Request() :: Start");
		ElectricalRequest2 request=new ElectricalRequest2();
		ElectricalPayload2 payload=new ElectricalPayload2();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));


			payload.setConsumer_id(result.getConsumer_id()!=null?result.getConsumer_id():"");

			payload.setService_provider(result.getKarza_service_provider()!=null?result.getKarza_service_provider():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");

			request.setHeader(header);
			request.setPayload(payload);

			reqJson=om.writeValueAsString(request);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaElecV3Request() :: End");
		return reqJson;
	}
	
	
	public String getKarzaTeleV3Request(IbRequestMaster result) {

        logger.info(" inside getKarzaTeleV3Request() :: Start");
        TelephoneRequest2 telephoneRequest2=new TelephoneRequest2();
        TelephonePayload2 payload=new TelephonePayload2();
        com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
        ObjectMapper om=new ObjectMapper();
        String reqJson="";
        try {

            header.setAppId(resProp.getString("com.qc.ibs.appid"));
            header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
            header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
            header.setToken(resProp.getString("com.qc.ibs.token"));
            payload.setTel_no(result.getTele_no1()!=null?result.getTele_no1():"");
            payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");
            telephoneRequest2.setHeader(header);
            telephoneRequest2.setPayload(payload);

            reqJson=om.writeValueAsString(telephoneRequest2);

        } catch (Exception e) {
            logger.error(" error while make json Request :: "+e);
        }
        logger.info(" inside getKarzaCaMembershipAuthRequest() :: End");
        return reqJson;

   
    }
    public String getKarzaCaMembershipAuthRequest(IbRequestMaster result) {

        logger.info(" inside getKarzaCaMembershipAuthRequest() :: Start");
        CAMemberShipAuthRequest caMemberShipAuthRequest=new CAMemberShipAuthRequest();
        CAMemberShipAuthPayload payload=new CAMemberShipAuthPayload();
        com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
        ObjectMapper om=new ObjectMapper();
        String reqJson="";
        try {

            header.setAppId(resProp.getString("com.qc.ibs.appid"));
            header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
            header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
            header.setToken(resProp.getString("com.qc.ibs.token"));
            payload.setMembership_no(result.getMembership_no()!=null?result.getMembership_no():"");
            payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");
            caMemberShipAuthRequest.setHeader(header);
            caMemberShipAuthRequest.setPayload(payload);

            reqJson=om.writeValueAsString(caMemberShipAuthRequest);

        } catch (Exception e) {
            logger.error(" error while make json Request :: "+e);
        }
        logger.info(" inside getKarzaCaMembershipAuthRequest() :: End");
        return reqJson;


   
    }
	
	
	
	public String getKarzaIfscCheckRequest(IbRequestMaster result) {
		logger.info(" inside getKarzaIfscCheckRequest() :: Start");
		IFSCRequest request=new IFSCRequest();
		IFSCPayload payload=new IFSCPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setIfsc(result.getIfsc()!=null?result.getIfsc():"");

			request.setHeader(header);
			request.setPayload(payload);

			reqJson=om.writeValueAsString(request);
		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaIfscCheckRequest() :: End");
		return reqJson;
	}
	public String getKarzaPngV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaPngV2Request() :: Start");
		PngRequest pngRequest=new PngRequest();
		PngPayload payload=new PngPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {
			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
			payload.setConsumer_id(result.getConsumer_id()!=null?result.getConsumer_id():"");
			payload.setBp_no(result.getBp_no()!=null?result.getBp_no():"");
			payload.setService_provider(result.getKarza_service_provider()!=null?result.getKarza_service_provider():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");	
			pngRequest.setHeader(header);
			pngRequest.setPayload(payload);
			reqJson=om.writeValueAsString(pngRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaPngV2Request() :: End");
		return reqJson;


	}
	
	public String getKarzaAADHARV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaAADHARV2Request() :: Start");
		putGenderCodeBureau();
		AadharRequest aadharRequest=new AadharRequest();
		AadharPayload payload=new AadharPayload();
		AadharDemographics demographics=new AadharDemographics();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {
			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
			payload.setAadhaarId(result.getAadhar()!=null?result.getAadhar():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");	
			demographics.setEmail(result.getActual_email_addr()!=null?result.getActual_email_addr():"");
			demographics.setGender(result.getGender()!=null?genderCodeBureau.get(result.getGender()):"");
			demographics.setPhone(result.getTele_no1()!=null?result.getTele_no1():"");
			payload.setDemographics(demographics);
			aadharRequest.setHeader(header);
			aadharRequest.setPayload(payload);
			reqJson=om.writeValueAsString(aadharRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaAADHARV2Request() :: End");
		return reqJson;


	}

	public String getKarzaIcwaiMemberV2Request(IbRequestMaster result) {

		logger.info(" inside getKarzaIcwaiMemberV2Request() :: Start");
		ICWAIMembershipRequest icwaiMembershipRequest=new ICWAIMembershipRequest();
		ICWAIMembershipPayload payload=new ICWAIMembershipPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));

			payload.setMembership_no(result.getMembership_no()!=null?result.getMembership_no():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");	

			icwaiMembershipRequest.setHeader(header);
			icwaiMembershipRequest.setPayload(payload);

			reqJson=om.writeValueAsString(icwaiMembershipRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaIcwaiMemberV2Request() :: End");
		return reqJson;


	}
	public String getKarzaHSNRequest(IbRequestMaster result) {

		logger.info(" inside getKarzaHSNRequest() :: Start");
		HSNRequest hsnRequest=new HSNRequest();
		HSNPayload payload=new HSNPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
			payload.setUniqueid(result.getUniqueId()!=null?result.getUniqueId():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");
			payload.setHsCode(result.getHsCode()!=null?result.getHsCode():"");

			hsnRequest.setHeader(header);
			hsnRequest.setPayload(payload);

			reqJson=om.writeValueAsString(hsnRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaHSNRequest() :: End");
		return reqJson;


	}

	public String getKarzaPassportRequest(IbRequestMaster result) {

		logger.info(" inside getKarzaPassportRequest() :: Start");
		PassportRequest passportRequest=new PassportRequest();
		PassportPayload payload=new PassportPayload();
		com.qualtech.karza.api.request.Header header = new com.qualtech.karza.api.request.Header();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
			payload.setUniqueid(result.getUniqueId()!=null?result.getUniqueId():"");
			payload.setName(result.getF_name()!=null?result.getF_name():"");
			payload.setConsent(result.getAllowConsent()!=null?result.getAllowConsent():"");
			payload.setLastName(result.getL_name()!=null?result.getL_name():"");
			payload.setDob(dateFormatPassport(result.getDob())!=null?dateFormatPassport(result.getDob()):"");
			payload.setDoe(result.getDoe()!=null?result.getDoe():"");
			payload.setGender(genderCode.get(result.getGender())!=null?genderCode.get(result.getGender()):"");
			payload.setPassportNumber(result.getIdno1()!=null?result.getIdno1():"");
			payload.setType(result.getPassportType()!=null?result.getPassportType():"");
			payload.setCountry(result.getCountry()!=null?result.getCountry():"");

			passportRequest.setHeader(header);
			passportRequest.setPayload(payload);

			reqJson=om.writeValueAsString(passportRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getKarzaPassportRequest() :: End");
		return reqJson;


	}

	public String getFinbitRequest(IbRequestMaster result,List<FibitRequest> ibsAccountDetail) {

		logger.info(" inside getFinbitRequest() :: Start");
		FinbitApiRequest finbitapirequest=new FinbitApiRequest();
		FinbitHeader header=new FinbitHeader();
		FinbitRequestPayload payload=new FinbitRequestPayload();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
			List<AccountDetail> accountDetail=new ArrayList<AccountDetail>();
			for(FibitRequest reqAccountDetail :ibsAccountDetail) {
				AccountDetail account=new AccountDetail();
				account.setAccounttype(reqAccountDetail.getAccounttype()!=null?reqAccountDetail.getAccounttype():"");
				account.setBankname(reqAccountDetail.getBankname()!=null?reqAccountDetail.getBankname():"");
				account.setBankstatement(reqAccountDetail.getBankstatement()!=null?reqAccountDetail.getBankstatement():null);
				account.setEmailaddress(reqAccountDetail.getEmailaddress()!=null?reqAccountDetail.getEmailaddress():"");
				account.setPassword(reqAccountDetail.getPassword()!=null?reqAccountDetail.getPassword():"");
				accountDetail.add(account);
			}

			payload.setAccountDetail(accountDetail);
			finbitapirequest.setHeader(header);
			finbitapirequest.setPayload(payload);

			reqJson=om.writeValueAsString(finbitapirequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getFinbitRequest() :: End");
		return reqJson;
	}
	public String getMultiBureauRequest(IbRequestMaster result) {
		logger.info(" inside getMultiBureauRequest() :: Start");
		putStateName();
		putGenderCodeBureau();
		BureauRequest bureauRequest=new BureauRequest();
		com.qualtech.multibureau.api.request.Header header=new com.qualtech.multibureau.api.request.Header();
		BureauPayload payload=new BureauPayload();
		ObjectMapper om=new ObjectMapper();
		String reqJson="";
		try {

			header.setAppId(resProp.getString("com.qc.ibs.appid"));
			header.setCorrelationId(result.getCorrelationid()+":"+result.getTransaction());
			header.setMsgVersion(resProp.getString("com.qc.ibs.msgVersion"));
			header.setToken(resProp.getString("com.qc.ibs.token"));
			Calendar cal1=Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy HH:mm:ss");
			String requesttime = format.format(cal1.getTime());
			Random rnd = new Random();
			int appid = 10000000 + rnd.nextInt(90000000);

			payload.setApplication_id(appid+"");
			payload.setCust_id(appid+"");
			payload.setRequest_type("REQUEST");
			payload.setRequest_time(requesttime);
			/*payload.setApplication_id("82274786");
			payload.setCust_id("82274900");
			payload.setRequest_type("REQUEST");
			payload.setRequest_time("29042013 11:30:00");*/
			payload.setFirstName(result.getF_name()!=null?result.getF_name():"");
			payload.setLastName(result.getL_name()!=null?result.getL_name():"");
			payload.setGender(genderCodeBureau.get(result.getGender())!=null?genderCodeBureau.get(result.getGender()):"");
			payload.setMarital_status(result.getMaritalStatus()!=null?result.getMaritalStatus():"");

			// date Setting in multibureau format

			try {


				String dob=dateFormatCibil(result.getDob());
				int age=0;        
				Date currentDate=new Date();
				Date date =null;

				try
				{
					SimpleDateFormat formatter=new SimpleDateFormat("ddMMyyyy"); 
					date =(Date)formatter.parse(dob);     //birthDate is a String, in format dd-MM-yyyy
					long diff = currentDate.getTime() - date.getTime();
					long d=(1000l*60*60*24*365);
					long years = Math.round(diff / d);
					age=(int) years;

				}
				catch(Exception ec)
				{
				}
				Calendar cal = Calendar.getInstance();
				cal.setTime(currentDate);
				int currentyear = cal.get(Calendar.YEAR);
				int currentmonth = cal.get(Calendar.MONTH);
				int currentday = cal.get(Calendar.DAY_OF_MONTH);
				cal = Calendar.getInstance();
				cal.setTime(date);
				/*	int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);*/

				payload.setBirth_dt(dob);
				payload.setAge(age+"");
				payload.setAge_as_on_dt(currentday+""+currentmonth+""+currentyear);


			} catch (Exception e) {
			}
			List<AddressBureau> address=new ArrayList<AddressBureau>();

			AddressBureau addres=new AddressBureau();
			addres.setAddress_type(result.getAddress_type1()!=null?result.getAddress_type1():"");
			addres.setAddress_residence_code(result.getResidence_code1()!=null?result.getResidence_code1():"");
			addres.setAddressInfo(result.getAddress1()!=null?result.getAddress1():"");
			addres.setAddress_city(result.getCity()!=null?result.getCity():"");
			addres.setAddress_pin(result.getPin1()!=null?result.getPin1():"");
			addres.setAddress_state(stateName.get(result.getState())!=null?stateName.get(result.getState()):"");
			address.add(addres);
			payload.setAddress(address);
			List<PhoneBureau> phone=new ArrayList<PhoneBureau>();
			if(result.getTele_type1()!=null) {
				PhoneBureau phoneBureau1=new PhoneBureau();
				phoneBureau1.setPhone_type(result.getTele_type1()!=null?result.getTele_type1():"");
				phoneBureau1.setPhone_number(result.getTele_no1()!=null?result.getTele_no1():"");
				phone.add(phoneBureau1);
			}
			if(result.getTele_type2()!=null) {
				PhoneBureau phoneBureau2=new PhoneBureau();
				phoneBureau2.setPhone_type(result.getTele_type2()!=null?result.getTele_type2():"");
				phoneBureau2.setPhone_number(result.getTele_no2()!=null?result.getTele_no2():"");
				phone.add(phoneBureau2);
			}
			payload.setPhone(phone);
			payload.setEmail_id_1(result.getActual_email_addr()!=null?result.getActual_email_addr():"");
			payload.setEmail_id_2(result.getBeneEmail()!=null?result.getBeneEmail():"");
			IdTypes id=new IdTypes();
            id.setPan(result.getIdno1()!=null?result.getIdno1():"");
            id.setPassport(result.getIdno2()!=null?result.getIdno2():"");
            id.setDrivingLicence(result.getIdno4()!=null?result.getIdno4():"");
            id.setRationcard(result.getIdno5()!=null?result.getIdno5():"");
            //id.setUid(result.getIdno1()!=null?result.getIdno1():"");
            id.setVoter(result.getIdno3()!=null?result.getIdno3():"");
            payload.setId(id);
			payload.setPriority(result.getPriority()!=null?result.getPriority():"");
			payload.setProduct_type(result.getProductType()!=null?result.getProductType():"");
			payload.setLoan_Type(result.getLoanType()!=null?result.getLoanType():"");
			payload.setLoan_amount(result.getLoanAmount()!=null?result.getLoanAmount():"");
			payload.setJoint_ind(result.getJointInd()!=null?result.getJointInd():"");
			payload.setInquiry_submitted_by(result.getInquirySubmitted_by()!=null?result.getInquirySubmitted_by():"");
			payload.setSource_system_name(result.getSourceSystemName()!=null?result.getSourceSystemName():"");
			payload.setSource_system_version(result.getSourceSystemVersion()!=null?result.getSourceSystemVersion():"");
			payload.setSource_system_vender(result.getSourceSystemVender()!=null?result.getSourceSystemVender():"");
			payload.setSource_system_instance_id(result.getSourceSystem_instance_id()!=null?result.getSourceSystem_instance_id():"");
			payload.setLoan_purpose_desc(result.getLoanPurposeDesc()!=null?result.getLoanPurposeDesc():"");
			payload.setBranch_ifsccode(result.getBranchIfsccode()!=null?result.getBranchIfsccode():"");
			payload.setKendra(result.getKendra()!=null?result.getKendra():"");
			payload.setInquiry_stage(result.getInquiryStage()!=null?result.getInquiryStage():"");
			payload.setAuthrization_flag(result.getAuthrizationflag()!=null?result.getAuthrizationflag():"");
			payload.setAuthroized_by(result.getAuthroizedBy()!=null?result.getAuthroizedBy():"");
			payload.setIndividual_corporate_flag(result.getIndividualCorporate_flag()!=null?result.getIndividualCorporate_flag():"");
			payload.setConstitution(result.getConstitution()!=null?result.getConstitution():"");
			payload.setNo_of_dependents(result.getNoOfDependents()!=null?result.getNoOfDependents():"");
			payload.setAlias(result.getAlias()!=null?result.getAlias():"");
			payload.setAct_opening_dt(result.getActOpeningDt()!=null?result.getActOpeningDt():"");
			payload.setAccount_number_1(result.getAccountNumber1()!=null?result.getAccountNumber1():"");
			payload.setAccount_number_2(result.getAccountNumber2()!=null?result.getAccountNumber2():"");
			payload.setAccount_number_3(result.getAccountNumber3()!=null?result.getAccountNumber3():"");
			payload.setAccount_number_4(result.getAccountNumber4()!=null?result.getAccountNumber4():"");
			payload.setTanure(result.getTanure()!=null?result.getTanure():"");
			payload.setGroup_id(result.getGroupId()!=null?result.getGroupId():"");
			payload.setNumber_credit_cards(result.getNumberCreditCards()!=null?result.getNumberCreditCards():"");
			payload.setCredit_card_no(result.getCreditCardNo()!=null?result.getCreditCardNo():"");
			payload.setMonthly_income(result.getMonthlyIncome()!=null?result.getMonthlyIncome():"");
			payload.setSoa_employer_name_c(result.getSoaEmployerNameC()!=null?result.getSoaEmployerNameC():"");
			payload.setTime_with_employ(result.getTimeWithEmploy()!=null?result.getTimeWithEmploy():"");
			payload.setCompany_category(result.getCompanyCategory()!=null?result.getCompanyCategory():"");
			payload.setNature_of_business(result.getNatureOfBusiness()!=null?result.getNatureOfBusiness():"");
			payload.setAsset_cost(result.getAssetCost()!=null?result.getAssetCost():"");
			payload.setCollateral_1(result.getCollateral1()!=null?result.getCollateral1():"");
			payload.setCollateral_1_valuation_1(result.getCollateral1Valuation1()!=null?result.getCollateral1Valuation1():"");
			payload.setCollateral_1_valuation_2(result.getCollateral1Valuation2()!=null?result.getCollateral1Valuation2():"");
			payload.setCollateral_2_valuation_1(result.getCollateral2Valuation1()!=null?result.getCollateral2Valuation1():"");
			payload.setCollateral_2_valuation_2(result.getCollateral2Valuation2()!=null?result.getCollateral2Valuation2():"");
			payload.setCollateral_2(result.getCollateral2()!=null?result.getCollateral2():"");
			bureauRequest.setHeader(header);
			bureauRequest.setPayload(payload);
			reqJson=om.writeValueAsString(bureauRequest);

		} catch (Exception e) {
			logger.error(" error while make json Request :: "+e);
		}
		logger.info(" inside getMultiBureauRequest() :: End");
		return reqJson;
	}
	public  String pdfFailure(ErrorInfo error,String service,PropertyFile resProp) {

        String finalPathPdf="";
        try {
            String logoPath=null;
            String filePath=null;
            try{
                logoPath=resProp.getString("com.qc.ibs.logo");
                filePath=resProp.getString("com.qc.ibs.pdfPath");
            }catch(Exception e){

            }

            finalPathPdf=filePath+service+"failure"+UniqueId.getUniqueId()+".pdf";
            Document document = new Document(PageSize.A4,36,36,36,36);
            PdfWriter.getInstance(document, new FileOutputStream(finalPathPdf));
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            /*PdfWriter writer = */PdfWriter.getInstance(document, bStream);
            document.open();
            StringBuffer sb = new StringBuffer();

//            String newPath=null;
            sb.append("<html>");

            sb.append("<head>");
            sb.append("<title>");sb.append("</title>");
            sb.append("</head>");

            sb.append("<body>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='right' width='50%'>");
            sb.append("<img src='"+logoPath+"' width='100' align='left'>");
            //sb.append("<h1 align='right' style='display: inline; font-size: 20px; color: #2475a4;'>IBS</h1>");
            
            sb.append("</td>");
            sb.append("</tr>");
            
            sb.append("</table>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='left' width='50%'>");sb.append("</td>");
            sb.append("<td align='right' width='50%'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<table width='100%' cellspacing='0' cellpadding='0'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' width='50%'>");sb.append("<font size='3' face='arial' color='#2475a4'><b>IBS "+service+" FAILURE REPORT</b></font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>&nbsp;</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<table width='100%' bgcolor='#f2f2f2' cellpadding='5'>");
            sb.append("<tr>");
            sb.append("<td width='20%' align='center'>");sb.append("</td>");
            sb.append("<td width='80%' valign='center'>");
            sb.append("<table width='100%' cellpadding='5'>");
            sb.append("<tr>");
            sb.append("<td width='20%' align='right'>");sb.append("</td>");
            sb.append("<td width='80%' align='left'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td width='20%' align='right'>");sb.append("</td>");
            sb.append("<td width='80%' align='left'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("<table width='100%'>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='3' face='arial' color='#c00000'>FAILURE REASON</font>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='10'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td>");
            sb.append("<table border='0.1' width='100%' cellspacing='0' cellpadding='7'>");
            sb.append("<tr>");
            sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>CODE</font>");sb.append("</td>");
            sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>STATUS</font>");sb.append("</td>");
            sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>MESSAGE</font>");sb.append("</td>");
            sb.append("<td bgcolor='#33d7ff' align='center'>");sb.append("<font size='0.5' face='arial'>DESCRIPTION</font>");sb.append("</td>");
            
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+error.getCode()+"</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+error.getStatus()+"</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+error.getMessage()+"</font>");sb.append("</td>");
            sb.append("<td align='center'>");sb.append("<font size='0.5' face='arial'>"+error.getDescription()+"</font>");sb.append("</td>");
    
            sb.append("</tr>");
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("<tr>");
            sb.append("<td align='center' height='20'>");sb.append("</td>");
            sb.append("</tr>");
            sb.append("</table>");

            sb.append("</body>");

            sb.append("</html>");
            
            HTMLWorker worker = new HTMLWorker(document);
            worker.parse(new StringReader(sb.toString()));
            document.close();

        } catch (Exception e) {
        	logger.error(" error while create failure pdf :: "+e);
            e.printStackTrace();
        }
        return finalPathPdf;
    }

}
