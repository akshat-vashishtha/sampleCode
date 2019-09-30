package com.qualtech.icici.api.utils;

import org.apache.log4j.Logger;

import com.qualtech.icici.api.request.PremCalc;
import com.qualtech.icici.api.request.Product;
import com.qualtech.icici.api.request.ProductDetails;



public class IciciSoapRequestXml {
	private static Logger logger = Logger.getLogger(IciciSoapRequestXml.class);

	public String getRequestXml(PremCalc premCalc){
		logger.info("IciciSoapRequestXml || getRequestXml|| START");
		StringBuilder sb=new StringBuilder();
		
				try{
					sb.append("<soap:Envelope ");
					sb.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"> ");
					
					sb.append("<soap:Header>");
						sb.append("<AuthSoapHd xmlns=\"http://tempuri.org/\">");
						sb.append("</AuthSoapHd>");
					sb.append("</soap:Header>");   
					
					sb.append("<soap:Body>");
					
						sb.append("<GenerateEBIDigital xmlns=\"http://tempuri.org/\">");
						sb.append("<strInputXML>");
						sb.append("<![CDATA[");
						         
      				        sb.append("<EBIRequest>");
								
					        	if(premCalc!=null) {
								sb.append("<DateOfBirth>");
								sb.append(premCalc.getDateOfBirth()!=null?premCalc.getDateOfBirth():"");
								sb.append("</DateOfBirth>");
								sb.append("<FirstName>");
								sb.append(premCalc.getFirstName()!=null?premCalc.getFirstName():"");
								sb.append("</FirstName>");
								sb.append("<Gender>");
								sb.append(premCalc.getGender()!=null?premCalc.getGender():"");
								sb.append("</Gender>");
								sb.append("<LastName>");
								sb.append(premCalc.getLastName()!=null?premCalc.getLastName():"");
								sb.append("</LastName>");
								sb.append("<MaritalStatus>");
								sb.append(premCalc.getMaritalStatus()!=null?premCalc.getMaritalStatus():"");
								sb.append("</MaritalStatus>");
									
								ProductDetails productDetails = premCalc.getProductDetails();
								if(productDetails!=null) {
								  sb.append("<ProductDetails>");
								  Product product = productDetails.getProduct();
								 
								  if(product!=null){
									sb.append("<Product>");
									
									if(product.getAnnualPremium()!=null && !product.getAnnualPremium().equals("")){
										sb.append("<AnnualPremium>"+product.getAnnualPremium()+"</AnnualPremium>");
									}else{
										sb.append("<AnnualPremium></AnnualPremium>");
									}
									if(product.getBenefitOption()!=null && !product.getBenefitOption().equals("")){
										sb.append("<BenefitOption>"+product.getBenefitOption()+"</BenefitOption>");
									}else{
										sb.append("<BenefitOption></BenefitOption>");
									}
									if(product.getCoverageOption()!=null && !product.getCoverageOption().equals("")){
										sb.append("<CoverageOption>"+product.getCoverageOption()+"</CoverageOption>");
									}else{
										sb.append("<CoverageOption></CoverageOption>");
									}
									if(product.getDeathBenefit()!=null && ! product.getDeathBenefit().equals("")){
										sb.append("<DeathBenefit>"+product.getDeathBenefit()+"</DeathBenefit>");
									}else{
										sb.append("<DeathBenefit></DeathBenefit>");
									}
									if(product.getIpAddress()!=null && !product.getIpAddress().equals("")){
										sb.append("<IPAddress>"+product.getIpAddress()+"</IPAddress>");
									}else{
										sb.append("<IPAddress></IPAddress>");
									}
									if(product.getLoanAmount()!=null && !product.getLoanAmount().equals("")){
										sb.append("<LoanAmount>"+product.getLoanAmount()+"</LoanAmount>");
									}else{
										sb.append("<LoanAmount></LoanAmount>");
									}
									if(product.getLoanTenure()!=null && !product.getLoanTenure().equals("")){
										sb.append("<LoanTenure>"+product.getLoanTenure()+"</LoanTenure>");
									}else{
										sb.append("<LoanTenure></LoanTenure>");
									}
									if(product.getMasterCode()!=null && !product.getMasterCode().equals("")){
										sb.append("<MasterCode>"+product.getMasterCode()+"</MasterCode>");
									}else{
										sb.append("<MasterCode></MasterCode>");
									}
									if(product.getModalPremium()!=null && !product.getModalPremium().equals("")){
										sb.append("<ModalPremium>"+product.getModalPremium()+"</ModalPremium>");
									}else{
										sb.append("<ModalPremium></ModalPremium>");
									}
									if(product.getModeOfPayment()!=null && !product.getModeOfPayment().equals("")){
										sb.append("<ModeOfPayment>"+product.getModeOfPayment()+"</ModeOfPayment>");
									}else{
										sb.append("<ModeOfPayment></ModeOfPayment>");
									}
									if(product.getPremiumPaymentOption()!=null && !product.getPremiumPaymentOption().equals("")){
										sb.append("<PremiumPaymentOption>"+product.getPremiumPaymentOption()+"</PremiumPaymentOption>");
									}else{
										sb.append("<PremiumPaymentOption></PremiumPaymentOption>");
									}
									if(product.getPremiumPaymentTerm()!=null  && !product.getPremiumPaymentTerm().equals("")){
										sb.append("<PremiumPaymentTerm>"+product.getPremiumPaymentTerm()+"</PremiumPaymentTerm>");
									}else{
										sb.append("<PremiumPaymentTerm></PremiumPaymentTerm>");
									}
									if(product.getProductCode()!=null && !product.getProductCode().equals("")){
										sb.append("<ProductCode>"+product.getProductCode()+"</ProductCode>");
									}else{
										sb.append("<ProductCode></ProductCode>");
									}
									if(product.getProductName()!=null && !product.getProductName().equals("")){
										sb.append("<ProductName>"+product.getProductName()+"</ProductName>");
									}else{
										sb.append("<ProductName></ProductName>");
									}
									if(product.getProductType()!=null && !product.getProductType().equals("")){
										sb.append("<ProductType>"+product.getProductType()+"</ProductType>");
									}else{
										sb.append("<ProductType></ProductType>");
									}
									if(product.getTerm()!=null && !product.getTerm().equals("")){
										sb.append("<Term>"+product.getTerm()+"</Term>");
									}else{
										sb.append("<Term></Term>");
									}
								sb.append("</Product>");
							}
				         }
						sb.append("</ProductDetails>");
						}
					  sb.append("</EBIRequest>");
					sb.append("]]>");
					sb.append("</strInputXML>");
					sb.append("</GenerateEBIDigital>");
				  sb.append("</soap:Body>");
				sb.append("</soap:Envelope>");
			}catch(Exception ee){
				logger.info("IciciSoapRequestXml || getRequestXml|| Exception :: "+ee.getMessage());
			}
			
		logger.info("IciciSoapRequestXml || getRequestXml|| SOAP XML :: "+sb.toString());
		logger.info("IciciSoapRequestXml || getRequestXml|| END");
		return sb.toString();
	}
}
