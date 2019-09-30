package com.qualtech.api.crons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qualtech.api.db.ServiceMaster;
import com.qualtech.api.db.UniqueId;
import com.qualtech.api.ibs.util.CibilIbsRequest;
import com.qualtech.api.ibs.util.CibilV2IBSRequest;
import com.qualtech.api.ibs.util.CommonDataInRequest;
import com.qualtech.api.ibs.util.CreditVidhyaIbsRequest;
import com.qualtech.api.ibs.util.FibitBankingRequest;
import com.qualtech.api.ibs.util.FibitRequest;
import com.qualtech.api.ibs.util.HdfcRequest;
import com.qualtech.api.ibs.util.IBSMedicalQuestionnaire;
import com.qualtech.api.ibs.util.IbsAllServiceRequest;
import com.qualtech.api.ibs.util.KarzaAddressMatching;
import com.qualtech.api.ibs.util.KarzaBankAccount;
import com.qualtech.api.ibs.util.KarzaCompSearchAndllplookup;
import com.qualtech.api.ibs.util.KarzaESICId;
import com.qualtech.api.ibs.util.KarzaElectricity;
import com.qualtech.api.ibs.util.KarzaFSSAILicence;
import com.qualtech.api.ibs.util.KarzaForm16Auth;
import com.qualtech.api.ibs.util.KarzaGstIdentification;
import com.qualtech.api.ibs.util.KarzaHsn;
import com.qualtech.api.ibs.util.KarzaICWAI;
import com.qualtech.api.ibs.util.KarzaIec;
import com.qualtech.api.ibs.util.KarzaLpg;
import com.qualtech.api.ibs.util.KarzaNameSimilarity;
import com.qualtech.api.ibs.util.KarzaNrega;
import com.qualtech.api.ibs.util.KarzaPNG;
import com.qualtech.api.ibs.util.KarzaPassport;
import com.qualtech.api.ibs.util.KarzaRCSearch;
import com.qualtech.api.ibs.util.KarzaService;
import com.qualtech.api.ibs.util.KarzaShopEstablishment;
import com.qualtech.api.ibs.util.KarzaWebsiteDomain;
import com.qualtech.api.ibs.util.KickoffAUTHIbsRequest;
import com.qualtech.api.ibs.util.KickoffGenQueIbsRequest;
import com.qualtech.api.ibs.util.KickoffSingleACtionIbsRequest;
import com.qualtech.api.ibs.util.KotakIbsRequest;
import com.qualtech.api.ibs.util.MultiBureauRequest;
import com.qualtech.api.ibs.util.NomineeDetails;
import com.qualtech.api.ibs.util.PersonAddressess;
import com.qualtech.api.ibs.util.PersonId;
import com.qualtech.api.ibs.util.PersonPhone;
import com.qualtech.api.ibs.util.PersonRelation;
import com.qualtech.api.ibs.util.ResquestStatus;
import com.qualtech.api.ibs.util.karzaITRAuth;

@Transactional
@Repository
public class CronDBConnectionImpl implements CronsDBConnection {

	static Logger logger = Logger.getLogger(CronsDBConnection.class.getName());
	@Autowired
	LocalSessionFactoryBean sessionFactoryBean;

	private Session getSession() {
		return sessionFactoryBean.getObject().getCurrentSession();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<IbRequestMaster> loadRequest()
	{
		List<IbRequestMaster> results = null;
		logger.info(" inside loadRequest() :: Start");
		try
		{
			Criteria criteria = getSession().createCriteria(IbRequestMaster.class);
			criteria.add(Restrictions.eq("request_status", ResquestStatus.INITIATED.toString()));
			results = (List<IbRequestMaster>) criteria.list();
		}
		catch (Exception e)
		{
			logger.error(" unable to find results from db :: " + e);
		}
		logger.info(" inside loadRequest() :: END");
		return results;
	}

	@Override
	public void updateRecord(IbRequestMaster result) 
	{
		logger.info(" inside updateRecord() :: Start");
		try
		{
			getSession().update(result);
		}
		catch (Exception e)
		{
			logger.error(" unable to update record :: " + e);
		}
		logger.info(" inside updateRecord() :: END");
	}

	@Override
	public void insertRecord(IbRequestMaster result) {
		logger.info(" inside insertRecord() :: Start");
		try {
			getSession().save(result);
		} catch (Exception e) {
			logger.error(" unable to insert record :: " + e);
		}
		logger.info(" inside insertRecord() :: END");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServiceMaster> getServiceList() {
		logger.info(" inside getServiceList() :: Start");
		List<ServiceMaster> list = new ArrayList<ServiceMaster>();
		try {

			Criteria criteria = getSession().createCriteria(ServiceMaster.class);
			criteria.add(Restrictions.eq("query_status", "Y"));
			criteria.setProjection(Projections.projectionList().add(Projections.property("service_name")).add(Projections.property("product_name")));
			//criteria.setProjection(Projections.projectionList().add(Projections.property("product_name")));
			List<Object[]> list_temp = criteria.list();
			for (Object[] obj: list_temp) {
				ServiceMaster master=new ServiceMaster();
				master.setService_name(""+obj[0]);
				master.setProduct_name(""+obj[1]);
				list.add(master);
			}

		} catch (Exception e) {
			logger.error(" error occured during retrive Service name :: " + e);
		}
		logger.info(" inside getServiceList() :: END");
		return list;
	}

	@Override
	public Map<String, String> getSaveInitialRequest(List<String> servicelist, String uniqueId) {
		logger.info(" inside getSaveInitialRequest() :: Start");
		Map<String, String> hm = new HashMap<String, String>();
		for (String servicename : servicelist) {
			try {


				IbRequestMaster ibmaster = new IbRequestMaster();
				ibmaster.setRequest_status("READY TO INITIATE");
				ibmaster.setTransaction(uniqueId);
				ibmaster.setService_provider(servicename);
				long id = (Long) getSession().save(ibmaster);
				hm.put(servicename, id + "");

			} catch (Exception e) {
				logger.error(" error occured during saving READY TO INITIATE state :: " + e);
			}
		} // for
		logger.info(" inside getSaveInitialRequest() :: END");
		return hm;
	}

	@Override
	public Boolean retriveRequest(List<String> servicelist, String uniqueid, Map<String, String> hm,
			IbsAllServiceRequest ibsAllServiceRequest) {
		logger.info(" inside retriveRequest() :: Start");
		Calendar cal = Calendar.getInstance();
		String initiated=ResquestStatus.INITIATED.toString();
		boolean flag = false;
		try {
			IbRequestMaster ibsrequest = initiateCommonData(ibsAllServiceRequest);
			for (String string : servicelist) {

				if (string.equals("DL V3") && hm.get(string) != null) {
					logger.info(" inside KARZA DL V3 request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					try {
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
						KarzaService kservice = ibsAllServiceRequest.getKarza();
						ibs.setAllowConsent(kservice.getConsent());
						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						flag = true;
					} catch (Exception e) {
						logger.error(
								" error occured during INITIATED state of KARZA DL V3 request with CorrelationId :: "
										+ correlationId);
					}
					logger.info(" inside KARZA DL V3 request  INITIATED successfully  :: End");
				} 
				if (string.equals("MULTIBUREAU INDV CIR") && hm.get(string) != null) {
					logger.info(" inside MULTI BUREAU request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					try {
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
						MultiBureauRequest multibureau = ibsAllServiceRequest.getMultiBureau();
						CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
						List<PersonAddressess> addresses = common.getAddresses();
						if(addresses!=null) {
							for (PersonAddressess personAddressess : addresses) 
							{
								ibs.setAddress1(((personAddressess.getAddressline1()!=null?personAddressess.getAddressline1():"")+" "+(personAddressess.getAddressline2()!=null?personAddressess.getAddressline2():"").trim()));
								ibs.setAddress_type1(personAddressess.getType());
								ibs.setCity(personAddressess.getCity());
								ibs.setState(personAddressess.getState());
							}
						}
						if (multibureau != null) {
							ibs.setPriority(multibureau.getPriority());
							ibs.setProductType(multibureau.getProductType());
							ibs.setLoanType(multibureau.getLoanType());
							ibs.setLoanAmount(multibureau.getLoanAmount());
							ibs.setJointInd(multibureau.getJointInd());
							ibs.setInquirySubmitted_by(multibureau.getInquirySubmitted_by());
							ibs.setSourceSystemName(multibureau.getSourceSystemName());
							ibs.setSourceSystemVersion(multibureau.getSourceSystemVersion());
							ibs.setSourceSystemVender(multibureau.getSourceSystemVender());
							ibs.setSourceSystem_instance_id(multibureau.getSourceSystem_instance_id());
							ibs.setLoanPurposeDesc(multibureau.getLoanPurposeDesc());
							ibs.setBranchIfsccode(multibureau.getBranchIfsccode());
							ibs.setKendra(multibureau.getKendra());
							ibs.setInquiryStage(multibureau.getInquiryStage());
							ibs.setAuthrizationflag(multibureau.getAuthrizationflag());
							ibs.setAuthroizedBy(multibureau.getAuthroizedBy());
							ibs.setIndividualCorporate_flag(multibureau.getIndividualCorporate_flag());
							ibs.setConstitution(multibureau.getConstitution());
							ibs.setNoOfDependents(multibureau.getNoOfDependents());
							ibs.setAlias(multibureau.getAlias());
							ibs.setActOpeningDt(multibureau.getActOpeningDt());
							ibs.setAccountNumber1(multibureau.getAccountNumber1());
							ibs.setAccountNumber2(multibureau.getAccountNumber2());
							ibs.setAccountNumber3(multibureau.getAccountNumber3());
							ibs.setAccountNumber4(multibureau.getAccountNumber4());
							ibs.setTanure(multibureau.getTanure());
							ibs.setGroupId(multibureau.getGroupId());
							ibs.setNumberCreditCards(multibureau.getNumberCreditCards());
							ibs.setCreditCardNo(multibureau.getCreditCardNo());
							ibs.setMonthlyIncome(multibureau.getMonthlyIncome());
							ibs.setSoaEmployerNameC(multibureau.getSoaEmployerNameC());
							ibs.setTimeWithEmploy(multibureau.getTimeWithEmploy());
							ibs.setCompanyCategory(multibureau.getCompanyCategory());
							ibs.setNatureOfBusiness(multibureau.getNatureOfBusiness());
							ibs.setAssetCost(multibureau.getAssetCost());
							ibs.setCollateral1(multibureau.getCollateral1());
							ibs.setCollateral1Valuation1(multibureau.getCollateral1Valuation1());
							ibs.setCollateral1Valuation2(multibureau.getCollateral1Valuation2());
							ibs.setCollateral2Valuation1(multibureau.getCollateral2Valuation1());
							ibs.setCollateral2Valuation2(multibureau.getCollateral2Valuation2());
							ibs.setCollateral2(multibureau.getCollateral2());
						}
						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						flag = true;
					} catch (Exception e) {
						logger.error(" error occured during INITIATED state of MULTI BUREAU  request with CorrelationId :: "
								+ correlationId);
					}
					logger.info(" inside MULTI BUREAU request  INITIATED successfully  :: End");
				}

				if(string.equals("HDFC")&& hm.get(string)!=null) {


					logger.info(" inside HDFC request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
					try {
						HdfcRequest hdfcRequest = ibsAllServiceRequest.getHdfcRequest();
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();

						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						/*ibs.setTitle(hdfcRequest.getTitle()!=null?hdfcRequest.getTitle():"");*/
						ibs.setPartnerId(hdfcRequest.getPartnerId()!=null?hdfcRequest.getPartnerId():"");
						ibs.setPrn(hdfcRequest.getPrn()!=null?hdfcRequest.getPrn():"");
						ibs.setPlan(hdfcRequest.getPlan()!=null?hdfcRequest.getPlan():"");
						ibs.setIpAddress(hdfcRequest.getIpAddress()!=null?hdfcRequest.getIpAddress():"");
						ibs.setDomain(hdfcRequest.getDomain()!=null?hdfcRequest.getDomain():"");
						ibs.setSumAssuredType(hdfcRequest.getSumAssuredType()!=null?hdfcRequest.getSumAssuredType():"");
						ibs.setSumAssured(hdfcRequest.getSumAssured()!=null?hdfcRequest.getSumAssured():"");
						ibs.setLoanType(hdfcRequest.getLoanType()!=null?hdfcRequest.getLoanType():"");
						ibs.setLoanDisbursement_date(hdfcRequest.getLoanDisbursement_date()!=null?hdfcRequest.getLoanDisbursement_date():"");
						ibs.setLoanAmount(hdfcRequest.getLoanAmount()!=null?hdfcRequest.getLoanAmount():"");
						ibs.setBasicPremium(hdfcRequest.getBasicPremium()!=null?hdfcRequest.getBasicPremium():"");
						ibs.setPolicyTerm(hdfcRequest.getPolicyTerm()!=null?hdfcRequest.getPolicyTerm():"");
						ibs.setLoanTenure(hdfcRequest.getLoanTenure()!=null?hdfcRequest.getLoanTenure():"");
						ibs.setPremiumPayable(hdfcRequest.getPremiumPayable()!=null?hdfcRequest.getPremiumPayable():"");
						ibs.setIsAgreementGenerated(hdfcRequest.getIsAgreementGenerated()!=null?hdfcRequest.getIsAgreementGenerated():"");
						ibs.setFundingOption(hdfcRequest.getFundingOption()!=null?hdfcRequest.getFundingOption():"");
						try {
							IBSMedicalQuestionnaire medicalques=hdfcRequest.getMedicalQuestionnaire();
							if(medicalques!=null) {
								ibs.setQuestionnaireId(medicalques.getQuestionnaireId()!=null?medicalques.getQuestionnaireId():"");
								if(medicalques.getResponse()!=null) {
									ibs.setHdfcQueOne(medicalques.getResponse().getOne()!=null?medicalques.getResponse().getOne():"0");	
									ibs.setHdfcQueTwo(medicalques.getResponse().getTwo()!=null?medicalques.getResponse().getTwo():"0");
									ibs.setHdfcQueThree(medicalques.getResponse().getThree()!=null?medicalques.getResponse().getThree():"0");
									ibs.setHdfcQueFour(medicalques.getResponse().getFour()!=null?medicalques.getResponse().getFour():"0");
									ibs.setHdfcQueFive(medicalques.getResponse().getFive()!=null?medicalques.getResponse().getFive():"0");
									ibs.setHdfcQueSix(medicalques.getResponse().getSix()!=null?medicalques.getResponse().getSix():"0");
									ibs.setHdfcQueSeven(medicalques.getResponse().getSeven()!=null?medicalques.getResponse().getSeven():"0");
									ibs.setHdfcQueEight(medicalques.getResponse().getEight()!=null?medicalques.getResponse().getEight():"0");
									ibs.setHdfcQueNine(medicalques.getResponse().getNine()!=null?medicalques.getResponse().getNine():"0");
								}
							}
						}catch (Exception e) {
							logger.error("getting exception inside IBSMedicalQuestionnaire");
						}

						for (PersonAddressess addresses : common.getAddresses()) {
							ibs.setHouse(addresses.getHouse()!=null?addresses.getHouse():"");
							ibs.setPin1(addresses.getPin()!=null?addresses.getPin():"");
							ibs.setState(addresses.getState()!=null?addresses.getState():"");
							ibs.setStreet(addresses.getStreet()!=null?addresses.getStreet():"");
						}


						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						for (NomineeDetails nomineeDetails : hdfcRequest.getNomineeDetails()) {
							nomineeDetails.setCorelationid(correlationId);
							getSession().saveOrUpdate(nomineeDetails);
						}
						flag = true;
					} catch (Exception e) {
						logger.error(" error occured during INITIATED state of HDFC request with CorrelationId :: "+ correlationId);
					}
					logger.info(" inside HDFC request  INITIATED successfully  :: End");

				}
				if (string.equals("KARZA PASSPORT V2") && hm.get(string) != null) {
					logger.info(" inside KARZA PASSPORT V2 request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					try {
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
						KarzaService kservice = ibsAllServiceRequest.getKarza();
						ibs.setAllowConsent(kservice.getConsent());
						KarzaPassport pass = kservice.getPassport();
						if (pass != null) {
							ibs.setDoe(pass.getDoe());
							ibs.setPassportType(pass.getType());
						}
						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						flag = true;
					} catch (Exception e) {
						logger.error(" error occured during INITIATED state of KARZA PASSPORT V2 request with CorrelationId :: "
								+ correlationId);
					}
					logger.info(" inside KARZA PASSPORT V2 request  INITIATED successfully  :: End");
				}
				if (string.equals("PERFIOS") && hm.get(string) != null) 
				{
					String correlationId = UniqueId.getUniqueId();

					IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();

					ibs.setRequest_status(initiated);
					ibs.setCreatedTime(cal.getTime());
					ibs.setCorrelationid(correlationId);
					ibs.setService_provider(string);
					ibs.setTransaction(uniqueid);
					long id = Long.parseLong(hm.get(string));
					ibs.setSeq_id(id);

					getSession().saveOrUpdate(ibs);

					flag = true;
				}
				if (string.equals("FINBIT") && hm.get(string) != null) {
					String correlationId = UniqueId.getUniqueId();
					FibitBankingRequest finbit = ibsAllServiceRequest.getBanking();

					IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();

					ibs.setRequest_status(initiated);
					ibs.setCreatedTime(cal.getTime());
					ibs.setCorrelationid(correlationId);
					ibs.setService_provider(string);
					ibs.setTransaction(uniqueid);
					long id = Long.parseLong(hm.get(string));
					ibs.setSeq_id(id);

					getSession().saveOrUpdate(ibs);

					for (FibitRequest accountDetail : finbit.getAccountDetail()) {
						accountDetail.setCorelationid(correlationId);
						getSession().saveOrUpdate(accountDetail);
					}

					flag = true;
				}
				if (string.equals("KARZA HSN V2") && hm.get(string) != null) {
					logger.info(" inside KARZA PASSPORT V2 request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					try {
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
						KarzaService kservice = ibsAllServiceRequest.getKarza();
						ibs.setAllowConsent(kservice.getConsent());
						KarzaHsn hsn = kservice.getHsn();
						if (hsn != null) {
							ibs.setHsCode(hsn.getHsCode());
						}
						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						flag = true;
					} catch (Exception e) {
						logger.error(
								" error occured during INITIATED state of KARZA PASSPORT V2 request with CorrelationId :: "
										+ correlationId);
					}
					logger.info(" inside KARZA PASSPORT V2 request  INITIATED successfully  :: End");
				}

				if (string.equals("KARZA LPG V3") && hm.get(string) != null) {
					logger.info(" inside KARZA LPG V3 request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					try {
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
						KarzaService kservice = ibsAllServiceRequest.getKarza();
						ibs.setAllowConsent(kservice.getConsent());
						KarzaLpg lpg = kservice.getLpg();
						if (lpg != null) {
							ibs.setLpg_id(lpg.getLpgId());
						}
						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						flag = true;
					} catch (Exception e) {
						logger.error(
								" error occured during INITIATED state of KARZA LPG V3 request with CorrelationId :: "
										+ correlationId);
					}
					logger.info(" inside KARZA LPG V3 request  INITIATED successfully  :: End");
				}
				if (string.equals("KARZA SHOPESTABLISHMENT V2") && hm.get(string) != null) {
					logger.info(" inside KARZA SHOPESTABLISHMENT V2 request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					try {
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
						KarzaService kservice = ibsAllServiceRequest.getKarza();
						ibs.setAllowConsent(kservice.getConsent());
						KarzaShopEstablishment shopEstablishment = kservice.getShopEstablishment();
						if (shopEstablishment != null) {
							ibs.setReg_no(shopEstablishment.getRegNo());
							ibs.setArea_code(shopEstablishment.getAreaCode());
						}
						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						flag = true;
					} catch (Exception e) {
						logger.error(
								" error occured during INITIATED state of KARZA SHOPESTABLISHMENT V2 request with CorrelationId :: "
										+ correlationId);
					}
					logger.info(" inside KARZA SHOPESTABLISHMENT V2 request  INITIATED successfully  :: End");
				}
				if (string.equals("KARZA ICSI MEMBERSHIP V2") && hm.get(string) != null) {
					logger.info(" inside KARZA ICSI MEMBERSHIP V2 request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					try {
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
						KarzaService kservice = ibsAllServiceRequest.getKarza();
						ibs.setAllowConsent(kservice.getConsent());
						ibs.setMembership_no(kservice.getIcsiMembership().getMembershipNo());
						ibs.setCp_no(kservice.getIcsiMembership().getCpNo());
						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						flag = true;
					} catch (Exception e) {
						logger.error(
								" error occured during INITIATED state of KARZA ICSI MEMBERSHIP V2 request with CorrelationId :: "
										+ correlationId);
					}
					logger.info(" inside KARZA ICSI MEMBERSHIP V2 request  INITIATED successfully  :: End");
				}

				if (string.equals("KARZA ICWAI FIRM AUTH V2") && hm.get(string) != null) {
					logger.info(" inside KARZA ICWAI FIRM AUTH V2 request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					try {
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
						KarzaService kservice = ibsAllServiceRequest.getKarza();
						ibs.setAllowConsent(kservice.getConsent());
						ibs.setReg_no(kservice.getIcwaiFirmAuth().getRegNo());
						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						flag = true;
					} catch (Exception e) {
						logger.error(
								" error occured during INITIATED state of KARZA ICWAI FIRM AUTH V2 request with CorrelationId :: "
										+ correlationId);
					}
					logger.info(" inside KARZA ICWAI FIRM AUTH V2 request  INITIATED successfully  :: End");
				}

				if (string.equals("KARZA GST IDENTIFICATION V2") && hm.get(string) != null) {
					logger.info(" inside KARZA GST IDENTIFICATION V2 request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					try {
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
						KarzaService kservice = ibsAllServiceRequest.getKarza();
						ibs.setAllowConsent(kservice.getConsent());
						KarzaGstIdentification gstauth = kservice.getGstIdenAuth();
						if (gstauth != null) {
							ibs.setGstin(gstauth.getGstin());
							ibs.setInput(gstauth.getInput());
						}
						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						flag = true;
					} catch (Exception e) {
						logger.error(
								" error occured during INITIATED state of KARZA GST IDENTIFICATION V2 request with CorrelationId :: "
										+ correlationId);
					}
					logger.info(" inside KARZA GST IDENTIFICATION V2 request  INITIATED successfully  :: End");
				}

				if (string.equals("KARZA FSSAI LICENCE V2") && hm.get(string) != null) {
					logger.info(" inside KARZA FSSAI LICENCE V2 request  :: Start");
					String correlationId = UniqueId.getUniqueId();
					try {
						IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
						KarzaService kservice = ibsAllServiceRequest.getKarza();
						ibs.setAllowConsent(kservice.getConsent());
						KarzaFSSAILicence fssai = kservice.getFssaiLicence();
						if (fssai != null) {
							ibs.setReg_no(kservice.getFssaiLicence().getRegNo());
						}
						ibs.setRequest_status(initiated);
						ibs.setCreatedTime(cal.getTime());
						ibs.setCorrelationid(correlationId);
						ibs.setService_provider(string);
						ibs.setTransaction(uniqueid);
						long id = Long.parseLong(hm.get(string));
						ibs.setSeq_id(id);
						getSession().saveOrUpdate(ibs);
						flag = true;
					} catch (Exception e) {
						logger.error(
								" error occured during INITIATED state of KARZA FSSAI LICENCE V2 request with CorrelationId :: "
										+ correlationId);
					}
					logger.info(" inside KARZA FSSAI LICENCE V2 request  INITIATED successfully  :: End");
				}

				if (string.equals("KARZA RC SEARCH V2") || string.equals("KARZA RC AUTH V2")) {
					if(hm.get(string) != null) {
						logger.info(" inside "+string+" request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaRCSearch rcserch = kservice.getRcSearch();
							if (rcserch != null) {
								ibs.setChassisNo(rcserch.getChassisNo());
								ibs.setEngineNo(rcserch.getEngineNo());
								ibs.setRcAuthRegNumber(rcserch.getRcRegNumber());

							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of "+string+"  request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside "+string+"  request  INITIATED successfully  :: End");
					}

				}
					if (string.equals("KARZA VOTER V2") && hm.get(string) != null) {
						logger.info(" inside KARZA VOTER V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA VOTER V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA VOTER V2 request  INITIATED successfully  :: End");
					} // if Voter v2 Karza
					if (string.equals("KARZA FORM 16 QUARTERLY V2") && hm.get(string) != null) {
						logger.info(" inside KARZA FORM 16 QUARTERLY V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaForm16Auth form16quar = kservice.getForm16();
							if (form16quar != null) {
								ibs.setFiscal_year(form16quar.getFiscalYear());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA FORM 16 QUARTERLY V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA FORM 16 QUARTERLY V2 request  INITIATED successfully  :: End");
					} // if

					if (string.equals("KARZA EMPLOYER LOOKUP") && hm.get(string) != null) {
						logger.info(" inside KARZA EMPLOYER LOOKUP request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA EMPLOYER LOOKUP request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA EMPLOYER LOOKUP request  INITIATED successfully  :: End");
					} // if Employerlookup Karza

					if (string.equals("KARZA ELEC V3") && hm.get(string) != null) {
						logger.info(" inside KARZA KARZA ELEC V3 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							if (kservice != null) {
								ibs.setAllowConsent(kservice.getConsent());
								KarzaElectricity electricity = kservice.getElectricity();
								ibs.setConsumer_id(electricity.getConsumerId());
								ibs.setKarza_service_provider(electricity.getServiceProvider());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA ELEC V3 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA ELEC V3 request  INITIATED successfully  :: End");
					} // if elec V3

					if (string.equals("KARZA IFSC Check") && hm.get(string) != null) {
						logger.info(" inside KARZA IFSC Check request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							if (kservice != null) {
								KarzaBankAccount ifsc = kservice.getBankAccount();
								ibs.setIfsc(ifsc.getIfsc());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA IFSC Check request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA IFSC Check request  INITIATED successfully  :: End");
					} // if ifsc

					if (string.equals("KARZA ICWAI MEMBER V2") && hm.get(string) != null) {
						logger.info(" inside KARZA ICWAI MEMBER V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaICWAI icwai = kservice.getIcwaiMember();
							if (icwai != null) {
								ibs.setMembership_no(icwai.getMemberShipNo());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA ICWAI MEMBER V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA ICWAI MEMBER V2 request  INITIATED successfully  :: End");
					} // if

					if (string.equals("KARZA PNG V2") && hm.get(string) != null) {
						logger.info(" inside KARZA PNG V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaPNG png = kservice.getPng();
							if (png != null) {
								ibs.setConsumer_id(png.getConsumerId());
								ibs.setBp_no(png.getBpNo());
								ibs.setKarza_service_provider(png.getServiceProvider());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA PNG V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA PNG V2 request  INITIATED successfully  :: End");
					} // if
					if (string.equals("KARZA ESICId V2") && hm.get(string) != null) {
						logger.info(" inside KARZA ESICId V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaESICId esicid = kservice.getEsicId();
							if (esicid != null) {
								ibs.setEsic_id(esicid.getEsicId());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA ESICId V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA ESICId V2 request  INITIATED successfully  :: End");
					} // if

					if (string.equals("KARZA FORM 16 AUTHENTICATION V2") && hm.get(string) != null) {
						logger.info(" inside KARZA KARZA FORM 16 AUTHENTICATION V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaForm16Auth form16Auth = kservice.getForm16();
							if (form16Auth != null) {
								ibs.setCert_no(form16Auth.getCertNo());
								ibs.setAmount(form16Auth.getAmount());
								ibs.setFiscal_year(form16Auth.getFiscalYear());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA  FORM 16 AUTHENTICATION V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA FORM 16 AUTHENTICATION V2 request  INITIATED successfully  :: End");
					} // if

					if (string.equals("KARZA WEBSITE DOMIAN V2") && hm.get(string) != null) {
						logger.info(" inside KARZA WEBSITE DOMIAN V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							KarzaWebsiteDomain webdomain = kservice.getWebsiteDomain();
							if (webdomain != null) {
								ibs.setDomain(webdomain.getDomain());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA WEBSITE DOMIAN V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA WEBSITE DOMIAN V2 request  INITIATED successfully  :: End");
					} // if
					if (string.equals("KARZA UAN LOOKUP V2") && hm.get(string) != null) {
						logger.info(" inside KARZA UAN LOOKUP V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA UAN LOOKUP V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA UAN LOOKUP V2 request  INITIATED successfully  :: End");
					} // if
					if (string.equals("KARZA ITR AUTH V2") && hm.get(string) != null) {
						logger.info(" inside KARZA ITR AUTH V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							karzaITRAuth itr = kservice.getItrAuth();
							if (itr != null) {
								ibs.setAck(itr.getAck());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA ITR AUTH V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA ITR AUTH V2 request  INITIATED successfully  :: End");
					} // if
					if (string.equals("KARZA BANK ACCOUNT V2") && hm.get(string) != null) {
						logger.info(" inside KARZA BANK ACCOUNT V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaBankAccount bankacc = kservice.getBankAccount();
							if (bankacc != null) {
								ibs.setIfsc(bankacc.getIfsc());
								ibs.setAccountNo(bankacc.getAccountNumber());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA BANK ACCOUNT V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA BANK ACCOUNT V2 request  INITIATED successfully  :: End");
					} // if

					if (string.equals("KARZA NAME SIMILARITY V2") && hm.get(string) != null) {
						logger.info(" inside KARZA NAME SIMILARITY V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							KarzaNameSimilarity namesim = kservice.getNameSimilarity();
							if (namesim != null) {
								ibs.setFullname(namesim.getName1());
								ibs.setShortname(namesim.getName2());
								ibs.setNameType(namesim.getType());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA NAME SIMILARITY V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA NAME SIMILARITY V2 request  INITIATED successfully  :: End");
					} // if

					if (string.equals("KARZA TAN V2") && hm.get(string) != null) {
						logger.info(" inside KARZA TAN V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA TAN V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA TAN V2 request  INITIATED successfully  :: End");
					} // if Tan v2 Karza
					if (string.equals("KARZA IEC V2") && hm.get(string) != null) {
						logger.info(" inside KARZA IEC V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaIec iec = kservice.getIec();
							if (iec != null) {
								ibs.setIec(iec.getIec());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA IEC V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA IEC V2 request  INITIATED successfully  :: End");
					} // if iec v2 Karza
					if (string.equals("KARZA COMP SEARCH V2") || string.equals("KARZA COMPANY LLP CIN LOOKUP V2")) {
						if (hm.get(string) != null) {
							logger.info(
									" inside KARZA COMP SEARCH V2 || KARZA COMPANY LLP CIN LOOKUP V2 request  :: Start");
							String correlationId = UniqueId.getUniqueId();
							try {
								IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
								KarzaService kservice = ibsAllServiceRequest.getKarza();
								ibs.setAllowConsent(kservice.getConsent());
								KarzaCompSearchAndllplookup cmpnyname = kservice.getCompSearchAndllplookup();
								if (cmpnyname != null) {
									ibs.setCompanyNameKarza(cmpnyname.getCompanyName());
								}
								ibs.setRequest_status(initiated);
								ibs.setCreatedTime(cal.getTime());
								ibs.setCorrelationid(correlationId);
								ibs.setService_provider(string);
								ibs.setTransaction(uniqueid);
								long id = Long.parseLong(hm.get(string));
								ibs.setSeq_id(id);
								getSession().saveOrUpdate(ibs);
								flag = true;
							} catch (Exception e) {
								logger.error(
										" error occured during INITIATED state of KARZA COMP SEARCH V2 || KARZA COMPANY LLP CIN LOOKUP V2 request with CorrelationId :: "
												+ correlationId);
							}
							logger.info(
									" inside KARZA COMP SEARCH V2 || KARZA COMPANY LLP CIN LOOKUP V2 request  INITIATED successfully  :: End");
						}
					} // if compsearch && LLPlOOKup v2 Karza

					if (string.equals("KARZA MCA SIGNATURE V2") && hm.get(string) != null) {
						logger.info(" inside KARZA MCA SIGNATURE V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA MCA SIGNATURE V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA MCA SIGNATURE V2 request  INITIATED successfully  :: End");
					} // if mca v2 Karza

					if (string.equals("KARZA COMPANY LLP IDENTIFICATION V2") && hm.get(string) != null) {
						logger.info(" inside KARZA COMPANY LLP IDENTIFICATION V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA COMPANY LLP IDENTIFICATION V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA COMPANY LLP IDENTIFICATION V2 request  INITIATED successfully  :: End");
					} // if llp v2 Karza

					if (string.equals("KARZA GST AUTHENTICATION V2") && hm.get(string) != null) {
						logger.info(" inside KARZA GST AUTHENTICATION V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaGstIdentification gstauth = kservice.getGstIdenAuth();
							if (gstauth != null) {
								ibs.setGstin(gstauth.getGstin());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA GST AUTHENTICATION V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA GST AUTHENTICATION V2 request  INITIATED successfully  :: End");
					} // if gstauth v2 Karza

					if (string.equals("PAN VALIDATION") && hm.get(string) != null) {
						logger.info(" inside PAN VALIDATION request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of PAN VALIDATION request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside PAN VALIDATION request  INITIATED successfully  :: End");
					}

					if (string.equals("KARZA ADDRESS MATCHING V2") && hm.get(string) != null) {
						logger.info(" inside KARZA ADDRESS MATCHING V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaAddressMatching address = kservice.getAddressMatching();
							if (address != null) {
								ibs.setAddressMatch1(address.getAddress1());
								ibs.setAddressMatch2(address.getAddress2());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA ADDRESS MATCHING V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA ADDRESS MATCHING V2 request  INITIATED successfully  :: End");
					}

					if (string.equals("KARZA UDYOG AADHAR V2") && hm.get(string) != null) {
						logger.info(" inside KARZA UDYOG AADHAR V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA UDYOG AADHAR V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA UDYOG AADHAR V2 request  INITIATED successfully  :: End");
					}

					if (string.equals("KARZA NREGA V2") && hm.get(string) != null) {
						logger.info(" inside KARZA NREGA V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							KarzaNrega nrega = kservice.getNrega();
							if (nrega != null) {
								ibs.setJobcardid(nrega.getJobcardid());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA NREGA V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA NREGA V2 request  INITIATED successfully  :: End");
					}

					if (string.equals("CREDITVIDYA EMAIL SAVE") && hm.get(string) != null) {
						logger.info(" inside CREDITVIDYA EMAIL SAVE request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							CreditVidhyaIbsRequest crservice = ibsAllServiceRequest.getCreditVidhya();
							if (crservice != null) {
								ibs.setUniqueId(crservice.getUniqueId());
								ibs.setLeadId(crservice.getLeadId());
								ibs.setTransactionId(crservice.getTransactionId());
								ibs.setLosId(crservice.getLosId());
								ibs.setCompanyName(crservice.getCompanyName());
								ibs.setOfficeAddressLine1(crservice.getOfficeAddressLine1());
								ibs.setOfficeAddressLine2(crservice.getOfficeAddressLine2());
								ibs.setOfficeAddressLine3(crservice.getOfficeAddressLine3());
								ibs.setOfficeCity(crservice.getOfficeCity());
								ibs.setOfficePinCode(crservice.getOfficePinCode());
								ibs.setOfficeState(crservice.getOfficeState());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of CREDITVIDYA EMAIL SAVE request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside CREDITVIDYA EMAIL SAVE request  INITIATED successfully  :: End");
					} // if Credit vidhya
					if (string.equals("KOTAK REVERSAL") && hm.get(string) != null) {
						logger.info(" inside CREDITVIDYA EMAIL SAVE request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KotakIbsRequest kotakservice = ibsAllServiceRequest.getKotak();
							if (kotakservice != null) {
								ibs.setMessageId(kotakservice.getMessageId());
								ibs.setMsgSource(kotakservice.getMsgSource());
								ibs.setClientCode(kotakservice.getClientCode());
								ibs.setPaymentDt(kotakservice.getPaymentDt());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of CREDITVIDYA EMAIL SAVE request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside CREDITVIDYA EMAIL SAVE request  INITIATED successfully  :: End");
					} // if Credit vidhya

					if (string.equals("KOTAK PAYMENT") && hm.get(string) != null) {
						logger.info(" inside KOTAK PAYMENT request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KotakIbsRequest kservice = ibsAllServiceRequest.getKotak();
							if (kservice != null) {
								ibs.setMessageId(kservice.getMessageId() != null ? kservice.getMessageId() : "");
								ibs.setMsgSource(kservice.getMsgSource() != null ? kservice.getMsgSource() : "");
								ibs.setClientCode(kservice.getClientCode() != null ? kservice.getClientCode() : "");
								ibs.setBatchRefNmbr(kservice.getBatchRefNmbr() != null ? kservice.getBatchRefNmbr() : "");
								ibs.setHeaderChecksum(
										kservice.getHeaderChecksum() != null ? kservice.getHeaderChecksum() : "");
								ibs.setReqRF1(kservice.getReqRF1() != null ? kservice.getReqRF1() : "");
								ibs.setReqRF2(kservice.getReqRF2() != null ? kservice.getReqRF2() : "");
								ibs.setReqRF3(kservice.getReqRF3() != null ? kservice.getReqRF3() : "");
								ibs.setReqRF4(kservice.getReqRF4() != null ? kservice.getReqRF4() : "");
								ibs.setReqRF5(kservice.getReqRF5() != null ? kservice.getReqRF5() : "");
								ibs.setInstRefNo(kservice.getInstRefNo() != null ? kservice.getInstRefNo() : "");
								ibs.setCompanyId(kservice.getCompanyId() != null ? kservice.getCompanyId() : "");
								ibs.setCompBatchId(kservice.getCompBatchId() != null ? kservice.getCompBatchId() : "");
								ibs.setConfidentialInd(
										kservice.getConfidentialInd() != null ? kservice.getConfidentialInd() : "");
								ibs.setMyProdCode(kservice.getMyProdCode() != null ? kservice.getMyProdCode() : "");
								ibs.setCompTransNo(kservice.getCompTransNo() != null ? kservice.getCompTransNo() : "");
								ibs.setPayMode(kservice.getPayMode() != null ? kservice.getPayMode() : "");
								ibs.setTxnAmnt(kservice.getTxnAmnt() != null ? kservice.getTxnAmnt() : "");
								ibs.setAccountNo(kservice.getAccountNo() != null ? kservice.getAccountNo() : "");
								ibs.setDrRefNmbr(kservice.getDrRefNmbr() != null ? kservice.getDrRefNmbr() : "");
								ibs.setDrDesc(kservice.getDrDesc() != null ? kservice.getDrDesc() : "");
								ibs.setPaymentDt(kservice.getPaymentDt() != null ? kservice.getPaymentDt() : "");
								ibs.setBankCdInd(kservice.getBankCdInd() != null ? kservice.getBankCdInd() : "");
								ibs.setBeneBnkCd(kservice.getBeneBnkCd() != null ? kservice.getBeneBnkCd() : "");
								ibs.setRecBrCd(kservice.getRecBrCd() != null ? kservice.getRecBrCd() : "");
								ibs.setBeneAcctNo(kservice.getBeneAcctNo() != null ? kservice.getBeneAcctNo() : "");
								ibs.setBeneName(kservice.getBeneName() != null ? kservice.getBeneName() : "");
								ibs.setBeneCode(kservice.getBeneCode() != null ? kservice.getBeneCode() : "");
								ibs.setBeneEmail(kservice.getBeneEmail() != null ? kservice.getBeneEmail() : "");
								ibs.setBeneFax(kservice.getBeneFax() != null ? kservice.getBeneFax() : "");
								ibs.setBeneMb(kservice.getBeneMb() != null ? kservice.getBeneMb() : "");
								ibs.setBeneAddr1(kservice.getBeneAddr1() != null ? kservice.getBeneAddr1() : "");
								ibs.setBeneAddr2(kservice.getBeneAddr2() != null ? kservice.getBeneAddr2() : "");
								ibs.setBeneAddr3(kservice.getBeneAddr3() != null ? kservice.getBeneAddr3() : "");
								ibs.setBeneAddr4(kservice.getBeneAddr4() != null ? kservice.getBeneAddr4() : "");
								ibs.setBeneAddr5(kservice.getBeneAddr5() != null ? kservice.getBeneAddr5() : "");

								ibs.setCity(kservice.getCity() != null ? kservice.getCity() : "");
								ibs.setZip(kservice.getZip() != null ? kservice.getZip() : "");
								ibs.setCountry(kservice.getCountry() != null ? kservice.getCountry() : "");
								ibs.setState(kservice.getState() != null ? kservice.getState() : "");

								ibs.setTelephoneNo(kservice.getTelephoneNo() != null ? kservice.getTelephoneNo() : "");
								ibs.setBeneId(kservice.getBeneId() != null ? kservice.getBeneId() : "");

								ibs.setBeneTaxId(kservice.getBeneTaxId() != null ? kservice.getBeneTaxId() : "");
								ibs.setAuthPerson(kservice.getAuthPerson() != null ? kservice.getAuthPerson() : "");
								ibs.setAuthPersonId(kservice.getAuthPersonId() != null ? kservice.getAuthPersonId() : "");

								ibs.setDeliveryMode(kservice.getDeliveryMode() != null ? kservice.getDeliveryMode() : "");
								ibs.setPayoutLoc(kservice.getPayoutLoc() != null ? kservice.getPayoutLoc() : "");
								ibs.setPickupBr(kservice.getPickupBr() != null ? kservice.getPickupBr() : "");

								ibs.setPaymentRef(kservice.getPaymentRef() != null ? kservice.getPaymentRef() : "");
								ibs.setChgBorneBy(kservice.getChgBorneBy() != null ? kservice.getChgBorneBy() : "");

								ibs.setInstDt(kservice.getChgBorneBy() != null ? kservice.getChgBorneBy() : "");
								ibs.setMicrNo(kservice.getChgBorneBy() != null ? kservice.getChgBorneBy() : "");
								ibs.setCreditRefNo(kservice.getChgBorneBy() != null ? kservice.getChgBorneBy() : "");
								ibs.setPaymentDtl(kservice.getPaymentDtl() != null ? kservice.getPaymentDtl() : "");
								ibs.setPaymentDtl1(kservice.getPaymentDtl1() != null ? kservice.getPaymentDtl1() : "");
								ibs.setPaymentDtl2(kservice.getPaymentDtl2() != null ? kservice.getPaymentDtl2() : "");
								ibs.setPaymentDtl3(kservice.getPaymentDtl3() != null ? kservice.getPaymentDtl3() : "");

								ibs.setMailToAddr1(kservice.getMailToAddr1() != null ? kservice.getMailToAddr1() : "");
								ibs.setMailToAddr2(kservice.getMailToAddr2() != null ? kservice.getMailToAddr2() : "");
								ibs.setMailToAddr3(kservice.getMailToAddr3() != null ? kservice.getMailToAddr3() : "");
								ibs.setMailToAddr4(kservice.getMailToAddr4() != null ? kservice.getMailToAddr4() : "");

								ibs.setMailTo(kservice.getMailTo() != null ? kservice.getMailTo() : "");

								ibs.setExchDoc(kservice.getExchDoc() != null ? kservice.getExchDoc() : "");
								ibs.setInstChecksum(kservice.getInstChecksum() != null ? kservice.getInstChecksum() : "");
								ibs.setInstRF1(kservice.getInstRF1() != null ? kservice.getInstRF1() : "");
								ibs.setInstRF2(kservice.getInstRF3() != null ? kservice.getInstRF2() : "");
								ibs.setInstRF3(kservice.getInstRF3() != null ? kservice.getInstRF3() : "");
								ibs.setInstRF4(kservice.getInstRF4() != null ? kservice.getInstRF4() : "");
								ibs.setInstRF5(kservice.getInstRF5() != null ? kservice.getInstRF5() : "");
								ibs.setInstRF6(kservice.getInstRF6() != null ? kservice.getInstRF6() : "");
								ibs.setInstRF7(kservice.getInstRF7() != null ? kservice.getInstRF7() : "");
								ibs.setInstRF8(kservice.getInstRF8() != null ? kservice.getInstRF8() : "");
								ibs.setInstRF9(kservice.getInstRF9() != null ? kservice.getInstRF9() : "");
								ibs.setInstRF10(kservice.getInstRF10() != null ? kservice.getInstRF10() : "");
								ibs.setInstRF11(kservice.getInstRF11() != null ? kservice.getInstRF11() : "");
								ibs.setInstRF12(kservice.getInstRF12() != null ? kservice.getInstRF12() : "");
								ibs.setInstRF13(kservice.getInstRF13() != null ? kservice.getInstRF13() : "");
								ibs.setInstRF14(kservice.getInstRF14() != null ? kservice.getInstRF14() : "");
								ibs.setInstRF15(kservice.getInstRF15() != null ? kservice.getInstRF15() : "");
								ibs.setEnrichment(kservice.getEnrichment() != null ? kservice.getEnrichment() : "");
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KOTAK PAYMENT request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KOTAK PAYMENT request  INITIATED successfully  :: End");
					} // if kotak

					if (string.equals("KARZA ELEC V2") && hm.get(string) != null) {
						logger.info(" inside KARZA ELEC V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							if (kservice != null) {
								ibs.setAllowConsent(kservice.getConsent());
								KarzaElectricity electricity = kservice.getElectricity();
								ibs.setConsumer_id(electricity.getConsumerId());
								ibs.setKarza_service_provider(electricity.getServiceProvider());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA ELEC V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA ELEC V2 request  INITIATED successfully  :: End");
					} // if DL elec
					if (string.equals("KARZA PAN") && hm.get(string) != null) {
						logger.info(" inside KARZA PAN request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(" error occured during INITIATED state of KARZA PAN request with CorrelationId :: "
									+ correlationId);
						}
						logger.info(" inside KARZA PAN request  INITIATED successfully  :: End");
					} // if DL Karza

					if (string.equals("KARZA EMAIL AUTH V2") && hm.get(string) != null) {
						logger.info(" inside KARZA EMAIL AUTH request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(" error occured during INITIATED state of KARZA EMAIL AUTH request with CorrelationId :: "
									+ correlationId);
						}
						logger.info(" inside KARZA EMAIL AUTH request  INITIATED successfully  :: End");
					}

					if (string.equals("TELE V3") && hm.get(string)!=null) {

						logger.info(" inside TELE V3 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setTele_no1(ibsrequest.getTele_no1());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of TELE V3 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside TELE V3 request  INITIATED successfully  :: End");


					}
					if(string.equals("KARZA CA MEMBERSHIP AUTH V2") && hm.get(string)!=null) {

						logger.info(" inside KARZA CA MEMBERSHIP AUTH V2 request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setMembership_no(kservice.getCaMembershipAuth().getMembershipNo());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of KARZA CA MEMBERSHIP AUTH V2 request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside KARZA CA MEMBERSHIP AUTH V2 request  INITIATED successfully  :: End");


					}

					if (string.equals("KARZA EPF OTP AUTH V2") && hm.get(string) != null) {
						logger.info(" inside KARZA EPF OTP AUTH request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(" error occured during INITIATED state of KARZA EPF OTP AUTH request with CorrelationId :: "
									+ correlationId);
						}
						logger.info(" inside KARZA EPF OTP AUTH request  INITIATED successfully  :: End");
					}

					if (string.equals("KARZA AADHAR V2") && hm.get(string) != null) {
						logger.info(" inside KARZA AADHAR request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							KarzaService kservice = ibsAllServiceRequest.getKarza();
							ibs.setAllowConsent(kservice.getConsent());
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(" error occured during INITIATED state of KARZA AADHAR v2 request with CorrelationId :: "
									+ correlationId);
						}
						logger.info(" inside KARZA AADHAR V2 request  INITIATED successfully  :: End");
					}

					if (string.equals("AUTH DELIVERY") && hm.get(string) != null) {
						logger.info(" inside AUTH DELIVERY request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
							List<PersonAddressess> addresses = common.getAddresses();
							StringBuilder sb = new StringBuilder();
							for (PersonAddressess personAddressess : addresses) {
								sb.append(personAddressess.getAddressline1());
								sb.append(personAddressess.getAddressline2());
								sb.append(personAddressess.getAddressline3());
								sb.append(personAddressess.getAddressline4());
								sb.append(personAddressess.getAddressline5());
							}
							ibs.setAddress1(sb + "");
							KickoffAUTHIbsRequest auth = ibsAllServiceRequest.getKickOFFAuth();
							if (auth != null) {
								ibs.setStg_one_hit_id(auth.getStgOneHitId());
								;
								ibs.setStg_two_hit_id(auth.getStgTwoHitId());
								ibs.setSingle_action_session_id(auth.getSingleActionSessionId());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of AUTH DELIVERY request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside AUTH DELIVERY request  INITIATED successfully  :: End");
					} // if Single Action

					try {
						if (string.equals("EXPERIAN KICKOFF") && hm.get(string) != null) {
							logger.info(" inside EXPERIAN KICKOFF request  :: Start");
							String correlationId = UniqueId.getUniqueId();
							try {
								IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
								CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
								List<PersonAddressess> addresses = common.getAddresses();
								StringBuilder sb = new StringBuilder();
								for (PersonAddressess personAddressess : addresses) {
									sb.append(personAddressess.getAddressline1() + " ");
									sb.append(personAddressess.getAddressline2() + " ");
									sb.append(personAddressess.getAddressline3() + " ");
									sb.append(personAddressess.getAddressline4() + " ");
									sb.append(personAddressess.getAddressline5());
								}
								KickoffSingleACtionIbsRequest kickoff = ibsAllServiceRequest.getKickOFFSingleAction();
								if (kickoff != null) {
									ibs.setClientName(kickoff.getClientName());
									ibs.setAllowCaptcha(kickoff.getAllowCaptcha());
									ibs.setAllowEdit(kickoff.getAllowEdit());
									ibs.setAllowInput(kickoff.getAllowInput());
									ibs.setAllowConsent(kickoff.getAllowConsent());
									ibs.setAllowConsent_additional(kickoff.getAllowConsent_additional());
									ibs.setAllowEmailVerify(kickoff.getAllowEmailVerify());
									ibs.setAllowVoucher(kickoff.getAllowVoucher());
									ibs.setNoValidationByPass(kickoff.getNoValidationByPass());
									ibs.setEmailConditionalByPass(kickoff.getEmailConditionalByPass());
									ibs.setFlatno(kickoff.getFlatno());
									ibs.setBuildingName(kickoff.getBuildingName());
									ibs.setRoadName(kickoff.getRoadName());
									ibs.setReason(kickoff.getReason());
									ibs.setVoucherCode(kickoff.getVoucherCode());
								}

								ibs.setAddress1(sb + "");
								ibs.setRequest_status(initiated);
								ibs.setCreatedTime(cal.getTime());
								ibs.setCorrelationid(correlationId);
								ibs.setService_provider(string);
								ibs.setTransaction(uniqueid);
								long id = Long.parseLong(hm.get(string));
								ibs.setSeq_id(id);
								getSession().saveOrUpdate(ibs);
								flag = true;
							} catch (Exception e) {
								logger.error(
										" error occured during INITIATED state of EXPERIAN KICKOFF request with CorrelationId :: "
												+ correlationId);
							}
							logger.info(" inside EXPERIAN KICKOFF request  INITIATED successfully  :: End");
						} // Auth Deleviry
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (string.equals("GENERATE QUESTION") && hm.get(string) != null) {
						logger.info(" inside GENERATE QUESTION request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
							List<PersonAddressess> addresses = common.getAddresses();
							StringBuilder sb = new StringBuilder();
							for (PersonAddressess personAddressess : addresses) {
								sb.append(personAddressess.getAddressline1() + " ");
								sb.append(personAddressess.getAddressline2() + " ");
								sb.append(personAddressess.getAddressline3() + " ");
								sb.append(personAddressess.getAddressline4() + " ");
								sb.append(personAddressess.getAddressline5());
							}
							KickoffGenQueIbsRequest kickoff = ibsAllServiceRequest.getKickOFFGenQue();
							ibs.setCaptch_code(kickoff.getCaptchCode());
							// change
							KickoffAUTHIbsRequest auth = ibsAllServiceRequest.getKickOFFAuth();
							if (auth != null) {
								ibs.setStg_one_hit_id(auth.getStgOneHitId());
								;
								ibs.setStg_two_hit_id(auth.getStgTwoHitId());
								ibs.setSingle_action_session_id(auth.getSingleActionSessionId());
							}
							ibs.setAddress1(sb + "");
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of GENERATE QUESTION request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside GENERATE QUESTION request  INITIATED successfully  :: End");
					}

					try {
						if (string.equals("EQUIFAX PCS") && hm.get(string) != null) {
							logger.info(" inside EQUIFAX PCS request  :: Start");
							String correlationId = UniqueId.getUniqueId();
							try {
								IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
								CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
								List<PersonAddressess> addresses = common.getAddresses();
								StringBuilder sb = new StringBuilder();
								for (PersonAddressess personAddressess : addresses) {
									sb.append(personAddressess.getAddressline1() + " ");
									sb.append(personAddressess.getAddressline2() + " ");
									sb.append(personAddressess.getAddressline3() + " ");
									sb.append(personAddressess.getAddressline4() + " ");
									sb.append(personAddressess.getAddressline5());
								}
								ibs.setAddress1(sb + "");
								ibs.setRequest_status(initiated);
								ibs.setCreatedTime(cal.getTime());
								ibs.setCorrelationid(correlationId);
								ibs.setService_provider(string);
								ibs.setTransaction(uniqueid);
								long id = Long.parseLong(hm.get(string));
								ibs.setSeq_id(id);
								getSession().saveOrUpdate(ibs);
								flag = true;
							} catch (Exception e) {
								logger.error(
										" error occured during INITIATED state of EQUIFAX PCS request with CorrelationId :: "
												+ correlationId);
							}
							logger.info(" inside EQUIFAX PCS request  INITIATED successfully  :: End");
						}
					} catch (Exception e) {
						logger.error(" error occured during EQUIFAX PCS request  INITIATED Inside retriveRequest ");
					}
					if (string.equals("EQUIFAX MCR") && hm.get(string) != null) {
						logger.info(" inside EQUIFAX MCR request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
							List<PersonAddressess> addresses = common.getAddresses();
							StringBuilder sb = new StringBuilder();
							for (PersonAddressess personAddressess : addresses) {
								sb.append(personAddressess.getAddressline1() + " ");
								sb.append(personAddressess.getAddressline2() + " ");
								sb.append(personAddressess.getAddressline3() + " ");
								sb.append(personAddressess.getAddressline4() + " ");
								sb.append(personAddressess.getAddressline5());
							}
							ibs.setAddress1(sb + "");
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of EQUIFAX MCR request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside EQUIFAX MCR request  INITIATED successfully  :: End");
					}
					if (string.equals("EQUIFAX VID") && hm.get(string) != null) {
						logger.info(" inside EQUIFAX VID request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
							List<PersonAddressess> addresses = common.getAddresses();
							StringBuilder sb = new StringBuilder();
							for (PersonAddressess personAddressess : addresses) {
								sb.append(personAddressess.getAddressline1() + " ");
								sb.append(personAddressess.getAddressline2() + " ");
								sb.append(personAddressess.getAddressline3() + " ");
								sb.append(personAddressess.getAddressline4() + " ");
								sb.append(personAddressess.getAddressline5());
							}
							ibs.setAddress1(sb + "");
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of EQUIFAX VID request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside EQUIFAX VID request  INITIATED successfully  :: End");
					}
					if (string.equals("EQUIFAX EVDR") && hm.get(string) != null) {
						logger.info(" inside EQUIFAX EVDR request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
							List<PersonAddressess> addresses = common.getAddresses();
							StringBuilder sb = new StringBuilder();
							for (PersonAddressess personAddressess : addresses) {
								sb.append(personAddressess.getAddressline1() + " ");
								sb.append(personAddressess.getAddressline2() + " ");
								sb.append(personAddressess.getAddressline3() + " ");
								sb.append(personAddressess.getAddressline4() + " ");
								sb.append(personAddressess.getAddressline5());
							}
							ibs.setAddress1(sb + "");
							if(ibsAllServiceRequest!=null && ibsAllServiceRequest.getEquifaxEVDR()!=null)
							{
								ibs.setAmount(ibsAllServiceRequest.getEquifaxEVDR().getAmount());
							}
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(
									" error occured during INITIATED state of EQUIFAX EVDR request with CorrelationId :: "
											+ correlationId);
						}
						logger.info(" inside EQUIFAX EVDR request  INITIATED successfully  :: End");
					}
					try {
						if (string.equals("CIBIL V2") && hm.get(string) != null) {
							logger.info(" inside CIBIL V2 request  :: Start");
							String correlationId = UniqueId.getUniqueId();
							try {
								IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
								CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
								List<PersonAddressess> addresses = common.getAddresses();
								for (PersonAddressess personAddressess : addresses) {
									ibs.setAddress1(personAddressess.getAddressline1());
									ibs.setAddress_type1(personAddressess.getAddresscategory());
									ibs.setCity1(personAddressess.getCity());
									ibs.setPin1(personAddressess.getPin());
									ibs.setState1(personAddressess.getState());
								}

								CibilIbsRequest cibilIbsRequest = ibsAllServiceRequest.getCibil();
								if (cibilIbsRequest != null) {
									CibilV2IBSRequest cibilv2 = cibilIbsRequest.getV2();
									ibs.setTxnAmnt(cibilv2.getEnquiryAmount());
									ibs.setPassportType(cibilv2.getReportType());
									ibs.setPayMode(cibilv2.getLoanPurpose());
									ibs.setExchDoc(cibilv2.getFutureUse());
									ibs.setFullname(cibilv2.getAlternateName());
									ibs.setAccountNo(cibilv2.getAccount1());
									ibs.setBeneCode(cibilv2.getGstStateCode());
									ibs.setBatchRefNmbr(cibilv2.getBranchReferenceNo());
									ibs.setInstRefNo(cibilv2.getCentreReferenceNo());

								}

								ibs.setScoretype(cibilIbsRequest.getScoreType());
								ibs.setRequest_status(initiated);
								ibs.setCreatedTime(cal.getTime());
								ibs.setCorrelationid(correlationId);
								ibs.setService_provider(string);
								ibs.setTransaction(uniqueid);
								long id = Long.parseLong(hm.get(string));
								ibs.setSeq_id(id);
								getSession().saveOrUpdate(ibs);
								flag = true;
							} catch (Exception e) {
								logger.error(" error occured during INITIATED state of CIBIL request with CorrelationId :: "
										+ correlationId);
							}
							logger.info(" inside CIBIL request  INITIATED successfully  :: End");
						}
					} catch (Exception e) {
						logger.error(" error occured during CIBIL V2 request  INITIATED Inside retriveRequest ");
					}

					try {
						if (string.equals("CIBIL") && hm.get(string) != null) {
							logger.info(" inside CIBIL request  :: Start");
							String correlationId = UniqueId.getUniqueId();
							try {
								IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
								CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
								List<PersonAddressess> addresses = common.getAddresses();
								for (PersonAddressess personAddressess : addresses) {
									ibs.setAddress1(personAddressess.getAddressline1());
									ibs.setAddress2(personAddressess.getAddressline2());
									ibs.setAddress3(personAddressess.getAddressline3());
									ibs.setAddress4(personAddressess.getAddressline4());
									ibs.setAddress5(personAddressess.getAddressline5());
								}
								CibilIbsRequest cibilIbsRequest = ibsAllServiceRequest.getCibil();
								ibs.setScoretype(cibilIbsRequest.getScoreType());
								ibs.setRequest_status(initiated);
								ibs.setCreatedTime(cal.getTime());
								ibs.setCorrelationid(correlationId);
								ibs.setService_provider(string);
								ibs.setTransaction(uniqueid);
								long id = Long.parseLong(hm.get(string));
								ibs.setSeq_id(id);
								getSession().saveOrUpdate(ibs);
								flag = true;
							} catch (Exception e) {
								logger.error(" error occured during INITIATED state of CIBIL request with CorrelationId :: "
										+ correlationId);
							}
							logger.info(" inside CIBIL request  INITIATED successfully  :: End");
						}
					} catch (Exception e) {
						logger.error(" error occured during CIBIL request  INITIATED Inside retriveRequest ");
					}
					if (string.equals("CRIF MFI") && hm.get(string) != null) {
						logger.info(" inside CRIF MFI request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
							List<PersonAddressess> addresses = common.getAddresses();
							StringBuilder sb = new StringBuilder();
							for (PersonAddressess personAddressess : addresses) {
								sb.append(personAddressess.getAddressline1() + " ");
								sb.append(personAddressess.getAddressline2() + " ");
								sb.append(personAddressess.getAddressline3() + " ");
								sb.append(personAddressess.getAddressline4() + " ");
								sb.append(personAddressess.getAddressline5());
							}
							ibs.setAddress1(sb + "");
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setService_provider(string);
							ibs.setTransaction(uniqueid);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(" error occured during INITIATED state of CRIF MFI request with CorrelationId :: "
									+ correlationId);
						}
						logger.info(" inside CRIF MFI request  INITIATED successfully  :: End");
					}
					if (string.equals("CRIF PCS") && hm.get(string) != null) {
						logger.info(" inside CRIF PCS request  :: Start");
						String correlationId = UniqueId.getUniqueId();
						try {
							IbRequestMaster ibs = (IbRequestMaster) ibsrequest.clone();
							CommonDataInRequest common = ibsAllServiceRequest.getDemographicInfo();
							List<PersonAddressess> addresses = common.getAddresses();
							StringBuilder sb = new StringBuilder();
							for (PersonAddressess personAddressess : addresses) {
								sb.append(personAddressess.getAddressline1() + " ");
								sb.append(personAddressess.getAddressline2() + " ");
								sb.append(personAddressess.getAddressline3() + " ");
								sb.append(personAddressess.getAddressline4() + " ");
								sb.append(personAddressess.getAddressline5());
							}
							ibs.setAddress1(sb + "");
							ibs.setRequest_status(initiated);
							ibs.setCreatedTime(cal.getTime());
							ibs.setCorrelationid(correlationId);
							ibs.setTransaction(uniqueid);
							ibs.setService_provider(string);
							long id = Long.parseLong(hm.get(string));
							ibs.setSeq_id(id);
							getSession().saveOrUpdate(ibs);
							flag = true;
						} catch (Exception e) {
							logger.error(" error occured during INITIATED state of CRIF PCS request with CorrelationId :: "
									+ correlationId);
						}
						logger.info(" inside CRIF PCS request  INITIATED successfully  :: End");
					}

				} // for loop
		} catch (Exception e) {
			logger.error(" error occured during INITIATED state Inside retriveRequest ");
		}
		return flag;
	}
	private IbRequestMaster initiateCommonData(IbsAllServiceRequest ibsAllServiceRequest) {
		IbRequestMaster ibsrequest = new IbRequestMaster();
		try {
			if (ibsAllServiceRequest != null) {
				CommonDataInRequest commonDataInRequest = ibsAllServiceRequest.getDemographicInfo();
				if (commonDataInRequest != null) {
					ibsrequest.setTitle(commonDataInRequest.getTitle()!=null?commonDataInRequest.getTitle():"");
					ibsrequest.setF_name(commonDataInRequest.getfName()!=null?commonDataInRequest.getfName().toUpperCase():"");
					ibsrequest.setM_name(commonDataInRequest.getmName()!=null?commonDataInRequest.getmName().toUpperCase():"");
					ibsrequest.setL_name(commonDataInRequest.getlName()!=null?commonDataInRequest.getlName().toUpperCase():""); 
					ibsrequest.setDob(commonDataInRequest.getDob());
					ibsrequest.setActual_email_addr(commonDataInRequest.getEmail());
					ibsrequest.setGender(commonDataInRequest.getGender());
					ibsrequest.setKey_person_name(commonDataInRequest.getKeyPersonName());
					ibsrequest.setKey_person_type(commonDataInRequest.getKeyPersonType());
					ibsrequest.setNominee_name(commonDataInRequest.getNomineeName());
					ibsrequest.setNominee_type(commonDataInRequest.getNomineeType());
					ibsrequest.setInquiry_purpose(commonDataInRequest.getInquiryPurpose());
					ibsrequest.setMaritalStatus(commonDataInRequest.getMaritalStatus());
					ibsrequest.setConsumer_id(commonDataInRequest.getConsumerId());
					List<PersonId> ids = commonDataInRequest.getIds();
					if (!ids.isEmpty()) {
						for (PersonId personId : ids) {

							if (personId.getIdName().equalsIgnoreCase("Pancard")) {
								ibsrequest.setIdname1(personId.getIdName());
								ibsrequest.setIdno1(personId.getIdNo());
							}
							if (personId.getIdName().equalsIgnoreCase("Passport")) {
								ibsrequest.setIdname2(personId.getIdName());
								ibsrequest.setIdno2(personId.getIdNo());
							}
							if (personId.getIdName().equalsIgnoreCase("aadhaar")) {
								ibsrequest.setAadhar(personId.getIdNo());
							}
							if (personId.getIdName().equalsIgnoreCase("uan")) {
								ibsrequest.setUan(personId.getIdNo());
							}
							if (personId.getIdName().equalsIgnoreCase("tan")) {
								ibsrequest.setTan(personId.getIdNo());
							}
							if (personId.getIdName().equalsIgnoreCase("cin")) {
								ibsrequest.setCin(personId.getIdNo());
							}
							if (personId.getIdName().equalsIgnoreCase("VoterID")) {
								ibsrequest.setIdname3(personId.getIdName());
								ibsrequest.setIdno3(personId.getIdNo());
							}
							if (personId.getIdName().equalsIgnoreCase("DL")) {
								ibsrequest.setIdname4(personId.getIdName());
								ibsrequest.setIdno4(personId.getIdNo());
							}
							if (personId.getIdName().equalsIgnoreCase("RationCard")) {
								ibsrequest.setIdname5(personId.getIdName());
								ibsrequest.setIdno5(personId.getIdNo());
							}
						} // for ids
					} // if ids
					List<PersonPhone> phones = commonDataInRequest.getPhones();
					if (!phones.isEmpty()) {
						int count = 0;
						for (PersonPhone phone : phones) {
							count++;
							if (count == 1) {
								ibsrequest.setTele_extn1(phone.getTelephoneExtn());
								ibsrequest.setTele_no1(phone.getTeleNo());
								ibsrequest.setTele_type1(phone.getType());
							}
							if (count == 2) {
								ibsrequest.setTele_extn2(phone.getTelephoneExtn());
								ibsrequest.setTele_no2(phone.getTeleNo());
								ibsrequest.setTele_type2(phone.getType());
							}
							if (count == 3) {
								ibsrequest.setTele_extn3(phone.getTelephoneExtn());
								ibsrequest.setTele_no3(phone.getTeleNo());
								ibsrequest.setTele_type3(phone.getType());
							}
							if (count == 4) {
								ibsrequest.setTele_extn4(phone.getTelephoneExtn());
								ibsrequest.setTele_no4(phone.getTeleNo());
								ibsrequest.setTele_type4(phone.getType());
							}
							if (count == 5) {
								ibsrequest.setTele_extn5(phone.getTelephoneExtn());
								ibsrequest.setTele_no5(phone.getTeleNo());
								ibsrequest.setTele_type5(phone.getType());
							}

						} // for phone
					} // if phone

					List<PersonAddressess> addressess = commonDataInRequest.getAddresses();
					if (!addressess.isEmpty()) {
						int count = 0;

						for (PersonAddressess address : addressess) {
							count++;

							if (count == 1) {
								ibsrequest.setResidence_code1(address.getResidencecode());
								ibsrequest.setCity1(address.getCity());
								ibsrequest.setState1(address.getState());
								ibsrequest.setPin1(address.getPin());
								ibsrequest.setAddress_type1(address.getAddresscategory());
							}
							if (count == 2) {
								ibsrequest.setResidence_code1(address.getResidencecode());
								ibsrequest.setCity1(address.getCity());
								ibsrequest.setState1(address.getState());
								ibsrequest.setPin1(address.getPin());
								ibsrequest.setAddress_type1(address.getAddresscategory());
							}


						} // for address

					} // if address

					List<PersonRelation> relations = commonDataInRequest.getRelations();
					if (!relations.isEmpty()) {
						int count = 0;
						for (PersonRelation relation : relations) {
							count++;
							if (count == 1) {
								ibsrequest.setRelation_name1(relation.getName());
								ibsrequest.setRelation1(relation.getRelation());
							}
							if (count == 2) {
								ibsrequest.setRelation_name2(relation.getName());
								ibsrequest.setRelation2(relation.getRelation());
							}
							if (count == 3) {
								ibsrequest.setRelation_name3(relation.getName());
								ibsrequest.setRelation3(relation.getRelation());
							}
							if (count == 4) {
								ibsrequest.setRelation_name4(relation.getName());
								ibsrequest.setRelation4(relation.getRelation());
							}
							if (count == 5) {
								ibsrequest.setRelation_name5(relation.getName());
								ibsrequest.setRelation5(relation.getRelation());
							}

						} // for relation
					} // if relation

				} // if common

			} // if allService

		} catch (Exception e) {
			logger.error(" error occured during INITIATED COMMONDATA");
		}
		return ibsrequest;
	}

	@Override
	public String retriveCredential(String tid) {
		String list = null;
		try {
			Criteria criteria = getSession().createCriteria(IbRequestMaster.class);
			criteria.add(Restrictions.eq("transaction", tid));
			StringBuilder sb = new StringBuilder();
			@SuppressWarnings("unchecked")
			List<IbRequestMaster> ibreq = criteria.list();
			for (IbRequestMaster ibRequestMaster : ibreq) {
				if (ibRequestMaster.getPdf_path() == null) {
					sb.append(ibRequestMaster.getService_provider() + "#" + ibRequestMaster.getRequest_status() + "#"
							+ "NA" + "*");
				} else {
					sb.append(ibRequestMaster.getService_provider() + "#" + ibRequestMaster.getRequest_status() + "#"
							+ ibRequestMaster.getPdf_path() + "*");
				}
			}
			list = sb.toString();
		} catch (Exception e) {
			logger.error(" unable to retriveCredential :: " + e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HistoryCridential> retriveHistory(String tid) {
		logger.info(" inside retriveHistory() :: Start");
		List<HistoryCridential> history = new ArrayList<HistoryCridential>();
		String date = null;
		try {
			logger.info(" loading History for TID :: " + tid);
			Criteria criteria = getSession().createCriteria(IbRequestMaster.class);
			criteria.add(Restrictions.eq("transaction", tid));
			criteria.addOrder(Order.desc("createdTime"));
			List<IbRequestMaster> ibreq = criteria.list();
			for (IbRequestMaster ibRequestMaster : ibreq) {
				HistoryCridential hr = new HistoryCridential();
				if (ibRequestMaster.getCreatedTime() != null) {
					date = ibRequestMaster.getCreatedTime().toString().substring(0,
							ibRequestMaster.getCreatedTime().toString().lastIndexOf("."));
				}
				hr.setCrdate(date != null ? date : "");
				String finalpdfPath = ibRequestMaster.getPdf_path();
				if(finalpdfPath!=null && finalpdfPath.contains("/"))
				{
					finalpdfPath=finalpdfPath.replaceAll("/","~~");
				}
				else if(finalpdfPath!=null)
				{
					finalpdfPath=finalpdfPath.replaceAll("\\\\","@@");	
				}
				hr.setPdfpath(finalpdfPath);
				hr.setCorrelationid(ibRequestMaster.getCorrelationid());
				hr.setfName(ibRequestMaster.getF_name());
				hr.setmName(ibRequestMaster.getM_name());
				hr.setlName(ibRequestMaster.getL_name());
				hr.setServiceProvider(ibRequestMaster.getService_provider());
				hr.setStatus(ibRequestMaster.getRequest_status());
				history.add(hr);

			}

		} catch (Exception e) {
			logger.error(" unable to retriveHistory :: " + e);
		}
		logger.info(" inside retriveHistory() :: END");
		return history;
	}

	@Override
	public List<FibitRequest> getIbsAccountDetail(String correlationid) {
		logger.info(" inside getIbsAccountDetail() :: START");
		try {
			Criteria criteria = getSession().createCriteria(FibitRequest.class);
			criteria.add(Restrictions.eq("corelationid", correlationid));
			@SuppressWarnings("unchecked")
			List<FibitRequest> ibreq = criteria.list();
			logger.info(" inside getIbsAccountDetail() :: END");
			return ibreq;
		} catch (Exception e) {
			logger.error(" inside getIbsAccountDetail have an exaception :: "+e);
			return Collections.emptyList();
		}

	}

	public List<NomineeDetails> getIbsNomineeDetail(String correlationid) {
		logger.info(" inside getIbsAccountDetail() :: START");
		try {
			Criteria criteria = getSession().createCriteria(NomineeDetails.class);
			criteria.add(Restrictions.eq("corelationid", correlationid));
			@SuppressWarnings("unchecked")
			List<NomineeDetails> ibreq = criteria.list();
			logger.info(" inside getIbsAccountDetail() :: END");
			return ibreq;
		} catch (Exception e) {
			logger.error(" inside getIbsAccountDetail have an exaception :: "+e);
			return Collections.emptyList();
		}

	}
}
