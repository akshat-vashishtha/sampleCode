package com.qualtech.finbit.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DayBalanceMap implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2258046555121660245L;
	@JsonProperty("1")
	private String one;
	@JsonProperty("5")
	private String five;
	@JsonProperty("10")
	private String ten;
	@JsonProperty("15")
	private String fifteen;
	@JsonProperty("20")
	private String twenty;
	@JsonProperty("25")
	private String twentyFive;
	@JsonProperty("30")
	private String thirty;
	
	
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public String getFive() {
		return five;
	}
	public void setFive(String five) {
		this.five = five;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getFifteen() {
		return fifteen;
	}
	public void setFifteen(String fifteen) {
		this.fifteen = fifteen;
	}
	public String getTwenty() {
		return twenty;
	}
	public void setTwenty(String twenty) {
		this.twenty = twenty;
	}
	public String getTwentyFive() {
		return twentyFive;
	}
	public void setTwentyFive(String twentyFive) {
		this.twentyFive = twentyFive;
	}
	public String getThirty() {
		return thirty;
	}
	public void setThirty(String thirty) {
		this.thirty = thirty;
	}
	@Override
	public String toString() {
		return "DayBalanceMap [one=" + one + ", five=" + five + ", ten=" + ten + ", fifteen=" + fifteen + ", twenty="
				+ twenty + ", twentyFive=" + twentyFive + ", thirty=" + thirty + "]";
	}
	
}
