package com.qualtech.cibil.api.entity;

import java.io.Serializable;

public class ScoringFactors implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String factor;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}
	@Override
	public String toString() {
		return "ScoringFactors [code=" + code + ", name=" + name + ", factor="
				+ factor + "]";
	}
}
