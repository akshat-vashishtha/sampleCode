package com.qualtech.karza.api.service;

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
import com.qualtech.karza.api.common.dto.FaceRequest;
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
import com.qualtech.karza.api.request.KarzaReqRes;
import com.qualtech.karza.api.request.KycOcrRequest;
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
import com.qualtech.karza.api.response.FaceResponse;
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
import com.qualtech.karza.api.response.KycOcrResponse;
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

public interface KarzaService {

	DlResponse dlRequestService(DlRequest dlRequest,KarzaReqRes rq);

	ElectricityResponse electricityRequestService(ElectricalRequest elecRequest,KarzaReqRes rq);

	LpgResponse lpgRequestService(LpgRequest lpgRequest, KarzaReqRes rq);

	TelephoneResponse teleRequestService(TelephoneRequest teleRequest,KarzaReqRes rq);

	PanResponse panRequestService(final PanRequest panRequest, KarzaReqRes rq) ;
	
	AadharResponse aadharRequestService(final AadharRequest aadharRequest,  KarzaReqRes rq) ;
	
	 
	
	MCASignatureResponse mcaSignaureRequestService(final MCASignatureRequest mcaSignatureRequest,  KarzaReqRes rq) ;
	
	IECResponse iecRequestService(IECRequest iecRequest, KarzaReqRes rq);
	
	CompanyLLPCINLookUpResponse companyLLPCINLookUpRequestService(CompanyLLPCINLookUpRequest companyLLPCINLookUpRequest, KarzaReqRes rq);
	
	
	GSTAuthenticationResponse gstAuthenticationRequestService(GSTAuthenticationRequest authenticationRequest, KarzaReqRes rq);
	
	ShopEstablishmentResponse shopEstablishmentRequestService(ShopEstablishmentRequest shopEstablishmentRequest,KarzaReqRes rq);
	
	CAMemberShipAuthResponse caMemberShipAuthService(CAMemberShipAuthRequest caMemberShipAuthRequest, KarzaReqRes rq);
	
	ICSIMemberShipResponse icsiMemberShipRequestService(ICSIMemberShipRequest icsiMemberShipRequest, KarzaReqRes rq);
	
	ICWAIFirmAuthResponse icwaiFirmAuthRequestService(ICWAIFirmAuthRequest icwaiFirmAuthRequest, KarzaReqRes rq);
	
	EPFAuthOTPResponse epfAuthOTPRequestService(EPFAuthOTPRequest epfAuthOTPRequest, KarzaReqRes rq);
	
	EPFAuthPassBookResponse epfAuthPassBookRequestService(EPFAuthPassBookRequest epfAuthPassBookRequest,KarzaReqRes rq);
	
	VoterResponse voterRequestService(final VoterRequest voterRequest,KarzaReqRes rq);
	
	NREGAResponse nregaRequestService(final NREGARequest nregaRequest, KarzaReqRes rq);
	
	TanResponse tanRequestService(final TanRequest tanRequest, KarzaReqRes rq) ;
	
	CompSearchResponse compSearchRequestService(final CompSearchRequest compSearchRequest,KarzaReqRes rq);
	
	UdyogAadharResponse udyogAadharRequestService(final UdyogAadharRequest udyogAadharRequest,KarzaReqRes rq);
	
	GstIdentificationResponse gstIdentificationRequestService(final GstIdentificationRequest gstIdentificationRequest,KarzaReqRes rq);
	
	FSSAILicenceResponse fssaiLicenceRequestService(final FSSAILicenceRequest fssaiLicenceRequest, KarzaReqRes rq);
	
	ICWAIMembershipResponse icwaiMembershipRequestService(final ICWAIMembershipRequest icwaiMembershipRequest,KarzaReqRes rq);
	
	PngResponse pngRequestService(final PngRequest pngRequest, KarzaReqRes rq);
	
	ESICIdResponse esicIdRequestService(final ESICIdRequest esicIdRequest, KarzaReqRes rq);
	
	Form16Response form16RequestService(final Form16Request form16Request, KarzaReqRes rq);
	
	Form16QuatResponse form16QuatRequestService(final Form16QuatRequest form16QuatRequest, KarzaReqRes rq);
	
	AddressResponse addressRequestService(final AddressRequest addressRequest, KarzaReqRes rq);

	NameSimilarityResponse nameSimilarityRequestService(NameSimilarityRequest nameSimilarityRequest, KarzaReqRes rq);

	BankAccountResponse bankAccountRequestService(BankAccountRequest bankAccountRequest, KarzaReqRes rq);

	RCSearchResponse rcSearchRequestService(RCSearchRequest rcSearchRequest, KarzaReqRes rq);

//	ITRAuthResponse itrAuthRequestRequestService(ITRAuthRequest itrAuthRequest, KarzaReqRes rq);

	EmailAuthResponse emailAuthRequestService(EmailAuthRequest emailAuthRequest, KarzaReqRes rq);

	//IFSCResponse ifscRequestService(IFSCRequest ifscRequest, KarzaReqRes rq);

	HSNResponse hsnRequestService(HSNRequest hsnRequest,  KarzaReqRes rq);

	RCAuthResponse rcAuthRequestService(RCAuthRequest rcAuthRequest, KarzaReqRes rq);
	
	FaceResponse faceRequestService(FaceRequest faceRequest,  KarzaReqRes rq);

	
	ElectricityResponse2 electricityRequestServiceQC(ElectricalRequest2 elecRequest, KarzaReqRes rq);

	DlResponse2 dlRequestServiceQC(DlRequest2 dlRequest, KarzaReqRes rq);

	TelephoneResponse2 teleRequestServiceQC(TelephoneRequest2 teleRequest, KarzaReqRes rq);

	LpgResponse2 lpgRequestServiceQC(LpgRequest2 lpgRequest,  KarzaReqRes rq);

	LpgIdentificationResponse lpgIdentificationRequestService(LpgIdentificationRequest lpgRequest, KarzaReqRes rq);

	PassportResponse passportRequestService(PassportRequest passportRequest, KarzaReqRes rqres);

	
	ITRAuthResponse itrAuthRequestRequestService(ITRAuthRequest itrAuthRequest, KarzaReqRes rqres);

	IFSCResponse ifscRequestService(IFSCRequest ifscRequest, KarzaReqRes rq);

	CompanyLLPIdentificationResponse companyLLPIdentificationRequestService(
			CompanyLLPIdentificationRequest companyLLPIdentificationRequest,
			KarzaReqRes rq);

	EmployerLookupResponse employerLookupRequestService(
			EmolpyerLookupRequest employerLookupRequest, KarzaReqRes rq);

	UanLookupResponse uanLookupRequestService(
			UanLookupRequest uanLookupRequest, KarzaReqRes rq);

	WebsiteDomainResponse websiteDomaminRequestService(
			WebsiteDomainRequest websiteDomainRequest, KarzaReqRes rq);

	KycOcrResponse ocrRequestServiceQC(KycOcrRequest ocrRequest, KarzaReqRes rq);
	
	
}
