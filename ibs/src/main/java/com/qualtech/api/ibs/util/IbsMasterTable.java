/*package com.qualtech.api.ibs.util;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
  @Table(name="IBS_MASTER_TABLE")
  //@Table(name="MASTER_TABLE_03")
public class IbsMasterTable implements Serializable{
	

	private static final long serialVersionUID = -9105050775994002336L;
	    @Id
		private int seq_no;
	    private String description;
	    private String product_name;
		private String query;
		private String query_status;
		private String sample_query;
		private String service_name;
		private String service_provider;
		private String service_url;
		
		public int getSeq_no() {
			return seq_no;
		}
		public void setSeq_no(int seq_no) {
			this.seq_no = seq_no;
		}
		public String getService_provider() {
			return service_provider;
		}
		public void setService_provider(String service_provider) {
			this.service_provider = service_provider;
		}
		public String getService_name() {
			return service_name;
		}
		public void setService_name(String service_name) {
			this.service_name = service_name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getQuery() {
			return query;
		}
		public void setQuery(String query) {
			this.query = query;
		}
		public String getProduct_name() {
			return product_name;
		}
		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}
		public String getService_url() {
			return service_url;
		}
		public void setService_url(String service_url) {
			this.service_url = service_url;
		}
		public String getQuery_status() {
			return query_status;
		}
		public void setQuery_status(String query_status) {
			this.query_status = query_status;
		}
		public String getSample_query() {
			return sample_query;
		}
		public void setSample_query(String sample_query) {
			this.sample_query = sample_query;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		@Override
		public String toString() {
			return "IBSMasterTable [seq_no=" + seq_no + ", service_provider=" + service_provider + ", service_name="
					+ service_name + ", description=" + description + ", query=" + query + ", product_name="
					+ product_name + ", service_url=" + service_url + ", query_status=" + query_status
					+ ", sample_query=" + sample_query + "]";
		}
		
		
		
		
	  

}
*/