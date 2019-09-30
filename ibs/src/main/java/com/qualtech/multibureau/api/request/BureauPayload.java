package com.qualtech.multibureau.api.request;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_REQ")
public class BureauPayload {

	@JsonIgnore
	@Id
	private int uniqueid;
	@JsonIgnore
	private String corelationid;
	private String application_id;
	private String cust_id;
	private String request_type;
	private String request_time;
	private String priority;
	private String product_type;
	private String loan_Type;
	private String loan_amount;
	private String joint_ind;
	private String inquiry_submitted_by;

	private String source_system_name;
	private String source_system_version;
	private String source_system_vender;
	private String source_system_instance_id;
	private String bureau_region;
	private String loan_purpose_desc;
	private String branch_ifsccode;

	private String kendra;
	private String inquiry_stage;
	private String authrization_flag;
	private String authroized_by;
	private String individual_corporate_flag;
	private String constitution;

	private String firstName;
	private String lastName;

	private String gender;
	private String marital_status;


	@OneToMany(fetch=FetchType.LAZY,mappedBy = "bureauPayload" ,cascade=CascadeType.ALL)
	private List<RelationDetails> relation;
	private String age;
	private String age_as_on_dt;
	private String birth_dt;
	private String no_of_dependents;

	//	@OneToOne(fetch=FetchType.LAZY,mappedBy = "bureauPayload" ,cascade=CascadeType.ALL)
	//	private IdDetails idDetails;

	private IdTypes id;

	@OneToMany(fetch=FetchType.LAZY,mappedBy = "bureauPayload" ,cascade=CascadeType.ALL)
	private List<AddressBureau> address;

	@OneToMany(fetch=FetchType.LAZY,mappedBy = "bureauPayload" ,cascade=CascadeType.ALL)
	private List<PhoneBureau> phone;
	private String email_id_1;
	private String email_id_2;
	private String alias;
	private String act_opening_dt;

	private String account_number_1;

	private String account_number_2;
	private String account_number_3;
	private String account_number_4;
	private String tanure;
	private String group_id;
	private String number_credit_cards;
	private String credit_card_no;
	private String monthly_income;
	private String soa_employer_name_c;
	private String time_with_employ;
	private String company_category;
	private String nature_of_business;
	private String asset_cost;
	private String collateral_1;
	private String collateral_1_valuation_1;
	private String collateral_1_valuation_2;
	private String collateral_2;
	private String collateral_2_valuation_1;
	private String collateral_2_valuation_2;



	public int getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(int uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getAccount_number_2() {
		return account_number_2;
	}
	public void setAccount_number_2(String account_number_2) {
		this.account_number_2 = account_number_2;
	}
	public String getAccount_number_3() {
		return account_number_3;
	}
	public void setAccount_number_3(String account_number_3) {
		this.account_number_3 = account_number_3;
	}
	public String getAccount_number_4() {
		return account_number_4;
	}
	public void setAccount_number_4(String account_number_4) {
		this.account_number_4 = account_number_4;
	}

	public String getTanure() {
		return tanure;
	}
	public void setTanure(String tanure) {
		this.tanure = tanure;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getNumber_credit_cards() {
		return number_credit_cards;
	}
	public void setNumber_credit_cards(String number_credit_cards) {
		this.number_credit_cards = number_credit_cards;
	}
	public String getCredit_card_no() {
		return credit_card_no;
	}
	public void setCredit_card_no(String credit_card_no) {
		this.credit_card_no = credit_card_no;
	}
	public String getMonthly_income() {
		return monthly_income;
	}
	public void setMonthly_income(String monthly_income) {
		this.monthly_income = monthly_income;
	}
	public String getSoa_employer_name_c() {
		return soa_employer_name_c;
	}
	public void setSoa_employer_name_c(String soa_employer_name_c) {
		this.soa_employer_name_c = soa_employer_name_c;
	}
	public String getTime_with_employ() {
		return time_with_employ;
	}
	public void setTime_with_employ(String time_with_employ) {
		this.time_with_employ = time_with_employ;
	}
	public String getCompany_category() {
		return company_category;
	}
	public void setCompany_category(String company_category) {
		this.company_category = company_category;
	}
	public String getNature_of_business() {
		return nature_of_business;
	}
	public void setNature_of_business(String nature_of_business) {
		this.nature_of_business = nature_of_business;
	}
	public String getAsset_cost() {
		return asset_cost;
	}
	public void setAsset_cost(String asset_cost) {
		this.asset_cost = asset_cost;
	}
	public String getCollateral_1() {
		return collateral_1;
	}
	public void setCollateral_1(String collateral_1) {
		this.collateral_1 = collateral_1;
	}
	public String getCollateral_1_valuation_1() {
		return collateral_1_valuation_1;
	}
	public void setCollateral_1_valuation_1(String collateral_1_valuation_1) {
		this.collateral_1_valuation_1 = collateral_1_valuation_1;
	}
	public String getCollateral_1_valuation_2() {
		return collateral_1_valuation_2;
	}
	public void setCollateral_1_valuation_2(String collateral_1_valuation_2) {
		this.collateral_1_valuation_2 = collateral_1_valuation_2;
	}
	public String getCollateral_2() {
		return collateral_2;
	}
	public void setCollateral_2(String collateral_2) {
		this.collateral_2 = collateral_2;
	}
	public String getCollateral_2_valuation_1() {
		return collateral_2_valuation_1;
	}
	public void setCollateral_2_valuation_1(String collateral_2_valuation_1) {
		this.collateral_2_valuation_1 = collateral_2_valuation_1;
	}
	public String getCollateral_2_valuation_2() {
		return collateral_2_valuation_2;
	}
	public void setCollateral_2_valuation_2(String collateral_2_valuation_2) {
		this.collateral_2_valuation_2 = collateral_2_valuation_2;
	}
	public String getBirth_dt() {
		return birth_dt;
	}
	public void setBirth_dt(String birth_dt) {
		this.birth_dt = birth_dt;
	}
	public String getNo_of_dependents() {
		return no_of_dependents;
	}
	public void setNo_of_dependents(String no_of_dependents) {
		this.no_of_dependents = no_of_dependents;
	}

	public List<RelationDetails> getRelation() {
		if(relation!=null){
			for(RelationDetails re:relation) {
				re.setBureauPayload(this);
			}
		}
		return relation;
	}
	public void setRelation(List<RelationDetails> relation) {
		this.relation = relation;
	}
	public String getSource_system_instance_id() {
		return source_system_instance_id;
	}
	public void setSource_system_instance_id(String source_system_instance_id) {
		this.source_system_instance_id = source_system_instance_id;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getRequest_type() {
		return request_type;
	}
	public void setRequest_type(String request_type) {
		this.request_type = request_type;
	}
	public String getRequest_time() {
		return request_time;
	}
	public void setRequest_time(String request_time) {
		this.request_time = request_time;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public String getJoint_ind() {
		return joint_ind;
	}
	public void setJoint_ind(String joint_ind) {
		this.joint_ind = joint_ind;
	}
	public String getInquiry_submitted_by() {
		return inquiry_submitted_by;
	}
	public void setInquiry_submitted_by(String inquiry_submitted_by) {
		this.inquiry_submitted_by = inquiry_submitted_by;
	}
	public String getSource_system_name() {
		return source_system_name;
	}
	public void setSource_system_name(String source_system_name) {
		this.source_system_name = source_system_name;
	}
	public String getSource_system_version() {
		return source_system_version;
	}
	public void setSource_system_version(String source_system_version) {
		this.source_system_version = source_system_version;
	}
	public String getSource_system_vender() {
		return source_system_vender;
	}
	public void setSource_system_vender(String source_system_vender) {
		this.source_system_vender = source_system_vender;
	}
	public String getBureau_region() {
		return bureau_region;
	}
	public void setBureau_region(String bureau_region) {
		this.bureau_region = bureau_region;
	}
	public String getLoan_purpose_desc() {
		return loan_purpose_desc;
	}
	public void setLoan_purpose_desc(String loan_purpose_desc) {
		this.loan_purpose_desc = loan_purpose_desc;
	}
	public String getBranch_ifsccode() {
		return branch_ifsccode;
	}
	public void setBranch_ifsccode(String branch_ifsccode) {
		this.branch_ifsccode = branch_ifsccode;
	}
	public String getKendra() {
		return kendra;
	}
	public void setKendra(String kendra) {
		this.kendra = kendra;
	}
	public String getInquiry_stage() {
		return inquiry_stage;
	}
	public void setInquiry_stage(String inquiry_stage) {
		this.inquiry_stage = inquiry_stage;
	}
	public String getAuthrization_flag() {
		return authrization_flag;
	}
	public void setAuthrization_flag(String authrization_flag) {
		this.authrization_flag = authrization_flag;
	}
	public String getAuthroized_by() {
		return authroized_by;
	}
	public void setAuthroized_by(String authroized_by) {
		this.authroized_by = authroized_by;
	}
	public String getIndividual_corporate_flag() {
		return individual_corporate_flag;
	}
	public void setIndividual_corporate_flag(String individual_corporate_flag) {
		this.individual_corporate_flag = individual_corporate_flag;
	}
	public String getConstitution() {
		return constitution;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAge_as_on_dt() {
		return age_as_on_dt;
	}
	public void setAge_as_on_dt(String age_as_on_dt) {
		this.age_as_on_dt = age_as_on_dt;
	}
	public List<AddressBureau> getAddress() {

		if(address!=null){
			for(AddressBureau rre:address) {
				rre.setBureauPayload(this);
			}
		}
		return address;
	}
	public void setAddress(List<AddressBureau> address) {
		this.address = address;
	}
	public List<PhoneBureau> getPhone() {

		if(phone!=null)
		{
			for(PhoneBureau pb:phone)
			{
				pb.setBureauPayload(this);
			}
		}
		return phone;
	}
	public void setPhone(List<PhoneBureau> phone) {
		this.phone = phone;
	}
	public String getEmail_id_1() {
		return email_id_1;
	}
	public void setEmail_id_1(String email_id_1) {
		this.email_id_1 = email_id_1;
	}
	public String getEmail_id_2() {
		return email_id_2;
	}
	public void setEmail_id_2(String email_id_2) {
		this.email_id_2 = email_id_2;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAct_opening_dt() {
		return act_opening_dt;
	}
	public void setAct_opening_dt(String act_opening_dt) {
		this.act_opening_dt = act_opening_dt;
	}
	public String getAccount_number_1() {
		return account_number_1;
	}
	public void setAccount_number_1(String account_number_1) {
		this.account_number_1 = account_number_1;
	}
	public String getLoan_Type() {
		return loan_Type;
	}
	public void setLoan_Type(String loan_Type) {
		this.loan_Type = loan_Type;
	}
	public String getLoan_amount() {
		return loan_amount;
	}
	public void setLoan_amount(String loan_amount) {
		this.loan_amount = loan_amount;
	}
	public IdTypes getId() {
		return id;
	}
	public void setId(IdTypes id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "BureauPayload [uniqueid=" + uniqueid + ", corelationid=" + corelationid + ", application_id="
				+ application_id + ", cust_id=" + cust_id + ", request_type=" + request_type + ", request_time="
				+ request_time + ", priority=" + priority + ", product_type=" + product_type + ", loan_Type="
				+ loan_Type + ", loan_amount=" + loan_amount + ", joint_ind=" + joint_ind + ", inquiry_submitted_by="
				+ inquiry_submitted_by + ", source_system_name=" + source_system_name + ", source_system_version="
				+ source_system_version + ", source_system_vender=" + source_system_vender
				+ ", source_system_instance_id=" + source_system_instance_id + ", bureau_region=" + bureau_region
				+ ", loan_purpose_desc=" + loan_purpose_desc + ", branch_ifsccode=" + branch_ifsccode + ", kendra="
				+ kendra + ", inquiry_stage=" + inquiry_stage + ", authrization_flag=" + authrization_flag
				+ ", authroized_by=" + authroized_by + ", individual_corporate_flag=" + individual_corporate_flag
				+ ", constitution=" + constitution + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", marital_status=" + marital_status + ", relation=" + relation + ", age=" + age
				+ ", age_as_on_dt=" + age_as_on_dt + ", birth_dt=" + birth_dt + ", no_of_dependents=" + no_of_dependents
				+ ", id=" + id + ", address=" + address + ", phone=" + phone + ", email_id_1=" + email_id_1
				+ ", email_id_2=" + email_id_2 + ", alias=" + alias + ", act_opening_dt=" + act_opening_dt
				+ ", account_number_1=" + account_number_1 + ", account_number_2=" + account_number_2
				+ ", account_number_3=" + account_number_3 + ", account_number_4=" + account_number_4 + ", tanure="
				+ tanure + ", group_id=" + group_id + ", number_credit_cards=" + number_credit_cards
				+ ", credit_card_no=" + credit_card_no + ", monthly_income=" + monthly_income + ", soa_employer_name_c="
				+ soa_employer_name_c + ", time_with_employ=" + time_with_employ + ", company_category="
				+ company_category + ", nature_of_business=" + nature_of_business + ", asset_cost=" + asset_cost
				+ ", collateral_1=" + collateral_1 + ", collateral_1_valuation_1=" + collateral_1_valuation_1
				+ ", collateral_1_valuation_2=" + collateral_1_valuation_2 + ", collateral_2=" + collateral_2
				+ ", collateral_2_valuation_1=" + collateral_2_valuation_1 + ", collateral_2_valuation_2="
				+ collateral_2_valuation_2 + "]";
	}


}
