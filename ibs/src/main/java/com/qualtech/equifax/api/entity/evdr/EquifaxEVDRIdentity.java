package com.qualtech.equifax.api.entity.evdr;

import javax.persistence.Column;


/*@Entity
@Table(name="EQUIFAX_EVDR_IDENTITY_INFO")
*/public class EquifaxEVDRIdentity 
{
	@Column(name="REPORTED_DATE")
	private String reported_date;
	@Column(name="ID_NUMBER")
	private String id_number;
	@Column(name="IDENTITY_TYPE")
	private String type;
	/*@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QC_EQUIFAX_EVDR_IDNTY_SEQ")
	@SequenceGenerator(name="QC_EQUIFAX_IDNTY_IDNTY_SEQ", sequenceName = "QC_EQUIFAX_EVDR_IDNTY_SEQ", allocationSize = 1)
	*/@Column(name="IDENTITY_INFO_ID")
	private Long identity_info_id;
	/*@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID")
	private EquifaxEvdrAllDetails equifaxevdrdetails_logs  ; 
	*/
	/*public EquifaxEvdrAllDetails getEquifaxevdrdetails_logs() {
		return equifaxevdrdetails_logs;
	}
	public void setEquifaxevdrdetails_logs(EquifaxEvdrAllDetails equifaxevdrdetails_logs) {
		this.equifaxevdrdetails_logs = equifaxevdrdetails_logs;
	}*/
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getReported_date() {
		return reported_date;
	}
	public void setReported_date(String reported_date) {
		this.reported_date = reported_date;
	}

	public String getId_number() {
		return id_number;
	}
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	public Long getIdentity_info_id() {
		return identity_info_id;
	}
	public void setIdentity_info_id(Long identity_info_id) {
		this.identity_info_id = identity_info_id;
	}
	
}
