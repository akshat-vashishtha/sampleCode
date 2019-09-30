package com.qualtech.multibureau.api.request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_BUREAU_RELATION_REQ")
public class RelationDetails {

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="UNIQUEID", nullable=false)
	private BureauPayload bureauPayload;
	
	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_RELATION_SQC", allocationSize = 1)
	private int id;
	
	private String father_name;
	private String spouse_name;
	private String mother_name;
	private String relation_type_1;
	private String relation_type_1_value;
	private String relation_type_2;
	private String relation_type_2_value;
	private String key_person_name;
	private String key_person_relation;
	private String nominee_name;
	private String nominee_relation_type;
	
	
	public BureauPayload getBureauPayload() {
		return bureauPayload;
	}
	public void setBureauPayload(BureauPayload bureauPayload) {
		this.bureauPayload = bureauPayload;
	}
	
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public String getSpouse_name() {
		return spouse_name;
	}
	public void setSpouse_name(String spouse_name) {
		this.spouse_name = spouse_name;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public String getRelation_type_1() {
		return relation_type_1;
	}
	public void setRelation_type_1(String relation_type_1) {
		this.relation_type_1 = relation_type_1;
	}
	public String getRelation_type_1_value() {
		return relation_type_1_value;
	}
	public void setRelation_type_1_value(String relation_type_1_value) {
		this.relation_type_1_value = relation_type_1_value;
	}
	public String getRelation_type_2() {
		return relation_type_2;
	}
	public void setRelation_type_2(String relation_type_2) {
		this.relation_type_2 = relation_type_2;
	}
	public String getRelation_type_2_value() {
		return relation_type_2_value;
	}
	public void setRelation_type_2_value(String relation_type_2_value) {
		this.relation_type_2_value = relation_type_2_value;
	}
	public String getKey_person_name() {
		return key_person_name;
	}
	public void setKey_person_name(String key_person_name) {
		this.key_person_name = key_person_name;
	}
	public String getKey_person_relation() {
		return key_person_relation;
	}
	public void setKey_person_relation(String key_person_relation) {
		this.key_person_relation = key_person_relation;
	}
	public String getNominee_relation_type() {
		return nominee_relation_type;
	}
	public void setNominee_relation_type(String nominee_relation_type) {
		this.nominee_relation_type = nominee_relation_type;
	}
	
	public String getNominee_name() {
		return nominee_name;
	}
	public void setNominee_name(String nominee_name) {
		this.nominee_name = nominee_name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "RelationDetails [id=" + id + ", father_name=" + father_name + ", spouse_name=" + spouse_name
				+ ", mother_name=" + mother_name + ", relation_type_1=" + relation_type_1 + ", relation_type_1_value="
				+ relation_type_1_value + ", relation_type_2=" + relation_type_2 + ", relation_type_2_value="
				+ relation_type_2_value + ", key_person_name=" + key_person_name + ", key_person_relation="
				+ key_person_relation + ", nominee_name=" + nominee_name + ", nominee_relation_type="
				+ nominee_relation_type + "]";
	}
	
	
}
