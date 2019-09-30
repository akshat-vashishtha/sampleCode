package com.qualtech.cibilv2.api.request;

import java.io.Serializable;

public class MemberRelationship implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2140069256399783946L;
	private String relationshipType;
	private String relationshipName;
	public String getRelationshipType() {
		return relationshipType;
	}
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	public String getRelationshipName() {
		return relationshipName;
	}
	public void setRelationshipName(String relationshipName) {
		this.relationshipName = relationshipName;
	}
	@Override
	public String toString() {
		return "MemberRelationship [relationshipType=" + relationshipType + ", relationshipName=" + relationshipName
				+ "]";
	}
	
	

}
