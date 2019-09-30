package com.qualtech.icici.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_ICICI_CST_APND")
public class ApnteDtls implements Serializable{


	private static final long serialVersionUID = -3783553349886832099L;
	
		@JsonIgnore
		@Id
		@GeneratedValue(generator = "REQ_SEQ")
		@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_ICICI_CST_APND_SEQ")
		@Column(name="EID")
		private int eid;
		
		
	    @JsonIgnore
	    @OneToOne
	    @JoinColumn(name="UNIQUE_ID",nullable=false)
		private NomineeInfos nomineeInfos;
		
	    private String frstNm;
	    private String lstNm;
	    private String gender;
	    private String dob;
	    private String relationship;
	    
		public int getEid() {
			return eid;
		}
		public void setEid(int eid) {
			this.eid = eid;
		}
		public String getFrstNm() {
			return frstNm;
		}
		public void setFrstNm(String frstNm) {
			this.frstNm = frstNm;
		}
		public String getLstNm() {
			return lstNm;
		}
		public void setLstNm(String lstNm) {
			this.lstNm = lstNm;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}
		public String getRelationship() {
			return relationship;
		}
		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}
		
		public NomineeInfos getNomineeInfos() {
			return nomineeInfos;
		}
		public void setNomineeInfos(NomineeInfos nomineeInfos) {
			this.nomineeInfos = nomineeInfos;
		}
		@Override
		public String toString() {
			return "ApnteDtls [frstNm=" + frstNm + ", lstNm=" + lstNm + ", gender=" + gender + ", dob=" + dob
					+ ", relationship=" + relationship + "]";
		}

	    
}
