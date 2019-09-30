package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class PersonRelation implements Serializable{
	
	
	private String name;
    private String relation;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	@Override
	public String toString() {
		return "PersonRelation [name=" + name + ", relation=" + relation + "]";
	}
    
    

}
