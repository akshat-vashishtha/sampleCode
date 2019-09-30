package com.qualtech.karza.api.request;

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

public class KarzaFullReqRes {

	private KarzaReqRes reqRes;
	private PassportResponse passportResponse;
	private PassportRequest passportRequest;
	private ITRAuthRequest itrAuthRequest;
	private ITRAuthResponse itrAuthResponse;
	private IFSCRequest ifscRequest;
	private IFSCResponse ifscResponse;
	private NREGARequest nregaRequest;
	private NREGAResponse nregaResponse;
	private TanRequest tanRequest;
	private TanResponse tanResponse;
	private DlRequest2 dlRequest;
	private DlResponse2 dlRes;
	private IECRequest iecRequest;
	private IECResponse iecResponse;
	private Form16Request form16Request;
	private Form16Response form16Response;
	private Form16QuatRequest form16QuatRequest;
	private Form16QuatResponse form16QuatResponse;
	private PngRequest pngRequest;
	private PngResponse pngResponse;
	private ESICIdRequest esicIdRequest;
	private ESICIdResponse esicIdResponse;
	private TelephoneRequest teleRequest;
	private TelephoneResponse teleRes;
	private ElectricalRequest elecRequest;
	private ElectricityResponse elecRes;
	private FSSAILicenceRequest fssaiLicenceRequest;
	private FSSAILicenceResponse fssaiLicenceResponse;
	private RCAuthRequest rcAuthRequest;
	private RCAuthResponse rcAuthResponse;
	private ElectricalRequest2 elecRequest2;
	private ElectricityResponse2 elecRes2;
	private PanRequest panRequest;
	private PanResponse PanRes;
	private TelephoneRequest2 teleRequest2;
	private TelephoneResponse2 teleRes2;
	private DlRequest dlRequest1;
	private DlResponse dlRes1;
	private CompanyLLPIdentificationRequest companyLLPIdentificationRequest;
	private CompanyLLPIdentificationResponse companyLLPIdentificationResponse;

	private HSNRequest hsnRequest;
	private HSNResponse hsnResponse;
	private EmailAuthRequest emailAuthRequest;
	private EmailAuthResponse emailAuthResponse;
	private AadharRequest aadharRequest;
	private AadharResponse aadharResponse;
	private UdyogAadharResponse udyogAadharResponse;
	private UdyogAadharRequest udyogAadharRequest;
	private GSTAuthenticationRequest gstAuthenticationRequest;
	private GSTAuthenticationResponse gstAuthenticationResponse;

	private CAMemberShipAuthRequest caMemberShipAuthRequest;
	private CAMemberShipAuthResponse caMemberShipAuthResponse;
	private ICSIMemberShipRequest icsiMemberShipRequest;
	private ICSIMemberShipResponse icsiMemberShipResponse;
	private ICWAIFirmAuthRequest icwaiFirmAuthRequest;
	private ICWAIFirmAuthResponse icwaiFirmAuthResponse;
	private EPFAuthOTPRequest epfAuthOTPRequest;
	private EPFAuthOTPResponse epfAuthOTPResponse;
	private GstIdentificationRequest gstIdentificationRequest;
	private GstIdentificationResponse gstIdentificationResponse;
	private EPFAuthPassBookRequest epfAuthPassBookRequest;
	private EPFAuthPassBookResponse epfAuthPassBookResponse;

	private MCASignatureRequest mcaSignatureRequest;
	private MCASignatureResponse mcaSignatureResponse;
	private CompanyLLPCINLookUpRequest companyLLPCINLookUpRequest;
	private CompanyLLPCINLookUpResponse companyLLPCINLookUpResponse;
	private CompSearchRequest compSearchRequest;
	private CompSearchResponse compSearchRessponse;
	private NameSimilarityRequest nameSimilarityRequest;
	private NameSimilarityResponse nameSimilarityResponse;
	private LpgIdentificationRequest lpgRequest;
	private LpgIdentificationResponse lpgRes;
	private VoterRequest voterRequest;
	private VoterResponse voterResponse;
	private LpgRequest lpgReq;
	private LpgResponse lpgResponse;
	private AddressRequest addressRequest;
	private AddressResponse addressResponse;

	private RCSearchRequest rcSearchRequest;
	private RCSearchResponse rcSearchResponse;
	private ICWAIMembershipRequest icwaiMembershipRequest;
	private ICWAIMembershipResponse icwaiMembershipResponse;
	private BankAccountRequest bankAccountRequest;
	private BankAccountResponse bankAccountResponse; 
	private  LpgRequest2 lpgRequest2;
	private LpgResponse2 lpgRes2;
    private EmolpyerLookupRequest employerLookupRequest;
    private EmployerLookupResponse employerLookupResponse;
    private  UanLookupRequest uanLookupRequest ;
    private UanLookupResponse uanLookupResponse;
    private WebsiteDomainRequest websiteDomainRequest;
	private WebsiteDomainResponse websiteDomainResponse;
	private KycOcrRequest ocrRequest;
	private KycOcrResponse ocrResponse;
    
	public KycOcrRequest getOcrRequest() {
		return ocrRequest;
	}

	public void setOcrRequest(KycOcrRequest ocrRequest) {
		this.ocrRequest = ocrRequest;
	}

	public KycOcrResponse getOcrResponse() {
		return ocrResponse;
	}

	public void setOcrResponse(KycOcrResponse ocrResponse) {
		this.ocrResponse = ocrResponse;
	}

	public WebsiteDomainRequest getWebsiteDomainRequest() {
		return websiteDomainRequest;
	}

	public void setWebsiteDomainRequest(WebsiteDomainRequest websiteDomainRequest) {
		this.websiteDomainRequest = websiteDomainRequest;
	}

	public WebsiteDomainResponse getWebsiteDomainResponse() {
		return websiteDomainResponse;
	}

	public void setWebsiteDomainResponse(WebsiteDomainResponse websiteDomainResponse) {
		this.websiteDomainResponse = websiteDomainResponse;
	}

	public UanLookupRequest getUanLookupRequest() {
		return uanLookupRequest;
	}

	public void setUanLookupRequest(UanLookupRequest uanLookupRequest) {
		this.uanLookupRequest = uanLookupRequest;
	}

	public UanLookupResponse getUanLookupResponse() {
		return uanLookupResponse;
	}

	public void setUanLookupResponse(UanLookupResponse uanLookupResponse) {
		this.uanLookupResponse = uanLookupResponse;
	}

	public EmolpyerLookupRequest getEmployerLookupRequest() {
		return employerLookupRequest;
	}

	public void setEmployerLookupRequest(EmolpyerLookupRequest employerLookupRequest) {
		this.employerLookupRequest = employerLookupRequest;
	}

	public EmployerLookupResponse getEmployerLookupResponse() {
		return employerLookupResponse;
	}

	public void setEmployerLookupResponse(
			EmployerLookupResponse employerLookupResponse) {
		this.employerLookupResponse = employerLookupResponse;
	}

	public LpgRequest2 getLpgRequest2() {
		return lpgRequest2;
	}

	public void setLpgRequest2(LpgRequest2 lpgRequest2) {
		this.lpgRequest2 = lpgRequest2;
	}

	public LpgResponse2 getLpgRes2() {
		return lpgRes2;
	}

	public void setLpgRes2(LpgResponse2 lpgRes2) {
		this.lpgRes2 = lpgRes2;
	}

	public BankAccountRequest getBankAccountRequest() {
		return bankAccountRequest;
	}

	public void setBankAccountRequest(BankAccountRequest bankAccountRequest) {
		this.bankAccountRequest = bankAccountRequest;
	}

	public BankAccountResponse getBankAccountResponse() {
		return bankAccountResponse;
	}

	public void setBankAccountResponse(BankAccountResponse bankAccountResponse) {
		this.bankAccountResponse = bankAccountResponse;
	}

	public RCSearchRequest getRcSearchRequest() {
		return rcSearchRequest;
	}

	public void setRcSearchRequest(RCSearchRequest rcSearchRequest) {
		this.rcSearchRequest = rcSearchRequest;
	}

	public RCSearchResponse getRcSearchResponse() {
		return rcSearchResponse;
	}

	public void setRcSearchResponse(RCSearchResponse rcSearchResponse) {
		this.rcSearchResponse = rcSearchResponse;
	}

	public ICWAIMembershipRequest getIcwaiMembershipRequest() {
		return icwaiMembershipRequest;
	}

	public void setIcwaiMembershipRequest(
			ICWAIMembershipRequest icwaiMembershipRequest) {
		this.icwaiMembershipRequest = icwaiMembershipRequest;
	}

	public ICWAIMembershipResponse getIcwaiMembershipResponse() {
		return icwaiMembershipResponse;
	}

	public void setIcwaiMembershipResponse(
			ICWAIMembershipResponse icwaiMembershipResponse) {
		this.icwaiMembershipResponse = icwaiMembershipResponse;
	}

	public MCASignatureRequest getMcaSignatureRequest() {
		return mcaSignatureRequest;
	}

	public void setMcaSignatureRequest(MCASignatureRequest mcaSignatureRequest) {
		this.mcaSignatureRequest = mcaSignatureRequest;
	}

	public MCASignatureResponse getMcaSignatureResponse() {
		return mcaSignatureResponse;
	}

	public void setMcaSignatureResponse(
			MCASignatureResponse mcaSignatureResponse) {
		this.mcaSignatureResponse = mcaSignatureResponse;
	}

	public CompanyLLPCINLookUpRequest getCompanyLLPCINLookUpRequest() {
		return companyLLPCINLookUpRequest;
	}

	public void setCompanyLLPCINLookUpRequest(
			CompanyLLPCINLookUpRequest companyLLPCINLookUpRequest) {
		this.companyLLPCINLookUpRequest = companyLLPCINLookUpRequest;
	}

	public CompanyLLPCINLookUpResponse getCompanyLLPCINLookUpResponse() {
		return companyLLPCINLookUpResponse;
	}

	public void setCompanyLLPCINLookUpResponse(
			CompanyLLPCINLookUpResponse companyLLPCINLookUpResponse) {
		this.companyLLPCINLookUpResponse = companyLLPCINLookUpResponse;
	}

	public CompSearchRequest getCompSearchRequest() {
		return compSearchRequest;
	}

	public void setCompSearchRequest(CompSearchRequest compSearchRequest) {
		this.compSearchRequest = compSearchRequest;
	}

	public CompSearchResponse getCompSearchRessponse() {
		return compSearchRessponse;
	}

	public void setCompSearchRessponse(CompSearchResponse compSearchRessponse) {
		this.compSearchRessponse = compSearchRessponse;
	}

	public NameSimilarityRequest getNameSimilarityRequest() {
		return nameSimilarityRequest;
	}

	public void setNameSimilarityRequest(
			NameSimilarityRequest nameSimilarityRequest) {
		this.nameSimilarityRequest = nameSimilarityRequest;
	}

	public NameSimilarityResponse getNameSimilarityResponse() {
		return nameSimilarityResponse;
	}

	public void setNameSimilarityResponse(
			NameSimilarityResponse nameSimilarityResponse) {
		this.nameSimilarityResponse = nameSimilarityResponse;
	}

	public LpgIdentificationRequest getLpgRequest() {
		return lpgRequest;
	}

	public void setLpgRequest(LpgIdentificationRequest lpgRequest) {
		this.lpgRequest = lpgRequest;
	}

	public LpgIdentificationResponse getLpgRes() {
		return lpgRes;
	}

	public void setLpgRes(LpgIdentificationResponse lpgRes) {
		this.lpgRes = lpgRes;
	}

	public VoterRequest getVoterRequest() {
		return voterRequest;
	}

	public void setVoterRequest(VoterRequest voterRequest) {
		this.voterRequest = voterRequest;
	}

	public VoterResponse getVoterResponse() {
		return voterResponse;
	}

	public void setVoterResponse(VoterResponse voterResponse) {
		this.voterResponse = voterResponse;
	}

	public LpgRequest getLpgReq() {
		return lpgReq;
	}

	public void setLpgReq(LpgRequest lpgReq) {
		this.lpgReq = lpgReq;
	}

	public LpgResponse getLpgResponse() {
		return lpgResponse;
	}

	public void setLpgResponse(LpgResponse lpgResponse) {
		this.lpgResponse = lpgResponse;
	}

	public AddressRequest getAddressRequest() {
		return addressRequest;
	}

	public void setAddressRequest(AddressRequest addressRequest) {
		this.addressRequest = addressRequest;
	}

	public AddressResponse getAddressResponse() {
		return addressResponse;
	}

	public void setAddressResponse(AddressResponse addressResponse) {
		this.addressResponse = addressResponse;
	}

	public HSNRequest getHsnRequest() {
		return hsnRequest;
	}

	public void setHsnRequest(HSNRequest hsnRequest) {
		this.hsnRequest = hsnRequest;
	}

	public HSNResponse getHsnResponse() {
		return hsnResponse;
	}

	public void setHsnResponse(HSNResponse hsnResponse) {
		this.hsnResponse = hsnResponse;
	}

	public EmailAuthRequest getEmailAuthRequest() {
		return emailAuthRequest;
	}

	public void setEmailAuthRequest(EmailAuthRequest emailAuthRequest) {
		this.emailAuthRequest = emailAuthRequest;
	}

	public EmailAuthResponse getEmailAuthResponse() {
		return emailAuthResponse;
	}

	public void setEmailAuthResponse(EmailAuthResponse emailAuthResponse) {
		this.emailAuthResponse = emailAuthResponse;
	}

	public AadharRequest getAadharRequest() {
		return aadharRequest;
	}

	public void setAadharRequest(AadharRequest aadharRequest) {
		this.aadharRequest = aadharRequest;
	}

	public AadharResponse getAadharResponse() {
		return aadharResponse;
	}

	public void setAadharResponse(AadharResponse aadharResponse) {
		this.aadharResponse = aadharResponse;
	}

	public UdyogAadharResponse getUdyogAadharResponse() {
		return udyogAadharResponse;
	}

	public void setUdyogAadharResponse(UdyogAadharResponse udyogAadharResponse) {
		this.udyogAadharResponse = udyogAadharResponse;
	}

	public UdyogAadharRequest getUdyogAadharRequest() {
		return udyogAadharRequest;
	}

	public void setUdyogAadharRequest(UdyogAadharRequest udyogAadharRequest) {
		this.udyogAadharRequest = udyogAadharRequest;
	}

	public GSTAuthenticationRequest getGstAuthenticationRequest() {
		return gstAuthenticationRequest;
	}

	public void setGstAuthenticationRequest(
			GSTAuthenticationRequest gstAuthenticationRequest) {
		this.gstAuthenticationRequest = gstAuthenticationRequest;
	}

	public GSTAuthenticationResponse getGstAuthenticationResponse() {
		return gstAuthenticationResponse;
	}

	public void setGstAuthenticationResponse(
			GSTAuthenticationResponse gstAuthenticationResponse) {
		this.gstAuthenticationResponse = gstAuthenticationResponse;
	}

	public CAMemberShipAuthRequest getCaMemberShipAuthRequest() {
		return caMemberShipAuthRequest;
	}

	public void setCaMemberShipAuthRequest(
			CAMemberShipAuthRequest caMemberShipAuthRequest) {
		this.caMemberShipAuthRequest = caMemberShipAuthRequest;
	}

	public CAMemberShipAuthResponse getCaMemberShipAuthResponse() {
		return caMemberShipAuthResponse;
	}

	public void setCaMemberShipAuthResponse(
			CAMemberShipAuthResponse caMemberShipAuthResponse) {
		this.caMemberShipAuthResponse = caMemberShipAuthResponse;
	}

	public ICSIMemberShipRequest getIcsiMemberShipRequest() {
		return icsiMemberShipRequest;
	}

	public void setIcsiMemberShipRequest(
			ICSIMemberShipRequest icsiMemberShipRequest) {
		this.icsiMemberShipRequest = icsiMemberShipRequest;
	}

	public ICSIMemberShipResponse getIcsiMemberShipResponse() {
		return icsiMemberShipResponse;
	}

	public void setIcsiMemberShipResponse(
			ICSIMemberShipResponse icsiMemberShipResponse) {
		this.icsiMemberShipResponse = icsiMemberShipResponse;
	}

	public ICWAIFirmAuthRequest getIcwaiFirmAuthRequest() {
		return icwaiFirmAuthRequest;
	}

	public void setIcwaiFirmAuthRequest(
			ICWAIFirmAuthRequest icwaiFirmAuthRequest) {
		this.icwaiFirmAuthRequest = icwaiFirmAuthRequest;
	}

	public ICWAIFirmAuthResponse getIcwaiFirmAuthResponse() {
		return icwaiFirmAuthResponse;
	}

	public void setIcwaiFirmAuthResponse(
			ICWAIFirmAuthResponse icwaiFirmAuthResponse) {
		this.icwaiFirmAuthResponse = icwaiFirmAuthResponse;
	}

	public EPFAuthOTPRequest getEpfAuthOTPRequest() {
		return epfAuthOTPRequest;
	}

	public void setEpfAuthOTPRequest(EPFAuthOTPRequest epfAuthOTPRequest) {
		this.epfAuthOTPRequest = epfAuthOTPRequest;
	}

	public EPFAuthOTPResponse getEpfAuthOTPResponse() {
		return epfAuthOTPResponse;
	}

	public void setEpfAuthOTPResponse(EPFAuthOTPResponse epfAuthOTPResponse) {
		this.epfAuthOTPResponse = epfAuthOTPResponse;
	}

	public GstIdentificationRequest getGstIdentificationRequest() {
		return gstIdentificationRequest;
	}

	public void setGstIdentificationRequest(
			GstIdentificationRequest gstIdentificationRequest) {
		this.gstIdentificationRequest = gstIdentificationRequest;
	}

	public GstIdentificationResponse getGstIdentificationResponse() {
		return gstIdentificationResponse;
	}

	public void setGstIdentificationResponse(
			GstIdentificationResponse gstIdentificationResponse) {
		this.gstIdentificationResponse = gstIdentificationResponse;
	}

	public EPFAuthPassBookRequest getEpfAuthPassBookRequest() {
		return epfAuthPassBookRequest;
	}

	public void setEpfAuthPassBookRequest(
			EPFAuthPassBookRequest epfAuthPassBookRequest) {
		this.epfAuthPassBookRequest = epfAuthPassBookRequest;
	}

	public EPFAuthPassBookResponse getEpfAuthPassBookResponse() {
		return epfAuthPassBookResponse;
	}

	public void setEpfAuthPassBookResponse(
			EPFAuthPassBookResponse epfAuthPassBookResponse) {
		this.epfAuthPassBookResponse = epfAuthPassBookResponse;
	}

	public CompanyLLPIdentificationRequest getCompanyLLPIdentificationRequest() {
		return companyLLPIdentificationRequest;
	}

	public void setCompanyLLPIdentificationRequest(
			CompanyLLPIdentificationRequest companyLLPIdentificationRequest) {
		this.companyLLPIdentificationRequest = companyLLPIdentificationRequest;
	}

	public CompanyLLPIdentificationResponse getCompanyLLPIdentificationResponse() {
		return companyLLPIdentificationResponse;
	}

	public void setCompanyLLPIdentificationResponse(
			CompanyLLPIdentificationResponse companyLLPIdentificationResponse) {
		this.companyLLPIdentificationResponse = companyLLPIdentificationResponse;
	}

	public DlRequest getDlRequest1() {
		return dlRequest1;
	}

	public void setDlRequest1(DlRequest dlRequest1) {
		this.dlRequest1 = dlRequest1;
	}

	public DlResponse getDlRes1() {
		return dlRes1;
	}

	public void setDlRes1(DlResponse dlRes1) {
		this.dlRes1 = dlRes1;
	}

	public TelephoneRequest2 getTeleRequest2() {
		return teleRequest2;
	}

	public void setTeleRequest2(TelephoneRequest2 teleRequest2) {
		this.teleRequest2 = teleRequest2;
	}

	public TelephoneResponse2 getTeleRes2() {
		return teleRes2;
	}

	public void setTeleRes2(TelephoneResponse2 teleRes2) {
		this.teleRes2 = teleRes2;
	}

	public PanRequest getPanRequest() {
		return panRequest;
	}

	public void setPanRequest(PanRequest panRequest) {
		this.panRequest = panRequest;
	}

	public PanResponse getPanRes() {
		return PanRes;
	}

	public void setPanRes(PanResponse panRes) {
		PanRes = panRes;
	}

	public ElectricalRequest2 getElecRequest2() {
		return elecRequest2;
	}

	public void setElecRequest2(ElectricalRequest2 elecRequest2) {
		this.elecRequest2 = elecRequest2;
	}

	public ElectricityResponse2 getElecRes2() {
		return elecRes2;
	}

	public void setElecRes2(ElectricityResponse2 elecRes2) {
		this.elecRes2 = elecRes2;
	}

	public RCAuthRequest getRcAuthRequest() {
		return rcAuthRequest;
	}

	public void setRcAuthRequest(RCAuthRequest rcAuthRequest) {
		this.rcAuthRequest = rcAuthRequest;
	}

	public RCAuthResponse getRcAuthResponse() {
		return rcAuthResponse;
	}

	public void setRcAuthResponse(RCAuthResponse rcAuthResponse) {
		this.rcAuthResponse = rcAuthResponse;
	}

	public FSSAILicenceRequest getFssaiLicenceRequest() {
		return fssaiLicenceRequest;
	}

	public void setFssaiLicenceRequest(FSSAILicenceRequest fssaiLicenceRequest) {
		this.fssaiLicenceRequest = fssaiLicenceRequest;
	}

	public FSSAILicenceResponse getFssaiLicenceResponse() {
		return fssaiLicenceResponse;
	}

	public void setFssaiLicenceResponse(
			FSSAILicenceResponse fssaiLicenceResponse) {
		this.fssaiLicenceResponse = fssaiLicenceResponse;
	}

	public ElectricalRequest getElecRequest() {
		return elecRequest;
	}

	public void setElecRequest(ElectricalRequest elecRequest) {
		this.elecRequest = elecRequest;
	}

	public ElectricityResponse getElecRes() {
		return elecRes;
	}

	public void setElecRes(ElectricityResponse elecRes) {
		this.elecRes = elecRes;
	}

	public TelephoneRequest getTeleRequest() {
		return teleRequest;
	}

	public void setTeleRequest(TelephoneRequest teleRequest) {
		this.teleRequest = teleRequest;
	}

	public TelephoneResponse getTeleRes() {
		return teleRes;
	}

	public void setTeleRes(TelephoneResponse teleRes) {
		this.teleRes = teleRes;
	}

	public ESICIdRequest getEsicIdRequest() {
		return esicIdRequest;
	}

	public void setEsicIdRequest(ESICIdRequest esicIdRequest) {
		this.esicIdRequest = esicIdRequest;
	}

	public ESICIdResponse getEsicIdResponse() {
		return esicIdResponse;
	}

	public void setEsicIdResponse(ESICIdResponse esicIdResponse) {
		this.esicIdResponse = esicIdResponse;
	}

	public PngRequest getPngRequest() {
		return pngRequest;
	}

	public void setPngRequest(PngRequest pngRequest) {
		this.pngRequest = pngRequest;
	}

	public PngResponse getPngResponse() {
		return pngResponse;
	}

	public void setPngResponse(PngResponse pngResponse) {
		this.pngResponse = pngResponse;
	}

	public Form16QuatRequest getForm16QuatRequest() {
		return form16QuatRequest;
	}

	public void setForm16QuatRequest(Form16QuatRequest form16QuatRequest) {
		this.form16QuatRequest = form16QuatRequest;
	}

	public Form16QuatResponse getForm16QuatResponse() {
		return form16QuatResponse;
	}

	public void setForm16QuatResponse(Form16QuatResponse form16QuatResponse) {
		this.form16QuatResponse = form16QuatResponse;
	}

	public Form16Request getForm16Request() {
		return form16Request;
	}

	public void setForm16Request(Form16Request form16Request) {
		this.form16Request = form16Request;
	}

	public Form16Response getForm16Response() {
		return form16Response;
	}

	public void setForm16Response(Form16Response form16Response) {
		this.form16Response = form16Response;
	}

	public IECRequest getIecRequest() {
		return iecRequest;
	}

	public void setIecRequest(IECRequest iecRequest) {
		this.iecRequest = iecRequest;
	}

	public IECResponse getIecResponse() {
		return iecResponse;
	}

	public void setIecResponse(IECResponse iecResponse) {
		this.iecResponse = iecResponse;
	}

	public DlRequest2 getDlRequest() {
		return dlRequest;
	}

	public void setDlRequest(DlRequest2 dlRequest) {
		this.dlRequest = dlRequest;
	}

	public DlResponse2 getDlRes() {
		return dlRes;
	}

	public void setDlRes(DlResponse2 dlRes) {
		this.dlRes = dlRes;
	}

	public TanRequest getTanRequest() {
		return tanRequest;
	}

	public void setTanRequest(TanRequest tanRequest) {
		this.tanRequest = tanRequest;
	}

	public TanResponse getTanResponse() {
		return tanResponse;
	}

	public void setTanResponse(TanResponse tanResponse) {
		this.tanResponse = tanResponse;
	}

	public NREGARequest getNregaRequest() {
		return nregaRequest;
	}

	public void setNregaRequest(NREGARequest nregaRequest) {
		this.nregaRequest = nregaRequest;
	}

	public NREGAResponse getNregaResponse() {
		return nregaResponse;
	}

	public void setNregaResponse(NREGAResponse nregaResponse) {
		this.nregaResponse = nregaResponse;
	}

	private ShopEstablishmentRequest shopEstablishmentRequest;
	private ShopEstablishmentResponse shopEstablishmentResponse;

	public ShopEstablishmentRequest getShopEstablishmentRequest() {
		return shopEstablishmentRequest;
	}

	public void setShopEstablishmentRequest(
			ShopEstablishmentRequest shopEstablishmentRequest) {
		this.shopEstablishmentRequest = shopEstablishmentRequest;
	}

	public ShopEstablishmentResponse getShopEstablishmentResponse() {
		return shopEstablishmentResponse;
	}

	public void setShopEstablishmentResponse(
			ShopEstablishmentResponse shopEstablishmentResponse) {
		this.shopEstablishmentResponse = shopEstablishmentResponse;
	}

	public KarzaReqRes getReqRes() {
		return reqRes;
	}

	public void setReqRes(KarzaReqRes reqRes) {
		this.reqRes = reqRes;
	}

	public PassportResponse getPassportResponse() {
		return passportResponse;
	}

	public void setPassportResponse(PassportResponse passportResponse) {
		this.passportResponse = passportResponse;
	}

	public PassportRequest getPassportRequest() {
		return passportRequest;
	}

	public void setPassportRequest(PassportRequest passportRequest) {
		this.passportRequest = passportRequest;
	}

	public ITRAuthRequest getItrAuthRequest() {
		return itrAuthRequest;
	}

	public void setItrAuthRequest(ITRAuthRequest itrAuthRequest) {
		this.itrAuthRequest = itrAuthRequest;
	}

	public ITRAuthResponse getItrAuthResponse() {
		return itrAuthResponse;
	}

	public void setItrAuthResponse(ITRAuthResponse itrAuthResponse) {
		this.itrAuthResponse = itrAuthResponse;
	}

	public IFSCRequest getIfscRequest() {
		return ifscRequest;
	}

	public void setIfscRequest(IFSCRequest ifscRequest) {
		this.ifscRequest = ifscRequest;
	}

	public IFSCResponse getIfscResponse() {
		return ifscResponse;
	}

	public void setIfscResponse(IFSCResponse ifscResponse) {
		this.ifscResponse = ifscResponse;
	}
}
