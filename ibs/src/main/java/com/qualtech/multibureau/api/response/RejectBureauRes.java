package com.qualtech.multibureau.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_BUREAU_REJECT_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RejectBureauRes implements Serializable {

	private static final long serialVersionUID = 1798372805676720589L;
		@Id
		@GeneratedValue(generator = "my_gen")
		@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_REJECT_SQC", allocationSize = 1)
		private int rid;
		
		private String bureau;
		private String tracking_id;
		private String product;
		private String status;
		
		@OneToMany(fetch=FetchType.LAZY,mappedBy = "rejBureauRes" ,cascade=CascadeType.ALL)
		private List<ErrorRejectRes> errors;
		
		@OneToMany(fetch=FetchType.LAZY,mappedBy = "rejBureauRes" ,cascade=CascadeType.ALL)
		private List<WarnRejectRes> warnings;
		
		
		@ManyToOne
		@JoinColumn(name="uniqueid", nullable=false)
		@JsonIgnore
		private BureauResult bureauRes;
		
		
		public List<WarnRejectRes> getWarnings() {
			
			if (warnings!=null)
			{
				for(WarnRejectRes res:warnings)
				{
					res.setRejBureauRes(this);
				}
			}
			return warnings;
		}
		public void setWarnings(List<WarnRejectRes> warnings) {
			this.warnings = warnings;
		}
		public List<ErrorRejectRes> getErrors() {
			
			if (errors!=null)
			{
				for(ErrorRejectRes res:errors)
				{
					res.setRejBureauRes(this);
				}
			}
			return errors;
		}
		public void setErrors(List<ErrorRejectRes> errors) {
			this.errors = errors;
		}
		public BureauResult getBureauRes() {
			return bureauRes;
		}
		public void setBureauRes(BureauResult bureauRes) {
			this.bureauRes = bureauRes;
		}
		public String getBureau() {
			return bureau;
		}
		public void setBureau(String bureau) {
			this.bureau = bureau;
		}
		public String getTracking_id() {
			return tracking_id;
		}
		public void setTracking_id(String tracking_id) {
			this.tracking_id = tracking_id;
		}
		public String getProduct() {
			return product;
		}
		public void setProduct(String product) {
			this.product = product;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public int getRid() {
			return rid;
		}
		public void setRid(int rid) {
			this.rid = rid;
		}
		@Override
		public String toString() {
			return "RejectBureauRes [rid=" + rid + ", bureau=" + bureau + ", tracking_id=" + tracking_id + ", product="
					+ product + ", status=" + status + ", errors=" + errors + ", warnings=" + warnings + "]";
		}
		
}
