package com.qualtech.kotak.api.utils;

import org.apache.log4j.Logger;

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

public class KotakSoapRequestXml {
	private static Logger logger = Logger.getLogger(KotakSoapRequestXml.class);
	public static String samplekotakRequest(){
		String msgId="SBFC_124567811";
		//String xml="<soap:Envelope xmlns:pay=\"http://www.kotak.com/schemas/CMS_Generic/Payment_Request.xsd\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"> <soap:Header/> <soap:Body> <pay:Payment> <pay:RequestHeader> <pay:MessageId>171004081257000_3107</pay:MessageId> <pay:MsgSource>MUTUALIND</pay:MsgSource> <pay:ClientCode>TEMPTEST1</pay:ClientCode> <pay:BatchRefNmbr>171004081257000_3106</pay:BatchRefNmbr> <pay:HeaderChecksum/> <pay:ReqRF1/> <pay:ReqRF2/> <pay:ReqRF3/> <pay:ReqRF4/> <pay:ReqRF5/> </pay:RequestHeader> <pay:InstrumentList> <pay:instrument> <pay:InstRefNo>171004081257000_3106</pay:InstRefNo> <pay:CompanyId/> <pay:CompBatchId/> <pay:ConfidentialInd/> <pay:MyProdCode>NETPAY</pay:MyProdCode> <pay:CompTransNo/> <pay:PayMode/> <pay:TxnAmnt>99.5</pay:TxnAmnt> <pay:AccountNo>09582650000173</pay:AccountNo> <pay:DrRefNmbr/> <pay:DrDesc/> <pay:PaymentDt>2017-10-04</pay:PaymentDt> <pay:BankCdInd/> <pay:BeneBnkCd/> <pay:RecBrCd>BOFA0BG3978</pay:RecBrCd> <pay:BeneAcctNo>1234569874</pay:BeneAcctNo> <pay:BeneName>INDIA TEST TEST</pay:BeneName> <pay:BeneCode/> <pay:BeneEmail/> <pay:BeneFax/> <pay:BeneMb/> <pay:BeneAddr1>IND</pay:BeneAddr1> <pay:BeneAddr2/> <pay:BeneAddr3/> <pay:BeneAddr4/> <pay:BeneAddr5/> <pay:city>IND</pay:city> <pay:zip>0</pay:zip> <pay:Country>INDIA</pay:Country> <pay:State/> <pay:TelephoneNo/> <pay:BeneId/> <pay:BeneTaxId/> <pay:AuthPerson/> <pay:AuthPersonId/> <pay:DeliveryMode/> <pay:PayoutLoc/> <pay:PickupBr/> <pay:PaymentRef/> <pay:ChgBorneBy/> <pay:InstDt>2017-10-04</pay:InstDt> <pay:MICRNo/> <pay:CreditRefNo/> <pay:PaymentDtl/> <pay:PaymentDtl1>LONDON</pay:PaymentDtl1> <pay:PaymentDtl2>UNITED KINGDOM</pay:PaymentDtl2> <pay:PaymentDtl3/> <pay:MailToAddr1/> <pay:MailToAddr2/> <pay:MailToAddr3/> <pay:MailToAddr4/> <pay:MailTo/> <pay:ExchDoc/> <pay:InstChecksum/> <pay:InstRF1/> <pay:InstRF2/> <pay:InstRF3/> <pay:InstRF4/> <pay:InstRF5/> <pay:InstRF6/> <pay:InstRF7/> <pay:InstRF8/> <pay:InstRF9/> <pay:InstRF10/> <pay:InstRF11/> <pay:InstRF12/> <pay:InstRF13/> <pay:InstRF14/> <pay:InstRF15/> <pay:EnrichmentSet> <pay:Enrichment>TEST CLIENT~SAVING~TEST~09582650000173~FAMILY_MAINTENANCE~1234569874</pay:Enrichment> </pay:EnrichmentSet> </pay:instrument> </pay:InstrumentList> </pay:Payment> </soap:Body> </soap:Envelope>";
		String xml="<soap:Envelope xmlns:pay=\"http://www.kotak.com/schemas/CMS_Generic/Payment_Request.xsd\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"> <soap:Header/> <soap:Body> <pay:Payment> <pay:RequestHeader> <pay:MessageId>"+msgId+"</pay:MessageId> <pay:MsgSource>SBFC</pay:MsgSource> <pay:ClientCode>TEMPTEST1</pay:ClientCode> <pay:BatchRefNmbr>171004081257000_3106</pay:BatchRefNmbr> <pay:HeaderChecksum/> <pay:ReqRF1/> <pay:ReqRF2/> <pay:ReqRF3/> <pay:ReqRF4/> <pay:ReqRF5/> </pay:RequestHeader> <pay:InstrumentList> <pay:instrument> <pay:InstRefNo>"+msgId+"</pay:InstRefNo> <pay:CompanyId/> <pay:CompBatchId/> <pay:ConfidentialInd/> <pay:MyProdCode>CMSPAY</pay:MyProdCode> <pay:CompTransNo/> <pay:PayMode/> <pay:TxnAmnt>99.5</pay:TxnAmnt> <pay:AccountNo>09582650000173</pay:AccountNo> <pay:DrRefNmbr/> <pay:DrDesc/> <pay:PaymentDt>2018-03-12</pay:PaymentDt> <pay:BankCdInd/> <pay:BeneBnkCd/> <pay:RecBrCd>BOFA0BG3978</pay:RecBrCd> <pay:BeneAcctNo>1234569874</pay:BeneAcctNo> <pay:BeneName>INDIA TEST TEST</pay:BeneName> <pay:BeneCode/> <pay:BeneEmail/> <pay:BeneFax/> <pay:BeneMb/> <pay:BeneAddr1>IND</pay:BeneAddr1> <pay:BeneAddr2/> <pay:BeneAddr3/> <pay:BeneAddr4/> <pay:BeneAddr5/> <pay:city>IND</pay:city> <pay:zip>0</pay:zip> <pay:Country>INDIA</pay:Country> <pay:State/> <pay:TelephoneNo/> <pay:BeneId/> <pay:BeneTaxId/> <pay:AuthPerson/> <pay:AuthPersonId/> <pay:DeliveryMode/> <pay:PayoutLoc/> <pay:PickupBr/> <pay:PaymentRef/> <pay:ChgBorneBy/> <pay:InstDt>2018-03-12</pay:InstDt> <pay:MICRNo/> <pay:CreditRefNo/> <pay:PaymentDtl/> <pay:PaymentDtl1>LONDON</pay:PaymentDtl1> <pay:PaymentDtl2>UNITED KINGDOM</pay:PaymentDtl2> <pay:PaymentDtl3/> <pay:MailToAddr1/> <pay:MailToAddr2/> <pay:MailToAddr3/> <pay:MailToAddr4/> <pay:MailTo/> <pay:ExchDoc/> <pay:InstChecksum/> <pay:InstRF1/> <pay:InstRF2/> <pay:InstRF3/> <pay:InstRF4/> <pay:InstRF5/> <pay:InstRF6/> <pay:InstRF7/> <pay:InstRF8/> <pay:InstRF9/> <pay:InstRF10/> <pay:InstRF11/> <pay:InstRF12/> <pay:InstRF13/> <pay:InstRF14/> <pay:InstRF15/> <pay:EnrichmentSet> <pay:Enrichment>TEST CLIENT~SAVING~TEST~09582650000173~FAMILY_MAINTENANCE~1234569874</pay:Enrichment> </pay:EnrichmentSet> </pay:instrument> </pay:InstrumentList> </pay:Payment> </soap:Body> </soap:Envelope>";
		return xml;
	}
	
	public static String sampleReversalRequest(){
		String reqId="SBFC_10001";
		String requestDate="2018-03-14";
		String msgSrc="SBFC";
		String xml="<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:rev=\"http://www.kotak.com/schemas/CMS_Generic/Reversal_Request.xsd\"> <soap:Header/> <soap:Body> <rev:Reversal> <rev:Header> <rev:Req_Id>"+reqId+"</rev:Req_Id> <rev:Msg_Src>"+msgSrc+"</rev:Msg_Src> <rev:Client_Code>TEMPTEST1</rev:Client_Code> <rev:Date_Post>"+requestDate+"</rev:Date_Post> </rev:Header> <rev:Details> <!--Zero or more repetitions:--> <rev:Msg_Id>"+reqId+"</rev:Msg_Id> </rev:Details> </rev:Reversal> </soap:Body> </soap:Envelope>";
		return xml;
	}
	
	public String getRequestXml(KotakRequest kotakRequest){
		logger.info("KotakSoapRequestXml || getRequestXml|| START");
		StringBuilder sb=new StringBuilder();
		KotakPayload payload=kotakRequest.getPayload();
		if(payload!=null){
			KotakPayment payment=payload.getKotakPayment();
			if(payment!=null){
				KotakPayRequestHeader header=payment.getPayheader();
				try{
					sb.append("<soap:Envelope ");
					sb.append("xmlns:pay=\"http://www.kotak.com/schemas/CMS_Generic/Payment_Request.xsd\" xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">");
					sb.append("<soap:Header/>");
					    sb.append("<soap:Body>");
					    
					        sb.append("<pay:Payment>");
					            sb.append("<pay:RequestHeader>");
									if(header!=null){
										sb.append("<pay:MessageId>"+header.getMessageId()+"</pay:MessageId>");
										sb.append("<pay:MsgSource>"+header.getMsgSource()+"</pay:MsgSource>");
										sb.append("<pay:ClientCode>"+header.getClientCode()+"</pay:ClientCode>");
										sb.append("<pay:BatchRefNmbr>"+header.getBatchRefNmbr()+"</pay:BatchRefNmbr>");
										if(header.getHeaderChecksum()!=null){
											sb.append("<pay:HeaderChecksum>"+header.getHeaderChecksum()+"</pay:HeaderChecksum>");
										}else{
											sb.append("<pay:HeaderChecksum/>");
										}									
										if(header.getReqRF1()!=null && !header.getReqRF1().equals("")){
											sb.append("<pay:ReqRF1>"+header.getReqRF1()+"</pay:ReqRF1>");
										}else{
											sb.append("<pay:ReqRF1/>");
										}
										if(header.getReqRF2()!=null && !header.getReqRF2().equals("")){
											sb.append("<pay:ReqRF2>"+header.getReqRF2()+"</pay:ReqRF2>");
										}else{
											sb.append("<pay:ReqRF2/>");
										}
										if(header.getReqRF3()!=null && !header.getReqRF3().equals("")){
											sb.append("<pay:ReqRF3>"+header.getReqRF3()+"</pay:ReqRF3>");
										}else{
											sb.append("<pay:ReqRF3/>");
										}
										if(header.getReqRF4()!=null && !header.getReqRF4().equals("")){
											sb.append("<pay:ReqRF4>"+header.getReqRF4()+"</pay:ReqRF4>");
										}else{
											sb.append("<pay:ReqRF4/>");
										}
										if(header.getReqRF5()!=null && !header.getReqRF5().equals("")){
											sb.append("<pay:ReqRF5>"+header.getReqRF5()+"</pay:ReqRF5>");
										}else{
											sb.append("<pay:ReqRF5/>");
										}
									}
								
								sb.append("</pay:RequestHeader>");
							KotakPayInstrumentList instrumentList=payment.getInstrumentList();
							if(instrumentList!=null){
								 sb.append("<pay:InstrumentList>");
								KotakPayInstrument instrument=instrumentList.getInstrument();
									if(instrument!=null){
										sb.append("<pay:instrument>");
											sb.append("<pay:InstRefNo>"+instrument.getInstRefNo()+"</pay:InstRefNo>");
											if(instrument.getCompanyId()!=null && !instrument.getCompanyId().equals("")){
												sb.append("<pay:CompanyId>"+instrument.getCompanyId()+"</pay:CompanyId>");
											}else{
												sb.append("<pay:CompanyId/>");
											}
											if(instrument.getCompBatchId()!=null && !instrument.getCompBatchId().equals("")){
												sb.append("<pay:CompBatchId>"+instrument.getCompBatchId()+"</pay:CompBatchId>");
											}else{
												sb.append("<pay:CompBatchId/>");
											}
										
											if(instrument.getConfidentialInd()!=null && !instrument.getConfidentialInd().equals("")){
												sb.append("<pay:ConfidentialInd>"+instrument.getConfidentialInd()+"</pay:ConfidentialInd>");
											}else{
												sb.append("<pay:ConfidentialInd/>");
											}
										
											sb.append("<pay:MyProdCode>"+instrument.getMyProdCode()+"</pay:MyProdCode>");
											if(instrument.getCompTransNo()!=null && ! instrument.getCompTransNo().equals("")){
												sb.append("<pay:CompTransNo>"+instrument.getCompTransNo()+"</pay:CompTransNo>");
											}else{
												sb.append("<pay:CompTransNo/>");
											}
											
											if(instrument.getPayMode()!=null && !instrument.getPayMode().equals("")){
												sb.append("<pay:PayMode>"+instrument.getPayMode()+"</pay:PayMode>");
											}else{
												sb.append("<pay:PayMode/>");
											}
																						
											sb.append("<pay:TxnAmnt>"+instrument.getTxnAmnt()+"</pay:TxnAmnt>");
											sb.append("<pay:AccountNo>"+instrument.getAccountNo()+"</pay:AccountNo>");
											if(instrument.getDrRefNmbr()!=null && !instrument.getDrRefNmbr().equals("")){
												sb.append("<pay:DrRefNmbr>"+instrument.getDrRefNmbr()+"</pay:DrRefNmbr>");
											}else{
												sb.append("<pay:DrRefNmbr/>");
											}
											if(instrument.getDrDesc()!=null && !instrument.getDrDesc().equals("")){
												sb.append("<pay:DrDesc>"+instrument.getDrDesc()+"</pay:DrDesc>");
											}else{
												sb.append("<pay:DrDesc/>");
											}
											sb.append("<pay:PaymentDt>"+instrument.getPaymentDt()+"</pay:PaymentDt>");
											if(instrument.getBankCdInd()!=null && !instrument.getBankCdInd().equals("")){
												sb.append("<pay:BankCdInd>"+instrument.getBankCdInd()+"</pay:BankCdInd>");
											}else{
												sb.append("<pay:BankCdInd/>");
											}
											if(instrument.getBeneBnkCd()!=null && !instrument.getBeneBnkCd().equals("")){
												sb.append("<pay:BeneBnkCd>"+instrument.getBeneBnkCd()+"</pay:BeneBnkCd>");
											}else{
												sb.append("<pay:BeneBnkCd/>");
											}
											
									
											sb.append("<pay:RecBrCd>"+instrument.getRecBrCd()+"</pay:RecBrCd>");
											sb.append("<pay:BeneAcctNo>"+instrument.getBeneAcctNo()+"</pay:BeneAcctNo>");
											sb.append("<pay:BeneName>"+instrument.getBeneName()+"</pay:BeneName>");
											if(instrument.getBeneCode()!=null && !instrument.getBeneCode().equals("")){
												sb.append("<pay:BeneCode>"+instrument.getBeneCode()+"</pay:BeneCode>");
											}else{
												sb.append("<pay:BeneCode/>");
											}
											if(instrument.getBeneEmail()!=null && !instrument.getBeneEmail().equals("")){
												sb.append("<pay:BeneEmail>"+instrument.getBeneEmail()+"</pay:BeneEmail>");
											}else{
												sb.append("<pay:BeneEmail/>");
											}
											if(instrument.getBeneFax()!=null  && !instrument.getBeneFax().equals("")){
												sb.append("<pay:BeneFax>"+instrument.getBeneFax()+"</pay:BeneFax>");
											}else{
												sb.append("<pay:BeneFax/>");
											}
											if(instrument.getBeneMb()!=null && !instrument.getBeneMb().equals("")){
												sb.append("<pay:BeneMb>"+instrument.getBeneMb()+"</pay:BeneMb>");
											}else{
												sb.append("<pay:BeneMb/>");
											}
											sb.append("<pay:BeneAddr1>"+instrument.getBeneAddr1()+"</pay:BeneAddr1>");
											if(instrument.getBeneAddr2()!=null && !instrument.getBeneAddr2().equals("")){
												sb.append("<pay:BeneAddr2>"+instrument.getBeneAddr2()+"</pay:BeneAddr2>");
											}else{
												sb.append("<pay:BeneAddr2/>");
											}
											if(instrument.getBeneAddr3()!=null && !instrument.getBeneAddr3().equals("")){
												sb.append("<pay:BeneAddr3>"+instrument.getBeneAddr3()+"</pay:BeneAddr3>");
											}else{
												sb.append("<pay:BeneAddr3/>");
											}
											
										
											if(instrument.getBeneAddr4()!=null && !instrument.getBeneAddr4().equals("")){
												sb.append("<pay:BeneAddr4>"+instrument.getBeneAddr4()+"</pay:BeneAddr4>");
											}else{
												sb.append("<pay:BeneAddr4/>");
											}
											if(instrument.getBeneAddr5()!=null && !instrument.getBeneAddr5().equals("")){
												sb.append("<pay:BeneAddr5>"+instrument.getBeneAddr5()+"</pay:BeneAddr5>");
											}else{
												sb.append("<pay:BeneAddr5/>");
											}
											sb.append("<pay:city>"+instrument.getCity()+"</pay:city>");
											sb.append("<pay:zip>"+instrument.getZip()+"</pay:zip>");
											sb.append("<pay:Country>"+instrument.getCountry()+"</pay:Country>");
											if(instrument.getState()!=null && !instrument.getState().equals("")){
												sb.append("<pay:State>"+instrument.getState()+"</pay:State>");
											}else{
												sb.append("<pay:State/>");
											}
											if(instrument.getTelephoneNo()!=null && !instrument.getTelephoneNo().equals("")){
												sb.append("<pay:TelephoneNo>"+instrument.getTelephoneNo()+"</pay:TelephoneNo>");
											}else{
												sb.append("<pay:TelephoneNo/>");
											}											
											
											if(instrument.getBeneId()!=null && !instrument.getBeneId().equals("")){
												sb.append("<pay:BeneId>"+instrument.getBeneId()+"</pay:BeneId>");
											}else{
												sb.append("<pay:BeneId/>");
											}
											if(instrument.getBeneTaxId()!=null && !instrument.getBeneTaxId().equals("")){
												sb.append("<pay:BeneTaxId>"+instrument.getBeneTaxId()+"</pay:BeneTaxId>");
											}else{
												sb.append("<pay:BeneTaxId/>");
											}
											
											if(instrument.getAuthPerson()!=null && !instrument.getAuthPerson().equals("")){
												sb.append("<pay:AuthPerson>"+instrument.getAuthPerson()+"</pay:AuthPerson>");
											}else{
												sb.append("<pay:AuthPerson/>");
											}
											
											if(instrument.getAuthPersonId()!=null && !instrument.getAuthPersonId().equals("")){
												sb.append("<pay:AuthPersonId>"+instrument.getAuthPersonId()+"</pay:AuthPersonId>");
											}else{
												sb.append("<pay:AuthPersonId/>");
											}
											
											if(instrument.getDeliveryMode()!=null && !instrument.getDeliveryMode().equals("")){
												sb.append("<pay:DeliveryMode>"+instrument.getDeliveryMode()+"</pay:DeliveryMode>");
											}else{
												sb.append("<pay:DeliveryMode/>");
											}
											
											if(instrument.getPayoutLoc()!=null && !instrument.getPayoutLoc().equals("")){
												sb.append("<pay:PayoutLoc>"+instrument.getPayoutLoc()+"</pay:PayoutLoc>");
											}else{
												sb.append("<pay:PayoutLoc/>");
											}
											
											if(instrument.getPickupBr()!=null && !instrument.getPickupBr().equals("")){
												sb.append("<pay:PickupBr>"+instrument.getPickupBr()+"</pay:PickupBr>");
											}else{
												sb.append("<pay:PickupBr/>");
											}
											
											if(instrument.getPaymentRef()!=null && !instrument.getPaymentRef().equals("")){
												sb.append("<pay:PaymentRef>"+instrument.getPaymentRef()+"</pay:PaymentRef>");
											}else{
												sb.append("<pay:PaymentRef/>");
											}
											
											if(instrument.getChgBorneBy()!=null && !instrument.getChgBorneBy().equals("")){
												sb.append("<pay:ChgBorneBy>"+instrument.getChgBorneBy()+"</pay:ChgBorneBy>");
											}else{
												sb.append("<pay:ChgBorneBy/>");
											}
											
											sb.append("<pay:InstDt>"+instrument.getInstDt()+"</pay:InstDt>");
											if(instrument.getMicrNo()!=null && !instrument.getMicrNo().equals("")){
												sb.append("<pay:MICRNo>"+instrument.getMicrNo()+"</pay:MICRNo>");
											}else{
												sb.append("<pay:MICRNo/>");
											}
											
											if(instrument.getCreditRefNo()!=null && !instrument.getCreditRefNo().equals("")){
												sb.append("<pay:CreditRefNo>"+instrument.getCreditRefNo()+"</pay:CreditRefNo>");
											}else{
												sb.append("<pay:CreditRefNo/>");
											}
											
											if(instrument.getPaymentDtl()!=null && !instrument.getPaymentDtl().equals("")){
												sb.append("<pay:PaymentDtl>"+instrument.getPaymentDtl()+"</pay:PaymentDtl>");
											}else{
												sb.append("<pay:PaymentDtl/>");
											}
											sb.append("<pay:PaymentDtl1>"+instrument.getPaymentDtl1()+"</pay:PaymentDtl1>");
											sb.append("<pay:PaymentDtl2>"+instrument.getPaymentDtl2()+"</pay:PaymentDtl2>");
											if(instrument.getPaymentDtl3()!=null && !instrument.getPaymentDtl3().equals("")){
												sb.append("<pay:PaymentDtl3>"+instrument.getPaymentDtl3()+"</pay:PaymentDtl3>");
											}else{
												sb.append("<pay:PaymentDtl3/>");
											}
											
											if(instrument.getMailToAddr1()!=null && !instrument.getMailToAddr1().equals("")){
												sb.append("<pay:MailToAddr1>"+instrument.getMailToAddr1()+"</pay:MailToAddr1>");
											}else{
												sb.append("<pay:MailToAddr1/>");
											}
											
											if(instrument.getMailToAddr2()!=null && !instrument.getMailToAddr2().equals("")){
												sb.append("<pay:MailToAddr2>"+instrument.getMailToAddr2()+"</pay:MailToAddr2>");
											}else{
												sb.append("<pay:MailToAddr2/>");
											}
											
											if(instrument.getMailToAddr3()!=null  && !instrument.getMailToAddr3().equals("")){
												sb.append("<pay:MailToAddr3>"+instrument.getMailToAddr3()+"</pay:MailToAddr3>");
											}else{
												sb.append("<pay:MailToAddr3/>");
											}
											
											if(instrument.getMailToAddr4()!=null && !instrument.getMailToAddr4().equals("")){
												sb.append("<pay:MailToAddr4>"+instrument.getMailToAddr4()+"</pay:MailToAddr4>");
											}else{
												sb.append("<pay:MailToAddr4/>");
											}
											
											if(instrument.getMailTo()!=null && !instrument.getMailTo().equals("")){
												sb.append("<pay:MailTo>"+instrument.getMailTo()+"</pay:MailTo>");
											}else{
												sb.append("<pay:MailTo/>");
											}
											
											if(instrument.getExchDoc()!=null && !instrument.getExchDoc().equals("")){
												sb.append("<pay:ExchDoc>"+instrument.getExchDoc()+"</pay:ExchDoc>");
											}else{
												sb.append("<pay:ExchDoc/>");
											}
											
											if(instrument.getInstChecksum()!=null && !instrument.getInstChecksum().equals("")){
												sb.append("<pay:InstChecksum>"+instrument.getInstChecksum()+"</pay:InstChecksum>");
											}else{
												sb.append("<pay:InstChecksum/>");
											}
											
											if(instrument.getInstRF1()!=null && !instrument.getInstRF1().equals("")){
												sb.append("<pay:InstRF1>"+instrument.getInstRF1()+"</pay:InstRF1>");
											}else{
												sb.append("<pay:InstRF1/>");
											}
											
											if(instrument.getInstRF2()!=null && !instrument.getInstRF2().equals("")){
												sb.append("<pay:InstRF2>"+instrument.getInstRF2()+"</pay:InstRF2>");
											}else{
												sb.append("<pay:InstRF2/>");
											}
											if(instrument.getInstRF3()!=null && !instrument.getInstRF3().equals("")){
												sb.append("<pay:InstRF3>"+instrument.getInstRF3()+"</pay:InstRF3>");
											}else{
												sb.append("<pay:InstRF3/>");
											}
											
											if(instrument.getInstRF4()!=null && !instrument.getInstRF4().equals("")){
												sb.append("<pay:InstRF4>"+instrument.getInstRF4()+"</pay:InstRF4>");
											}else{
												sb.append("<pay:InstRF4/>");
											}
											if(instrument.getInstRF5()!=null && !instrument.getInstRF5().equals("")){
												sb.append("<pay:InstRF5>"+instrument.getInstRF5()+"</pay:InstRF5>");
											}else{
												sb.append("<pay:InstRF5/>");
											}
											if(instrument.getInstRF6()!=null && !instrument.getInstRF6().equals("")){
												sb.append("<pay:InstRF6>"+instrument.getInstRF6()+"</pay:InstRF6>");
											}else{
												sb.append("<pay:InstRF6/>");
											}
											if(instrument.getInstRF7()!=null && !instrument.getInstRF7().equals("")){
												sb.append("<pay:InstRF7>"+instrument.getInstRF7()+"</pay:InstRF7>");
											}else{
												sb.append("<pay:InstRF7/>");
											}
											if(instrument.getInstRF8()!=null && !instrument.getInstRF8().equals("")){
												sb.append("<pay:InstRF8>"+instrument.getInstRF8()+"</pay:InstRF8>");
											}else{
												sb.append("<pay:InstRF8/>");
											}
											if(instrument.getInstRF9()!=null && !instrument.getInstRF9().equals("")){
												sb.append("<pay:InstRF9>"+instrument.getInstRF9()+"</pay:InstRF9>");
											}else{
												sb.append("<pay:InstRF9/>");
											}
											if(instrument.getInstRF10()!=null && !instrument.getInstRF10().equals("")){
												sb.append("<pay:InstRF10>"+instrument.getInstRF10()+"</pay:InstRF10>");
											}else{
												sb.append("<pay:InstRF10/>");
											}
											if(instrument.getInstRF11()!=null && !instrument.getInstRF11().equals("")){
												sb.append("<pay:InstRF11>"+instrument.getInstRF11()+"</pay:InstRF11>");
											}else{
												sb.append("<pay:InstRF11/>");
											}
											if(instrument.getInstRF12()!=null && !instrument.getInstRF12().equals("")){
												sb.append("<pay:InstRF12>"+instrument.getInstRF12()+"</pay:InstRF12>");
											}else{
												sb.append("<pay:InstRF12/>");
											}
											if(instrument.getInstRF13()!=null && !instrument.getInstRF13().equals("")){
												sb.append("<pay:InstRF13>"+instrument.getInstRF13()+"</pay:InstRF13>");
											}else{
												sb.append("<pay:InstRF13/>");
											}
											if(instrument.getInstRF14()!=null && !instrument.getInstRF14().equals("")){
												sb.append("<pay:InstRF14>"+instrument.getInstRF14()+"</pay:InstRF14>");
											}else{
												sb.append("<pay:InstRF14/>");
											}
											if(instrument.getInstRF15()!=null && !instrument.getInstRF15().equals("")){
												sb.append("<pay:InstRF15>"+instrument.getInstRF15()+"</pay:InstRF15>");
											}else{
												sb.append("<pay:InstRF15/>");
											}
											KotakPayEnrichmentSet enrichmentSet=instrument.getEnrichmentSet();
											if(enrichmentSet!=null)
											{
												sb.append("<pay:EnrichmentSet>");
												sb.append("<pay:Enrichment>"+enrichmentSet.getEnrichment()+"</pay:Enrichment>");
											sb.append("</pay:EnrichmentSet>");	
											}
											else
											{
												sb.append("<pay:EnrichmentSet>");
												sb.append("<pay:Enrichment>");
												sb.append(instrument.getAccountNo()).append("-").append(instrument.getBeneAcctNo()).append("-").append(instrument.getBeneName());
												sb.append("</pay:Enrichment>");
											    sb.append("</pay:EnrichmentSet>");	
											}
											
										sb.append("</pay:instrument>");
									}
											
							
							}
							sb.append("</pay:InstrumentList>");
						sb.append("</pay:Payment>");
						sb.append("</soap:Body>");
					sb.append("</soap:Envelope>");
				}
				catch(Exception ee){
					logger.info("KotakSoapRequestXml || getRequestXml|| Exception :: "+ee.getMessage());
				}
			}
			
		}
		logger.info("KotakSoapRequestXml || getRequestXml|| SOAP XML :: "+sb.toString());
		logger.info("KotakSoapRequestXml || getRequestXml|| END");
		return sb.toString();
	}
	
	
	
	public String getReversalrequest(KotakRequestReversal kotakRequest){
		
		StringBuilder sb=new StringBuilder();
		try{
			
			KotakReversalPayload payload=kotakRequest.getPayload();
			if(payload!=null){
				KotakReversal reversal=payload.getReversal();
				if(reversal!=null){
					sb.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:rev=\"http://www.kotak.com/schemas/CMS_Generic/Reversal_Request.xsd\">");
					   sb.append("<soap:Header/>");
					   sb.append("<soap:Body>");
					      sb.append("<rev:Reversal>");
					      KotakReversalHeader header=reversal.getReversalHeader();
					      sb.append("<rev:Header>");
					      if(header!=null && !header.equals("")){
					    	   sb.append("<rev:Req_Id>"+header.getRequestId()+"</rev:Req_Id>");
					            sb.append("<rev:Msg_Src>"+header.getMsgSource()+"</rev:Msg_Src>");
					            sb.append("<rev:Client_Code>"+header.getClientCode()+"</rev:Client_Code>");
					            sb.append("<rev:Date_Post>"+header.getDatePost()+"</rev:Date_Post>");
					      		}
					         sb.append("</rev:Header>");
					         
					         sb.append("<rev:Details>");
					         KotakReversalDtls details=reversal.getDetails();
					         if(details!=null && !details.equals("")){
					        	 sb.append("<!--Zero or more repetitions:-->");
						            sb.append("<rev:Msg_Id>"+details.getMessageId()+"</rev:Msg_Id>");
					         }
					         sb.append("</rev:Details>");
					      sb.append("</rev:Reversal>");
					   sb.append("</soap:Body>");
					sb.append("</soap:Envelope>");
					
					
				}
			}
			
			
		}catch(Exception ee){
			
		}
		return sb.toString();
	}
	
	
	
}
