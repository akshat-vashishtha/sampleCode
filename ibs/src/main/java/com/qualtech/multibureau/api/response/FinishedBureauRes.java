package com.qualtech.multibureau.api.response;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_BUREAU_FINISHED_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinishedBureauRes implements Serializable {

	private static final long serialVersionUID = 8542491891362404039L;

		
		
		@Id
		@GeneratedValue(generator = "my_gen")
		@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_FINISHED_SQC", allocationSize = 1)
		@JsonIgnore
		private int finId;
		
		private String bureau;
		private String tracking_id;
		private String product;
		private String status;
		
		@OneToOne(fetch=FetchType.LAZY,mappedBy = "finBureauRes" ,cascade=CascadeType.ALL)
		private JsonBureauRes json_response_object;
		
		//Add new extra 
		@Lob
		private byte[] pdf_report;
		@Lob
		private String bureau_string;
		@Lob
		private String html_report;
//		private String sent_order;
		
		
		@ManyToOne
		@JoinColumn(name="uniqueid", nullable=false)
		@JsonIgnore
		private BureauResult bureauRes;
		
		public String getBureau_string() {
			return bureau_string;
		}
		public void setBureau_string(String bureau_string) {
			this.bureau_string = bureau_string;
		}
		public String getHtml_report() {
			return html_report;
		}
		public void setHtml_report(String html_report) {
			this.html_report = html_report;
		}
		
		public byte[] getPdf_report() {
			return pdf_report;
		}
		public void setPdf_report(byte[] pdf_report) {
			this.pdf_report = pdf_report;
		}
		public JsonBureauRes getJson_response_object() {
			return json_response_object;
		}
		public void setJson_response_object(JsonBureauRes json_response_object) {
			this.json_response_object = json_response_object;
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
		public int getFinId() {
			return finId;
		}
		public void setFinId(int finId) {
			this.finId = finId;
		}
		@Override
		public String toString() {
			return "FinishedBureauRes [finId=" + finId + ", bureau=" + bureau + ", tracking_id=" + tracking_id
					+ ", product=" + product + ", status=" + status + ", json_response_object=" + json_response_object
					+ ", pdf_report=" + Arrays.toString(pdf_report) + ", bureau_string=" + bureau_string
					+ ", html_report=" + html_report + "]";
		}
	
}
