package com.qualtech.api.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IBS_SYS_CONFIGURATION")
//@Table(name="QM_SYS_CONFIGURATION_HARD")
public class Sys_Configuration 
{

	@Id
	private String configid;
	private String	applicationname;
	private String	paramid;
	private String	paramname;
	private String	paramvalue;
	private String	createdby;
	private String	created_datetime;
	private String	updateby;
	private String	update_datetime;
	private String	status;
	private String	maker_id;
	private String	maker_date;
	private String	maker_remarks;
	private String	maker_sysdate;
	private String	auth_id;
	private String	auth_date;
	private String	auth_remark;
	private String	auth_sysdate;
	public String getConfigid() {
		return configid;
	}
	public void setConfigid(String configid) {
		this.configid = configid;
	}
	public String getApplicationname() {
		return applicationname;
	}
	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}
	public String getParamid() {
		return paramid;
	}
	public void setParamid(String paramid) {
		this.paramid = paramid;
	}
	public String getParamname() {
		return paramname;
	}
	public void setParamname(String paramname) {
		this.paramname = paramname;
	}
	public String getParamvalue() {
		return paramvalue;
	}
	public void setParamvalue(String paramvalue) {
		this.paramvalue = paramvalue;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreated_datetime() {
		return created_datetime;
	}
	public void setCreated_datetime(String created_datetime) {
		this.created_datetime = created_datetime;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public String getUpdate_datetime() {
		return update_datetime;
	}
	public void setUpdate_datetime(String update_datetime) {
		this.update_datetime = update_datetime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMaker_id() {
		return maker_id;
	}
	public void setMaker_id(String maker_id) {
		this.maker_id = maker_id;
	}
	public String getMaker_date() {
		return maker_date;
	}
	public void setMaker_date(String maker_date) {
		this.maker_date = maker_date;
	}
	public String getMaker_remarks() {
		return maker_remarks;
	}
	public void setMaker_remarks(String maker_remarks) {
		this.maker_remarks = maker_remarks;
	}
	public String getMaker_sysdate() {
		return maker_sysdate;
	}
	public void setMaker_sysdate(String maker_sysdate) {
		this.maker_sysdate = maker_sysdate;
	}
	public String getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(String auth_id) {
		this.auth_id = auth_id;
	}
	public String getAuth_date() {
		return auth_date;
	}
	public void setAuth_date(String auth_date) {
		this.auth_date = auth_date;
	}
	public String getAuth_remark() {
		return auth_remark;
	}
	public void setAuth_remark(String auth_remark) {
		this.auth_remark = auth_remark;
	}
	public String getAuth_sysdate() {
		return auth_sysdate;
	}
	public void setAuth_sysdate(String auth_sysdate) {
		this.auth_sysdate = auth_sysdate;
	}
	
	
}
