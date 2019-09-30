package com.qualtech.multibureau.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_JSON_RES")
public class JsonBureauRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5979591120689070787L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_JSON_SQC", allocationSize = 1)
	@JsonIgnore
	private int id;

	private String timeprocessed;
	private String invuserpwd;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private List<ErrorDomainRes> errordomainlist;

	private String dateprocessed;
	private String mbrrefnbr;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private List<AddressJsonRes> addresslist;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private List<AccountListRes> accountlist;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private List<EnquirylistRes> enquirylist;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private List<PhoneJsonRes> phonelist;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private NameJsonRes name;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private List<IDJsonRes> idlist;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private HeaderJsonRes header;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private List<ScoreListJsonRes> scorelist;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private INDVReportsJsonRes indvreports;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private InquiryRequestInfoJsonRes inquiryrequestinfo;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private InquiryResponseHeaderRes inquiryresponseheader;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private ReportDataRes reportdata;

	// experian

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private TotalCAPSSummaryRes totalcapssummary;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private CapsRes caps;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private MatchResultRes matchresult;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private CurrentApplicationRes currentapplication;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private CreditProfileHeaderRes creditprofileheader;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private UserMessageRes usermessage;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private ExperianScoreRes score;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private NonCreditCAPSRes noncreditcaps;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "jsonBureauRes", cascade = CascadeType.ALL)
	private CaisAccountRes caisaccount;

	@OneToOne
	@JoinColumn(name = "finId", nullable = false)
	@JsonIgnore
	private FinishedBureauRes finBureauRes;

	public InquiryRequestInfoJsonRes getInquiryrequestinfo() {
		if (this.inquiryrequestinfo != null) {
			this.inquiryrequestinfo.setJsonBureauRes(this);
		}
		return inquiryrequestinfo;
	}

	public void setInquiryrequestinfo(InquiryRequestInfoJsonRes inquiryrequestinfo) {
		this.inquiryrequestinfo = inquiryrequestinfo;
	}

	public InquiryResponseHeaderRes getInquiryresponseheader() {
		if (this.inquiryresponseheader != null) {
			this.inquiryresponseheader.setJsonBureauRes(this);
		}
		return inquiryresponseheader;
	}

	public void setInquiryresponseheader(InquiryResponseHeaderRes inquiryresponseheader) {
		this.inquiryresponseheader = inquiryresponseheader;
	}

	public ReportDataRes getReportdata() {
		if (this.reportdata != null) {
			this.reportdata.setJsonBureauRes(this);
		}
		return reportdata;
	}

	public void setReportdata(ReportDataRes reportdata) {
		this.reportdata = reportdata;
	}

	public INDVReportsJsonRes getIndvreports() {
		if (this.indvreports != null) {
			this.indvreports.setJsonBureauRes(this);
		}
		return indvreports;

	}

	public void setIndvreports(INDVReportsJsonRes indvreports) {
		this.indvreports = indvreports;
	}

	public String getTimeprocessed() {
		return timeprocessed;
	}

	public void setTimeprocessed(String timeprocessed) {
		this.timeprocessed = timeprocessed;
	}

	public String getInvuserpwd() {
		return invuserpwd;
	}

	public void setInvuserpwd(String invuserpwd) {
		this.invuserpwd = invuserpwd;
	}

	public List<ErrorDomainRes> getErrordomainlist() {

		if (errordomainlist != null) {
			for (ErrorDomainRes res : errordomainlist) {
				res.setJsonBureauRes(this);
			}
		}
		return errordomainlist;
	}

	public void setErrordomainlist(List<ErrorDomainRes> errordomainlist) {
		this.errordomainlist = errordomainlist;
	}

	public String getDateprocessed() {
		return dateprocessed;
	}

	public void setDateprocessed(String dateprocessed) {
		this.dateprocessed = dateprocessed;
	}

	public String getMbrrefnbr() {
		return mbrrefnbr;
	}

	public void setMbrrefnbr(String mbrrefnbr) {
		this.mbrrefnbr = mbrrefnbr;
	}

	public HeaderJsonRes getHeader() {
		if (this.header != null) {
			this.header.setJsonBureauRes(this);
		}
		return header;
	}

	public void setHeader(HeaderJsonRes header) {
		this.header = header;
	}

	public List<AccountListRes> getAccountlist() {
		if (accountlist != null) {
			for (AccountListRes res : accountlist) {
				res.setJsonBureauRes(this);
			}
		}
		return accountlist;
	}

	public void setAccountlist(List<AccountListRes> accountlist) {
		this.accountlist = accountlist;
	}

	public List<EnquirylistRes> getEnquirylist() {
		if (enquirylist != null) {
			for (EnquirylistRes res : enquirylist) {
				res.setJsonBureauRes(this);
			}
		}
		return enquirylist;
	}

	public void setEnquirylist(List<EnquirylistRes> enquirylist) {
		this.enquirylist = enquirylist;
	}

	public List<ScoreListJsonRes> getScorelist() {
		if (scorelist != null) {
			for (ScoreListJsonRes res : scorelist) {
				res.setJsonBureauRes(this);
			}
		}
		return scorelist;
	}

	public void setScorelist(List<ScoreListJsonRes> scorelist) {
		this.scorelist = scorelist;
	}

	public NameJsonRes getName() {
		if (this.name != null) {
			this.name.setJsonBureauRes(this);
		}
		return name;
	}

	public void setName(NameJsonRes name) {
		this.name = name;
	}

	public FinishedBureauRes getFinBureauRes() {
		return finBureauRes;
	}

	public void setFinBureauRes(FinishedBureauRes finBureauRes) {
		this.finBureauRes = finBureauRes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<AddressJsonRes> getAddresslist() {
		if (addresslist != null) {
			for (AddressJsonRes res : addresslist) {
				res.setJsonBureauRes(this);
			}
		}
		return addresslist;
	}

	public void setAddresslist(List<AddressJsonRes> addresslist) {
		this.addresslist = addresslist;
	}

	public List<PhoneJsonRes> getPhonelist() {

		if (phonelist != null) {
			for (PhoneJsonRes res : phonelist) {
				res.setJsonBureauRes(this);
			}
		}
		return phonelist;
	}

	public void setPhonelist(List<PhoneJsonRes> phonelist) {
		this.phonelist = phonelist;
	}

	public List<IDJsonRes> getIdlist() {
		if (idlist != null) {
			for (IDJsonRes res : idlist) {
				res.setJsonBureauRes(this);
			}
		}
		return idlist;
	}

	public void setIdlist(List<IDJsonRes> idlist) {
		this.idlist = idlist;
	}

	public TotalCAPSSummaryRes getTotalcapssummary() {
		if (this.totalcapssummary != null) {
			this.totalcapssummary.setJsonBureauRes(this);
		}
		return totalcapssummary;
	}

	public void setTotalcapssummary(TotalCAPSSummaryRes totalcapssummary) {
		this.totalcapssummary = totalcapssummary;
	}

	public CapsRes getCaps() {
		if (this.caps != null) {
			this.caps.setJsonBureauRes(this);
		}
		return caps;
	}

	public void setCaps(CapsRes caps) {
		this.caps = caps;
	}

	public MatchResultRes getMatchresult() {
		if (this.matchresult != null) {
			this.matchresult.setJsonBureauRes(this);
		}
		return matchresult;
	}

	public void setMatchresult(MatchResultRes matchresult) {
		this.matchresult = matchresult;
	}

	public CurrentApplicationRes getCurrentapplication() {
		if (this.currentapplication != null) {
			this.currentapplication.setJsonBureauRes(this);
		}
		return currentapplication;
	}

	public void setCurrentapplication(CurrentApplicationRes currentapplication) {
		this.currentapplication = currentapplication;
	}

	public CreditProfileHeaderRes getCreditprofileheader() {
		if (this.creditprofileheader != null) {
			this.creditprofileheader.setJsonBureauRes(this);
		}
		return creditprofileheader;
	}

	public void setCreditprofileheader(CreditProfileHeaderRes creditprofileheader) {
		this.creditprofileheader = creditprofileheader;
	}

	public UserMessageRes getUsermessage() {
		if (this.usermessage != null) {
			this.usermessage.setJsonBureauRes(this);
		}
		return usermessage;
	}

	public void setUsermessage(UserMessageRes usermessage) {
		this.usermessage = usermessage;
	}

	public ExperianScoreRes getScore() {
		if (this.score != null) {
			this.score.setJsonBureauRes(this);
		}
		return score;
	}

	public void setScore(ExperianScoreRes score) {
		this.score = score;
	}

	public NonCreditCAPSRes getNoncreditcaps() {
		if (this.noncreditcaps != null) {
			this.noncreditcaps.setJsonBureauRes(this);
		}
		return noncreditcaps;
	}

	public void setNoncreditcaps(NonCreditCAPSRes noncreditcaps) {
		this.noncreditcaps = noncreditcaps;
	}

	public CaisAccountRes getCaisaccount() {
		if (this.caisaccount != null) {
			this.caisaccount.setJsonBureauRes(this);
		}
		return caisaccount;
	}

	public void setCaisaccount(CaisAccountRes caisaccount) {
		this.caisaccount = caisaccount;
	}

	@Override
	public String toString() {
		return "JsonBureauRes [id=" + id + ", timeprocessed=" + timeprocessed + ", invuserpwd=" + invuserpwd
				+ ", errordomainlist=" + errordomainlist + ", dateprocessed=" + dateprocessed + ", mbrrefnbr="
				+ mbrrefnbr + ", addresslist=" + addresslist + ", accountlist=" + accountlist + ", enquirylist="
				+ enquirylist + ", phonelist=" + phonelist + ", name=" + name + ", idlist=" + idlist + ", header="
				+ header + ", scorelist=" + scorelist + ", indvreports=" + indvreports + ", inquiryrequestinfo="
				+ inquiryrequestinfo + ", inquiryresponseheader=" + inquiryresponseheader + ", reportdata=" + reportdata
				+ ", totalcapssummary=" + totalcapssummary + ", caps=" + caps + ", matchresult=" + matchresult
				+ ", currentapplication=" + currentapplication + ", creditprofileheader=" + creditprofileheader
				+ ", usermessage=" + usermessage + ", score=" + score + ", noncreditcaps=" + noncreditcaps
				+ ", caisaccount=" + caisaccount + "]";
	}
}
