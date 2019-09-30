package com.qualtech.icici.api.request;

import java.io.Serializable;

public class Rider  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String sa;
	private String term;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSa() {
		return sa;
	}
	public void setSa(String sa) {
		this.sa = sa;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	@Override
	public String toString() {
		return "Rider [name=" + name + ", sa=" + sa + ", term=" + term + "]";
	}

}
