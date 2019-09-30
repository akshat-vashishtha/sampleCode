package com.qualtech.cibilv2.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
@Table(name="IB_C2_RES_ROOT")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Root implements Serializable{

	private static final long serialVersionUID = 9077418141833199455L;
	
	@Transient
	private ReportType reportType;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "root" ,cascade=CascadeType.ALL)
	private List<ReportTypeField> reportTypefield;
	
	@Transient
	private HeaderInformation headerInformation;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "root" ,cascade=CascadeType.ALL)
	private List<HeaderInformationField> headerInformationField;
	
	
	
	@Transient
	private OtherInformation otherInformation;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "root", cascade = CascadeType.ALL)
	private List<Field> otherInformationfield;

	@Transient
	private SearchInformation searchInformation;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "root", cascade = CascadeType.ALL)
	private List<SearchInformationField> searchInformationField;

	@Transient
	private ConsumerResponseStatus consumerResponseStatus;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "root", cascade = CascadeType.ALL)
	private List<ConsumerResInfoDataField> consumerResInfoDataField;

	@Transient
	private ConsumerInformation consumerInformation;
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "root", cascade = CascadeType.ALL)
	private List<ConsumerInformationDataField> consumerInformationDataField;
	
	@Transient
	private CreditReportSummary creditReportSummary;
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy="root",cascade= CascadeType.ALL)
	private List<CrReportSummInfoDataField> creditReportSummmaryField;
	
	@Transient
	private Alerts alerts;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,mappedBy="root",cascade=CascadeType.ALL)
	private List<AlertsField> alertsField;
	
	@Transient
	private Score score;
	@Transient
	private PlScore plScore;
	@Transient
	private MfiEmploymentInformation mfiEmploymentInformation;
	@JsonProperty("MFI")
	@Transient
	private List<Mfi> mfi;
	@Column(name = "COBORROWER")
	private String coborrower;
	@Transient
	private RecentEnquiry recentEnquiry;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY,mappedBy="root",cascade=CascadeType.ALL)
	private List<RecentEnquiryField> recentEnquiryField;
	
	@Transient
	private ErrorResponse errorResponse;
	
	@Column(name = "UNIQUEID")
	@Id
	private Long uniqueid;

	
	public List<ReportTypeField> getReportTypefield() {
		return reportTypefield;
	}
	public void setReportTypefield(List<ReportTypeField> reportTypefield) {
		this.reportTypefield = reportTypefield;
	}
	public List<RecentEnquiryField> getRecentEnquiryField() {
		
		if(recentEnquiry!=null && recentEnquiry.getInformationData()!=null && recentEnquiry.getInformationData()!=null) {
			recentEnquiryField=recentEnquiry.getInformationData();
			for(RecentEnquiryField recentEnqFld:recentEnquiryField) {
				recentEnqFld.setRoot(this);
			}
		}
		
		return recentEnquiryField;
}
	public void setRecentEnquiryField(List<RecentEnquiryField> recentEnquiryField) {
		this.recentEnquiryField = recentEnquiryField;
	}
	public List<AlertsField> getAlertsField() {
		
		if(alerts!=null && alerts.getAlertsinformationData()!=null && alerts.getAlertsinformationData().getField()!=null  ) {
			alertsField=alerts.getAlertsinformationData().getField();
			for(AlertsField alertfield:alertsField) {
				alertfield.setRoot(this);
			}
		}
		
		return alertsField;
	}
	public void setAlertsField(List<AlertsField> alertsField) {
		this.alertsField = alertsField;
	}
	public List<CrReportSummInfoDataField> getCreditReportSummmaryField() {
		
		if(creditReportSummary!=null && creditReportSummary.getCrsInfoData()!=null  ) {
			consumerInformationDataField=consumerInformation.getConsumerInformationData().getConsumerInformationDataField();
			for(CrReportSummInfoDataField consumerReSummInfoField:creditReportSummmaryField) {
				consumerReSummInfoField.setRoot(this);
			}
		}
		
		
		return creditReportSummmaryField;
	}
	public void setCreditReportSummmaryField(List<CrReportSummInfoDataField> creditReportSummmaryField) {
		this.creditReportSummmaryField = creditReportSummmaryField;
	}
	public List<ConsumerInformationDataField> getConsumerInformationDataField() {
		if(consumerInformation!=null && consumerInformation.getConsumerInformationData()!=null && consumerInformation.getConsumerInformationData().getConsumerInformationDataField()!=null ) {
			consumerResInfoDataField=consumerResponseStatus.getConsumerResInfoData().getConsumerResInfoDataField();
			for(ConsumerInformationDataField consumerInfoField:consumerInformationDataField) {
				consumerInfoField.setRoot(this);
			}
		}
		
		
		return consumerInformationDataField;
	}
	public void setConsumerInformationDataField(List<ConsumerInformationDataField> consumerInformationDataField) {
		this.consumerInformationDataField = consumerInformationDataField;
	}
	

	public List<ConsumerResInfoDataField> getConsumerResInfoDataField() {
		
		if(consumerResponseStatus!=null && consumerResponseStatus.getConsumerResInfoData()!=null && consumerResponseStatus.getConsumerResInfoData().getConsumerResInfoDataField()!=null ) {
			consumerResInfoDataField=consumerResponseStatus.getConsumerResInfoData().getConsumerResInfoDataField();
			for(ConsumerResInfoDataField consumerResInfoField:consumerResInfoDataField) {
				consumerResInfoField.setRoot(this);
			}
		}
		
		
		return consumerResInfoDataField;
	}

	public void setConsumerResInfoDataField(List<ConsumerResInfoDataField> consumerResInfoDataField) {
		this.consumerResInfoDataField = consumerResInfoDataField;
	}
	
	
	public Long getUniqueid() {
		return uniqueid;
	}
	public List<Field> getOtherInformationfield() {
		if(otherInformation!=null && otherInformation.getInformationData()!=null && otherInformation.getInformationData().getField()!=null ) {
			otherInformationfield=otherInformation.getInformationData().getField();
			for(Field OtherInfoField:otherInformationfield) {
				OtherInfoField.setRoot(this);
			}
		}
		return otherInformationfield;
	}
	public void setOtherInformationfield(List<Field> otherInformationfield) {
		this.otherInformationfield = otherInformationfield;
	}
	public void setUniqueid(Long uniqueid) {
		this.uniqueid = uniqueid;
	}
	public ReportType getReportType() {
		if(reportType!=null && reportType.getInformationData()!=null && reportType.getInformationData().getField()!=null ) {
			reportTypefield=reportType.getInformationData().getField();
			for(ReportTypeField field1:reportTypefield) {
				field1.setRoot(this);
			}
		}
		return reportType;
	}
	public void setReportType(ReportType reportType) {
		
		this.reportType = reportType;
	}
	public HeaderInformation getHeaderInformation() {
		
		if(headerInformation!=null && headerInformation.getHeaderInformationData()!=null && headerInformation.getHeaderInformationData().getHeaderInformationfield()!=null ) {
			headerInformationField=headerInformation.getHeaderInformationData().getHeaderInformationfield();
			for(HeaderInformationField field1:headerInformationField) {
				field1.setRoot(this);
			}
		}
		
		return headerInformation;
	}
	public void setHeaderInformation(HeaderInformation headerInformation) {
		this.headerInformation = headerInformation;
	}
	public OtherInformation getOtherInformation() {
		if(otherInformation!=null && otherInformation.getInformationData()!=null && otherInformation.getInformationData().getField()!=null ) {
			otherInformationfield=otherInformation.getInformationData().getField();
			for(Field OtherInfoField:otherInformationfield) {
				OtherInfoField.setRoot(this);
			}
		}
		return otherInformation;
	}
	public void setOtherInformation(OtherInformation otherInformation) {
		if(otherInformation!=null && otherInformation.getInformationData()!=null && otherInformation.getInformationData().getField()!=null ) {
			otherInformationfield=otherInformation.getInformationData().getField();
			for(Field OtherInfoField:otherInformationfield) {
				OtherInfoField.setRoot(this);
			}
		}
		this.otherInformation = otherInformation;
	}
	public SearchInformation getSearchInformation() {
		return searchInformation;
	}
	public void setSearchInformation(SearchInformation searchInformation) {
		this.searchInformation = searchInformation;
	}
	public ConsumerResponseStatus getConsumerResponseStatus() {
		return consumerResponseStatus;
	}
	public void setConsumerResponseStatus(ConsumerResponseStatus consumerResponseStatus) {
		this.consumerResponseStatus = consumerResponseStatus;
	}
	public ConsumerInformation getConsumerInformation() {
		return consumerInformation;
	}
	public void setConsumerInformation(ConsumerInformation consumerInformation) {
		this.consumerInformation = consumerInformation;
	}
	public CreditReportSummary getCreditReportSummary() {
		return creditReportSummary;
	}
	public void setCreditReportSummary(CreditReportSummary creditReportSummary) {
		this.creditReportSummary = creditReportSummary;
	}
	public Alerts getAlerts() {
		return alerts;
	}
	public void setAlerts(Alerts alerts) {
		this.alerts = alerts;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	public PlScore getPlScore() {
		return plScore;
	}
	public void setPlScore(PlScore plScore) {
		this.plScore = plScore;
	}
	public MfiEmploymentInformation getMfiEmploymentInformation() {
		return mfiEmploymentInformation;
	}
	public void setMfiEmploymentInformation(MfiEmploymentInformation mfiEmploymentInformation) {
		this.mfiEmploymentInformation = mfiEmploymentInformation;
	}

	public List<Mfi> getMfi() {
		return mfi;
	}
	
	public void setMfi(List<Mfi> mfi) {
		this.mfi = mfi;
	}
	public String getCoborrower() {
		return coborrower;
	}
	public void setCoborrower(String coborrower) {
		this.coborrower = coborrower;
	}
	public RecentEnquiry getRecentEnquiry() {
		return recentEnquiry;
	}
	public void setRecentEnquiry(RecentEnquiry recentEnquiry) {
		if(recentEnquiry!=null && recentEnquiry.getInformationData()!=null && recentEnquiry.getInformationData()!=null) {
			recentEnquiryField=recentEnquiry.getInformationData();
			for(RecentEnquiryField recentEnqFld:recentEnquiryField) {
				recentEnqFld.setRoot(this);
			}
		}
		this.recentEnquiry = recentEnquiry;
	}
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(ErrorResponse errorResponse) {
		
		this.errorResponse = errorResponse;
	}
	
	
	
	
	public List<SearchInformationField> getSearchInformationField() {

		if(searchInformation!=null && searchInformation.getSearchInformationData()!=null && searchInformation.getSearchInformationData().getSearchInformationField()!=null ) {
			searchInformationField=searchInformation.getSearchInformationData().getSearchInformationField();
			for(SearchInformationField searchInfoField:searchInformationField) {
				searchInfoField.setRoot(this);
			}
		}

		return searchInformationField;
	}
	public void setSearchInformationField(List<SearchInformationField> searchInformationField) {
		this.searchInformationField = searchInformationField;
	}
	@Override
	public String toString() {
		return "Root [reportType=" + reportType + ", headerInformation=" + headerInformation + ", otherInformation="
				+ otherInformation + ", searchInformation=" + searchInformation + ", consumerResponseStatus="
				+ consumerResponseStatus + ", consumerInformation=" + consumerInformation + ", creditReportSummary="
				+ creditReportSummary + ", alerts=" + alerts + ", score=" + score + ", plScore=" + plScore
				+ ", mfiEmploymentInformation=" + mfiEmploymentInformation + ", mfi=" + mfi + ", coborrower="
				+ coborrower + ", recentEnquiry=" + recentEnquiry + ", errorResponse=" + errorResponse + "]";
	}
	
}
