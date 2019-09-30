package com.qualtech.multibureau.api.response;

import java.io.Serializable;

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
@Table(name = "IB_BUREAU_USER_MSG_RES")
public class UserMessageRes implements Serializable {

	private static final long serialVersionUID = 925018053430393766L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_USER_MSG_SQC", allocationSize = 1)
	@JsonIgnore
	private int userMsgId;


	private String usermessagetext;
	
	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public int getUserMsgId() {
		return userMsgId;
	}

	public void setUserMsgId(int userMsgId) {
		this.userMsgId = userMsgId;
	}
	
		
	public String getUsermessagetext() {
		return usermessagetext;
	}

	public void setUsermessagetext(String usermessagetext) {
		this.usermessagetext = usermessagetext;
	}

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	@Override
	public String toString() {
		return "UserMessageRes [userMsgId=" + userMsgId + ", usermessagetext=" + usermessagetext + "]";
	}

}
