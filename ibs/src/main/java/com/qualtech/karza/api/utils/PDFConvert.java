package com.qualtech.karza.api.utils;

import com.qualtech.karza.api.common.dto.AadharRequest;
import com.qualtech.karza.api.common.dto.AddressRequest;
import com.qualtech.karza.api.common.dto.BankAccountRequest;
import com.qualtech.karza.api.common.dto.CAMemberShipAuthRequest;
import com.qualtech.karza.api.common.dto.CompSearchRequest;
import com.qualtech.karza.api.common.dto.CompanyLLPCINLookUpRequest;
import com.qualtech.karza.api.common.dto.CompanyLLPIdentificationRequest;
import com.qualtech.karza.api.common.dto.DlRequest;
import com.qualtech.karza.api.common.dto.DlRequest2;
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
import com.qualtech.karza.api.common.dto.KarzaDto;
import com.qualtech.karza.api.common.dto.LpgIdentificationRequest;
import com.qualtech.karza.api.common.dto.LpgRequest;
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
import com.qualtech.karza.api.common.dto.TelephoneRequest;
import com.qualtech.karza.api.common.dto.TelephoneRequest2;
import com.qualtech.karza.api.common.dto.UanLookupRequest;
import com.qualtech.karza.api.common.dto.UdyogAadharRequest;
import com.qualtech.karza.api.common.dto.VoterRequest;
import com.qualtech.karza.api.common.dto.WebsiteDomainRequest;
import com.qualtech.karza.api.response.AadharResponse;
import com.qualtech.karza.api.response.AddressResponse;
import com.qualtech.karza.api.response.BankAccountResponse;
import com.qualtech.karza.api.response.CAMemberShipAuthResponse;
import com.qualtech.karza.api.response.CompSearchResponse;
import com.qualtech.karza.api.response.CompanyLLPCINLookUpResponse;
import com.qualtech.karza.api.response.CompanyLLPIdentificationResponse;
import com.qualtech.karza.api.response.DlResponse;
import com.qualtech.karza.api.response.DlResponse2;
import com.qualtech.karza.api.response.EPFAuthOTPResponse;
import com.qualtech.karza.api.response.EPFAuthPassBookResponse;
import com.qualtech.karza.api.response.ESICIdResponse;
import com.qualtech.karza.api.response.ElectricityResponse;
import com.qualtech.karza.api.response.ElectricityResponse2;
import com.qualtech.karza.api.response.EmailAuthResponse;
import com.qualtech.karza.api.response.EmployerLookupResponse;
import com.qualtech.karza.api.response.FSSAILicenceResponse;
import com.qualtech.karza.api.response.Form16QuatResponse;
import com.qualtech.karza.api.response.Form16Response;
import com.qualtech.karza.api.response.GSTAuthenticationResponse;
import com.qualtech.karza.api.response.GstIdentificationResponse;
import com.qualtech.karza.api.response.HSNResponse;
import com.qualtech.karza.api.response.ICSIMemberShipResponse;
import com.qualtech.karza.api.response.ICWAIFirmAuthResponse;
import com.qualtech.karza.api.response.ICWAIMembershipResponse;
import com.qualtech.karza.api.response.IECResponse;
import com.qualtech.karza.api.response.IFSCResponse;
import com.qualtech.karza.api.response.ITRAuthResponse;
import com.qualtech.karza.api.response.LpgIdentificationResponse;
import com.qualtech.karza.api.response.LpgResponse;
import com.qualtech.karza.api.response.LpgResponse2;
import com.qualtech.karza.api.response.MCASignatureResponse;
import com.qualtech.karza.api.response.NREGAResponse;
import com.qualtech.karza.api.response.NameSimilarityResponse;
import com.qualtech.karza.api.response.PanResponse;
import com.qualtech.karza.api.response.PassportResponse;
import com.qualtech.karza.api.response.PngResponse;
import com.qualtech.karza.api.response.RCAuthResponse;
import com.qualtech.karza.api.response.RCSearchResponse;
import com.qualtech.karza.api.response.ShopEstablishmentResponse;
import com.qualtech.karza.api.response.TanResponse;
import com.qualtech.karza.api.response.TelephoneResponse;
import com.qualtech.karza.api.response.TelephoneResponse2;
import com.qualtech.karza.api.response.UanLookupResponse;
import com.qualtech.karza.api.response.UdyogAadharResponse;
import com.qualtech.karza.api.response.VoterResponse;
import com.qualtech.karza.api.response.WebsiteDomainResponse;

public interface PDFConvert {
	
	
	

	KarzaDto getPdfByteArrayLpgQC(LpgResponse2 lpgRes, LpgRequest2 lpgRequest);

	KarzaDto getPdfByteArrayPan(PanResponse panRes, PanRequest panRequest, String string);

	KarzaDto getPdfByteArrayPassport(PassportResponse passportRespons, PassportRequest passportRequest, String string);

	KarzaDto getPdfByteArrayItrAuth(ITRAuthResponse itrAuthResponse, ITRAuthRequest itrAuthRequest, String string);

	KarzaDto getPdfByteArrayIFSC(IFSCResponse ifscRespons, IFSCRequest ifscRequest, String string);

	KarzaDto getPdfByteArrayShopEstabilishmenth(
			ShopEstablishmentResponse shopEstablishmentResponse,
			ShopEstablishmentRequest shopEstablishmentRequest, String string);

	KarzaDto getPdfByteArrayNrega(NREGAResponse nregaResponse,NREGARequest nregaRequest, String string);

	KarzaDto getPdfByteArrayTan(TanResponse tanResponse, TanRequest tanRequest, String string);

	KarzaDto getPdfByteArrayDlQC(DlResponse2 dlRes, DlRequest2 dlRequest, String string);

	KarzaDto getPdfByteArrayIec(IECResponse iecResponse, IECRequest iecRequest, String string);

	KarzaDto getPdfByteArrayForm16Auth(Form16Response form16Response,Form16Request form16Request, String string);

	KarzaDto getPdfByteArrayForm16Ouat(Form16QuatResponse form16QuatResponse,Form16QuatRequest form16QuatRequest, String string);

	KarzaDto getPdfByteArrayPng(PngResponse pngResponse, PngRequest pngRequest, String string);

	KarzaDto getPdfByteArrayEsicId(ESICIdResponse esicIdResponse,ESICIdRequest esicIdRequest, String string);

	KarzaDto getPdfByteArrayTele(TelephoneResponse teleRes,
			TelephoneRequest teleRequest, String string);

	KarzaDto getPdfByteArrayElec(ElectricityResponse elecRes,
			ElectricalRequest elecRequest, String string);

	KarzaDto getPdfByteArrayFssaiLicence(FSSAILicenceResponse fssaiLicenceResponse,FSSAILicenceRequest fssaiLicenceRequest, String string);

	KarzaDto getPdfByteArrayRCAuth(RCAuthResponse rcAuthResponse,
			RCAuthRequest rcAuthRequest, String string);

	KarzaDto getPdfByteArrayElecQC(ElectricityResponse2 elecRes,ElectricalRequest2 elecRequest, String string);

	KarzaDto getPdfByteArrayTeleQC(TelephoneResponse2 teleRes,
			TelephoneRequest2 teleRequest, String string);

	KarzaDto getPdfByteArrayDl(DlResponse dlRes, DlRequest dlRequest, String string);

	KarzaDto getPdfByteArrayAadhar(AadharResponse aadharResponse,
			AadharRequest aadharRequest, String string);

	KarzaDto getPdfByteArrayCompanyLLPIdentification(
			CompanyLLPIdentificationResponse companyLLPIdentificationResponse,
			CompanyLLPIdentificationRequest companyLLPIdentificationRequest, String string);

	KarzaDto getPdfByteArrayHsn(HSNResponse hsnResponse, HSNRequest hsnRequest, String string);

	KarzaDto getPdfByteArrayUdyogAadhar(
			UdyogAadharResponse udyogAadharResponse,
			UdyogAadharRequest udyogAadharRequest, String string);

	KarzaDto getPdfByteArraygstAuth(
			GSTAuthenticationResponse gstAuthenticationResponse,
			GSTAuthenticationRequest authenticationRequest, String string);

	KarzaDto getPdfByteArrayEmailAuth(EmailAuthResponse emailAuthResponse,
			EmailAuthRequest emailAuthRequest, String string);

	KarzaDto getPdfByteArrayCaMemberShipAuth(
			CAMemberShipAuthResponse caMemberShipAuthResponse,
			CAMemberShipAuthRequest caMemberShipAuthRequest, String string);

	KarzaDto getPdfByteArrayICSIMemberShip(
			ICSIMemberShipResponse icsiMemberShipResponse,
			ICSIMemberShipRequest icsiMemberShipRequest, String string);

	KarzaDto getPdfByteArrayIcwaiFirmAuth(
			ICWAIFirmAuthResponse icwaiFirmAuthResponse,
			ICWAIFirmAuthRequest icwaiFirmAuthRequest, String string);

	KarzaDto getPdfByteArrayGSTIdentification(
			GstIdentificationResponse gstIdentificationResponse,
			GstIdentificationRequest gstIdentificationRequest, String string);

	KarzaDto getPdfByteArrayEpfAuthPassbook(
			EPFAuthPassBookResponse epfAuthPassBookResponse,
			EPFAuthPassBookRequest epfAuthPassBookRequest, String string);

	KarzaDto getPdfByteArrayLpg(LpgResponse lpgResponse, LpgRequest lpgReq, String string);

	KarzaDto getPdfByteArrayVoter(VoterResponse voterResponse,
			VoterRequest voterRequest, String string);

	KarzaDto getPdfByteArrayLpgIdentifier(LpgIdentificationResponse lpgRes,
			LpgIdentificationRequest lpgRequest, String string);

	KarzaDto getPdfByteArrayNameSimilarity(
			NameSimilarityResponse nameSimilarityResponse,
			NameSimilarityRequest nameSimilarityRequest, String string);

	KarzaDto getPdfByteArrayCompSearch(CompSearchResponse compSearchRessponse,
			CompSearchRequest compSearchRequest, String string);

	KarzaDto getPdfByteArrayCompanyLLPCINLookUp(
			CompanyLLPCINLookUpResponse companyLLPCINLookUpResponse,
			CompanyLLPCINLookUpRequest companyLLPCINLookUpRequest, String string);

	KarzaDto getPdfByteArrayAddressMatch(AddressResponse addressResponse,
			AddressRequest addressRequest, String string);

	KarzaDto getPdfByteArrayMCASignature(
			MCASignatureResponse mcaSignatureResponse,
			MCASignatureRequest mcaSignatureRequest, String string);

	KarzaDto getPdfByteArrayRcSearch(RCSearchResponse rcSearchResponse,
			RCSearchRequest rcSearchRequest, String string);

	KarzaDto getPdfByteArrayIcwaiMembership(
			ICWAIMembershipResponse icwaiMembershipResponse,
			ICWAIMembershipRequest icwaiMembershipRequest, String string);

	KarzaDto getPdfByteArrayBankAccount(
			BankAccountResponse bankAccountResponse,
			BankAccountRequest bankAccountRequest, String tag);

	KarzaDto getPdfByteArrayLpgQC(LpgResponse2 lpgRes, LpgRequest2 lpgRequest,
			String tag);

	KarzaDto getPdfByteArrayEmployerLookup(
			EmployerLookupResponse employerLookupResponse,
			EmolpyerLookupRequest employerLookupRequest, String tag);

	KarzaDto getPdfByteArrayUanLookup(UanLookupResponse uanLookupResponse,
			UanLookupRequest uanLookupRequest, String tag);

	KarzaDto getPdfByteArrayWebsiteDomain(
			WebsiteDomainResponse websiteDomainResponse,
			WebsiteDomainRequest websiteDomainRequest, String tag);

	KarzaDto getPdfByteArrayGSTIdentification(EPFAuthOTPResponse epfAuthOTPResponse,
			EPFAuthOTPRequest epfAuthOTPRequest, String pdf_name);

	

}
