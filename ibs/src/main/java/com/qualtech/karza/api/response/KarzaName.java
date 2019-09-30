package com.qualtech.karza.api.response;

import java.io.Serializable;

public class KarzaName implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4038085052942814147L;
	private String score;
	private String match;
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getMatch() {
		return match;
	}
	@Override
	public String toString() {
		return "KarzaName [score=" + score + ", match=" + match + "]";
	}
	public void setMatch(String match) {
		this.match = match;
	}
	
	
	
}
