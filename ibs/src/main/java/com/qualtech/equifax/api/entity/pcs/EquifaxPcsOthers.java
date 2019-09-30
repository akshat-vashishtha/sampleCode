package com.qualtech.equifax.api.entity.pcs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;


@Entity
@Table(name="IB_EQ_P_OTHER")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsOthers 
{
		  @Column(name="ALL_LINE_SEVER_WRITTEN")
		  private String all_line_sever_written;
		  @Column(name="SEVER_WRITTEN_IN_SIX_MONTH")
		  private String sever_written_in_six_month;
		  @Column(name="NUMBER_OF_OPEN_TRADES")
		  private String number_of_open_trades;
		  @Column(name="AGE_OF_OLDEST_TRADE")
		  private String age_of_oldest_trade;
		  @Column(name="SEVER_WRITTEN_IN_NINE_MONTH")
		  private String sever_written_in_nine_month;
		  @Id
		  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_PCS_OTHERKEY_SQC")
		  @SequenceGenerator(name="IB_EQ_PCS_OTHERKEY_SQC", sequenceName = "IB_EQ_PCS_OTHERKEY_SQC", allocationSize = 1)
		  @Column(name="OTHER_ID")
		  private Long other_id;
		   @OneToOne
		  @JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
		  @JsonIgnore
	       private  EquifaxPcsAllDetails equifaxpcDetailslogs;
			 
       
		public EquifaxPcsAllDetails getEquifaxpcDetailslogs() {
			return equifaxpcDetailslogs;
         }
         public void setEquifaxpcDetailslogs(EquifaxPcsAllDetails equifaxpcDetailslogs) {
			this.equifaxpcDetailslogs = equifaxpcDetailslogs;
         }	
  
		  public String getAll_line_sever_written() {
			return all_line_sever_written;
		}
		public void setAll_line_sever_written(String all_line_sever_written) {
			this.all_line_sever_written = all_line_sever_written;
		}
		public String getSever_written_in_six_month() {
			return sever_written_in_six_month;
		}
		public void setSever_written_in_six_month(String sever_written_in_six_month) {
			this.sever_written_in_six_month = sever_written_in_six_month;
		}
		public String getNumber_of_open_trades() {
			return number_of_open_trades;
		}
		public void setNumber_of_open_trades(String number_of_open_trades) {
			this.number_of_open_trades = number_of_open_trades;
		}
		public String getAge_of_oldest_trade() {
			return age_of_oldest_trade;
		}
		public void setAge_of_oldest_trade(String age_of_oldest_trade) {
			this.age_of_oldest_trade = age_of_oldest_trade;
		}
		public String getSever_written_in_nine_month() {
			return sever_written_in_nine_month;
		}
		public void setSever_written_in_nine_month(String sever_written_in_nine_month) {
			this.sever_written_in_nine_month = sever_written_in_nine_month;
		}
		
		public Long getOther_id() {
			return other_id;
		}
		public void setOther_id(Long other_id) {
			this.other_id = other_id;
		}
		@Override
		public String toString() {
			return "EquifaxPcsOthers [all_line_sever_written=" + all_line_sever_written
					+ ", sever_written_in_six_month=" + sever_written_in_six_month + ", number_of_open_trades="
					+ number_of_open_trades + ", age_of_oldest_trade=" + age_of_oldest_trade
					+ ", sever_written_in_nine_month=" + sever_written_in_nine_month + ", other_id=" + other_id + "]";
		}
}
